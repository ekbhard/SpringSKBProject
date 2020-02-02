package com.skb.SpringSKBProject.listner;

import com.skb.SpringSKBProject.domain.User;
import com.skb.SpringSKBProject.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked with the
 * message as the parameter.
 */
@Component
public class UserMessageListener {

    private UserRepository userRepository;

    private static final Logger log = LogManager.getLogger(UserMessageListener.class);

    public UserMessageListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Этот метод будет вызываться всякий раз когда новое
     *
     * @param message
     */
    public void receiveMessage(Map<String, String> message) {
        log.info("Received <" + message + ">");
        Long id = Long.valueOf(message.get("id"));
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        user.setApproved(true);
        userRepository.save(user);
        log.info("Message processed...");
    }
}
