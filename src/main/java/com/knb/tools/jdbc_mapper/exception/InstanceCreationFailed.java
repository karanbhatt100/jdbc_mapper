package com.knb.tools.jdbc_mapper.exception;

public class InstanceCreationFailed extends RuntimeException {

    public InstanceCreationFailed(Exception e) {
        super(e);
    }

}