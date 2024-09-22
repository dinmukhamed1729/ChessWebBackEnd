package ChessWeb;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.entity.User;
import ChessWeb.game.Board;
import ChessWeb.repository.UserRepository;
import ChessWeb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

class ApplicationTests {
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Board board;

	@Test
	void contextLoads() {
	}



	@Test
	@Transactional
	void registration(){
		var name = "newUser21";
        var email = "Email22";
        var pass = "pass";
        userService.registerNewUser(new RegistrationDto(name,email,pass));
		assert (userRepository.existsByName(name));
	}

}
