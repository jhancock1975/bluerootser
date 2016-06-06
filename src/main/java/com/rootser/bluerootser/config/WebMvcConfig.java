package com.rootser.bluerootser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rootser.bluerootser.aspect.LoggingAspect;
import com.rootser.bluerootser.constants.Constants;
import com.rootser.bluerootser.controller.AppErrorController;
 
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableCaching
public class WebMvcConfig extends WebMvcConfigurerAdapter{
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
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
    
    @Autowired
    private ErrorAttributes errorAttributes;

    @Bean
    public AppErrorController appErrorController(){return new AppErrorController(errorAttributes);}
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/myArticles").setViewName("myArticles");
        registry.addViewController("/loginView").setViewName("loginView");
        registry.addViewController("/help").setViewName("helpView");
        registry.addViewController("/" + Constants.signup).setViewName(Constants.signup);
        registry.addViewController(Constants.memorizationTechniques).setViewName("memorizationTechniques");
        registry.addViewController(Constants.ERROR).setViewName("/");
    }

    
}
