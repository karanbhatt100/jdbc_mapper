package com.knb.tools.jdbc_mapper.model;

import com.knb.tools.jdbc_mapper.annotation.CustomMapper;
import com.knb.tools.jdbc_mapper.annotation.FieldId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Result {

    @FieldId("NULL_VALUE")
    private String nullValue;

    @FieldId("STR_VALUE")
    private String strValue;

    @FieldId("INT_VALUE")
    private int intValue;

    @FieldId("LONG_VALUE")
    private long longValue;

    @FieldId("SHORT_VALUE")
    private short shortValue;

    @FieldId("FLOAT_VALUE")
    private float floatValue;

    @FieldId("DOUBLE_VALUE")
    private double doubleValue;

    @FieldId("BOOL_VALUE")
    private boolean boolValue;

    @FieldId("CHAR_VALUE")
    private char charValue;

    @FieldId("DATE_VALUE")
    private Date dateValue;

    @FieldId("BYTE_VALUE")
    private byte byteValue;

    private String extraField;
    private String wrongValue;

    @FieldId(value = "CUSTOM_VALUE", customMapper = @CustomMapper(className = "com.knb.tools.jdbc_mapper.util.UtilFunc", methodName = "customMapperMethod", isStatic = false))
    private String customValue;

    @FieldId(value = "CUSTOM_VALUE_NO_CLASS", customMapper = @CustomMapper(className = "", methodName = "customMapperMethod"))
    private String customValueNoClass;

    @FieldId(value = "CUSTOM_VALUE_NO_METHOD", customMapper = @CustomMapper(className = "", methodName = "customValueNoMethod"))
    private String customValueNoMethod;

    @FieldId(value = "CUSTOM_VALUE_STATIC", customMapper = @CustomMapper(className = "com.knb.tools.jdbc_mapper.util.UtilFunc", methodName = "customMapperStaticMethod"))
    private String customStaticValue;
}
