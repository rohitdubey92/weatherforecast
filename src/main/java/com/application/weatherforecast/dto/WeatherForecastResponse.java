package com.application.weatherforecast.dto;

public class WeatherForecastResponse {

    private Float lowTemp;

    private Float highTemp;

    private String instruction;

    public Float getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(Float lowTemp) {
        this.lowTemp = lowTemp;
    }

    public Float getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(Float highTemp) {
        this.highTemp = highTemp;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
