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

        objList.forEach(o -> {
            Map<String, Object> map = new HashMap<>();
            Arrays.stream(cls.getDeclaredFields()).toList().forEach(fld -> {
                String fieldName = fld.getName();
                Object value = getValue(fld, o);
                map.put(fieldName, value);
            });
            mapList.add(map);
        });

        return mapList;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<Map<String, Object>> toMap(List<Object> objList) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        Class<T> cls = (Class<T>) objList.getFirst().getClass();

        objList.forEach(o -> {
            Map<String, Object> map = new HashMap<>();
            Arrays.stream(cls.getDeclaredFields()).toList().forEach(fld -> {
                if (!fld.isAnnotationPresent(FieldId.class))
                    return;
                String fieldId = fld.getAnnotation(FieldId.class).value();
                Object value = getValue(fld, o);
                map.put(fieldId, value);
            });
            mapList.add(map);
        });

        return mapList;
    }


    public static <T> Map<String, Object> toMap(Object obj) {

        Map<String, Object> map = new HashMap<>();

        Arrays.stream(obj.getClass().getDeclaredFields()).toList().forEach(fld -> {
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

}
