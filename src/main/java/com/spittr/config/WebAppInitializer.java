package com.spittr.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.spittr.filter.CharacterEncodingUTF8Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		Filter[] filters = new Filter[]{
				CharacterEncodingUTF8Filter.getInstance
		};
		return filters;
	}

}
