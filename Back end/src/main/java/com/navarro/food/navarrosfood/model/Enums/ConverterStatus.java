package com.navarro.food.navarrosfood.model.Enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConverterStatus implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if(Objects.isNull(status)) return null;
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if(Objects.isNull(value)) return null;

        return Stream.of(Status.values())
                .filter(data -> data.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
