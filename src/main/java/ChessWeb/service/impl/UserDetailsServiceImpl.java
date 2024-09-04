package ChessWeb.service.impl;

import ChessWeb.repository.UserRepository;
import ChessWeb.security.UserDetailsImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record UserDetailsServiceImpl(UserRepository userRepository) implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        log.info("Start loadUserByUsername %s".formatted(username));
        return new UserDetailsImp(userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username))));
    }
}
