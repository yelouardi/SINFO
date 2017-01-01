package org.sinfo.config;

import org.sinfo.security.auth.CustomerAuthService;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yelouardi SecurityConfig
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	
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
    auth.userDetailsService(new CustomerAuthService(userService));
  }


}
