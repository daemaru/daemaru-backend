package com.demaru.global.security;

import com.demaru.global.error.GlobalExceptionFilter;
import com.demaru.global.security.jwt.JwtFilter;
import com.demaru.global.security.jwt.JwtParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtParser jwtParser;
    private final ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests((authz) -> authz
                        .antMatchers(HttpMethod.POST, "/admins/signup").permitAll()

                        .antMatchers(HttpMethod.POST, "/admins/login").permitAll()

                        .antMatchers(HttpMethod.GET, "/schedules").permitAll()
                        .antMatchers(HttpMethod.POST, "/schedules").authenticated()
                        .antMatchers(HttpMethod.PATCH, "/schedules/{schedule_id}").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/schedules/{schedule_id}").authenticated()

                        .antMatchers(HttpMethod.GET, "/archives").permitAll()

                        .anyRequest().denyAll()
                )

                .addFilterBefore(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new GlobalExceptionFilter(objectMapper), JwtFilter.class);

        return http.build();
    }

}
