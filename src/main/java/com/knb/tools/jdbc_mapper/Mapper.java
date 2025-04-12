package com.knb.tools.jdbc_mapper;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.knb.tools.jdbc_mapper.annotation.CustomMapper;
import com.knb.tools.jdbc_mapper.annotation.FieldId;
import com.knb.tools.jdbc_mapper.exception.CustomMapperCallFailed;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
public class Mapper {
    private Mapper() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Field fld, Object value) {

        if (Objects.isNull(value)) {
            return null;
        }

        Object returnValue = customMapper(fld, value);

        if (!Objects.isNull(returnValue)) {
            return (T) returnValue;
        }

        if (fld.getType().getTypeName().equals(String.class.getTypeName())) {
            return (T) asString(value);
        }

        if (fld.getType().getTypeName().equals(Integer.class.getTypeName()) ||
                fld.getType().getTypeName().equals("int")) {
            return (T) asInteger(value);
        }

        if (fld.getType().getTypeName().equals(Long.class.getTypeName()) ||
                fld.getType().getTypeName().equals("long")) {
            return (T) asLong(value);
        }

        if (fld.getType().getTypeName().equals(Short.class.getTypeName()) ||
                fld.getType().getTypeName().equals("short")) {
            return (T) asShort(value);
        }

        if (fld.getType().getTypeName().equals(Boolean.class.getTypeName()) ||
                fld.getType().getTypeName().equals("boolean")) {
            return (T) asBoolean(value);
        }

        if (fld.getType().getTypeName().equals(Float.class.getTypeName()) ||
                fld.getType().getTypeName().equals("float")) {
            return (T) asFloat(value);
        }

        if (fld.getType().getTypeName().equals(Double.class.getTypeName()) ||
                fld.getType().getTypeName().equals("double")) {
            return (T) asDouble(value);
        }

        if (fld.getType().getTypeName().equals(Character.class.getTypeName()) ||
                fld.getType().getTypeName().equals("char")) {
            return (T) asChar(value);
        }

        return (T) value;
    }

    private static String asString(Object value) {
        return value.toString();
    }

    private static Integer asInteger(Object value) {
        return Ints.tryParse(value.toString());
    }

    private static Long asLong(Object value) {
        return Longs.tryParse(value.toString());
    }

    private static Short asShort(Object value) {
        try {
            return Short.parseShort(value.toString());
        } catch (Exception e) {
            log.error("Parsing value {} as SHORT failed because {}", value, e.getMessage());
            return 0;
        }
    }

    private static Boolean asBoolean(Object value) {
        return Boolean.valueOf(value.toString());
    }

    private static Float asFloat(Object value) {
        return Floats.tryParse(value.toString());
    }

    private static Double asDouble(Object value) {
        return Doubles.tryParse(value.toString());
    }

    private static Character asChar(Object value) {
        return value.toString().charAt(0);
    }

    private static Object customMapper(Field fld, Object value) {
        try {
            if (fld.getAnnotation(FieldId.class).customMapper().className().isEmpty() &&
                    fld.getAnnotation(FieldId.class).customMapper().methodName().isEmpty()
            ) {
                return null;
            }

            CustomMapper cm = fld.getAnnotation(FieldId.class).customMapper();

            Class<?> c = Class.forName(cm.className());
            String methodName = cm.methodName();
            Method method = c.getDeclaredMethod(methodName, Object.class);

            if (!cm.isStatic()) {
                Object cls = c.getDeclaredConstructor().newInstance();
                return method.invoke(cls, value);
            } else {
                return method.invoke(null, value);
            }
        } catch (Exception e) {
            throw new CustomMapperCallFailed(e);
        }
    }
}
