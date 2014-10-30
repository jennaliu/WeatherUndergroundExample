
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;

public class QpfDay {

    @Expose
    private Double in;
    @Expose
    private Integer mm;

    public Double getIn() {
        return in;
    }

    public void setIn(Double in) {
        this.in = in;
    }

    public Integer getMm() {
        return mm;
    }

    public void setMm(Integer mm) {
        this.mm = mm;
    }

}
