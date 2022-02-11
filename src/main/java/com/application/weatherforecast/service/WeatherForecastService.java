package com.application.weatherforecast.service;

import com.application.weatherforecast.dto.WeatherForecastResponse;
import org.springframework.http.ResponseEntity;

public interface WeatherForecastService {

    ResponseEntity<WeatherForecastResponse> getWeatherOfACity(String cityName);
}
