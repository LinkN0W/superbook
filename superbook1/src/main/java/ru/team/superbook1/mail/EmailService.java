package ru.team.superbook1.mail;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.repositories.BookRepository;
import ru.team.superbook1.repositories.FormRepository;
import ru.team.superbook1.repositories.UserRepository;
import ru.team.superbook1.services.FormService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@EnableScheduling
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FormService formService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    public void sendEmail(String toEmail, String subject, String text) throws MessagingException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(toEmail);
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);

    }

   @Scheduled(fixedRate = 30000)
    public void sendDataAboutUser(){
        boolean isDuty;
        SimpleDateFormat normalDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
       for(User user : userRepository.findUsersByDeleteIsFalse()){
           StringBuffer message = new StringBuffer("Ваша задолжность\n");
           for(Form form : formService.countUserPenalties(user.getId(), new Date())){
               Book book = bookRepository.findById(form.getBookId()).get();
               message.append(book.getTitle())
                       .append(" Взята ").append(normalDateFormat.format(form.getDateOfTaking()))
                       .append(", Срок сдачи ").append(normalDateFormat.format(form.getTermOfReturning()))
                       .append(", Количество просроченных дней ").append(form.getDelay())
                       .append(", Пени ").append(form.getPenalties()).append("\n");
           }
           try {
               sendEmail(user.getEmail(),"Задолжность по книгам", message.toString());
           } catch (MessagingException e) {
               throw new RuntimeException(e);
           }
       }

    }
}


