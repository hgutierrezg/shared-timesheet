package com.hgutierrezg.training.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import filters.CORSFilter;

public class SharedTimesheetInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	  
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SharedTimesheetConfiguration.class };
    }
   
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
   
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
     
    @Override
    protected Filter[] getServletFilters() {
        return new CORSFilter[]{new CORSFilter()};
    }
  
}
