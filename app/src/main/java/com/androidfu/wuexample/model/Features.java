
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class Features {

    @Expose
    private Integer forecast;

    public Integer getForecast() {
        return forecast;
    }

    public void setForecast(Integer forecast) {
        this.forecast = forecast;
    }

}
