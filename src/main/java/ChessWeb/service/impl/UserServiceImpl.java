package ChessWeb.service.impl;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.entity.User;
import ChessWeb.repository.UserRepository;
import ChessWeb.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) implements UserService {

    public void registerNewUser(RegistrationDto registrationDto) {
        if (userRepository.existsByName(registrationDto.name()))
            throw new DataIntegrityViolationException("User with username " + registrationDto.name() + " already exists.");

        if (userRepository.existsByEmail(registrationDto.email()))
            throw new DataIntegrityViolationException("User with email " + registrationDto.email() + " already exists.");
        System.out.println("=============================================================");
        userRepository.save(new User(null, registrationDto.name(), registrationDto.email(), passwordEncoder.encode(registrationDto.password()) ,null));
    }

}