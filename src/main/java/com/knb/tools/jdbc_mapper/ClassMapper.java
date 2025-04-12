package com.knb.tools.jdbc_mapper;

import com.knb.tools.jdbc_mapper.annotation.FieldId;
import com.knb.tools.jdbc_mapper.exception.FieldReadingFailed;
import com.knb.tools.jdbc_mapper.exception.RowMapperFailed;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ClassMapper {
    private ClassMapper() {
    }

    public static <T> T fromMap(Map<String, Object> result, Class<T> classType) {
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
            field.set(newInstance, FieldMapper.getFieldValue(field, result.get(fieldId)));
        } catch (Exception e) {
            log.error("Mapping field {} failed because {}", field.getName(), e.getMessage());
        }
    }

    public static <T> Map<String, Object> toMap(Object obj, Class<T> classType) {

        Map<String, Object> map = new HashMap<>();

        Arrays.stream(classType.getDeclaredFields()).toList().forEach(fld -> {
            if (!fld.isAnnotationPresent(FieldId.class))
                return;
            String fieldId = fld.getAnnotation(FieldId.class).value();
            Object value = getValue(fld, obj);
            map.put(fieldId, value);
        });

        return map;
    }

    private static Object getValue(Field fld, Object obj) {
        try {
            fld.setAccessible(true);
            return fld.get(obj);
        } catch (Exception e) {
            log.error("Mapping field {} failed because {}", fld.getName(), e.getMessage());
            throw new FieldReadingFailed(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> toMap(Object obj) {

        Map<String, Object> map = new HashMap<>();

        Class<T> cls = (Class<T>) obj.getClass();

        Arrays.stream(cls.getDeclaredFields()).toList().forEach(fld -> {
            String fieldName = fld.getName();
            Object value = getValue(fld, obj);
            map.put(fieldName, value);
        });

        return map;
    }
}
