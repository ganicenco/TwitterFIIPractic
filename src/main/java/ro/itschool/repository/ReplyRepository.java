package ro.itschool.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.itschool.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reply WHERE post_id = ?", nativeQuery = true)
    void deleteReplies(Long id);

}
