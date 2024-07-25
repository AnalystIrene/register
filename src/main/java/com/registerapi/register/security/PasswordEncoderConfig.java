////package com.registerapi.register.security;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////
////@Configuration
////public class PasswordEncoderConfig {
//////    @Bean
//////    public PasswordEncoder passwordEncoder() {
//////        return new BCryptPasswordEncoder();
//////    }
////
////}
////
package com.registerapi.register.security;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    public CommandLineRunner run() {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = "adminpassword";
            String encodedPassword = encoder.encode(rawPassword);
            System.out.println("Encoded password: " + encodedPassword);
        };
    }
}

