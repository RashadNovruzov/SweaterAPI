package dev.rashad.springboot.service;

import dev.rashad.springboot.exceptions.UserNotFoundException;
import dev.rashad.springboot.model.User;
import dev.rashad.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Map<Integer,String> getFollowers(){
        User user = getUserFromContext();
        List<User> followers = user.getFollowers();
        Map<Integer,String> map = new HashMap<>();
        followers.stream().forEach(u->map.put(u.getId(),u.getName()));
        return map;
    }

    public Map<Integer,String> getFollowings(){
        User user = getUserFromContext();
        List<User> followings = user.getFollowings();
        Map<Integer,String> map = new HashMap<>();
        followings.stream().forEach(u->map.put(u.getId(),u.getName()));
        return map;
    }

    public void follow(int id) throws UserNotFoundException {
        User user = getUserFromContext();
        User user1 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("can not found user"));
        if(!user.getFollowings().contains(user1)){
            user.getFollowings().add(user1);
            userRepository.save(user);
        }
    }

    public Map<Integer,String> findUser(String name){
        Map<Integer,String> map = new HashMap<>();
        List<User> users = userRepository.findByUsernameContaining(name);
        if(users.isEmpty()) return map;
        users.stream().forEach((u)->map.put(u.getId(),u.getUsername()));
        return map;
    }

    private User getUserFromContext(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

    public void unfollow(int id) throws UserNotFoundException{
        User user = getUserFromContext();
        User user1 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("can not found user"));
        if(user.getFollowings().contains(user1)){
            user.getFollowings().remove(user1);
            user1.getFollowers().remove(user);
            userRepository.save(user);
        }
    }
}
