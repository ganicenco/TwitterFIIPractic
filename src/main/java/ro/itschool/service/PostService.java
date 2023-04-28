package ro.itschool.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Post;
import ro.itschool.entity.Reply;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public interface PostService {

    Post save(Post newPost);

    List<Post> filterPosts(LocalDateTime timestamp);

    void deletePostById(Long id);

    void repost(Long id);

    List<Post> getPostWithMentions();

  //  Optional<Post> findById(Long postId);

    List<Post> getMyPosts();

    List<Post> getPostsFromFollowedUsers();


}
