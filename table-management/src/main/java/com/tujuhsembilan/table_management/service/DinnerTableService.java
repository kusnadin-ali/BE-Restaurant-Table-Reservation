package com.tujuhsembilan.table_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tujuhsembilan.core.constant.ApiConstant.ResponseCode;
import com.tujuhsembilan.core.constant.ApiConstant.ResponseMessage;
import com.tujuhsembilan.core.dto.ResponseDto;
import com.tujuhsembilan.core.utils.ResponseUtil;
import com.tujuhsembilan.table_management.dto.DinnerTablePojo;
import com.tujuhsembilan.table_management.dto.DinnerTableRequest;
import com.tujuhsembilan.table_management.model.DinnerTable;
import com.tujuhsembilan.table_management.repository.DinnerTableRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DinnerTableService {
    
    private final DinnerTableRepository dinnerTableRepository;

    public ResponseDto<Object> getAllDinnerTable() {
        List<DinnerTablePojo> dinnerTables = dinnerTableRepository.getAll();
        return ResponseUtil.success(dinnerTables);
    }

    public ResponseDto<Object> addNewDinnertable(DinnerTableRequest request){
        Optional<DinnerTable> dinnerTableExist = dinnerTableRepository.findByTableId(request.getTableId().toUpperCase());
        if(dinnerTableExist.isPresent()){
            return ResponseUtil.error(null,"02","Table already exist");
        }

        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.setTableId(request.getTableId().toUpperCase());
        dinnerTable.setChairAmount(request.getChairAmount());
        dinnerTableRepository.save(dinnerTable);

        return ResponseUtil.success(dinnerTable, ResponseMessage.SUCCESS_CREATE_DATA);
    }

    public ResponseDto<Object> getDetailDinnerTable(String tableId) {
        Optional<DinnerTablePojo> dinnerTable = dinnerTableRepository.getOneByTableId(tableId.toUpperCase());
        if(!dinnerTable.isPresent()){
            return ResponseUtil.error(null, "02", "Table not found");
        }

        return ResponseUtil.success(dinnerTable.get());
    }

    public ResponseDto<Object> updateDetailDinnerTable(DinnerTableRequest request) {
        Optional<DinnerTable> dinnerTableExist = dinnerTableRepository.findByTableId(request.getTableId().toUpperCase());
        if(!dinnerTableExist.isPresent()){
            return ResponseUtil.error(null, ResponseCode.ERROR_CODE, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
        }

        DinnerTable dinnerTableEdit = dinnerTableExist.get();
        dinnerTableEdit.setTableId(request.getTableId().toUpperCase());
        dinnerTableEdit.setChairAmount(request.getChairAmount());
        dinnerTableRepository.save(dinnerTableEdit);

        return ResponseUtil.success(dinnerTableEdit, ResponseMessage.SUCCESS_UPDATE_DATA);
    }

    public ResponseDto<Object> deleteDinnerTable(String tableId) {
        Optional<DinnerTable> dinnerTableExist = dinnerTableRepository.findByTableId(tableId.toUpperCase());
        if(!dinnerTableExist.isPresent()){
            return ResponseUtil.error(null, ResponseCode.ERROR_CODE, ResponseMessage.ERROR_DATA_DOESNT_EXIST);
        }

        dinnerTableRepository.delete(dinnerTableExist.get());

        return ResponseUtil.success(null, ResponseMessage.SUCCESS_REMOVE_DATA);
    }
}
