package Security;
import Security.Repository.UserRepository;
import Security.Services.UserService;
import Security.model.Address;
import Security.model.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @InjectMocks
    static UserService userService;

    @Mock
    static UserRepository userRepository;

    @Mock
    static BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //TODO: fix test
    @Test
    void createServiceTest() {
        User user = new User();
        when(userService.save(user)).then(answer -> {
            Assert.assertEquals(answer.getMock(), user);
            return null;
        });
    }

    @Test
    void findByEmail() {
        User user = new User();
        user.setEmail("gatman@yuhoo.com");
        user.setPassword("Password");
        user.setId(5L);
        userService.save(user);
        when(userService.findByEmail(user)).thenReturn(user);
        User testUser = userService.findByEmail(user);
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    void getAllUsersByPage() {
        ArrayList<User> users = new ArrayList<>();
        for (int x = 0; x < 3; x++){
            User user = new User();
            user.setId((long) x);
            users.add(user);
        }
        Pageable page = PageRequest.of(0, 3);
        when(userService.getAllUsersByPage(page)).thenReturn(new PageImpl<>(users, page, 3));

        Page testPage = userService.getAllUsersByPage(page);

        // assertThat(testPage.getContent().size() == 3);
        verify(userRepository).findAll(page);
    }


    @Test
    void update() {
        User user = new User();
        User user1 = new User();
        long id = 1L;



        when(userService.update(user,id)).thenReturn(user1);
        userService.update(user,id);
        verify(userService).update(user,id);

    }

    @Test
    void delete() {
        User user1 = new User();
        long id = 1L;
        user1.setId(id);
        user1.setAddresses(new HashSet<>(Collections.singleton(new Address())));
        doNothing().when(userRepository).delete(any(User.class));
        userService.save(user1);
        userService.deleteUser(id);
        verify(userRepository).deleteById(id);
    }
}
