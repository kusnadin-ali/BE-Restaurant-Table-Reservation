package com.tujuhsembilan.table_management.controller;

import java.util.Enumeration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;

import com.tujuhsembilan.table_management.service.DinnerTableService;

import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/dinner-table")
@RequiredArgsConstructor
@Slf4j
public class DinnerTableController {

    private final DinnerTableService dinnerTableService;
    
    @GetMapping()
    public ResponseEntity<?> getAllDinnerTable(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        String headerValue = request.getHeader(headerName);
        log.info("Header {}: {}", headerName, headerValue); // Log nama dan nilai
    }
        return dinnerTableService.getAllDinnerTable();
    }
}
