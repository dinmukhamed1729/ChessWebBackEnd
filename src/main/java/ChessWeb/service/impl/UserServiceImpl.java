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
        userRepository.save(new User(null, registrationDto.name(), registrationDto.email(), passwordEncoder.encode(registrationDto.password()),null, null, null));
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

    @Override
    public void newGame(String from, String sendTo, Long gameId) {
        var player2 = userRepository.findByName(from)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(from)))
                .gameId(gameId);
        var player1 = userRepository.findByName(sendTo)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(sendTo)));

        player1.gameId(gameId);
        player2.gameId(gameId);

        userRepository.save(player2);
        userRepository.save(player1);
    }

    @Override
    public Long getGameIdByUserName(String username) {
        return userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username))).gameId();
    }

    @Override
    public List<User> findByGameId(Long gameId) {
        return userRepository.findAllByGameId(gameId);
    }
}