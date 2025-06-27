package com.kj.textile.TextileERP.config;

import com.kj.textile.TextileERP.security.JwtAuthenticationEntryPoint;
import com.kj.textile.TextileERP.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {   
    private static final String[] WHITE_LIST_URLS = {
        "/hello1",
        "/registration",
        "/verificationToken*",
        "/resendtoken*",
        "/resetpassword*",
        "/loginuser",
        "/savepassword*", "/auth/loginuser",
            "/excel*",
};
    @Autowired
    private JwtAuthenticationEntryPoint point;



    @Bean
    public JwtAuthenticationFilter JwtAuthenticationFilter(){
        return new  JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(11);
    }
    @Bean
     SecurityFilterChain  securityFilterChain(HttpSecurity http)throws Exception{


        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())).authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/**").authenticated()
                                .requestMatchers(WHITE_LIST_URLS).permitAll()
                                 .requestMatchers("/api/v2/auth/**").hasRole("ADMIN")
                                .requestMatchers("/api/v2/user/**").hasRole("USER")
                                .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
