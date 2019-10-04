package org.jespanol.conference;

import jakarta.nosql.mapping.AttributeConverter;
import org.bson.types.ObjectId;
import org.eclipse.jnosql.artemis.util.StringUtils;

public class ObjectIdConverter implements AttributeConverter<String, ObjectId> {

    @Override
    public ObjectId convertToDatabaseColumn(String attribute) {
        if (StringUtils.isNotBlank(attribute)) {
            return new ObjectId(attribute);
        }
        return null;
    }

    @Override
    public String convertToEntityAttribute(ObjectId dbData) {
        if (dbData != null) {
            return dbData.toHexString();
        }
        return null;
    }
}
