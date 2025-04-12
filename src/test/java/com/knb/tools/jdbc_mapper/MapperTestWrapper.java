package com.knb.tools.jdbc_mapper;

import com.knb.tools.jdbc_mapper.model.ResultWrapped;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperWrapperTest {

    @Test
    void shouldGenerateStringSuccessfully() throws NoSuchFieldException {
        String value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("strValue"), "This is normal String");
        assertEquals("This is normal String", value);
    }

    @Test
    void shouldGenerateIntSuccessfully() throws NoSuchFieldException {
        Integer value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("intValue"), Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, value);
    }

    @Test
    void shouldGenerateLongSuccessfully() throws NoSuchFieldException {
        Long value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("longValue"), Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, value);
    }

    @Test
    void shouldGenerateShortSuccessfully() throws NoSuchFieldException {
        Short value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("shortValue"), 123);
        assertEquals((short) 123, value);
    }

    @Test
    void shouldGenerateShortFailed() throws NoSuchFieldException {
        Short value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("shortValue"), Short.MAX_VALUE + 1);
        assertEquals((short) 0, value);
    }

    @Test
    void shouldGenerateBooleanSuccessfully() throws NoSuchFieldException {
        Boolean value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("boolValue"), true);
        assertEquals(Boolean.TRUE, value);
    }

    @Test
    void shouldGenerateBooleanFailed() throws NoSuchFieldException {
        Boolean value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("boolValue"), "trueAndFalse");
        assertEquals(Boolean.FALSE, value);
    }

    @Test
    void shouldGenerateFloatSuccessfully() throws NoSuchFieldException {
        Float value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("floatValue"), Float.MAX_VALUE);
        assertEquals(Float.MAX_VALUE, value);
    }

    @Test
    void shouldGenerateDoubleSuccessfully() throws NoSuchFieldException {
        Double value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("doubleValue"), Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, value);
    }

    @Test
    void shouldGenerateCharSuccessfully() throws NoSuchFieldException {
        Character value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("charValue"), Character.MAX_VALUE);
        assertEquals(Character.MAX_VALUE, value);
    }

    @Test
    void shouldGenerateCharGetFirstChar() throws NoSuchFieldException {
        Character value = Mapper.getFieldValue(ResultWrapped.class.getDeclaredField("charValue"), 1234567899);
        assertEquals('1', value);
    }


}