package ru.team.superbook1.mail;


import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public String sendEmail(@RequestBody Sender sender) throws MessagingException {
        emailService.sendEmail(sender.getToEmail(),sender.getSubject(), sender.getText());

        return "Email sent successfully";
    }

}
