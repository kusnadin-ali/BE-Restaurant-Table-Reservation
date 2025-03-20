package com.tujuhsembilan.table_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DinnerTableRequest {
    private String tableId;

    private Integer chairAmount;
}
