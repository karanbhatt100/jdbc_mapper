package com.knb.tools.jdbc_mapper.exception;

public class FieldReadingFailed extends RuntimeException {

    public FieldReadingFailed(Exception e) {
        super(e);
    }

}