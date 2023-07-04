package ru.team.superbook1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.team.superbook1.dto.UserDTO;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.message.request.RegisterRequest;
import ru.team.superbook1.message.response.ResponseMessage;
import ru.team.superbook1.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if (!this.userService.canRegister(request.getEmail()))
            return new ResponseEntity<>(new ResponseMessage("Email is already in use"),
                    HttpStatus.BAD_REQUEST);

        User user = new User(UUID.randomUUID(),
                request.getEmail(),
                request.getPassword(),
                User.UserRole.USER);
        userService.save(user);

        return new ResponseEntity<>(new ResponseMessage("User registration is successful"), HttpStatus.OK);
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
