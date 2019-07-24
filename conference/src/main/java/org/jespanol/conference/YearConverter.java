package org.jespanol.conference;

import jakarta.nosql.mapping.AttributeConverter;

import java.time.Year;

public class YearConverter implements AttributeConverter<Year, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Year attribute) {
        if (attribute != null) {
            return attribute.getValue();
        }
        return null;
    }

    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        if (dbData != null) {
            return Year.of(dbData);
        }
        return null;
    }

}
