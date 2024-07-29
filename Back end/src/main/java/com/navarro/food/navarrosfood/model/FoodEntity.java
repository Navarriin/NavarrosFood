package com.navarro.food.navarrosfood.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.navarro.food.navarrosfood.enums.ConverterStatus;
import com.navarro.food.navarrosfood.enums.ConverterType;
import com.navarro.food.navarrosfood.enums.Status;
import com.navarro.food.navarrosfood.enums.Type;
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
    @Column(name = "food_id")
    private Long foodNumber;
    private String name;
    private String image;

    @Convert(converter = ConverterType.class)
    private Type type;

    @Column(name = "value_food")
    private BigDecimal value;

    @ManyToMany(mappedBy = "foods", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<UserEntity> users = new ArrayList<>();

    public FoodEntity(String name, String image, BigDecimal value, Type type) {
        this.name = name;
        this.image = image;
        this.value = value;
        this.type = type;
    }
}
