package dev.rashad.springboot.service;

import dev.rashad.springboot.dto.CommentDto;
import dev.rashad.springboot.exceptions.PostNotFoundException;
import dev.rashad.springboot.model.Comment;
import dev.rashad.springboot.model.Post;
import dev.rashad.springboot.model.User;
import dev.rashad.springboot.repository.PostRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final PostRepository postRepository;

    public List<Comment> getComments(int id){
        Post post = postRepository.findById(id).orElse(null);
        if(post == null){
            throw new PostNotFoundException("Post not found");
        }
        return post.getComments();

    }

    public void addComment(String text, int id) {
        Comment comment = new Comment();
        comment.setText(text);
        Post post = postRepository.findById(id).orElse(null);
        if(post == null){
            throw new PostNotFoundException("Post not found");
        }
        comment.setPost(post);
        comment.setAuthor(getUserFromContext());
    }

    private User getUserFromContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}
