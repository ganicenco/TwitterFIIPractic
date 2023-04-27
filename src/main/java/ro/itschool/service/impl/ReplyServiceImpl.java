package ro.itschool.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.itschool.entity.Post;
import ro.itschool.entity.Reply;
import ro.itschool.entity.User;
import ro.itschool.repository.PostRepository;
import ro.itschool.repository.ReplyRepository;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.ReplyService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void addReplyToPost(Long postId, Reply reply) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalLoggedInUser = userRepository.findByUsername(loggedUser.getUsername());

        Optional<Post> optionalPost = postRepository.findById(postId);

        reply.setTimestamp(LocalDateTime.now());
        reply.setUser(optionalLoggedInUser.get());

        Set<Reply> replies = optionalPost.get().getReplies();
        replies.add(reply);
        optionalPost.get().setReplies(replies);
        replyRepository.save(reply);

    }
}

