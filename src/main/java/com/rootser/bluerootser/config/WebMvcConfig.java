package com.rootser.bluerootser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rootser.bluerootser.aspect.LoggingAspect;
import com.rootser.bluerootser.repository.CustomerRepository;
 
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
public class WebMvcConfig extends WebMvcConfigurerAdapter{
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    
    @Bean
    public LoggingAspect loggingAspect(){
    	return new LoggingAspect();
    }
    
}