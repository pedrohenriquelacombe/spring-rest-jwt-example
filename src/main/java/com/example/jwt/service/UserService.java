package com.example.jwt.service;

import com.example.jwt.model.RoleModel;
import com.example.jwt.model.UserModel;
import com.example.jwt.repository.RoleRepository;
import com.example.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = this.userRepository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found in the database.");
        });

        Collection<SimpleGrantedAuthority> authorities = new ArrayDeque<>();
        for (RoleModel role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    public UserModel save(UserModel user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public void addRoleToUser(String email, String roleName) {
        UserModel user = this.userRepository.findByEmail(email).orElseThrow();
        RoleModel role = this.roleRepository.findByName(roleName).orElseThrow();

        user.getRoles().add(role);
        this.userRepository.save(user);
    }

}
