package org.suspensive.lovepdfnonreactive.infraestructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.suspensive.lovepdfnonreactive.domain.models.Role;
import org.suspensive.lovepdfnonreactive.infraestructure.config.security.filter.JwtFilter;
import org.suspensive.lovepdfnonreactive.infraestructure.utils.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    public SecurityConfig(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    //Private Requests
                    http.requestMatchers(HttpMethod.GET, "user/pdfs/**").hasRole(Role.DEFAULT_ROLE.getRoleName());
                    http.requestMatchers(HttpMethod.POST, "user/pdfs/upload").hasRole(Role.DEFAULT_ROLE.getRoleName());
                    http.requestMatchers(HttpMethod.DELETE, "user/pdfs/**").hasRole(Role.DEFAULT_ROLE.getRoleName());

                    http.anyRequest().permitAll();
                })
                .addFilterAfter(new JwtFilter(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
