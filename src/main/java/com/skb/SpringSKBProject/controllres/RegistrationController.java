package com.skb.SpringSKBProject.controllres;

import com.skb.SpringSKBProject.domain.User;
import com.skb.SpringSKBProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model,
                          @RequestParam("surname") String surname,
                          @RequestParam("name") String name,
                          @RequestParam("patronymic") String patronymic){
        if (user.getPassword().equals("") || user.getUsername().equals("")){
            model.put("message","Null login and password");
            return "registration";
        }
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (!userFromDb.isApproved()){
            model.put("message","Вы заблокированы");
            return "registration";
        }

        if (userFromDb != null){
            model.put("message","Такой юзер уже существует");
            return "registration";
        }
        user.setActive(true);
        user.setName(name);
        user.setPatronymic(patronymic);
        user.setSurname(surname);
        userRepository.save(user);
        return "redirect:/registration";
    }
}
