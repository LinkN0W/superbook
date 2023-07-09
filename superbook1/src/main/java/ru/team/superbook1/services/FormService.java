package ru.team.superbook1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.functional.CountFunctional;
import ru.team.superbook1.functional.FormFunctional;
import ru.team.superbook1.repositories.BookRepository;
import ru.team.superbook1.repositories.FormRepository;
import ru.team.superbook1.repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FormService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final BookRepository bookRepository;


    private CountFunctional countFunctional;

    public FormService(UserRepository userRepository, FormRepository formRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.formRepository = formRepository;
        this.bookRepository = bookRepository;
    }

    public Optional<Form> addBookToUser(UUID userId,Form form) {
        Optional<User> currentUser = userRepository.findById(userId);
        Optional<Book> addedBook = bookRepository.findByIdAndAmountIsNotNull(form.getBookId());

        if(currentUser.isPresent() && addedBook.isPresent()) {
            form.setUserId(currentUser.get().getId());
            form.setDateOfTaking(new Date());
            form.setPenalties(0);
            formRepository.save(form);
            bookRepository.updateBookByIdMinusOneFromAmount(form.getBookId());
            return formRepository.findById(form.getId());
        }
        else return Optional.empty();
    }

    public void deleteForm(UUID userId, UUID bookId){
        formRepository.deleteByUserIdAndBookId(userId, bookId);
        bookRepository.updateBookByIdPlusOneFromAmount(bookId);
    }


    public Iterable<Form> countUserPenalties(UUID userId, Date date){
        List<Form> forms = formRepository.findAllByUserIdAndDateOfReturningIsNull(userId);
        countFunctional = new FormFunctional();
        forms =  countFunctional.countUserPenaltiesForOnce(forms, date);
        forms.stream().forEach(e -> formRepository.save(e));
        return forms;
    }

    public Iterable<Form> countAllPenalties(Date date){
        List<Form> forms = formRepository.findAllByDateOfReturningIsNull();
        countFunctional = new FormFunctional();
        forms =  countFunctional.countUserPenaltiesForOnce(forms, date);
        forms.stream().forEach(e -> formRepository.save(e));
        return forms;
    }
}
