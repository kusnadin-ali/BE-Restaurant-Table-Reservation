package com.tujuhsembilan.table_management.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.tujuhsembilan.core.utils.ResponseUtil;
import com.tujuhsembilan.table_management.dto.DinnerTablePojo;
import com.tujuhsembilan.table_management.service.DinnerTableService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class DinnerTableControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DinnerTableService dinnerTableService;

    @Test
    public void givenDinnerTable_whenGetAllDinnerTable_thenStatus200() throws Exception {
        log.info("tests1");
        List<DinnerTablePojo> responseDinnerTable = new ArrayList<DinnerTablePojo>();
        responseDinnerTable.add(new DinnerTablePojo("A1", 4));
        responseDinnerTable.add(new DinnerTablePojo("B1", 5));
        ResponseEntity<?> mockResponse = ResponseUtil.success(responseDinnerTable);
        // Mocking service
        Mockito.doReturn(mockResponse).when(dinnerTableService).getAllDinnerTable();

        // Lakukan request ke endpoint dan verifikasi hasilnya
        mockMvc.perform(MockMvcRequestBuilders.get("/dinner-table")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                // Verifikasi struktur response, misal: response dikemas dalam field "result"
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.length()").value(2))
                // Verifikasi data pertama
            .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].tableId").value("A1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result[0].chairAmount").value(4))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result[1].tableId").value("B1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result[1].chairAmount").value(5))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("00"));

        // Verifikasi bahwa service dipanggil sekali
        Mockito.verify(dinnerTableService, Mockito.times(1)).getAllDinnerTable();
    }
}
