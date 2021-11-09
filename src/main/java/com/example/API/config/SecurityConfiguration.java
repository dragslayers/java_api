package com.example.API.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/security/register").permitAll()
                .antMatchers(HttpMethod.POST,"/api/security/login").permitAll()
                .antMatchers(HttpMethod.POST,"/api/security/candidats").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated();
//                .and()
//                .addFilter(new JWTAuthenficationFilter(authenticationManager()))
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .sessionManagement().sessionCreationPolicy.STATELESS);

    }
}
