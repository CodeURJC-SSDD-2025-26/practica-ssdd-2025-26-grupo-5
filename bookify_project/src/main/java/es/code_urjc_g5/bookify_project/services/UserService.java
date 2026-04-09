package es.code_urjc_g5.bookify_project.services;

import es.code_urjc_g5.bookify_project.models.User;
import es.code_urjc_g5.bookify_project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(">>> INTENTANDO LOGIN CON: " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println(">>> USUARIO NO ENCONTRADO");
                    return new UsernameNotFoundException("No encontrado: " + email);
                });
        System.out.println(">>> USUARIO ENCONTRADO: " + user.getUserName());
        System.out.println(">>> PASSWORD EN BD: " + user.getUserPassword());
        System.out.println(">>> ROL: " + user.getUserRole());

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserEmail())
                .password(user.getUserPassword())
                .authorities("ROLE_" + user.getUserRole())
                .build();
    }

    public boolean registerUser(User user) {
        if (userRepository.findByEmail(user.getUserEmail()).isPresent()) {
            return false; // email ya existe
        }
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        user.setUserRole("USER");
        userRepository.save(user);
        return true;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}