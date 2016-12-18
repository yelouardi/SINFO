package org.sinfo.config;

import org.sinfo.security.auth.AuthService;
import org.sinfo.security.auth.filter.CustomUsernamePasswordAuthenticationFilter;
import org.sinfo.security.auth.handler.AuthenticationFailureHandler;
import org.sinfo.security.auth.handler.AuthenticationSuccessHandler;
import org.sinfo.security.auth.handler.RestAuthenticationEntryPoint;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author yelouardi
 * SecurityConfig
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String AUTHENTICATE_ENDPOINT = "/authenticate";

	@Autowired
	UserService userService;	
    // Beans connected with translating input and output to JSON
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    // Bean responsible for getting information about user details
    @Bean
    AuthService authService() {
        return new AuthService(userService);
    }
	
    @Bean
    public CustomUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(AUTHENTICATE_ENDPOINT, "POST"));
        authFilter.setAuthenticationManager(super.authenticationManager());
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authFilter.setUsernameParameter("j_username");
        authFilter.setPasswordParameter("j_password");
        return authFilter;
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
		.and().addFilterBefore(authenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class)
		.csrf().disable().authorizeRequests().antMatchers("/V2/**").permitAll().antMatchers("/topics/**").authenticated().and().formLogin()
		.loginProcessingUrl(AUTHENTICATE_ENDPOINT).failureHandler(authenticationFailureHandler())
		.successHandler(authenticationSuccessHandler()).and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService());
    }
}			
