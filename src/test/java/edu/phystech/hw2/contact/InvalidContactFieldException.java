package edu.phystech.hw2.contact;

public class InvalidContactFieldException extends RuntimeException {
    private final String field;

    public InvalidContactFieldException(String field) {
        this.field = field;
    }

    public String getFieldName() {
        return this.field;
    }
}