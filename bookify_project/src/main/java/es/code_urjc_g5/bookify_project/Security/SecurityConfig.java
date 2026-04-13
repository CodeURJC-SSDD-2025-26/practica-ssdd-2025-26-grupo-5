package es.code_urjc_g5.bookify_project.Security;

import es.code_urjc_g5.bookify_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private UserService userService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Bean
        public DaoAuthenticationProvider authProvider() {
                DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
                provider.setUserDetailsService(userService);
                provider.setPasswordEncoder(passwordEncoder);
                return provider;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authenticationProvider(authProvider())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/css/**", "/js/**", "/imgs/**").permitAll()
                                                .requestMatchers("/", "/index", "/book/**").permitAll()
                                                .requestMatchers("/contact", "/pp", "/ToS", "/aboutUs").permitAll()
                                                .requestMatchers("/login", "/signUp").permitAll()
                                                .requestMatchers("/profile/me", "/myLibrary", "/collection/**")
                                                .authenticated()
                                                .requestMatchers("/admin/**").hasRole("ADMIN") // solo admins pueden
                                                                                               // acceder a /admin
                                                .anyRequest().authenticated() // el resto también requiere login
                                )
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .defaultSuccessUrl("/profile/me", true)
                                                .failureUrl("/login?error")
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login")
                                                .permitAll())
                                .csrf(csrf -> csrf.disable());

                return http.build();
        }
}