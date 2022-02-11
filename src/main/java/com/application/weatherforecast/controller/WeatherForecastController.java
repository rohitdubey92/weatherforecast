package com.application.weatherforecast.controller;

import com.application.weatherforecast.dto.WeatherForecastResponse;
import com.application.weatherforecast.service.WeatherForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherForecastController {


    @Autowired
    private WeatherForecastService weatherForecastService;

    @GetMapping("/v1/weather-forecast")
    public ResponseEntity<WeatherForecastResponse> getWeatherOfACity(@RequestParam(required = true) String cityName) {
        return weatherForecastService.getWeatherOfACity(cityName);
    }


}
