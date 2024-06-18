package ChessWeb.service;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.entity.User;
import ChessWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   
    public void registerNewUser( RegistrationDto user) {
        log.info("start registerNewUser ");
        if(userRepository.existsByName(user.name()))
            throw new DataIntegrityViolationException("User with username " + user.name() + " already exists.");
        
        if (userRepository.existsByEmail(user.email()))
            throw new DataIntegrityViolationException("User with email " + user.email() + " already exists.");

        
        userRepository.save(User.builder()
            .email(user.email())
            .name(user.name())
            .password(passwordEncoder.encode(user.password()))
            .build());
    }
}