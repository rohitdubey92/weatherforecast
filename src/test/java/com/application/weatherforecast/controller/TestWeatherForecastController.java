package com.application.weatherforecast.controller;

import com.application.weatherforecast.dto.WeatherForecastResponse;
import com.application.weatherforecast.exception.ExceptionResponse;
import com.application.weatherforecast.service.WeatherForecastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeatherForecastController.class)
public class TestWeatherForecastController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherForecastService weatherForecastService;


    @Test
    void getWeatherOfACityValidApplySunscreen() throws Exception {
        WeatherForecastResponse weatherForecastResponse= new WeatherForecastResponse();
        weatherForecastResponse.setHighTemp(41.0F);
        weatherForecastResponse.setLowTemp(5.0F);
        weatherForecastResponse.setInstruction("Use sunscreen lotion");
        ResponseEntity<WeatherForecastResponse> responseResponseEntity= new ResponseEntity<WeatherForecastResponse>(weatherForecastResponse,HttpStatus.OK);

        when(weatherForecastService.getWeatherOfACity("london")).thenReturn(responseResponseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/weather-forecast").
                param("cityName", "london"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.instruction").value("Use sunscreen lotion"));

    }

    @Test
    void getWeatherOfACityValid() throws Exception {
        WeatherForecastResponse weatherForecastResponse= new WeatherForecastResponse();
        weatherForecastResponse.setHighTemp(10.0F);
        weatherForecastResponse.setLowTemp(5.0F);
        weatherForecastResponse.setInstruction("Enjoy your day");
        ResponseEntity<WeatherForecastResponse> responseResponseEntity= new ResponseEntity<WeatherForecastResponse>(weatherForecastResponse,HttpStatus.OK);

        when(weatherForecastService.getWeatherOfACity("london")).thenReturn(responseResponseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/weather-forecast").
                param("cityName", "london"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.instruction").value("Enjoy your day"));

    }
    @Test
    void getWeatherOfACityInvalid() throws Exception {
        ExceptionResponse exceptionResponse= new ExceptionResponse(404,"","Please enter a correct city name.");
        ResponseEntity responseEntity=new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
        when(weatherForecastService.getWeatherOfACity("abc")).thenReturn(responseEntity);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/weather-forecast").
                param("cityName", "abc"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.suggestion").value("Please enter a correct city name."));;
    }
}
