package ro.itschool.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.itschool.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(
            value = "SELECT * FROM user u WHERE u.username LIKE %:keyword% OR u.first_name LIKE %:keyword% " +
                    "OR u.last_name LIKE %:keyword%",
            nativeQuery = true)
    List<User> searchUserBy(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO follow(follower_id, followed_id) VALUES (:followerId, :followedId)", nativeQuery = true)
    void insertIntoFollowTable(@Param("followerId") Long followerId, @Param("followedId") Long followedId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM follow WHERE follower_id  = :followerId AND followed_id = :followedId", nativeQuery = true)
    void deleteFromFollowTable(@Param("followerId") Long followerId, @Param("followedId") Long followedId);


    @Query(value = """
            SELECT b.id, b.first_name, b.last_name, b.username, b.email FROM user a INNER JOIN follow
            ON a.id = follow.follower_id
            INNER JOIN user b
            ON b.id = follow.followed_id
            WHERE a.id = ?;
            """, nativeQuery = true)
    List<Object[]> getFollowedUsers(Long id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM follow WHERE follower_id = ?", nativeQuery = true)
    void deleteFollower(Long id);


}


