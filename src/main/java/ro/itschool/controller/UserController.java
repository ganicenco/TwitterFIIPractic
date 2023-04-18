package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.User;
import ro.itschool.service.UserService;

import java.util.List;

@ToString
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //1. Register : allow users to register with a unique username, a first name and a last name, an e-mail and a password
    //works
    //without spring security
    @PostMapping(path = "/register")
    public void registerNewUser(@RequestBody User user) {
        userService.registerNewUser(user);
    }


    //extra
    //works
    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    //2. Search : search for other users by username, firstname or lastname
    //works
    @GetMapping(path = "/search")
    public List<User> searchUser(@RequestParam String keyword) {
        return userService.searchUser(keyword);
    }


    // 3. Follow : follow another user and start receiving his public posts
    //works
    @PostMapping(value = "/follow/{id}")
    public void followUser(@PathVariable Long id) {
        userService.followUser(id);
    }

    @DeleteMapping(value = "/unfollow/{id}")
    public void unfollowUser(@PathVariable Long id) {
        userService.unfollowUSer(id);
    }

//    @GetMapping(value = "/followed")
//    public void getFollowedUsers() {
//        userService.getFollowedUsers();
//    }

    //5. Unregister : remove user and all his posts
    //works
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }


}
