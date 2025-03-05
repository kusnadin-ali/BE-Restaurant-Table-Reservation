package com.tujuhsembilan.table_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.table_management.dto.DinnerTablePojo;
import com.tujuhsembilan.table_management.model.DinnerTable;

@Repository
public interface DinnerTableRepository extends JpaRepository<DinnerTable, Long> {
    
    List<DinnerTable> findAll();

    @Query(value = "SELECT dt.table_id as tableId, dt.chair_amount as chairAmount FROM public.dinner_table dt", nativeQuery = true)
    List<DinnerTablePojo> getAll();

    @Query(value = "SELECT dt.table_id as tableId, dt.chair_amount as chairAmount FROM public.dinner_table dt WHERE UPPER(dt.table_id) = UPPER(:tableId)", nativeQuery = true)
    Optional<DinnerTablePojo> getOneByTableId(String tableId);

    Optional<DinnerTable> findByTableId(String tableId);
}
