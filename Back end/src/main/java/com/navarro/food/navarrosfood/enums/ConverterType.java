package com.navarro.food.navarrosfood.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConverterType implements AttributeConverter<Type, Character> {


    @Override
    public Character convertToDatabaseColumn(Type attribute) {
        if(Objects.isNull(attribute)) return null;
        return attribute.getValue();
    }

    @Override
    public Type convertToEntityAttribute(Character value) {
        if(Objects.isNull(value)) return null;

        return Stream.of(Type.values())
                .filter(data -> data.getValue().equals(value))
                .findFirst().orElse(null);
    }
}
