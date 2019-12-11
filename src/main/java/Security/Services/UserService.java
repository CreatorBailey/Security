package Security.Services;

import Security.Repository.UserRepository;
import Security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.nio.ByteBuffer;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User save(User user){
        User user1 = new User();
        user1.setFirst_name(user.getFirst_name());
        user1.setLast_name(user.getLast_name());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setId(ByteBuffer.wrap(KeyGenerators.secureRandom().generateKey()).getLong());
        return userRepository.save(user1);
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
}
