package com.knb.tools.jdbc_mapper.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilFunc {

    public static Map<String, Object> getMapWithFieldId() {
        Map<String, Object> result = new HashMap<>();
        result.put("STR_VALUE", "stringValue");
        result.put("NULL_VALUE", null);

        result.put("INT_VALUE", Integer.MAX_VALUE);
        result.put("LONG_VALUE", Long.MAX_VALUE);
        result.put("SHORT_VALUE", Short.MAX_VALUE);

        result.put("FLOAT_VALUE", Float.MAX_VALUE);
        result.put("DOUBLE_VALUE", Double.MAX_VALUE);

        result.put("BOOL_VALUE", true);
        result.put("CHAR_VALUE", Character.MAX_VALUE);
        result.put("DATE_VALUE", new Date());
        result.put("BYTE_VALUE", Byte.MAX_VALUE);
        result.put("WRONG_VALUE", Byte.MAX_VALUE);
        result.put("CUSTOM_VALUE","Custom Value");
        result.put("CUSTOM_VALUE_STATIC","Custom Value Static");

        return result;
    }

    public static Map<String, Object> getMapWithVariableName() {
        Map<String, Object> result = new HashMap<>();
        result.put("strValue", "stringValue");

        result.put("intValue", Integer.MAX_VALUE);
        result.put("longValue", Long.MAX_VALUE);
        result.put("shortValue", Short.MAX_VALUE);

        result.put("floatValue", Float.MAX_VALUE);
        result.put("doubleValue", Double.MAX_VALUE);

        result.put("boolValue", true);
        result.put("charValue", Character.MAX_VALUE);
        result.put("dateValue", new Date());
        result.put("byteValue", Byte.MAX_VALUE);
        result.put("wrongValue", Byte.MAX_VALUE);

        return result;
    }

    public String customMapperMethod(Object value) {
        return "This is custom mapper. Which is mapping value give from Argument that is  = " + value;
    }

    public static String customMapperStaticMethod(Object value) {
        return "This is custom static mapper. Which is mapping value give from Argument that is  = " + value;
    }
}
