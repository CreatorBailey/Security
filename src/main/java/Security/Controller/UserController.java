package Security.Controller;

import Security.Services.UserService;
import Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/users")
    ResponseEntity<?> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @PutMapping("/users/{id}")
    ResponseEntity<?> editUser(@RequestBody User user, @PathVariable("id")long id ){
        return new ResponseEntity<>(userService.update(user,id), HttpStatus.CREATED);
    }

}
