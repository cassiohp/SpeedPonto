package com.cassio.speedponto.exceptions;

public class CpfExistsException extends Exception{
    public CpfExistsException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
