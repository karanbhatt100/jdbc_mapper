package io.github.karanbhatt100.tools.jdbc_mapper.exception;

public class FieldReadingFailed extends RuntimeException {

    public FieldReadingFailed(Exception e) {
        super(e);
    }

}