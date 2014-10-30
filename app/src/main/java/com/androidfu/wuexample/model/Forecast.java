
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class Forecast {

    @Expose
    private Response response;
    @Expose
    private Forecast_ forecast;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Forecast_ getForecast() {
        return forecast;
    }

    public void setForecast(Forecast_ forecast) {
        this.forecast = forecast;
    }

}
