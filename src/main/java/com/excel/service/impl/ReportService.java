package com.excel.service.impl;

import com.excel.entity.Employee;
import com.excel.repository.ReportRepository;
import com.excel.service.Report;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
@Service
public class ReportService implements Report {

    @Autowired
    ReportRepository reportRepository;
    @Override
    public void generateReport(HttpServletResponse response) throws IOException {

        List<Employee> list = reportRepository.findAll();


//    Create a excel file ie workBook
        HSSFWorkbook workBook = new HSSFWorkbook();
        //  create sheet with name .
        HSSFSheet sheet = workBook.createSheet("Employee-Details");

        //create row start with 0.
        HSSFRow row = sheet.createRow(0);

        //create cell with 0 and set Header value
        row.createCell(0).setCellValue("employeeId");
        row.createCell(1).setCellValue("FirstName");
        row.createCell(2).setCellValue("LastName");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("Phone");
        row.createCell(5).setCellValue("Salary");
        row.createCell(6).setCellValue("Department");



        int rowIndex = 1;
        for(Employee emp : list) {
            HSSFRow dataRow = sheet.createRow(rowIndex);
            dataRow.createCell(0).setCellValue(emp.getEmployeeId());
            dataRow.createCell(1).setCellValue(emp.getFirstName());
            dataRow.createCell(2).setCellValue(emp.getLastName());
            dataRow.createCell(3).setCellValue(emp.getEmail());
            dataRow.createCell(4).setCellValue(emp.getPhone());
            dataRow.createCell(5).setCellValue(emp.getSalary());
            dataRow.createCell(6).setCellValue(emp.getDepartment());


            rowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workBook.write(ops);
        ops.close();

    }

    @Override
    public String createReport(Employee emp) {
        reportRepository.save(emp);
        return "Sucessfully created";
    }

    @Override
    public String upload(InputStream inputStream) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<Employee> employees = new ArrayList<>();
        for (int i =1; i<= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Employee emp = new Employee();
           /* emp.setEmployeeId(row.getCell(0).getRowIndex());*/
            emp.setFirstName(row.getCell(1).getStringCellValue());
            emp.setLastName(row.getCell(2).getStringCellValue());
            emp.setEmail(row.getCell(3).getStringCellValue());
            emp.setPhone(row.getCell(4).getStringCellValue());
            emp.setSalary(row.getCell(5).getNumericCellValue());
            emp.setDepartment(row.getCell(6).getStringCellValue());
            employees.add(emp);
        }

        reportRepository.saveAll(employees);
        return "Sucessfully saved data into db...";
    }
}
