
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class High {

    @Expose
    private String fahrenheit;
    @Expose
    private String celsius;

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

}
