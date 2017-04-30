package com.thoughtworks.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

@Configuration
public class PropertyConfig {
    private static final String DEFAULT_DATASOURCE_PROPERTY_FILE = "dataSource.properties";
    private static final String DATASOURCE_PROPERTY_KEY = "dataSource.property.file";
    private static final String DEFAULT_JPA_CONFIG_FILE = "jpa.properties";
    private static final String JPA_PROPERTY_KEY = "jpa.property.file";

    @Bean
    public Resource dataSourcePropertiesResource(Environment environment) {
        Resource dataSourceResource;
        if (environment.containsProperty(DATASOURCE_PROPERTY_KEY)) {
            dataSourceResource = new FileSystemResource(Paths.get(environment.getProperty(DATASOURCE_PROPERTY_KEY)).toFile());
        } else {
            dataSourceResource = new ClassPathResource(DEFAULT_DATASOURCE_PROPERTY_FILE);
        }
        return dataSourceResource;
    }

    @Bean
    public Resource jpaPropertiesResource(Environment environment) {
        Resource jpaPropertiesResource;
        if (environment.containsProperty(JPA_PROPERTY_KEY)) {
            jpaPropertiesResource = new FileSystemResource(Paths.get(environment.getProperty(JPA_PROPERTY_KEY)).toFile());
        } else {
            jpaPropertiesResource = new ClassPathResource(DEFAULT_JPA_CONFIG_FILE);
        }
        return jpaPropertiesResource;
    }

    @Bean
    public Properties dataSourceProperties(Resource dataSourcePropertiesResource) throws IOException {
        return PropertiesLoaderUtils.loadProperties(dataSourcePropertiesResource);
    }

    @Bean
    public Properties jpaProperties(Resource jpaPropertiesResource) throws IOException {
        return PropertiesLoaderUtils.loadProperties(jpaPropertiesResource);
    }
}

