package Security.Services;

import Security.Repository.UserRepository;
import Security.model.Address;
import Security.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.keygen.KeyGenerators;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.Optional;

@Service
public class UserService {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        User user1 = new User();
        user1.setFirst_name(user.getFirst_name());
        user1.setLast_name(user.getLast_name());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        Long userId = (ByteBuffer.wrap(KeyGenerators.secureRandom().generateKey()).getLong());
        user1.setId(userId);
//       Converting default linkedhashmap to a list of addresses
        Set<Address> addresses = mapper.convertValue(
                user.getAddresses(),
                new TypeReference<Set<Address>>() { });
        user1.setAddresses(addresses);
//        Generating a random ID for each address
        user1.getAddresses().forEach(address -> {
            address.setId((ByteBuffer.wrap(KeyGenerators.secureRandom().generateKey()).getLong()));
            address.setUserId(userId);
        });
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


    public User update(User user1, long id){
         User user = userRepository.getOne(id);
         if (user1.getEmail()!=null) {
             user.setEmail(user1.getEmail());
         }if (user1.getFirst_name()!=null) {
            user.setFirst_name(user1.getFirst_name());
        }if (user1.getLast_name()!=null) {
            user.setLast_name(user1.getLast_name());
        }if (user1.getPassword()!=null) {
            user.setPassword(user1.getPassword());
        }if (user1.getAddresses()!=null) {
            user.setAddresses(user1.getAddresses());
        }

        return userRepository.save(user);
    }
    public void deleteUser(long id){
         userRepository.deleteById(id);
    }
    public Optional<User> getById(long id){
        return userRepository.findById(id);
    }
}
