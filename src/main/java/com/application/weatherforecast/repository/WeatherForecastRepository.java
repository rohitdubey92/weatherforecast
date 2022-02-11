package com.application.weatherforecast.repository;


import com.application.weatherforecast.dto.WeatherAPIResponse;

public interface WeatherForecastRepository {

    WeatherAPIResponse getWeatherOfACity(String cityName, String apiUrl, String appId);

}
