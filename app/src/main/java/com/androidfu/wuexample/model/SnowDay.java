
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class SnowDay {

    @Expose
    private Double in;
    @Expose
    private Double cm;

    public Double getIn() {
        return in;
    }

    public void setIn(Double in) {
        this.in = in;
    }

    public Double getCm() {
        return cm;
    }

    public void setCm(Double cm) {
        this.cm = cm;
    }

}
