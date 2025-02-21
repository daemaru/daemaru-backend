package com.demaru.global.security;

import com.demaru.global.security.jwt.JwtFilter;
import com.demaru.global.security.jwt.JwtParser;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtParser jwtParser;

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
                        .antMatchers(HttpMethod.GET, "/schedules/get").permitAll()
                        .antMatchers(HttpMethod.GET, "/archives/get").permitAll()

                        .antMatchers(HttpMethod.POST, "/login").permitAll()
                        .antMatchers(HttpMethod.POST, "/schedules/post").permitAll()
                        .antMatchers(HttpMethod.PATCH, "/schedules/patch").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/schedules/delete").authenticated()

                        .anyRequest().denyAll()
                )

                .addFilterBefore(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
