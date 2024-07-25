////package com.registerapi.register.service;
////
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.stereotype.Service;
////
////import java.util.Collections;
////
////@Service
////public class AdminUserDetailsService implements UserDetailsService {
////
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        if ("admin".equals(username)) {
////            return new User("admin", "adminpassword", Collections.singletonList(() -> "ROLE_ADMIN"));
////        } else {
////            throw new UsernameNotFoundException("User not found");
////        }
////    }
////}
//package com.registerapi.register.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class AdminUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if ("admin".equals(username)) {
//            return User.builder()
//                    .username("admin")
//                    .password("{bcrypt}$2a$10$9thGydOoy.hRRMH5JmN2/O2fE1c3Vf1GJ4K0GR4fg6Tz6CQmb7iy.") // Example BCrypt hash for "adminpassword"
//                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
//                    .build();
//        } else {
//            throw new UsernameNotFoundException("User not found");
//        }
//    }
//}
//
package com.registerapi.register.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("$2a$10$wR4swBUue6EXknrOeC4iuuw75rlHqb8FfElhVHQrO16qdAJDE2lZe") // Use the pre-defined encoded password
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")))
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
