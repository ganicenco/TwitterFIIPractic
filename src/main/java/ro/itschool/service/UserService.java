package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> searchUser(String keyword);

    void getUser(User user);

    //  Optional<User> findByName(String name);

    void followUser(Long followedId);

    void unfollowUSer(Long id);


    //List<User> getFollowedUsers();

    void deleteById(Long id);

    Optional<User> findById(Long userId);

    void registerNewUser(User user);

    List<User> findAll();

    void save(User user);

}
