package com.rom.rrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().
                antMatchers("/").
                permitAll();

        http.csrf().disable();
        http.httpBasic().disable();
        http.headers().frameOptions().disable();
        //@formatter:on
    }
}
