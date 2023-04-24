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
//    public ResponseEntity<?> addReply(@RequestBody Reply reply, @PathVariable Long postId) {
//        Optional<Post> optionalPost = postService.findById(postId);

//        if (optionalPost.isEmpty()) {
//            return new ResponseEntity<>("Post with id:" + postId + " " + "not found", HttpStatus.BAD_REQUEST);
//        } else {
//            Post post = optionalPost.get();
//            post.setReplies(reply.getReplies());
//            postRepository.save(post);
//            return new ResponseEntity<>(replyService.save(reply), HttpStatus.OK);
//

    //   }

    //    public void addReply(@RequestBody Reply reply, @PathVariable Long postId) {
//        Optional<Post> post = postService.findById(postId);
//        if (post.isPresent()) {
//            Post post1 = post.get();
//            post1.setReplies(reply.getReplies());
////       Reply reply1 = new Reply();
////            reply1.setMessage(reply.getMessage());
////            reply1.setTimestamp(reply.getTimestamp());
////            reply1.getReplies();
////            reply1.setIsPublic(reply.getIsPublic());
////            replyRepository.save(reply1);
////            post.get().setReplies((Set<Reply>) reply1);
//        }
    //}
    //}
    public void addReply(@PathVariable Long postId, @RequestBody Reply reply) {
        Optional<Post> optionalPost = postService.findById(postId);
        if (optionalPost.isPresent()) {
            Post post1 = optionalPost.get();
            post1.setReplies(reply.getReplies());
            replyService.save(reply);
//
//            postRepository.save(post1);


        }
    }
}
