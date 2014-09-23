package com.dps.common.enums.jsonserializer;

import com.dps.common.enums.GenericEnum;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Customize the default JSON serializer to not only return the name, but also other attributes.
 */
public class GenericEnumSerializer extends JsonSerializer<GenericEnum> {

    private Logger logger = LoggerFactory.getLogger(GenericEnumSerializer.class);

    @Override
    public void serialize(GenericEnum genericEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        // output the custom Json
        jsonGenerator.writeStartObject();

        // the type
        jsonGenerator.writeFieldName("id");
        jsonGenerator.writeString(String.valueOf(genericEnum.getId()));

        // the full name
        jsonGenerator.writeFieldName("name");
        jsonGenerator.writeString(genericEnum.getName());

        // end tag
        jsonGenerator.writeEndObject();

    }
}
