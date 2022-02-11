package com.application.weatherforecast.repository;

import com.application.weatherforecast.dto.WeatherAPIResponse;
import com.application.weatherforecast.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

@Repository
public class WeatherForecastRepositoryImpl implements WeatherForecastRepository {

    @Autowired
    private RestTemplate restTemplate;


    public WeatherAPIResponse getWeatherOfACity(String cityName, String apiUrl,
                                                String appId) {
        URI url = new UriTemplate(apiUrl).expand(cityName, appId);
        WeatherAPIResponse weatherAPIResponse = null;
        try {
            weatherAPIResponse = restTemplate.getForObject(url, WeatherAPIResponse.class);
        } catch (HttpClientErrorException e) {
            throw new CityNotFoundException(e.getMessage(), e);
        }
        return weatherAPIResponse;
    }

}
