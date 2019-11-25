package com.odolirprojetosupero.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(
                        "select username,passwd, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from authorities where username=?");
    }


    //Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().realmName("user").and().authorizeRequests()
                .antMatchers("/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("perform_login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .csrf()
                .disable();

//        http
//                .authorizeRequests().antMatchers("/**").hasAnyRole("ADMIN", "USER")
//                .and()
//                .authorizeRequests().antMatchers("/login").permitAll();



    }

}