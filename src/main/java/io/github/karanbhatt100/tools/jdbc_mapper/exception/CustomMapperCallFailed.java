package io.github.karanbhatt100.tools.jdbc_mapper.exception;

public class CustomMapperCallFailed extends RuntimeException {

    public CustomMapperCallFailed(Exception e) {
        super(e);
    }

}