package com.skb.SpringSKBProject.services;

import com.skb.SpringSKBProject.Application;
import com.skb.SpringSKBProject.domain.User;
import com.skb.SpringSKBProject.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserService(UserRepository userRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<User> getNotApprovedUsers(){
        return userRepository.findAll().stream().filter(user -> !user.isApproved())
                .collect(Collectors.toList());
    }

    public void sendProductMessage(String id) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", id);
        log.info("Отправка запроса в очередь");
        rabbitTemplate.convertAndSend(Application.SFG_MESSAGE_QUEUE, actionmap);
    }
}
