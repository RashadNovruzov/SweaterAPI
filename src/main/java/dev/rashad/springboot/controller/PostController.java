package dev.rashad.springboot.controller;

import dev.rashad.springboot.dto.PostDto;
import dev.rashad.springboot.dto.ResponsePostDto;
import dev.rashad.springboot.model.Post;
import dev.rashad.springboot.model.Tag;
import dev.rashad.springboot.model.User;
import dev.rashad.springboot.service.PostService;
import dev.rashad.springboot.utils.CreatingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid PostDto postDto, BindingResult bindingResult){
        CreatingException.throwIncorrectDataException(bindingResult);
        postService.save(convertToPost(postDto));
        return ResponseEntity.ok("Created");
    }

    @GetMapping("/my-profile")
    public ResponseEntity<List<ResponsePostDto>> getPosts(){
        List<ResponsePostDto> posts = new ArrayList<>();
        List<Post> userPosts = getUserFromContext().getPosts();
        userPosts.stream().forEach(p->posts.add(new ResponsePostDto(p.getPostText(), p.getTags())));
        return ResponseEntity.ok(posts);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<ResponsePostDto>> getUserPosts(@PathVariable("id") int id){
        return ResponseEntity.ok(postService.findUserPosts(id));
    }



    private Post convertToPost(PostDto postDto){
        Post post = new Post();
        post.setPostText(postDto.getPostText());
        Arrays.stream(postDto.getTags().split(",")).forEach((t)->post.getTags().add(new Tag(t)));
        post.setUser(getUserFromContext());
        return post;
    }

    private User getUserFromContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}
