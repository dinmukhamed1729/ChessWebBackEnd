package ChessWeb.controller;

import ChessWeb.dto.UserDto;
import ChessWeb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

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

    @GetMapping("/friendList")
    public List<UserDto> friendList(Principal principal) {
        return userService.findAllFriends(principal.getName());
    }
}
