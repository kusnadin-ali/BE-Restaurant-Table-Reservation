package com.tujuhsembilan.table_management.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tujuhsembilan.table_management.dto.DinnerTablePojo;
import com.tujuhsembilan.table_management.repository.DinnerTableRepository;
import com.tujuhsembilan.table_management.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DinnerTableService {
    
    private final DinnerTableRepository dinnerTableRepository;

    public ResponseEntity<?> getAllDinnerTable() {
        // List<DinnerTable> dinnerTables = dinnerTableRepository.findAll();
        List<DinnerTablePojo> dinnerTables = dinnerTableRepository.getAll();
        return ResponseUtil.success(dinnerTables);
    }
}
