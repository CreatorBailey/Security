package Security;
import Security.Services.UserService;
import Security.model.User;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


class UserServiceTest {

    //TODO: fix test
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
        user.setPassword("Password");
        user.setId(5L);
        userService.save(user);
        when(userService.findByEmail(user)).thenReturn(user);
        User testUser = userService.findByEmail(user);
        assertThat(testUser.getId().equals(user.getId()));
    }

    @Test
    void getAllUsersByPage() {
        UserService userService = mock(UserService.class);
        User user1 = new User();
        user1.setId(1L);
        userService.save(user1);
        User user2 = new User();
        user2.setId(2L);
        userService.save(user2);
        User user3 = new User();
        user3.setId(3L);
        userService.save(user3);
        User user4 = new User();
        user4.setId(4L);
        userService.save(user4);
        User user5 = new User();
        user5.setId(5L);
        userService.save(user5);
        User user6 = new User();
        user6.setId(6L);
        userService.save(user6);
        Pageable page = PageRequest.of(0, 3);
        when(userService.getAllUsersByPage(page)).thenReturn(new PageImpl<>(new ArrayList<>(Arrays.asList(user1, user2, user3)), page, 3));

        Page testPage = userService.getAllUsersByPage(page);

        assertThat(testPage.getContent().size() == 3);
    }


    @Test
    void update() {
        UserService userService = mock(UserService.class);
        User user = new User();
        User user1 = new User();
        long id = 1L;



        when(userService.update(user,id)).thenReturn(user1);
        userService.update(user,id);
        verify(userService).update(user,id);

    }

    @Test
    void delete() {
        UserService userService = mock(UserService.class);
        User user = new User();
        long id = 1L;
        user.setId(id);
        when(userService.save(user)).thenReturn(user);
        userService.save(user);
        when(userService.getById(id)).thenReturn(Optional.of(user));

        Optional optional = userService.getById(id);
        assertThat(optional.isPresent());
        userService.deleteUser(id);

        optional = userService.getById(id);
        assertThat(optional.isEmpty());
        verify(userService).deleteUser(id);
    }
}
