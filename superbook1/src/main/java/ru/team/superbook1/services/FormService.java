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

    public Optional<Form> addBookToUser(UUID userId,Form form) {
        User currentUser = userRepository.findById(userId).get();
        form.setUserId(currentUser.getId());
        form.setDateOfTaking(new Date());
        form.setPenalties(0);
        formRepository.save(form);
        return formRepository.findById(form.getId());
    }

    public Iterable<Form> countUserPenalties(UUID userId, Date date) {
        List<Form> forms = formRepository.findAllByUserId(userId);
        forms.stream().forEach(e -> {
                    int difference = (int) ((e.getDateOfReturning().getTime() - date.getTime()) / 86400000);
                    if(difference < 0){
                        e.setDelay(Math.abs(difference));
                        if(Math.abs(difference) > 30)
                            e.setPenalties( 5*(Math.abs(difference)-30));
                        formRepository.save(e);
                    }
                }
        );

        forms.stream().forEach(e -> System.out.println(e.getPenalties()));

        return forms;
    }
}
