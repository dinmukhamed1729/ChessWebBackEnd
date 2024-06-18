package ChessWeb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
         .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((auth) -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/registration").permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("css/**", "js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            
            .build();
    }
    

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
}