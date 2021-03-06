package com.skb.SpringSKBProject.controllres;

import com.skb.SpringSKBProject.domain.User;
import com.skb.SpringSKBProject.repositories.UserRepository;

import com.skb.SpringSKBProject.services.EmailService;
import com.skb.SpringSKBProject.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private UserRepository userRepository;
    private EmailService emailService;
    private UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, EmailService emailService, UserService userService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("users")
    public String listUsers(Map<String,Object> model){
        List<User> users = userService.getNotApprovedUsers();
        model.put("users",users);
        return "users";
    }

    @RequestMapping(value = "deleted/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id,Map<String,Object> model){
        User currentUser = userRepository.getUserById(id);
        emailService.sendMail(currentUser.getEmail(), "Запрос отклонен", "Попробуйте в следующий раз");
        userRepository.delete(currentUser);
        model.put("user",currentUser);
        return "redirect:/users";
    }

    @RequestMapping(value = "accepted/{id}",method = RequestMethod.GET)
    public String accept(@PathVariable Long id,Map<String,Object> model){
        User currentUser = userRepository.getUserById(id);
        userService.sendProductMessage(id.toString());
        emailService.sendMail(currentUser.getEmail(), "Запрос пропущен", "Добро пожаловать");
        return "redirect:/users";
    }

}
