package ChessWeb.service;

import ChessWeb.dto.RegistrationDto;
import org.springframework.stereotype.Service;


public  interface UserService {
    void registerNewUser(RegistrationDto registrationDto);
}
