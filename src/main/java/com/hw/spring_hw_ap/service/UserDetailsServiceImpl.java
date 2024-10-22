package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.entity.User;
import com.hw.spring_hw_ap.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("User not found: " + username); // Выводим сообщение о не найденном пользователе
                    return new UsernameNotFoundException("User not found: " + username);
                });

        System.out.println("User found: " + user.getUsername()); // Выводим сообщение о найденном пользователе

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // пароль уже должен быть зашифрован
                .roles("USER") // Пример роли пользователя
                .build();
    }
}