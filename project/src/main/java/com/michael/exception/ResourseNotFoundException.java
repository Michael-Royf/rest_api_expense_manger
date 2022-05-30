package com.michael.exception;

public class ResourseNotFoundException extends RuntimeException  {
 public static final long serialVersionUID = 1L;

    public ResourseNotFoundException(String message) {
        super(message);
    }
}
