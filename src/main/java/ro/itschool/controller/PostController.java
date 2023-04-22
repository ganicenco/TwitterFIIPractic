package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Post;
import ro.itschool.entity.User;
import ro.itschool.exceptions.UserNotFoundException;
import ro.itschool.service.PostService;
import ro.itschool.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final UserService userService;

    //1. Add post : post a public message
    @PostMapping(value = "/create")
    private void addPost(@RequestBody Post newPost) {
        postService.save(newPost);
    }

    // 2. Get own posts : return all posts added by the current user;
    @GetMapping(value = "/get-own-posts")
    public List<Post> getMyPosts() {
        return postService.getMyPosts();
    }

    // 2'.Able to filter posts newer than a timestamp
    @GetMapping(value = "/filter-timestamp")
    public List<Post> filterPosts(@RequestParam("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate timestamp) {
        return postService.filterPosts(timestamp);
    }

    //3. Get feed : return all posts added by users followed by the current user


    // 4. Delete post and all the likes
    //works
    @DeleteMapping(value = "/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }


    //5. Repost : “copy” an existing post from a different user
    //works
    @PostMapping(value = "/repost/{id}")
    public void repost(@PathVariable Long id) {
        postService.repost(id);
    }

    //   6. Get mentions : return all posts in which the current user was mentioned
    //  doesn't work
    @GetMapping(value = "/mentions")
    public List<Post> getPostWithMentions() {
       return postService.getPostWithMentions();
    }

}

