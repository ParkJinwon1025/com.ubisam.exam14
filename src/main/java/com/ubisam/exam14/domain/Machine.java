package com.ubisam.exam14.domain;

// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "example_machine")
public class Machine {

    @Id
    @GeneratedValue
    private Long id;

    private String machineName;
    private Integer machineType;
    private String machineCountry;

    // 하나의 장비(개념/모델)는 여러 공장에서 사용될 수 있음
    // @ManyToMany
    // private List<Factory> factories;
}
