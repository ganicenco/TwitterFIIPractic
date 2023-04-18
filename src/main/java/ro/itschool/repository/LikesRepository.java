package ro.itschool.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.itschool.entity.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO likes (user_id, post_id) VALUES (:userId, :postId)", nativeQuery = true)
    void likePost(@Param("userId") Long userId, @Param("postId") Long postId);

    @Transactional
    @Modifying
    @Query(value = "DELETE from likes WHERE user_id = :userId AND post_id = :postId", nativeQuery = true)
    void unlikePost(@Param("userId") Long userId, @Param("postId") Long postId);
}