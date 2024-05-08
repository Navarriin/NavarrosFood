package com.navarro.food.navarrosfood.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodNumber;
    private String name;
    private String description;
    private String image;
    private BigDecimal value;

    public FoodEntity(String name, String description, String image, BigDecimal value) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.value = value;
    }
}
