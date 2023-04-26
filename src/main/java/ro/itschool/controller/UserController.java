package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.controller.modelDTO.UserDTO;
import ro.itschool.entity.User;
import ro.itschool.service.UserService;

import java.util.List;

@ToString
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/all")
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }


    //2. Search : search for other users by username, firstname or lastname
    @GetMapping(path = "/search")
    public List<UserDTO> searchUser(@RequestParam String keyword) {
        return userService.searchUser(keyword);
    }


    //3. Follow : follow another user and start receiving his public posts
    @PostMapping(value = "/follow/{id}")
    public void followUser(@PathVariable Long id) {
        userService.followUser(id);
    }

    //4. Unfollow : unfollow a user and stop receiving feeds from this user
    @DeleteMapping(value = "/unfollow/{id}")
    public void unfollowUser(@PathVariable Long id) {
        userService.unfollowUSer(id);
    }

    @GetMapping(value = "/followed")
    public List<UserDTO> getFollowedUsers() {
       return userService.getFollowedUsers();
    }

    //5. Unregister : remove user and all his posts
    @DeleteMapping(value = "/delete/{id}")
    public void unregister(@PathVariable Long id) {
        userService.deleteById(id);
    }


}
