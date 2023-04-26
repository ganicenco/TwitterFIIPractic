package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.controller.modelDTO.UserDTO;
import ro.itschool.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> searchUser(String keyword);

    List<UserDTO> getUser(User user);

    //  Optional<User> findByName(String name);

    void followUser(Long followedId);

    void unfollowUSer(Long id);


    List<User> getFollowedUsers();

    void deleteById(Long id);

    List<UserDTO> findById(Long userId);

    void saveUsersToWorkWith(User user);

    List<UserDTO> findAll();

    void save(User user);

}
