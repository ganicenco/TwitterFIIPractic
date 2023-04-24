package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public Post save(Post newPost) {
        newPost.setTimestamp(LocalDate.now());
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        newPost.setUser(optionalLoggedInUser.get());
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
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(principal.getUsername());
        return postRepository.getPostsWithMention(optionalLoggedInUser.get().getUsername());
    }


    @Override
    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public List<Post> getMyPosts() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findById(principal.getId());
        return postRepository.findByUserId(optionalLoggedInUser.get().getId());
    }

    @Override
    public Object getPostsFromFollowedUsers() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findById(principal.getId());
        List<User> springUsers = userRepository.getFollowedUsers(optionalLoggedInUser.get().getId())
                .stream()
                .map(elem -> new User(
                        elem[0].toString(),
                        elem[1].toString(),
                        elem[2].toString(),
                        elem[3].toString(),
                        elem[4].toString()))
                .toList();
        return springUsers.stream()
                .map(user -> postRepository.findByUserId(user.getId()))
                .flatMap(Collection::stream)
                .toList();
    }
}



