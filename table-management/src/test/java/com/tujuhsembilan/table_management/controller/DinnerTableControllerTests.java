package com.tujuhsembilan.table_management.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.core.constant.ApiConstant.ResponseMessage;
import com.tujuhsembilan.core.dto.ResponseDto;
import com.tujuhsembilan.core.utils.ResponseUtil;
import com.tujuhsembilan.table_management.dto.DinnerTablePojo;
import com.tujuhsembilan.table_management.dto.DinnerTableRequest;
import com.tujuhsembilan.table_management.model.DinnerTable;
import com.tujuhsembilan.table_management.service.DinnerTableService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(DinnerTableController.class )
public class DinnerTableControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DinnerTableService dinnerTableService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenDinnerTable_whenGetAllDinnerTable_thenStatus200() throws Exception {
        List<DinnerTablePojo> responseDinnerTable = new ArrayList<DinnerTablePojo>();
        responseDinnerTable.add(new DinnerTablePojo("A1", 4));
        responseDinnerTable.add(new DinnerTablePojo("B1", 5));
        ResponseDto<Object> mockResponse = ResponseUtil.success(responseDinnerTable);

        when(dinnerTableService.getAllDinnerTable()).thenReturn(mockResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dinner-table")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = objectMapper.writeValueAsString(mockResponse);

        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void givenDetailDinnerTableTest() throws Exception {
        DinnerTablePojo dinnerTable = new DinnerTablePojo("A1", 4);

        when(dinnerTableService.getDetailDinnerTable("A1")).thenReturn(ResponseUtil.success(dinnerTable));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/dinner-table/A1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = objectMapper.writeValueAsString(ResponseUtil.success(dinnerTable));

        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void createDinnerTableTest() throws Exception {
        DinnerTableRequest request = new DinnerTableRequest("A1", 4);
        DinnerTable newDinnerTable = new DinnerTable(null, request.getTableId().toUpperCase(), request.getChairAmount());

        ResponseDto<Object> mockResponse = ResponseUtil.success(newDinnerTable,  ResponseMessage.SUCCESS_CREATE_DATA);
        when(dinnerTableService.addNewDinnertable(request)).thenReturn(ResponseUtil.success(newDinnerTable,  ResponseMessage.SUCCESS_CREATE_DATA));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/dinner-table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("x-role", "ROLE_ADMIN")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = objectMapper.writeValueAsString(mockResponse);

        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void updateDinnerTableTest() throws Exception {
        DinnerTableRequest request = new DinnerTableRequest("A1", 4);
        DinnerTable newDinnerTable = new DinnerTable(Long.valueOf(1), request.getTableId().toUpperCase(), request.getChairAmount());

        ResponseDto<Object> mockResponse = ResponseUtil.success(newDinnerTable,  ResponseMessage.SUCCESS_UPDATE_DATA);

        when(dinnerTableService.updateDetailDinnerTable(request)).thenReturn(mockResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/dinner-table")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("x-role", "ROLE_ADMIN")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = objectMapper.writeValueAsString(mockResponse);

        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void deleteDinnerTableTest() throws Exception {

        ResponseDto<Object> mockResponse = ResponseUtil.success(null, ResponseMessage.SUCCESS_REMOVE_DATA);

        when(dinnerTableService.deleteDinnerTable("A1")).thenReturn(mockResponse);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/dinner-table/A1")
                .header("x-role", "ROLE_ADMIN")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedJson = objectMapper.writeValueAsString(mockResponse);

        JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
    }
}
