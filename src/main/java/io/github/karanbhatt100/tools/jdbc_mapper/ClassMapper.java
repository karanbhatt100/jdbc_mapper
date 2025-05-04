package io.github.karanbhatt100.tools.jdbc_mapper;

import io.github.karanbhatt100.tools.jdbc_mapper.annotation.FieldId;
import io.github.karanbhatt100.tools.jdbc_mapper.exception.FieldReadingFailed;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class ClassMapper {

    private ClassMapper() {}

    @SuppressWarnings("unchecked")
    public static <T> Map<String, Object> toMapViaVariable(Object obj) {

        Map<String, Object> map = new HashMap<>();

        Class<T> cls = (Class<T>) obj.getClass();

        Arrays.stream(cls.getDeclaredFields()).toList().forEach(fld -> {
            String fieldName = fld.getName();
            Object value = getValue(fld, obj);
            map.put(fieldName, value);
        });

        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Map<String, Object>> toMapViaVariable(List<Object> objList) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Class<T> cls = (Class<T>) objList.getFirst().getClass();
        List<Field> fldList = Arrays.stream(cls.getDeclaredFields()).toList();

        objList.forEach(o -> {
            Map<String, Object> map = new HashMap<>();
            fldList.forEach(fld -> {
                String fieldName = fld.getName();
                Object value = getValue(fld, o);
                map.put(fieldName, value);
            });
            mapList.add(map);
        });

        return mapList;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Map<String, Object>> toMap(List<T> objList) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Class<T> cls = (Class<T>) objList.getFirst().getClass();
        List<Field> fieldList = Arrays.stream(cls.getDeclaredFields()).toList().stream().filter(f -> f.isAnnotationPresent(FieldId.class)).toList();

        objList.forEach(o -> {
            Map<String, Object> map = new HashMap<>();
            fieldList.forEach(fld -> {
                String fieldId = fld.getAnnotation(FieldId.class).value();
                Object value = getValue(fld, o);
                map.put(fieldId, value);
            });
            mapList.add(map);
        });

        return mapList;
    }


    public static Map<String, Object> toMap(Object obj) {

        Map<String, Object> map = new HashMap<>();
        List<Field> fieldList = Arrays.stream(obj.getClass().getDeclaredFields()).toList().stream().filter(f -> f.isAnnotationPresent(FieldId.class)).toList();

        fieldList.forEach(fld -> {
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

}
