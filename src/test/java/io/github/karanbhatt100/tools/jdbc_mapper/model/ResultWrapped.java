package io.github.karanbhatt100.tools.jdbc_mapper.model;

import io.github.karanbhatt100.tools.jdbc_mapper.annotation.FieldId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResultWrapped {
    @FieldId("STR_VALUE")
    private String strValue;

    @FieldId("INT_VALUE")
    private Integer intValue;

    @FieldId("LONG_VALUE")
    private Long longValue;

    @FieldId("SHORT_VALUE")
    private Short shortValue;

    @FieldId("FLOAT_VALUE")
    private Float floatValue;

    @FieldId("DOUBLE_VALUE")
    private Double doubleValue;

    @FieldId("BOOL_VALUE")
    private Boolean boolValue;

    @FieldId("CHAR_VALUE")
    private Character charValue;

    @FieldId("DATE_VALUE")
    private Date date;

    @FieldId("EXTRA_VALUE")
    private String extraValue;

    @FieldId("BYTE_VALUE")
    private Byte byteValue;

    @FieldId("WRONG_VALUE")
    private String wrongValue;

    private String extraField;
}
