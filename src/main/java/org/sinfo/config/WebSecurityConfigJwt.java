package org.sinfo.config;

import org.sinfo.security.auth.AuthService;
import org.sinfo.security.jwt.JWTAuthenticationFilter;
import org.sinfo.security.jwt.JWTLoginFilter;
import org.sinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigJwt extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;
    @Bean
    AuthService authService() {
        return new AuthService(userService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        //http.headers().cacheControl();

        http.csrf().disable() // disable csrf for our requests.
                .authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated().and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in
                // header
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Create a default account
        auth.userDetailsService(authService());
    }
}