package ru.team.superbook1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.services.FormService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/form")
public class FormController {

    @Autowired
    FormService formService;
    @PostMapping("/add")
    public Optional<Form> addBookToUser(@RequestBody Form form){
        return formService.addBookToUser(form);
    }

    @GetMapping("/count/{userId}")
    public Optional<Form> countUserPenalties(@PathVariable UUID userId ){
        return formService.countUserPenalties(userId);
    }



}
