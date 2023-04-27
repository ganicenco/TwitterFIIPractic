package ro.itschool.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.entity.Post;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTimestampAfter(/*@Param("timestamp") */ LocalDateTime timestamp);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM likes WHERE post_id = ?", nativeQuery = true)
    void deleteLikesByPostId(Long id);

    @Query(value = "SELECT *, 0 AS clazz_ FROM post WHERE message LIKE %:keyword%", nativeQuery = true)
    List<Post> getPostsWithMention(String keyword);

    @Query(value = "SELECT *, 0 AS clazz_ FROM post WHERE user_id = ?", nativeQuery = true)
    List<Post> findByUserId(Long userId);

}
