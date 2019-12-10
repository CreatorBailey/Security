package Security.Services;

import Security.Repository.UserRepository;
import Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User save(User user){
        return userRepository.save(user);
    }
}
