package ru.team.superbook1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.functional.CountFunctional;
import ru.team.superbook1.functional.FormFunctional;
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


    CountFunctional countFunctional;

    public Optional<Form> addBookToUser(UUID userId,Form form) {
        User currentUser = userRepository.findById(userId).get();
        form.setUserId(currentUser.getId());
        form.setDateOfTaking(new Date());
        form.setPenalties(0);
        formRepository.save(form);
        return formRepository.findById(form.getId());
    }




    public Iterable<Form> countPenalties(UUID userId, Date date){
        List<Form> forms = formRepository.findAllByUserId(userId);
        countFunctional = new FormFunctional();
        forms =  countFunctional.countUserPenaltiesForOnce(forms, date);
        forms.stream().forEach(e -> formRepository.save(e));
        return forms;
    }
}
