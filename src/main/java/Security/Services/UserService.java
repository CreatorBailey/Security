package Security.Services;

import Security.Repository.UserRepository;
import Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User save(User user){
        return userRepository.save(user);
    }
    public User findByEmail(User user){
        User user1 = userRepository.findByEmail(user.getEmail());
        if(user1.getPassword().equals(user.getPassword())){
            return user1;
        }
        return null;
    }
    public Page<User> getAllUsersByPage(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public User update(User user, long id){
        return userRepository.save(user);
    }
    public void deleteUser(long id){
         userRepository.deleteById(id);
    }

   
}
