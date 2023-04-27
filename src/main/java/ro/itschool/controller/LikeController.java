package ro.itschool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Post;
import ro.itschool.entity.User;
import ro.itschool.exceptions.PostNotFoundException;
import ro.itschool.exceptions.UserNotFoundException;
import ro.itschool.service.LikesService;
import ro.itschool.service.PostService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/likes")
@RequiredArgsConstructor
public class LikeController {
    private final LikesService likesService;

    @PostMapping(value = "/like/{postId}")
    public void likePost(@PathVariable Long postId) {
        likesService.likePost(postId);

    }

    @DeleteMapping(value = "/unlike/{id}")
    public void unlikePost(@PathVariable Long id) {
        likesService.unlikePost(id);

    }
}

