package com.tujuhsembilan.table_management.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tujuhsembilan.core.dto.ResponseDto;
import com.tujuhsembilan.table_management.dto.DinnerTableRequest;
import com.tujuhsembilan.table_management.service.DinnerTableService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/dinner-table")
@RequiredArgsConstructor
@Slf4j
public class DinnerTableController {

    private final DinnerTableService dinnerTableService;
    
    @GetMapping()
    public ResponseDto<Object> getAllDinnerTable(HttpServletRequest request) {
        return dinnerTableService.getAllDinnerTable();
    }

    @PostMapping()
    @PreAuthorize("@roleEvaluator.hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseDto<Object> addNewDinnerTable(@RequestBody DinnerTableRequest request) {
        return dinnerTableService.addNewDinnertable(request);
    }

    @GetMapping("/{tableId}")
    public ResponseDto<Object> getDetailDinnerTable(@PathVariable String tableId) {
        return dinnerTableService.getDetailDinnerTable(tableId);
    }
    
    @PutMapping()
    @PreAuthorize("@roleEvaluator.hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseDto<Object> updateDetailDinnerTable(@RequestBody DinnerTableRequest request) {
        return dinnerTableService.updateDetailDinnerTable(request);
    }

    @DeleteMapping("/{tableId}")
    @PreAuthorize("@roleEvaluator.hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    public ResponseDto<Object> deleteDinnerTable(@PathVariable String tableId) {
        return dinnerTableService.deleteDinnerTable(tableId);
    }
}
