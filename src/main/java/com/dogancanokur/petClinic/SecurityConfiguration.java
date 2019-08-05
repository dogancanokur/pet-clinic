package com.dogancanokur.petClinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends AbstractSecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                // herkes erişsin
                .antMatchers(
                        "/**/favicon.ico", "/css/**", "/js/**", "/images/**", "/webjars/**", "/login.html"
                )
                .permitAll();

        // editör rolü erişsin
//        http.authorizeRequests()
//                .antMatchers("/rest/**")
//                .access("hasRole('EDITOR')");

        http.authorizeRequests()
                // admin rolü erişsin
                .antMatchers("/actuator/**")
                .access("hasRole('ADMIN')");

        http.authorizeRequests().anyRequest().authenticated();

        // login html
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .failureUrl("/login.html?loginFailed=true");

        // beni hatırla
        http.rememberMe().userDetailsService(userDetailsService);

//        http.httpBasic();
    }

    // kullanıcı yetkilendirme
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
