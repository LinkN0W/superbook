package ru.team.superbook1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean canRegister( String email) {

        if (!userRepository.findUserByEmail(email).isEmpty()) {
            return false;
        }
        return true;

    }


    public void save(User user){
        userRepository.save(user);
    }

    public Optional<User> findUserById(UUID id){
        return userRepository.findById(id);
    }

    public Optional<User> update(UUID id, User user){
        userRepository.updateUserById(id, user.getEmail(), user.getPassword(), user.getRole().name());
        return findUserById(id);
    }
    public void delete(UUID id){
        userRepository.delete(id,true);
    }

}
