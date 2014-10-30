
package com.androidfu.wuexample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Forecastday_ {

    @Expose
    private Date date;
    @Expose
    private Integer period;
    @Expose
    private High high;
    @Expose
    private Low low;
    @Expose
    private String conditions;
    @Expose
    private String icon;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @Expose
    private String skyicon;
    @Expose
    private Integer pop;
    @SerializedName("qpf_allday")
    @Expose
    private QpfAllday qpfAllday;
    @SerializedName("qpf_day")
    @Expose
    private QpfDay qpfDay;
    @SerializedName("qpf_night")
    @Expose
    private QpfNight qpfNight;
    @SerializedName("snow_allday")
    @Expose
    private SnowAllday snowAllday;
    @SerializedName("snow_day")
    @Expose
    private SnowDay snowDay;
    @SerializedName("snow_night")
    @Expose
    private SnowNight snowNight;
    @Expose
    private Maxwind maxwind;
    @Expose
    private Avewind avewind;
    @Expose
    private Integer avehumidity;
    @Expose
    private Integer maxhumidity;
    @Expose
    private Integer minhumidity;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public Low getLow() {
        return low;
    }

    public void setLow(Low low) {
        this.low = low;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSkyicon() {
        return skyicon;
    }

    public void setSkyicon(String skyicon) {
        this.skyicon = skyicon;
    }

    public Integer getPop() {
        return pop;
    }

    public void setPop(Integer pop) {
        this.pop = pop;
    }

    public QpfAllday getQpfAllday() {
        return qpfAllday;
    }

    public void setQpfAllday(QpfAllday qpfAllday) {
        this.qpfAllday = qpfAllday;
    }

    public QpfDay getQpfDay() {
        return qpfDay;
    }

    public void setQpfDay(QpfDay qpfDay) {
        this.qpfDay = qpfDay;
    }

    public QpfNight getQpfNight() {
        return qpfNight;
    }

    public void setQpfNight(QpfNight qpfNight) {
        this.qpfNight = qpfNight;
    }

    public SnowAllday getSnowAllday() {
        return snowAllday;
    }

    public void setSnowAllday(SnowAllday snowAllday) {
        this.snowAllday = snowAllday;
    }

    public SnowDay getSnowDay() {
        return snowDay;
    }

    public void setSnowDay(SnowDay snowDay) {
        this.snowDay = snowDay;
    }

    public SnowNight getSnowNight() {
        return snowNight;
    }

    public void setSnowNight(SnowNight snowNight) {
        this.snowNight = snowNight;
    }

    public Maxwind getMaxwind() {
        return maxwind;
    }

    public void setMaxwind(Maxwind maxwind) {
        this.maxwind = maxwind;
    }

    public Avewind getAvewind() {
        return avewind;
    }

    public void setAvewind(Avewind avewind) {
        this.avewind = avewind;
    }

    public Integer getAvehumidity() {
        return avehumidity;
    }

    public void setAvehumidity(Integer avehumidity) {
        this.avehumidity = avehumidity;
    }

    public Integer getMaxhumidity() {
        return maxhumidity;
    }

    public void setMaxhumidity(Integer maxhumidity) {
        this.maxhumidity = maxhumidity;
    }

    public Integer getMinhumidity() {
        return minhumidity;
    }

    public void setMinhumidity(Integer minhumidity) {
        this.minhumidity = minhumidity;
    }

}
