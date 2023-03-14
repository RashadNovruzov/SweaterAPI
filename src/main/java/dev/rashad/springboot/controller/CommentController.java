package dev.rashad.springboot.controller;

import dev.rashad.springboot.dto.CommentDto;
import dev.rashad.springboot.model.Comment;
import dev.rashad.springboot.service.CommentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Data
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/posts/{id}")
    public List<CommentDto> commentsOfPost(@PathVariable("id") int id){
        return convertCommentToCommentDto(commentService.getComments(id));
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<String> addComment(@RequestBody String text, @PathVariable("id") int id){
        if(text.isEmpty()){
            return ResponseEntity.ok("Comment cant be empty");
        }
        commentService.addComment(text,id);
        return ResponseEntity.ok("added");
    }

    private List<CommentDto> convertCommentToCommentDto(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        comments.forEach((c)->commentDtos.add(new CommentDto(c.getText(),c.getAuthor().getName(), c.getAuthor().getId())));
        return commentDtos;
    }
}
