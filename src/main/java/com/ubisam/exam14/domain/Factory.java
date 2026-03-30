package com.ubisam.exam14.domain;

// import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "example_factory")
public class Factory {

    @Id
    @GeneratedValue
    private Long id;

    private String factoryName;
    private String factoryDescription;

    // 하나의 공장은 여러 장비 "모델"을 사용할 수 있음
    // 하나의 장비 "모델"은 여러 공장에서 공통으로 사용될 수 있음
    // @ManyToMany(mappedBy = "factories")
    // private List<Machine> machines;

    @Transient
    private String searchName;

    @Transient
    private String searchDescription;

}
