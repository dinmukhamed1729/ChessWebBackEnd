package ChessWeb.service;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.dto.UserDto;
import ChessWeb.entity.User;

import java.util.List;


public  interface UserService {
    void registerNewUser(RegistrationDto registrationDto);
    List<User> getAllUsers();

    void addFriend(String user, String friendName);

    List<UserDto> findAllFriends(String name);

    void newGame(String from, String sendTo, Long gameId);

    Long getGameIdByUserName(String username);

    List<User> findByGameId(Long gameId);
}
