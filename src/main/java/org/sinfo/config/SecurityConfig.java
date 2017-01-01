package org.sinfo.config;

import org.sinfo.security.auth.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

/**
 * @author yelouardi SecurityConfig
 */
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomerAuthService userService;
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  http
	    .csrf().disable()
	    .authorizeRequests()
	        .antMatchers("/login","/register","/logout","/j_spring_security_check").permitAll()
	        .antMatchers("/topics","/topics/**","/admin","/admin/**").hasRole("ROL_admin")
	        .anyRequest().authenticated();
	        
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Bean
  public HeaderHttpSessionStrategy sessionStrategy() {
    return new HeaderHttpSessionStrategy();
  }

}
