package com.example.QuoraAppClone.services;

import com.example.QuoraAppClone.dtos.UserDTO;
import com.example.QuoraAppClone.models.Tag;
import com.example.QuoraAppClone.models.User;
import com.example.QuoraAppClone.repositories.TagRepository;
import com.example.QuoraAppClone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private TagRepository tagRepository;

    public UserService(UserRepository userRepository ,TagRepository tagRepository ){
        this.userRepository=userRepository;
        this.tagRepository=tagRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id){
       return userRepository.findById(id);
    }

    public User createUser(UserDTO userDto){
       User user = new User();
       user.setUsername(userDto.getUsername());
       user.setPassword(userDto.getPassword());
       return userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public void followTag(Long userId, Long tagId){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(()->new RuntimeException("Tag not found"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }


}
