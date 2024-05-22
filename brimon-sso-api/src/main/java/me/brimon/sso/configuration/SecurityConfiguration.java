package me.brimon.sso.configuration;

import me.brimon.sso.jwt.JWTUtil;
import me.brimon.sso.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.requestMatchers(HttpMethod.POST, "/login").permitAll())
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        String idForEncode = "bcrypt";
        Map encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    @Bean
    AuthenticationManager authenticationManager(UserService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(List.of(authenticationProvider));
    }
}
