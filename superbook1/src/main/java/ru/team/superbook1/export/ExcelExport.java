package ru.team.superbook1.export;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.superbook1.entities.Book;
import ru.team.superbook1.entities.Form;
import ru.team.superbook1.entities.User;
import ru.team.superbook1.repositories.BookRepository;
import ru.team.superbook1.repositories.FormRepository;
import ru.team.superbook1.repositories.UserRepository;
import ru.team.superbook1.services.FormService;

import java.util.Date;
import java.util.List;


@Service
public class ExcelExport {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final BookRepository bookRepository;



    String[] nameColumns = {
        "Почта пользователя","Название книги","Дата приобретения", "Дата возврата","Пени", "Дни просрочки"
    };



    public ExcelExport(UserRepository userRepository, FormRepository formRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.formRepository = formRepository;
        this.bookRepository = bookRepository;
    }


    public String exportData(String name, Date date){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);
        int rowNum = 0;
        List<Form> forms = (List<Form>) formRepository.findAll();



        return "ok";
    }





    }

