package com.knb.tools.jdbc_mapper;

import com.knb.tools.jdbc_mapper.model.Result;
import com.knb.tools.jdbc_mapper.util.UtilFunc;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassMapperTest {

    @Test
    void shouldGenerateMapSuccessfully() {
        Result result = UtilFunc.getResult();
        Map<String, Object> value = ClassMapper.toMap(result, Result.class);

        assertEquals(result.getStrValue(), value.get("STR_VALUE"));
        assertEquals(result.getNullValue(), value.get("NULL_VALUE"));
        assertEquals(result.getIntValue(), value.get("INT_VALUE"));
        assertEquals(result.getLongValue(), value.get("LONG_VALUE"));
        assertEquals(result.getShortValue(), value.get("SHORT_VALUE"));
        assertEquals(result.getFloatValue(), value.get("FLOAT_VALUE"));
        assertEquals(result.getDoubleValue(), value.get("DOUBLE_VALUE"));
        assertEquals(result.isBoolValue(), value.get("BOOL_VALUE"));
        assertEquals(result.getCharValue(), value.get("CHAR_VALUE"));
        assertEquals(result.getDateValue(), value.get("DATE_VALUE"));
        assertEquals(result.getByteValue(), value.get("BYTE_VALUE"));
    }

    @Test
    void shouldGenerateMapSuccessfullyByVariable() {
        Result result = UtilFunc.getResult();
        Map<String, Object> value = ClassMapper.toMap(result);

        assertEquals(result.getStrValue(), value.get("strValue"));
        assertEquals(result.getNullValue(), value.get("nullValue"));
        assertEquals(result.getIntValue(), value.get("intValue"));
        assertEquals(result.getLongValue(), value.get("longValue"));
        assertEquals(result.getShortValue(), value.get("shortValue"));
        assertEquals(result.getFloatValue(), value.get("floatValue"));
        assertEquals(result.getDoubleValue(), value.get("doubleValue"));
        assertEquals(result.isBoolValue(), value.get("boolValue"));
        assertEquals(result.getCharValue(), value.get("charValue"));
        assertEquals(result.getDateValue(), value.get("dateValue"));
        assertEquals(result.getByteValue(), value.get("byteValue"));
    }

    @Test
    void shouldGenerateMapSuccessfullyForList() {
        Result result = UtilFunc.getResult();
        List<Map<String, Object>> valueList = ClassMapper.toMap(List.of(result));

        Map<String, Object> value = valueList.getFirst();
        assertEquals(result.getStrValue(), value.get("strValue"));
        assertEquals(result.getNullValue(), value.get("nullValue"));
        assertEquals(result.getIntValue(), value.get("intValue"));
        assertEquals(result.getLongValue(), value.get("longValue"));
        assertEquals(result.getShortValue(), value.get("shortValue"));
        assertEquals(result.getFloatValue(), value.get("floatValue"));
        assertEquals(result.getDoubleValue(), value.get("doubleValue"));
        assertEquals(result.isBoolValue(), value.get("boolValue"));
        assertEquals(result.getCharValue(), value.get("charValue"));
        assertEquals(result.getDateValue(), value.get("dateValue"));
        assertEquals(result.getByteValue(), value.get("byteValue"));
    }

}
