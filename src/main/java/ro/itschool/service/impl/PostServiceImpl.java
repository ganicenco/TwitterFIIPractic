package ro.itschool.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Post;
import ro.itschool.entity.User;
import ro.itschool.repository.PostRepository;
import ro.itschool.repository.ReplyRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.PostService;
import ro.itschool.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final ReplyRepository replyRepository;

    public Post save(Post newPost) {
        newPost.setTimestamp(LocalDateTime.now());
        return postRepository.save(newPost);
    }

    @Override
    public List<Post> filterPosts(LocalDate timestamp) {
        return postRepository.findByTimestampGreaterThan(timestamp);
    }

    @Override
    public void deletePostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        post.ifPresent(p -> {
            p.setUser(null);
            postRepository.save(p);
            postRepository.deleteLikesByPostId(id);
            replyRepository.deleteReplies(id);
            postRepository.deleteById(id);
        });
    }


//    @Override
//    public List<Post> getPostFromFollowedUsers() {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
//        List<User> users = userRepository.getFollowedUsers(principal.getId())
//                .stream()
//                .map(element -> new User(
//                        element[0].toString(),
//                        element[1].toString(),
//                        element[2].toString(),
//                        element[3].toString(),
//                        element[4].toString()))
//                .toList();
//
//        return users.stream()
//                .map(user -> postRepository.findById(user.getId()))
//                .flatMap(Collection::stream)
//                .toList();
//
//    }


    @Override
    public void repost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        optionalPost.ifPresent(p -> {
            Post post = new Post();
            post.setTimestamp(p.getTimestamp());
            post.setMessage(p.getMessage());
            //post.setUser(p.getUser());
            save(post);
        });
    }

    @Override
    public List<Post> getPostWithMentions() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return postRepository.getPostsWithMention(authentication.getName());
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        return postRepository.getPostsWithMention(optionalLoggedInUser.get().getUsername());
    }


    @Override
    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }
}


