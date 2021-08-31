package com.altra.apps.schema.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class CustomUtils {

    public static final Predicate<String> isEmpty = x -> Objects.equals(x, "");
    public static final Predicate<String> isNull = Objects::isNull;

    private CustomUtils() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(
                "Do not instantiate this class, use statically.");
    }

    public static Boolean isEmptyOrNull(String value) {
        return isNull.test(value) || isEmpty.test(value);
    }

    public static Long getEpochCurrentTime() {
        return System.currentTimeMillis() / 1000; //Returns epoch in seconds.
    }

    public static String getUniqueId() {
        return UUID.randomUUID().toString().toUpperCase(Locale.ROOT).replace("-", "");
    }


    public static void ObjectToJson(Object obj) {
        //ObjectMapper mapper = new ObjectMapper();
        //RestTemplateConfiguration jacksonConfiguration = new RestTemplateConfiguration();
        final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        final ObjectMapper mapper = jackson2ObjectMapperBuilder.build();

        try {
            String jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);

            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}

