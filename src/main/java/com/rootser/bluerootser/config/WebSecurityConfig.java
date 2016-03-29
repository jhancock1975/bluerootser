package com.rootser.bluerootser.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.rootser.bluerootser.constants.Constants;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers(Constants.root.toString(), 
                		"/" + Constants.home.toString(),
                		"/" + Constants.resources.toString(), 
                		"/" +  Constants.articles.toString(),
                		"/" + Constants.help.toString(), 
                		"/" + Constants.memorizationTechniques.toString())
             .permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .formLogin()
                .loginPage("/loginView")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }
    
    //configAuthentication taken from
    //http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
    
    @Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user_roles where username=?");
	}	
}