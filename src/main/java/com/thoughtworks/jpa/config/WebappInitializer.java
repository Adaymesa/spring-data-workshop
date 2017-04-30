package com.thoughtworks.jpa.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebappInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RepositoryConfig.class, JsonMapperConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfig.class, ControllerConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/*"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                getCharacterEncodingFilter()
        };
    }

    private CharacterEncodingFilter getCharacterEncodingFilter() {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.setInitParameter("defaultHtmlEscape", Boolean.TRUE.toString());
    }
}
