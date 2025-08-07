package com.kj.textile.TextileERP.config;

import com.kj.textile.TextileERP.security.JwtAuthenticationEntryPoint;
import com.kj.textile.TextileERP.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Slf4j
public class WebSecurityConfig {   
    private static final String[] WHITE_LIST_URLS = {
        "/hello",
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

    @Value("${app.cors.allowed-origin}")
    private String allowedOrigin;


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
                        auth.requestMatchers(WHITE_LIST_URLS).permitAll()
                                .requestMatchers("/api/v1/**").authenticated()
                                .requestMatchers("/api/v2/auth/**").hasAnyRole("ADMIN","SUPERADMIN")
                                .requestMatchers("/api/v2/superauth/**").hasRole("SUPERADMIN")
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
        log.info("allowedOrigin:=" + allowedOrigin);
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin(allowedOrigin);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
