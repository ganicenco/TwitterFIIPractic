package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Post;
import ro.itschool.entity.Reply;
import ro.itschool.entity.User;
import ro.itschool.exceptions.PostNotFoundException;
import ro.itschool.repository.PostRepository;
import ro.itschool.repository.ReplyRepository;
import ro.itschool.service.PostService;
import ro.itschool.service.ReplyService;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final PostService postService;

    //1. Add post reply : reply to existing posts or other replies. Able to specify if the reply
    //is public or only for the parent post’s owner

    @PostMapping(value = "/add/{postId}")
    public void addReply(@PathVariable Long postId, @RequestBody Reply reply) {
        replyService.addReplyToPost(postId, reply);
    }
}
