package ru.team.superbook1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.team.superbook1.dto.UserDTO;
import ru.team.superbook1.entities.User;

import ru.team.superbook1.services.UserService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/myInfo")
    public User showInfoCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(auth.getName());
    }


    @GetMapping("/show/{userId}")
    public Optional<UserDTO> show(@PathVariable UUID userId){
        User user = userService.findUserById(userId).get();
        return Optional.of(new UserDTO(user.getEmail(), user.getPassword(), user.getRole().name(), user.getBooks()));
    }

    @PostMapping("/edit/{userId}")
    public Optional<User> update(@PathVariable UUID userId, @RequestBody User user){
        return userService.update(userId,user);
    }

    @DeleteMapping("/delete/{userId}")
    public String delete(@PathVariable UUID userId){
        userService.delete(userId);
        return "OK";
    }

}
