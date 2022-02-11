package com.application.weatherforecast.exception;

public class ExceptionResponse {

    private Integer status;
    private String message;
    private String suggestion;

    public ExceptionResponse() {
    }

    public ExceptionResponse(Integer status, String message, String suggestion) {
        this.status = status;
        this.message = message;
        this.suggestion = suggestion;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSuggestion() {
        return suggestion;
    }
}
