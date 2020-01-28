package com.skb.SpringSKBProject.services;

import com.skb.SpringSKBProject.domain.User;
import com.skb.SpringSKBProject.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getNotApprovedUsers(){
        return userRepository.findAll().stream().filter(user -> !user.isApproved())
                .collect(Collectors.toList());
    }
}
