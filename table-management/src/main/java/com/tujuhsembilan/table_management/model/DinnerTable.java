package com.tujuhsembilan.table_management.model;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dinner_table", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DinnerTable implements Serializable{
    
    @Serial
    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "table_id", length = 3)
    private String tableId;

    @Column(name = "chair_amount", columnDefinition = "SMALLINT")
    private Integer chairAmount;
}
