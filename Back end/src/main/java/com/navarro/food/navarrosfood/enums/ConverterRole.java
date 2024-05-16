package com.navarro.food.navarrosfood.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConverterRole implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole role) {
        if(Objects.isNull(role)) return null;
        return role.getRole();
    }

    @Override
    public UserRole convertToEntityAttribute(String value) {
        if(Objects.isNull(value)) return null;

        return Stream.of(UserRole.values())
                .filter(data -> data.getRole().equals(value))
                .findFirst()
                .orElse(null);
    }
}
