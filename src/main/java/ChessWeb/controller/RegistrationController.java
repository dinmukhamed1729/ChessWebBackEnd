package ChessWeb.controller;

import ChessWeb.dto.RegistrationDto;
import ChessWeb.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RequiredArgsConstructor
@RestController			
public class RegistrationController {
    
    private final UserService userService;
    
    
    @PostMapping("/registration")
    public ResponseEntity<String> registrate(@RequestBody RegistrationDto user)  {
        log.info(user.toString());
        try {
            userService.registerNewUser(user);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Restration user failed: " + e.getMessage());
        }
        return ResponseEntity.ok("ok");
    }
    
}