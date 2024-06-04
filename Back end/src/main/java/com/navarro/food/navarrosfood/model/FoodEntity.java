package com.navarro.food.navarrosfood.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.navarro.food.navarrosfood.enums.ConverterStatus;
import com.navarro.food.navarrosfood.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "foods")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long foodNumber;
    private String name;
    private String description;
    private String image;

    @Column(name = "value_food")
    private BigDecimal value;

    @Convert(converter = ConverterStatus.class)
    private Status status = Status.ACTIVE;

    @ManyToMany(mappedBy = "foods", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserEntity> users = new ArrayList<>();

    public FoodEntity(String name, String description, String image, BigDecimal value) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.value = value;
    }
}
