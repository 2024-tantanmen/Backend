package com.tantanmen.carbofootprint.global.security.config;

import com.tantanmen.carbofootprint.global.security.jwt.JwtTokenProvider;
import com.tantanmen.carbofootprint.global.security.jwt.filter.JwtTokenFilter;
import com.tantanmen.carbofootprint.global.security.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // swagger 관련
                        .requestMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html","/webjars/**","/swagger-ui/**","/v3/api-docs/**","/v2/swagger.json").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/", "/login","/signup", "/api/chatbot", "/api/classification", "/api/foods/recommend/**", "/api/chat/rooms").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(customUserDetailService);


        return http.build();
    }
}
