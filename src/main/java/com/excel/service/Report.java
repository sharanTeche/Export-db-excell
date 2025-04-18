package com.excel.service;

import com.excel.entity.Employee;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.stream.IntStream;

public interface Report {

    public void generateReport(HttpServletResponse response) throws IOException;

    public String createReport(Employee emp);

    public String upload(InputStream inputStream) throws IOException;
}
