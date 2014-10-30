
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class Maxwind {

    @Expose
    private Integer mph;
    @Expose
    private Integer kph;
    @Expose
    private String dir;
    @Expose
    private Integer degrees;

    public Integer getMph() {
        return mph;
    }

    public void setMph(Integer mph) {
        this.mph = mph;
    }

    public Integer getKph() {
        return kph;
    }

    public void setKph(Integer kph) {
        this.kph = kph;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

}
