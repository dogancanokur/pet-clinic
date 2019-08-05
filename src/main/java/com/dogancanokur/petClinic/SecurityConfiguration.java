package com.dogancanokur.petClinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(
                "/**/favicon.ico", "/css/**", "/js/**", "/images/**", "/webjars/**", "/login.html"
        ).permitAll();

        http.authorizeRequests().anyRequest().authenticated();

        // login html
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureUrl("/login.html?loginFailed=true");

        // beni hatırla
        http.rememberMe().userDetailsService(userDetailsService);

        http.httpBasic();
    }
}
