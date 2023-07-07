package ru.team.superbook1.mail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sender {

    private String toEmail;

    private String subject;

    private String text;

}
