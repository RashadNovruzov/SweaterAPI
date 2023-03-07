package dev.rashad.springboot.service;

import dev.rashad.springboot.model.Post;
import dev.rashad.springboot.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post){
        postRepository.save(post);
    }
}
