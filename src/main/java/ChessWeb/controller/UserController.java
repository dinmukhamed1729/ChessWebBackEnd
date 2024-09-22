package ChessWeb.controller;

import ChessWeb.dto.UserDto;
import ChessWeb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/name")
    public String getName(Principal principal){
        return principal.getName();
    }

    @GetMapping("/allUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(it -> new UserDto(it.id(), it.name())).toList();
    }

    @PostMapping("/addFriend")
    public ResponseEntity<String> addFriend(Principal user, @RequestBody Map<String, String> request) {
        System.out.println("======================" + request.get("name") + " " + user.getName());
        userService.addFriend(user.getName(), request.get("name"));
        return ResponseEntity.ok("\"Friend \" + " + request.get("name") + " added successfully\"");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UsernameNotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @GetMapping("/friendList")
    public List<UserDto> friendList(Principal principal) {
        return userService.findAllFriends(principal.getName());
    }
}
