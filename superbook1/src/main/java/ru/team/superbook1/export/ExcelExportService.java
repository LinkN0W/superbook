package ru.team.superbook1.export;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class ExcelExportService {


    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FormRepository formRepository;

    @Autowired
    private final BookRepository bookRepository;

    private CountFunctional countFunctional;


    private final XSSFWorkbook wb;
    private final XSSFSheet sheet;

    String[] nameColumns = {
        "Почта пользователя","Название книги","Дата приобретения", "Дата возврата","Пени", "Дни просрочки"
    };



    public ExcelExportService(UserRepository userRepository, FormRepository formRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.formRepository = formRepository;
        this.bookRepository = bookRepository;
        this.wb = new XSSFWorkbook();
        this.sheet = wb.createSheet();
    }


    public String exportData(String name, Date dateOfBegin, Date dateOfEnd){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(name);

        List<Form> forms = (List<Form>) formRepository.findAllByDatePeriod(dateOfBegin, dateOfEnd);

        countFunctional = new FormFunctional();
        List<Form> newFrom = countFunctional.countUserPenaltiesForPeriod(forms,dateOfBegin, dateOfEnd);

        createTitle();
        int rowNum = 0;
        for(Form form : newFrom) createRow(rowNum++,form);
        try {
            writeWorkbook();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "ok";
    }

    private void createTitle() {
        XSSFRow rowTitle = sheet.createRow(0);
        setCellValue(rowTitle.createCell(0), "Почта пользователя");
        setCellValue(rowTitle.createCell(1), "Название книги");
        setCellValue(rowTitle.createCell(2), "Дата приобретения");
        setCellValue(rowTitle.createCell(3), "Дата возврата");
        setCellValue(rowTitle.createCell(4), "Пени");
        setCellValue(rowTitle.createCell(5), "Дни просрочки");
    }


    public void createRow(int index, Form form) {
        XSSFRow row = sheet.createRow(index);
        User user = userRepository.findById(form.getUserId()).get();
        Book book = bookRepository.findById(form.getBookId()).get();
        setCellValue(row.createCell(0), user.getEmail());
        setCellValue(row.createCell(1), book.getTitle());
        setCellValue(row.createCell(2), form.getDateOfTaking().toString());
        if(form.getDateOfReturning() != null)
            setCellValue(row.createCell(3), form.getDateOfReturning().toString());
        else setCellValue(row.createCell(3), "");
        setCellValue(row.createCell(4), form.getPenalties());
        setCellValue(row.createCell(5), form.getDelay());
    }


    private void setCellValue(XSSFCell cell, String value) {
        cell.setCellValue(value);
    }

    private void setCellValue(XSSFCell cell, int value) {
        cell.setCellValue(value);
    }

    public void writeWorkbook() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(Instant.now().getEpochSecond() + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
    }


    }

