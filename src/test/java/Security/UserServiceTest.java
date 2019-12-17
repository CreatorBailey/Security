package Security;
import Security.Services.UserService;
import Security.model.User;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.*;


public class UserServiceTest extends UserService {

    @Test
    void createServiceTest() {
        UserService userService = mock(UserService.class);
        User user = new User();
        when(userService.save(user)).then(answer -> {
            Assert.assertEquals(answer.getMock(), user);
            return null;
        });
    }

    @Test
    void findByEmail() {
        UserService userService = mock(UserService.class);
        User user = new User();
        user.setEmail("gatman@yuhoo.com");
        userService.save(user);
        when(userService.findByEmail(user)).then(answer -> {
            Assert.assertEquals(answer.getMock(), user);
            return null;

        });
    }
    @Test
    void getAllUsersByPage(){
        UserService userService = mock(UserService.class);
        User user = new User();
        userService.save(user);
        userService.save(user);
        userService.save(user);
        userService.save(user);
        userService.save(user);
        userService.save(user);
        Pageable page = PageRequest.of(0, 3);
        when(userService.getAllUsersByPage(page)).then(answer -> {
            Assert.assertEquals(answer.getMock(), user);
            return null;

        });
    }
}