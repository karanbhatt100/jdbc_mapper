package io.github.karanbhatt100.tools.jdbc_mapper;

import io.github.karanbhatt100.tools.jdbc_mapper.annotation.FieldId;
import io.github.karanbhatt100.tools.jdbc_mapper.exception.InstanceCreationFailed;
import io.github.karanbhatt100.tools.jdbc_mapper.exception.RowMapperFailed;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class MapClasser {
    private MapClasser() {
    }

    public static <T> List<T> fromMap(List<Map<String, Object>> result, Class<T> classType) {
        try {
            List<Field> fldList = Arrays.stream(classType.getDeclaredFields())
                    .filter(f ->
                            f.isAnnotationPresent(FieldId.class)
                    ).toList();

            List<T> classInstanceList = new ArrayList<>();
            result.parallelStream().forEach(map -> createInstance(fldList, map, classType, false).ifPresent(classInstanceList::add));

            return classInstanceList;
        } catch (Exception e) {
            throw new RowMapperFailed(e);
        }
    }

    public static <T> List<T> fromMapViaVariable(List<Map<String, Object>> result, Class<T> classType) {
        try {
            List<Field> fldList = Arrays.stream(classType.getDeclaredFields()).toList();

            List<T> classInstanceList = new ArrayList<>();
            result.parallelStream().forEach(map -> createInstance(fldList, map, classType, true).ifPresent(classInstanceList::add));

            return classInstanceList;
        } catch (Exception e) {
            throw new RowMapperFailed(e);
        }
    }

    private static <T> Optional<T> createInstance(List<Field> fldList, Map<String, Object> map, Class<T> classType, boolean viaVariable) {
        try {
            T newIns = classType.getDeclaredConstructor().newInstance();
            for (Field fld : fldList) {
                mapColumn(fld, map, newIns, viaVariable);
            }
            return Optional.of(newIns);
        } catch (Exception e) {
            throw new InstanceCreationFailed(e);
        }
    }

    public static <T> T fromMapViaVariable(Map<String, Object> result, Class<T> classType) {
        try {
            T newIns = classType.getDeclaredConstructor().newInstance();

            Arrays.stream(classType.getDeclaredFields())
                    .toList().parallelStream().forEach(f -> mapColumn(f, result, newIns, true));

            return newIns;
        } catch (Exception e) {
            throw new RowMapperFailed(e);
        }
    }

    public static <T> T fromMap(Map<String, Object> result, Class<T> classType) {
        try {
            T newIns = classType.getDeclaredConstructor().newInstance();

            Arrays.stream(classType.getDeclaredFields())
                    .filter(f ->
                            f.isAnnotationPresent(FieldId.class)
                    ).toList().parallelStream().forEach(f -> mapColumn(f, result, newIns, false));

            return newIns;
        } catch (Exception e) {
            throw new RowMapperFailed(e);
        }
    }

    private static void mapColumn(Field field, Map<String, Object> result, Object newInstance, boolean viaVariable) {
        try {
            String fieldId;

            if (viaVariable)
                fieldId = field.getName();
            else
                fieldId = field.getAnnotation(FieldId.class).value();

            if (!result.containsKey(fieldId)) {
                return;
            }

            field.setAccessible(true);
            field.set(newInstance, FieldMapper.getFieldValue(field, result.get(fieldId)));
        } catch (Exception e) {
            log.error("Mapping field {} failed because {}", field.getName(), e.getMessage());
        }
    }
}
