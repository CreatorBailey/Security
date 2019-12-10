package Security.Controller;

import Security.Services.UserService;
import Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/users")
    ResponseEntity<?> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
