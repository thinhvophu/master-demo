package com.thinhvp.masterdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SpringWebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/", ".html");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/*.html").addResourceLocations("classpath:static/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:static/img/");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_JSON, MediaType.TEXT_HTML);
	}
}
