package com.application.weatherforecast.service;

import com.application.weatherforecast.dto.WeatherAPIResponse;
import com.application.weatherforecast.dto.WeatherForecastResponse;
import com.application.weatherforecast.repository.WeatherForecastRepository;
import com.application.weatherforecast.utility.WeatherForecastConstants;
import com.application.weatherforecast.utility.WeatherForecastUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    @Autowired
    private WeatherForecastUtility weatherForecastUtility;

    @Value("${weather-api}")
    private String weatherApiUrl;

    @Value("${appid}")
    private String weatherApiAppId;

    private ObjectMapper objectMapper;


    public ResponseEntity<WeatherForecastResponse> getWeatherOfACity(String cityName) {
        WeatherAPIResponse weatherAPIResponse = weatherForecastRepository.getWeatherOfACity(cityName, weatherApiUrl,
                weatherApiAppId);
        return new ResponseEntity<>(checkWeatherAndSendInstructions(weatherAPIResponse), HttpStatus.OK);
    }


    private WeatherForecastResponse checkWeatherAndSendInstructions(WeatherAPIResponse weatherAPIResponse) {

        Float lowTempWithin3Days = null;
        Float highTempWithin3Days = null;
        Float wind = null;
        Boolean rainPredicted = false;
        for (Map map : weatherAPIResponse.getList()) {
            Integer weatherApiDate = Integer.parseInt(map.get(WeatherForecastConstants.DATE) + "");
            if (checkIfDateWithingRange(weatherApiDate)) {
                Float minTemp = weatherForecastUtility.convertKelvinToCelcius(Float.parseFloat(((Map) map.get(WeatherForecastConstants.MAIN)).get(WeatherForecastConstants.TEMP_MIN) + ""));
                Float maxTemp = weatherForecastUtility.convertKelvinToCelcius(Float.parseFloat(((Map) map.get(WeatherForecastConstants.MAIN)).get(WeatherForecastConstants.TEMP_MAX) + ""));
                Float windSpeed = Float.parseFloat(((Map) map.get(WeatherForecastConstants.WIND)).get(WeatherForecastConstants.SPEED) + "");
                String weatherDesc = ((Map)((List)map.get(WeatherForecastConstants.WEATHER)).get(0)).get(WeatherForecastConstants.MAIN)+"";
                if (lowTempWithin3Days == null && highTempWithin3Days == null && wind == null) {
                    lowTempWithin3Days = minTemp;
                    highTempWithin3Days = maxTemp;
                    wind = windSpeed;
                }
                if (minTemp < lowTempWithin3Days) {
                    lowTempWithin3Days = minTemp;
                }
                if (maxTemp > highTempWithin3Days) {
                    highTempWithin3Days = maxTemp;
                }
                if (windSpeed > wind) {
                    wind = windSpeed;
                }
                if (weatherDesc.contains(WeatherForecastConstants.RAIN) && rainPredicted) {
                    rainPredicted = true;
                }
            }

        }
        return createWeatherForecastResponse(lowTempWithin3Days, highTempWithin3Days, rainPredicted, wind);
    }

    private Boolean checkIfDateWithingRange(Integer date) {

        Date apiDate = new java.util.Date(date * 1000L);
        long duration = apiDate.getTime() - Calendar.getInstance().getTime().getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
        if (diffInDays >= 0 && diffInDays <= 3) {
            return true;
        }
        return false;
    }

    private WeatherForecastResponse createWeatherForecastResponse(Float minTemp,
                                                                  Float maxTemp,
                                                                  Boolean rainPredicted, Float windSpeed) {

        WeatherForecastResponse weatherForecastResponse = new WeatherForecastResponse();
        weatherForecastResponse.setHighTemp(maxTemp);
        weatherForecastResponse.setLowTemp(minTemp);
        String instruction = WeatherForecastConstants.CLEAR_WEATHER;
        if (rainPredicted) {
            instruction = WeatherForecastConstants.RAIN_PREDICTED;
        }
        if (maxTemp > 40) {
            instruction = WeatherForecastConstants.TOO_HOT;
        }
        if (windSpeed > 10) {
            instruction = WeatherForecastConstants.TOO_WINDY;
        }
        weatherForecastResponse.setInstruction(instruction);
        return weatherForecastResponse;

    }


}
