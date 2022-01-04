package com.example.demo.config;


import com.example.demo.Security.JwtAuthenticationEntryPoint;
import com.example.demo.Security.JwtAuthenticationFilter;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService usersService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usersService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(authenticationProvider())
                .eraseCredentials(true)
                .userDetailsService(usersService)
                .passwordEncoder(passwordEncoder());

    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/oauth2/authorization/google")
                .defaultSuccessUrl("http://localhost:8000/a")
                .failureUrl("/oauth2/authorization/google")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/").permitAll()
        ;
    }



//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/**").authorizeRequests()
//                // .antMatcher("/mainPage").authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login();
//
////        http
////                .cors()
////                .and()
////                .csrf()
////                .disable()
////                .exceptionHandling()
////                .authenticationEntryPoint(unauthorizedHandler)
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests()
////                .antMatchers("/",
////                        "/favicon.ico",
////                        "/**/*.png",
////                        "/**/*.gif",
////                        "/**/*.svg",
////                        "/**/*.jpg",
////                        "/**/*.html",
////                        "/**/*.css",
////                        "/admin",
////                        "/**/*.js")
////                .permitAll()
////                .antMatchers("/")
////                .permitAll()
////                .antMatchers("/api/auth/signin")
////                .permitAll()
////                .antMatchers("/api/auth/signup")
////                .permitAll()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .logout();
//
//
////        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////        http.authorizeRequests()
////                .antMatchers("/", "/login", "/oauth/**").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().permitAll()
////                .and()
////                .oauth2Login()
////                .loginPage("/login")
////                .userInfoEndpoint()
////                .userService(usersService);
//    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
        web.ignoring().antMatchers("/api/auth/signin");
    }

}
