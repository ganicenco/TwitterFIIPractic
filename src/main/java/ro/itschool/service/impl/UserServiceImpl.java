package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.User;
import ro.itschool.repository.PostRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public List<User> searchUser(String keyword) {
        if (keyword == null) {
            keyword = "";
        }
        return userRepository.searchUserBy(keyword);
    }

    @Override
    public void getUser(User user) {
        userRepository.findById(user.getId());
    }

    public void followUser(Long followedId) {
        //cautam userul pe care dorim sa-l urmarim
        Optional<User> toBeFollowed = userRepository.findById(followedId);
        //identificam userul logat
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(loggedUser.getUsername());
        //inseram in DB leatura dintre follow si followed
        toBeFollowed.ifPresent(user -> userRepository.insertIntoFollowTable(optionalLoggedInUser.get().getId(), user.getId()));
    }

    @Override
    public void unfollowUSer(Long unfollowedId) {
        //cautam userul pe care dorim sa-l urmarim
        Optional<User> toBeFollowed = userRepository.findById(unfollowedId);
        //identificam userul logat
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(loggedUser.getUsername());
        //sterge din DB leatura dintre follow si followed
        toBeFollowed.ifPresent(user -> userRepository.deleteFromFollowTable(optionalLoggedInUser.get().getId(), user.getId()));
    }


    //     @Override
//      public List<User> getFollowedUsers() {
//          //ce pun in paranteze. parca nu merge nimic
//          return userRepository.getFollowedUsers()
//                  .stream()
//                  .map(elem -> new User(elem[0].toString(), elem[1].toString(), elem[2].toString(), elem[3].toString(), elem[4].toString()))
//                  .toList();
//      }, k
//
//

    @Override
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(user1 -> {
            userRepository.deleteFollower(id);
            postRepository.deleteByUserId(user1.getId());
            userRepository.deleteById(id);
        });
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void registerNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}
