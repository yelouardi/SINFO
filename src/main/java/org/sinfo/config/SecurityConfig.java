package org.sinfo.config;

import org.sinfo.security.auth.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * @author yelouardi SecurityConfig
 */
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customerAuthService")
    CustomerAuthService customerAuthService;

	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  http
	    .csrf().disable()
	    .authorizeRequests()
	        .antMatchers("/login","/register","/logout").permitAll()
	        .antMatchers("/topics","/topics/**","/admin","/admin/**").hasRole("ROL_admin")
	        .anyRequest().authenticated();
	        
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customerAuthService);
  }

  
  @Bean
  public HttpSessionStrategy httpSessionStrategy() {
      return new HeaderHttpSessionStrategy();
  }

}
