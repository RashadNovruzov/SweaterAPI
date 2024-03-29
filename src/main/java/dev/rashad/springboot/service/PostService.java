package dev.rashad.springboot.service;

import dev.rashad.springboot.dto.PostDto;
import dev.rashad.springboot.dto.ResponsePostDto;
import dev.rashad.springboot.model.Post;
import dev.rashad.springboot.model.Tag;
import dev.rashad.springboot.model.User;
import dev.rashad.springboot.repository.PostRepository;
import dev.rashad.springboot.repository.TagRepository;
import dev.rashad.springboot.repository.UserRepository;
import dev.rashad.springboot.utils.CreatingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public void save(Post post){
        postRepository.save(post);
    }

    public List<ResponsePostDto> findUserPosts(int id){
        List<ResponsePostDto> posts = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            CreatingException.throwUserNotFoundException("User not found");
        }
        user.get().getPosts().stream().forEach(p->posts.add(new ResponsePostDto(p.getId(),p.getPostText(),p.getTags())));
        return posts;
    }

    public List<ResponsePostDto> findPostsByTag(String query) {
        List<ResponsePostDto> postDtos = new ArrayList<>();
        List<Tag> tags = tagRepository.findByTagTitle(query);
        tags.forEach(
                (t)->t.getPosts().forEach(
                        (p)->postDtos.add(new ResponsePostDto(p.getId(),p.getPostText(),p.getTags()))
                )
        );
        return postDtos;
    }

    public ResponsePostDto getPost(int id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) return new ResponsePostDto();
        Post post1 = post.get();
        return new ResponsePostDto(post1.getId(),post1.getPostText(),post1.getTags());
    }

    public void edit(PostDto postDto, int id) {
        Post post = postRepository.findById(id).get();
        post.setPostText(postDto.getPostText());
        Arrays.stream(postDto.getTags().split(",")).forEach((t)->post.getTags().add(new Tag(t)));
        postRepository.save(post);
    }
}
