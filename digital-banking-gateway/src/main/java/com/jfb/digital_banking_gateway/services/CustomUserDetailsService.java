package com.jfb.digital_banking_gateway.services;

import com.jfb.digital_banking_gateway.core.domain.models.User;
import com.jfb.digital_banking_gateway.dataprovider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o nome de usuário: " + username));

        return user; // Aqui retornamos o próprio objeto User que já implementa UserDetails
    }
}
