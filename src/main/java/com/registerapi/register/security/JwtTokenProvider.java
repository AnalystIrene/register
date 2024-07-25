////package com.registerapi.register.security;
////
////import io.jsonwebtoken.Claims;
////import io.jsonwebtoken.Jwts;
////import io.jsonwebtoken.SignatureAlgorithm;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.stereotype.Component;
////
////import java.util.Date;
////
////@Component
////public class JwtTokenProvider {
////
////    @Value("${jwt.secret}")
////    private String secretKey;
////
////    @Value("${jwt.expiration}")
////    private long validityInMilliseconds; // 1 hour
////
////    public String createToken(Authentication authentication) {
////        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////        Date now = new Date();
////        Date validity = new Date(now.getTime() + validityInMilliseconds);
////
////        return Jwts.builder()
////                .setSubject(userDetails.getUsername())
////                .setIssuedAt(now)
////                .setExpiration(validity)
////                .signWith(SignatureAlgorithm.HS256, secretKey)
////                .compact();
////    }
////
////    public String getUsername(String token) {
////        return Jwts.parser()
////                .setSigningKey(secretKey)
////                .parseClaimsJws(token)
////                .getBody()
////                .getSubject();
////    }
////
////    public boolean validateToken(String token) {
////        try {
////            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
////            return true;
////        } catch (Exception e) {
////            // Handle exceptions like ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, etc.
////            return false;
////        }
////    }
////
////    public String resolveToken(String token) {
////        if (token != null && token.startsWith("Bearer ")) {
////            return token.substring(7);
////        }
////        return null;
////    }
////}
////
//package com.registerapi.register.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Component
//@Service
//public class JwtTokenProvider {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.expiration}")
//    private long validityInMilliseconds;
//
//    public String createToken(String username) {
//        Claims claims = Jwts.claims().setSubject(username);
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public String getUsername(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String resolveToken(HttpServletRequest request) {
//        String header = request.getHeader("Authorization");
//        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7); // Extract the token
//        }
//        return null;
//    }
//}
