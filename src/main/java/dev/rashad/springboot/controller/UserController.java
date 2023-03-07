package dev.rashad.springboot.controller;

import dev.rashad.springboot.dto.EditProfileDto;
import dev.rashad.springboot.dto.UserDto;
import dev.rashad.springboot.exceptions.IncorrectData;
import dev.rashad.springboot.exceptions.UserNotFoundException;
import dev.rashad.springboot.model.User;
import dev.rashad.springboot.repository.UserRepository;
import dev.rashad.springboot.service.SubscriptionService;
import dev.rashad.springboot.utils.CreatingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final SubscriptionService subscriptionService;

    @GetMapping("/my-profile")
    public UserDto numOfFollowingsAndFollowers(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return getUserDto(user);
    }

    @GetMapping("/followers")
    public Map<Integer,String> getFollowers(){
        return subscriptionService.getFollowers();
    }

    @GetMapping("/followings")
    public Map<Integer,String> followings(){
        return subscriptionService.getFollowings();
    }

    @GetMapping("/{id}")
    public Map<String,Object> getUserInfo(@PathVariable("id") int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("Can not find user with this is");
        }
        return Map.of("info", getUserDto(user.get()),"following",getUserFromContext().getFollowings().contains(user.get()));
    }

    @GetMapping("/follow/{id}")
    public ResponseEntity<String> followUser(@PathVariable("id") int id) throws UserNotFoundException{
        subscriptionService.follow(id);
        return ResponseEntity.ok("Followed!");
    }

    @GetMapping("/unfollow/{id}")
    public ResponseEntity<String> unfollowUser(@PathVariable("id") int id) throws UserNotFoundException{
        subscriptionService.unfollow(id);
        return ResponseEntity.ok("Unfollowed");
    }

    @PostMapping("/edit-profile")
    public ResponseEntity<String> edit(@RequestBody @Valid EditProfileDto editProfileDto, BindingResult bindingResult){
        CreatingException.throwIncorrectDataException(bindingResult);
        User user = getUserFromContext();
        user.setAbout(editProfileDto.getAbout());
        user.setUsername(editProfileDto.getUsername());
        userRepository.save(user);
        return ResponseEntity.ok("Edited");
    }

    private UserDto getUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .about(user.getAbout())
                .followers(user.getFollowers().size())
                .followings(user.getFollowings().size())
                .build();
    }

    private User getUserFromContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }
}
