package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Post;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    Post save(Post newPost);

    List<Post> filterPosts(LocalDate timestamp);

    void deletePostById(Long id);

    void repost(Long id);

    List<Post> getPostWithMentions();

    Optional<Post> findById(Long postId);

    List<Post> getMyPosts();

    List<Post> getPostsFromFollowedUsers();
}
