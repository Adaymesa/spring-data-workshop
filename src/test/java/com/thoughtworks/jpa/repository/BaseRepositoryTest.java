package com.thoughtworks.jpa.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.thoughtworks.jpa.config.PersistenceConfig;
import com.thoughtworks.jpa.config.RepositoryConfig;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Properties;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = {
        DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@Rollback
public abstract class BaseRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    protected void detachedEntity(Object entity) {
        this.entityManager.detach(entity);
    }

    @Configuration
    @Import({PersistenceConfig.class, RepositoryConfig.class})
    public static class ContextConfiguration {
        @Bean
        public Properties dataSourceProperties(@Value("classpath:test-data-source.properties")
                                                       Resource dataSourcePropertiesResource) throws IOException {
            return PropertiesLoaderUtils.loadProperties(dataSourcePropertiesResource);
        }

        @Bean
        public Properties jpaProperties(@Value("classpath:test-jpa.properties")
                                                Resource jpaPropertiesResource) throws IOException {
            return PropertiesLoaderUtils.loadProperties(jpaPropertiesResource);
        }
    }
}
