//package com.registerapi.register.security;
//
//import com.registerapi.register.security.ResponseLoggingFilter;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.naming.factory.BeanFactory;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    private final AdminAuthenticationProvider adminAuthenticationProvider;
//    private final ResponseLoggingFilter responseLoggingFilter;
//
//    public SecurityConfiguration(AdminAuthenticationProvider adminAuthenticationProvider,
//                                 ResponseLoggingFilter responseLoggingFilter) {
//        this.adminAuthenticationProvider = adminAuthenticationProvider;
//        this.responseLoggingFilter = responseLoggingFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/auth/**").permitAll() // Allow access to authentication endpoints
//                                .requestMatchers("/api/v1/students/**").authenticated() // Secure endpoints
//                )
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginProcessingUrl("/auth/login") // Endpoint for processing login
//                                .successHandler((request, response, authentication) -> {
//                                    response.setStatus(HttpServletResponse.SC_OK);
//                                    response.getWriter().write("Authenticated");
//                                })
//                                .failureHandler((request, response, exception) -> {
//                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                                    response.getWriter().write("Authentication failed");
//                                })
//                )
//                .logout(logout ->
//                        logout
//                                .logoutUrl("/auth/logout")
//                                .logoutSuccessHandler((request, response, authentication) -> {
//                                    response.setStatus(HttpServletResponse.SC_OK);
//                                    response.getWriter().write("Logged out");
//                                })
//                )
//                .csrf(csrf -> csrf.disable()) // for API simplicity
//                .addFilterBefore(responseLoggingFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public FilterRegistrationBean<ResponseLoggingFilter> loggingFilter() {
//        FilterRegistrationBean<ResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(responseLoggingFilter);
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
//
//
//}


//two
package com.registerapi.register.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final ResponseLoggingFilter responseLoggingFilter;

    public SecurityConfiguration(UserDetailsService userDetailsService,
                                 ResponseLoggingFilter responseLoggingFilter) {
        this.userDetailsService = userDetailsService;
        this.responseLoggingFilter = responseLoggingFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll() // Allow access to authentication endpoints
                        .requestMatchers("/api/v1/admin/**").authenticated() // Secure endpoints
                )
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/auth/login") // Endpoint for processing login
                        .successHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Authenticated");
                        })
                        .failureHandler((request, response, exception) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Authentication failed");
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Logged out");
                        })
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in API
                .addFilterBefore(responseLoggingFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
