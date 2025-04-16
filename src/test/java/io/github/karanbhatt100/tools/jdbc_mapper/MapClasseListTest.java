package io.github.karanbhatt100.tools.jdbc_mapper;

import io.github.karanbhatt100.tools.jdbc_mapper.model.Result;
import io.github.karanbhatt100.tools.jdbc_mapper.util.UtilFunc;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapClasseListTest {

    @Test
    void shouldMapValuesSuccessfully() {
        Map<String, Object> result = UtilFunc.getMapWithFieldId();

        List<Result> mapResultList = MapClasser.fromMap(List.of(result), Result.class);
        Result mapResult = mapResultList.getFirst();

        assertEquals(result.get("NULL_VALUE"), mapResult.getNullValue());
        assertNull(mapResult.getNullValue());
        assertEquals(result.get("STR_VALUE"), mapResult.getStrValue());

        assertEquals(result.get("BYTE_VALUE"), mapResult.getByteValue());
        assertEquals(result.get("SHORT_VALUE"), mapResult.getShortValue());
        assertEquals(result.get("INT_VALUE"), mapResult.getIntValue());
        assertEquals(result.get("LONG_VALUE"), mapResult.getLongValue());

        assertEquals(result.get("FLOAT_VALUE"), mapResult.getFloatValue());
        assertEquals(result.get("DOUBLE_VALUE"), mapResult.getDoubleValue());

        assertEquals(result.get("BOOL_VALUE"), mapResult.isBoolValue());
        assertEquals(result.get("CHAR_VALUE"), mapResult.getCharValue());
        assertEquals(result.get("DATE_VALUE"), mapResult.getDateValue());

        assertEquals("This is custom static mapper. Which is mapping value give from Argument that is  = " + result.get("CUSTOM_VALUE_STATIC"), mapResult.getCustomStaticValue());
        assertEquals("This is custom mapper. Which is mapping value give from Argument that is  = " + result.get("CUSTOM_VALUE"), mapResult.getCustomValue());
    }

    @Test
    void shouldMapValuesSuccessfullyByVariable() {
        Map<String, Object> result = UtilFunc.getMapWithVariableName();

        List<Result> mapResultList = MapClasser.fromMapViaVariable(List.of(result), Result.class);
        Result mapResult = mapResultList.getFirst();

        assertEquals(result.get("nullValue"), mapResult.getNullValue());
        assertNull(mapResult.getNullValue());
        assertEquals(result.get("strValue"), mapResult.getStrValue());

        assertEquals(result.get("byteValue"), mapResult.getByteValue());
        assertEquals(result.get("shortValue"), mapResult.getShortValue());
        assertEquals(result.get("intValue"), mapResult.getIntValue());
        assertEquals(result.get("longValue"), mapResult.getLongValue());

        assertEquals(result.get("floatValue"), mapResult.getFloatValue());
        assertEquals(result.get("doubleValue"), mapResult.getDoubleValue());

        assertEquals(result.get("boolValue"), mapResult.isBoolValue());
        assertEquals(result.get("charValue"), mapResult.getCharValue());
        assertEquals(result.get("dateValue"), mapResult.getDateValue());
    }
}
