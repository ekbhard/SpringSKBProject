package com.skb.SpringSKBProject.controllres;

import com.skb.SpringSKBProject.domain.User;
import com.skb.SpringSKBProject.repositories.UserRepository;

import com.skb.SpringSKBProject.services.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jt on 1/10/17.
 */
@Controller
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public UserController(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @RequestMapping("/users")
    public String redirToList(){
        return "redirect:/users/list";
    }

    @RequestMapping({"/users/list", "/product"})
    public String listProducts(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id){
        User currentUser = userRepository.getUserById(id);
        emailService.sendMail(currentUser.getEmail(), "Добро пожаловать", "Добро пожаловать");
        userRepository.delete(Long.valueOf(id));
        return "redirect:/users/list";
    }

}
