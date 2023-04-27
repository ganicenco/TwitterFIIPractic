package ro.itschool.startup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.MyRole;
import ro.itschool.entity.Post;
import ro.itschool.enums.RoleName;
import ro.itschool.entity.User;
import ro.itschool.repository.PostRepository;
import ro.itschool.repository.RoleRepository;
import ro.itschool.service.UserService;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class RunAtStartup {
    private final UserService userService;
    private final PostRepository postRepository;
    private final RoleRepository roleRepository;

    @EventListener(ApplicationReadyEvent.class)

    public void insertUsersIntoDb() {
        roleRepository.save(new MyRole(RoleName.ROLE_USER));
        roleRepository.save(new MyRole(RoleName.ROLE_ADMIN));
        User user1 = new User();
        user1.setFirstName("Ioana");
        user1.setLastName("Mocanu");
        user1.setUsername("ioanam");
        user1.setEmail("ioana.mocanu@gmail.com");
        user1.setPassword("pass");
        Set<Post> posts = Set.of(new Post("this is a test post 1", LocalDateTime.now(), user1),
                new Post("this is a test post 2", LocalDateTime.now(), user1),
                new Post("this is a test post 3", LocalDateTime.now(), user1));
        user1.setPosts(posts);
        userService.saveUsersToWorkWith(user1);

        User user2 = new User();
        user2.setFirstName("Loredana");
        user2.setLastName("Chiriac");
        user2.setUsername("loredanac");
        user2.setEmail("loredana.chiriac@gmail.com");
        user2.setPassword("pass");
        Set<Post> posts1 = Set.of(new Post("this is another test post", LocalDateTime.now(), user2),
                new Post("yet another test post", LocalDateTime.now(), user2),
                new Post("post 3 of user 2", LocalDateTime.now(), user2));
        user2.setPosts(posts1);
        userService.saveUsersToWorkWith(user2);

        User user3 = new User();
        user3.setFirstName("Pavel");
        user3.setLastName("Dimitrie");
        user3.setUsername("paveld");
        user3.setEmail("pavel.dimitrie@gmail.com");
        user3.setPassword("pass");
        Set<Post> posts3 = Set.of(new Post("Test post 3", LocalDateTime.now(), user3),
                new Post("test 2 for user3", LocalDateTime.now(), user3),
                new Post("the last post for user 3", LocalDateTime.now(), user3));
        user3.setPosts(posts3);
        userService.saveUsersToWorkWith(user3);
    }
}
