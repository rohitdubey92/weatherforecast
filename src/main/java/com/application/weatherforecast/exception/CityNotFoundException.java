package com.application.weatherforecast.exception;

public class CityNotFoundException extends RuntimeException{

    public CityNotFoundException() {
        super();
    }
    public CityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
