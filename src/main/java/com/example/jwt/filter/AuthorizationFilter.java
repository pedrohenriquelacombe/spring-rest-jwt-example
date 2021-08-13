package com.example.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/auth0/token")) {
            filterChain.doFilter(request, response);
        } else {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (!ObjectUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);

                    String email = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

                    Collection<SimpleGrantedAuthority> authorities = new ArrayList();
                    Arrays.stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(email, null, authorities));

                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    HttpStatus httpStatus = HttpStatus.FORBIDDEN;

                    Map<String, Object> errorInfo = new HashMap();
                    errorInfo.put("message", e.getMessage());
                    errorInfo.put("status", httpStatus.value());
                    errorInfo.put("path", request.getServletPath());
                    errorInfo.put("error", httpStatus.getReasonPhrase());

                    response.setStatus(httpStatus.value());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(), errorInfo);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}
