package ru.fidarov.SecurityApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.fidarov.SecurityApplication.services.PersonDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public WebSecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .userDetailsService(personDetailsService)
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/content/admin").hasRole("ADMIN")
                        .requestMatchers("/content/hello","/auth/login","/auth/registration","/error").permitAll()
                        .anyRequest().hasAnyRole("USER","ROLE"))
                .logout(logout->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login"))
                .formLogin(formLogin->formLogin
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/content/hello",true)
                        .failureUrl("/auth/login?error"))
                .build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
