package com.spittr.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shihyuho.jackson.databind.DynamicFilterMixIn;
import com.github.shihyuho.jackson.databind.DynamicFilterProvider;
import com.github.shihyuho.jackson.databind.DynamicFilterResponseBodyAdvice;
import com.spittr.authorization.interceptor.AuthorizationInterceptor;
import com.spittr.authorization.resolvers.CurrentUserArgumentResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages={
		"com.spittr.controller",
		"com.spittr.user.controller",
		"com.spittr.message.controller",
		"com.spittr.image.controller",
		"com.spittr.location.controller",
		"com.spittr.websocket.controller",
		"com.spittr.authorization.controller"
		})
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Autowired
	private CurrentUserArgumentResolver currentUserArgumentResolver;
	
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
	 @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(authorizationInterceptor);
	    }
	 
	 @Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub
		argumentResolvers.add(currentUserArgumentResolver);
	}
	 
	 @Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Object.class, DynamicFilterMixIn.class);
		mapper.setFilterProvider(new DynamicFilterProvider());
		converters.add(new MappingJackson2HttpMessageConverter(mapper));
	}
	 
	@Bean
	public DynamicFilterResponseBodyAdvice dynamicFilterResponseBodyAdvice(){
		return new DynamicFilterResponseBodyAdvice();
	}
	 
}
