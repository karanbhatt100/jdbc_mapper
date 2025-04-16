package io.github.karanbhatt100.tools.jdbc_mapper.exception;

public class RowMapperFailed extends RuntimeException {

    public RowMapperFailed(Exception e) {
        super(e);
    }

}