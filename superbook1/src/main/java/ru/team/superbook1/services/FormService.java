package ru.team.superbook1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.repositories.FormRepository;
import ru.team.superbook1.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FormRepository formRepository;

    public Optional<Form> addBookToUser(Form form) {
        User currentUser = userRepository.findById(UUID.fromString("3b28ebee-3eeb-47b4-9d60-235d0e27ac5a")).get();
        form.setUserId(currentUser.getId());
        form.setDateOfTaking(new Date());
        form.setPenalties(0);
        formRepository.save(form);
        return formRepository.findById(form.getId());
    }

    public Optional<Form> countUserPenalties(UUID userId) {
        Date currentDate = new Date();
        List<Form> forms = formRepository.findAllByUserId(userId);
        return Optional.ofNullable(forms.get(1));
    }
}
