package com.books.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Created by Harry on 14.06.2018.
 */

@Configuration
@ComponentScan("com.books.*")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http.authorizeRequests().antMatchers(
                "/login",
                "/logout"
        ).permitAll();


        http.authorizeRequests().antMatchers("/edit-book/**","/edit-book-form/**",
                "/create-book","/create-book-form","/orders-list","/order/**","/delete-book/**")
                .access("hasRole('ADMIN')");



        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/Errors/Error403");
        http.authorizeRequests().and().formLogin()//
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }



}
