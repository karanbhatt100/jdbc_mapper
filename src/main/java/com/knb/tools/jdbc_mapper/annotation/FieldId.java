package com.knb.tools.jdbc_mapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldId {
    String value();

    CustomMapper customMapper() default @CustomMapper(className = "", methodName = "", isStatic = true);
}
