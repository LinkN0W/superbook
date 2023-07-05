package ru.team.superbook1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.export.ExcelExport;
import ru.team.superbook1.services.FormService;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/form")
public class FormController {

    @Autowired
    private FormService formService;

    @Autowired
    private ExcelExport excelExport;
    @PostMapping("/add")
    public Optional<Form> addBookToUser(@RequestBody Form form){
        return formService.addBookToUser(UUID.fromString("3b28ebee-3eeb-47b4-9d60-235d0e27ac5a"),form);
    }

    @GetMapping("/count/{userId}")
    public Iterable<Form> countUserPenalties(@PathVariable UUID userId ){
        return formService.countUserPenalties(userId, new Date());
    }

    @GetMapping("/export")
    public String export(){
        return excelExport.exportData("name", new Date());
    }



}
