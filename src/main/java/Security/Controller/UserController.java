package Security.Controller;

import Security.Services.UserService;
import Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;


@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/users")
    ResponseEntity<?> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @PostMapping("/users/login")
    ResponseEntity<?> findByEmail(@RequestBody User user){
        User user1 = userService.findByEmail(user);
        if(user1 == null){
            return new ResponseEntity<>("User login failed", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User login successful", HttpStatus.ACCEPTED);
    }
    @GetMapping("/users/")
    ResponseEntity<?> getAllUsers(Pageable pageable){
        return new ResponseEntity<>(userService.getAllUsersByPage(pageable), HttpStatus.ACCEPTED);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable long id){
        return new ResponseEntity<>(userService.update(user,id),HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> delete(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
   }
   @GetMapping("/users/{id}")
    ResponseEntity<?>getById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
   }

}
