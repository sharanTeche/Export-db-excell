package com.excel.controller;

import com.excel.entity.Employee;
import com.excel.service.Report;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.http.HttpResponse;

@RestController
public class ReportController {

    @Autowired
    private Report report;

    /*
    insert data into db
    * */

    @PostMapping("create")
    public String createReport(@RequestBody Employee emp) {

       return  report.createReport(emp);
    }
    /*
         Export db data to Excell file
    * */

    @GetMapping("excell")
    public void generateExcellReport(HttpServletResponse response) throws IOException {
        response.setContentType("applicatin/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue  = "attachement;filename=employee.xls";

        response.setHeader(headerKey, headerValue);

        report.generateReport(response);

    }

    /*
          Export Excell file data to db
    * */

    @PostMapping("upload")
    public String uploadExcellData(@RequestParam("file") MultipartFile file) throws IOException {
       return report.upload(file.getInputStream());
    }
}
