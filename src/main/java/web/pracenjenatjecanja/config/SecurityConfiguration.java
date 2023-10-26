package web.pracenjenatjecanja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.pracenjenatjecanja.controllers.LogoutHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private LogoutHandler logoutHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("turnir/guest/{idTurnir}").permitAll();
                    auth.anyRequest().authenticated();
                })
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).addLogoutHandler(logoutHandler))
                .oauth2Login(Customizer.withDefaults())
                .build();
    }
}


