package com.knb.tools.jdbc_mapper;

import com.knb.tools.jdbc_mapper.annotation.FieldId;
import com.knb.tools.jdbc_mapper.exception.RowMapperFailed;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class RowMapper {
    private RowMapper() {
    }

    public static <T> T rowMapper(Map<String, Object> result, Class<T> classType) {
        try {
            T newIns = classType.getDeclaredConstructor().newInstance();

            Arrays.stream(classType.getDeclaredFields())
                    .filter(f ->
                            f.isAnnotationPresent(FieldId.class)
                    ).toList().parallelStream().forEach(f -> mapColumn(f, result, newIns));

            return newIns;
        } catch (Exception e) {
            throw new RowMapperFailed(e);
        }
    }

    private static void mapColumn(Field field, Map<String, Object> result, Object newInstance) {
        try {

            String fieldId = field.getAnnotation(FieldId.class).value();

            if (!result.containsKey(fieldId)) {
                return;
            }

            field.setAccessible(true);
            field.set(newInstance, Mapper.getFieldValue(field, result.get(fieldId)));
        } catch (Exception e) {
            log.error("Mapping field {} failed because {}", field.getName(), e.getMessage());
        }
    }
}
