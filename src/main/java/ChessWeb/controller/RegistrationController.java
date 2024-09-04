package ChessWeb.controller;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDto model) {
        System.out.println(model.email()+" ============================="+model.name());
        try {
            userService.registerNewUser(model);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User registration failed: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Restoration user failed: " + e.getMessage());
        }
        System.out.println(model.email()+" "+model.name());
        return ResponseEntity.ok("ok");
    }
}