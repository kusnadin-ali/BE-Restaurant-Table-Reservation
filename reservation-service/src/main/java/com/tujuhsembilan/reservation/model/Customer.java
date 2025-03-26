package com.tujuhsembilan.reservation.model;

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
@Table(name = "customer", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable{
    
    @Serial
    private static final long serialVersionUID = -1L;

    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String username;

    @Column(name = "is_delete")
    private Boolean isDelete;
}
