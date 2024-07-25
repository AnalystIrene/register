
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
//        http
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/auth/**").permitAll() // Allow access to authentication endpoints
//                        .requestMatchers("/api/v1/admin/**").authenticated() // Secure endpoints
//                )

            http
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                   .addFilterBefore(responseLoggingFilter, UsernamePasswordAuthenticationFilter.class)


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
//package com.registerapi.register.security;
//
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    private final UserDetailsService userDetailsService;
//    private final ResponseLoggingFilter responseLoggingFilter;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    public SecurityConfiguration(UserDetailsService userDetailsService,
//                                 ResponseLoggingFilter responseLoggingFilter,
//                                 JwtTokenProvider jwtTokenProvider) {
//        this.userDetailsService = userDetailsService;
//        this.responseLoggingFilter = responseLoggingFilter;
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/auth/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .addFilterBefore(responseLoggingFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider, userDetailsService), UsernamePasswordAuthenticationFilter.class)
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginProcessingUrl("/api/auth/login")
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
//                                .logoutUrl("/api/auth/logout")
//                                .logoutSuccessHandler((request, response, authentication) -> {
//                                    response.setStatus(HttpServletResponse.SC_OK);
//                                    response.getWriter().write("Logged out");
//                                })
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
