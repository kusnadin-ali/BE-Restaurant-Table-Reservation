package com.tujuhsembilan.table_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DinnerTablePojo {
    private String tableId;

    private Integer chairAmount;
}
