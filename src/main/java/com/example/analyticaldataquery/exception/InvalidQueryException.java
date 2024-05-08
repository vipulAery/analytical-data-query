package com.example.analyticaldataquery.exception;

import lombok.Getter;

@Getter
public class InvalidQueryException extends IllegalArgumentException {
    private final String query;
    public InvalidQueryException(String message, String query) {
        super(message);
        this.query = query;
    }
}
