package com.application.weatherforecast.utility;

import org.springframework.stereotype.Component;

@Component
public class WeatherForecastUtility {


    public Float convertKelvinToCelcius(Float temp) {
        return (temp - 273.15F);

    }
}
