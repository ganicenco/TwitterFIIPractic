package ro.itschool.service;

import org.springframework.stereotype.Service;
import ro.itschool.entity.Reply;

@Service
public interface ReplyService {

    void addReplyToPost(Long postId, Reply reply);
}
