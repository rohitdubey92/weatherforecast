package com.application.weatherforecast.dto;

import java.util.List;
import java.util.Map;

public class WeatherAPIResponse {
    private List<Map> list;

    public List<Map> getList() {
        return list;
    }

    public void setList( List<Map> list) {
        this.list = list;
    }
}
