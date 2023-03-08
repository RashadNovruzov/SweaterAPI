package dev.rashad.springboot.service;

import dev.rashad.springboot.dto.ResponsePostDto;
import dev.rashad.springboot.model.Post;
import dev.rashad.springboot.model.User;
import dev.rashad.springboot.repository.PostRepository;
import dev.rashad.springboot.repository.UserRepository;
import dev.rashad.springboot.utils.CreatingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void save(Post post){
        postRepository.save(post);
    }

    public List<ResponsePostDto> findUserPosts(int id){
        List<ResponsePostDto> posts = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            CreatingException.throwUserNotFoundException("User not found");
        }
        user.get().getPosts().stream().forEach(p->posts.add(new ResponsePostDto(p.getPostText(),p.getTags())));
        return posts;
    }
}
