package ChessWeb.service.impl;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.dto.UserDto;
import ChessWeb.entity.User;
import ChessWeb.repository.UserRepository;
import ChessWeb.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) implements UserService {

    public void registerNewUser(RegistrationDto registrationDto) {
        if (userRepository.existsByName(registrationDto.name()))
            throw new DataIntegrityViolationException("User with username " + registrationDto.name() + " already exists.");

        if (userRepository.existsByEmail(registrationDto.email()))
            throw new DataIntegrityViolationException("User with email " + registrationDto.email() + " already exists.");
        userRepository.save(new User(null, registrationDto.name(), registrationDto.email(), passwordEncoder.encode(registrationDto.password()), null, null));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addFriend(String userName, String friendName) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(userName)));
        user.addFriend(userRepository.findByName(friendName)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(friendName))));

        userRepository.save(user);
    }

    @Override
    public List<UserDto> findAllFriends(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(name)))
                .friends().stream().map(it -> new UserDto(it.id(), it.name())).toList();
    }
}