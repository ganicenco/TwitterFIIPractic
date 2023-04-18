package ro.itschool.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.entity.Post;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTimestampGreaterThan(@Param("timestamp") LocalDate timestamp);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM likes WHERE post_id = ?", nativeQuery = true)
    void deleteLikesByPostId(Long id);

    @Query(value = "SELECT * FROM post WHERE message LIKE %:keyword%", nativeQuery = true)
    List<Post> getPostsWithMention(String keyword);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM post WHERE user_id = ?", nativeQuery = true)
    void deleteByUserId(Long id);

}
