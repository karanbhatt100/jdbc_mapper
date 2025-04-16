package io.github.karanbhatt100.tools.jdbc_mapper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface CustomMapper {
    String className();

    String methodName();

    boolean isStatic() default true;
}
