package com.yinn.ymall.cart.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Order(1)
@EnableWebSecurity
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
            )
            .csrf().disable()
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}