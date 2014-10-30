package com.androidfu.wuexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidfu.wuexample.model.CurrentConditions;
import com.androidfu.wuexample.model.Forecast;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * You're going to have to register for a Weather Underground API key and put it in a strings
 * resource file.  I suggest using secret.xml as that will be excluded from source control via
 * our .gitignore file.
 *
 * Other than that please know that this is NOT production worthy code and is intended to provide
 * a very basic solution for one of my coding challenges.  Proceed with caution ;)
 */
public class CurrentConditionsActivity extends Activity implements Button.OnClickListener{

    private static final String TAG = CurrentConditionsActivity.class.getSimpleName();

    // TODO Add a refresh button
    private TextView textViewStatus;
    private TextView textViewLocationName;
    private TextView textViewTemperature;
    private TextView textViewHumidity;
    private TextView textViewTemperatureHigh;
    private TextView textViewTemperatureLow;
    private TextView textViewWindSpeed;
    private TextView textViewWindDirection;
    private TextView textViewObservationTime;
    private TextView buttonRefresh;

    static private String LOCATION_NAME = "LocationName";
    static private String TEMPERATURE = "Temperature";
    static private String HUMIDITY = "Humidity";
    static private String TEMPERATURE_HIGH = "TemperatureHigh";
    static private String TEMPERATURE_LOW = "TemperatureLOW";
    static private String WIND_SPEED = "WindSpeed";
    static private String WIND_DIRECTION = "WindDirection";
    static private String OBSERVATION_TIME = "ObservationTime";

    private String mLocationName;
    private String mTemperature;
    private String mHumidity;
    private String mTemperatureHigh;
    private String mTemperatureLow;
    private String mWindSpeed;
    private String mWindDirection;
    private String mObservationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_conditions);

        textViewStatus = (TextView) findViewById(R.id.tv_Status);
        textViewLocationName = (TextView) findViewById(R.id.tv_LocationName);
        textViewTemperature = (TextView) findViewById(R.id.tv_Temperature);
        textViewHumidity = (TextView) findViewById(R.id.tv_Humidity);
        textViewTemperatureHigh = (TextView) findViewById(R.id.tv_ForecastTemperatureHigh);
        textViewTemperatureLow = (TextView) findViewById(R.id.tv_ForecastTemperatureLow);
        textViewWindSpeed = (TextView) findViewById(R.id.tv_WindSpeed);
        textViewWindDirection = (TextView) findViewById(R.id.tv_WindDirection);
        textViewObservationTime = (TextView) findViewById(R.id.tv_ObservationTime);
        buttonRefresh = (TextView) findViewById(R.id.btn_Refresh);
        buttonRefresh.setOnClickListener(this);


        if(savedInstanceState != null){
            mLocationName = savedInstanceState.getString(LOCATION_NAME);
            mTemperature = savedInstanceState.getString(TEMPERATURE);
            mHumidity = savedInstanceState.getString(HUMIDITY);
            mObservationTime = savedInstanceState.getString(OBSERVATION_TIME);
            mTemperatureLow = savedInstanceState.getString(TEMPERATURE_LOW);
            mTemperatureHigh = savedInstanceState.getString(TEMPERATURE_HIGH);
            mWindSpeed = savedInstanceState.getString(WIND_SPEED);
            mWindDirection = savedInstanceState.getString(WIND_DIRECTION);

            textViewLocationName.setText(mLocationName);
            textViewTemperature.setText(mTemperature);
            textViewHumidity.setText(mHumidity);
            textViewObservationTime.setText(mObservationTime);
            textViewTemperatureLow.setText(mTemperatureLow);
            textViewTemperatureHigh.setText(mTemperatureHigh);
            textViewWindSpeed.setText(mWindSpeed);
            textViewWindDirection.setText(mWindDirection);

        }else{
            GetCurrentConditionsTask getCurrentConditionsTask = new GetCurrentConditionsTask();
            getCurrentConditionsTask.execute();
            GetForecastTask getForecastTask = new GetForecastTask();
            getForecastTask.execute();
        }

    }

    // TODO if we've already fetched the data then don't fetch it again: rotate the phone and observe, get a phone call or response to a notification and observe ;)
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LOCATION_NAME, mLocationName);
        outState.putString(TEMPERATURE, mTemperature);
        outState.putString(HUMIDITY, mHumidity);
        outState.putString(TEMPERATURE_HIGH, mTemperatureHigh);
        outState.putString(TEMPERATURE_LOW, mTemperatureLow);
        outState.putString(WIND_SPEED, mWindSpeed);
        outState.putString(WIND_DIRECTION, mWindDirection);
        outState.putString(OBSERVATION_TIME, mObservationTime);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        GetCurrentConditionsTask getCurrentConditionsTask = new GetCurrentConditionsTask();
        getCurrentConditionsTask.execute();
        GetForecastTask getForecastTask = new GetForecastTask();
        getForecastTask.execute();
    }

    private class GetCurrentConditionsTask extends AsyncTask<Void, Void, CurrentConditions> {

        private String url = "http://api.wunderground.com/api/{APIKEY}/conditions/q/SC/Charleston.json";

        @Override
        protected void onPostExecute(CurrentConditions currentConditions) {
            super.onPostExecute(currentConditions);
            if (textViewStatus == null) {
                return;
            }
            if (currentConditions != null) {
                //noinspection StringBufferReplaceableByString
                mLocationName = currentConditions.getCurrentObservation().getDisplayLocation().getFull();
                mTemperature = currentConditions.getCurrentObservation().getTempF().toString();
                mHumidity = currentConditions.getCurrentObservation().getRelativeHumidity();
                mObservationTime = currentConditions.getCurrentObservation().getObservationTime();

                textViewLocationName.setText(mLocationName);
                textViewTemperature.setText(mTemperature);
                textViewHumidity.setText(mHumidity);
                textViewObservationTime.setText(mObservationTime);
            } else {
                textViewStatus.setText(getString(R.string.error_something_went_wrong));
            }
        }

        @Override
        protected CurrentConditions doInBackground(Void... params) {
            // TODO Add a progress bar to indicate "work" to the user.
            InputStream source = retrieveStream(
                    url.replace("{APIKEY}",
                     /* you need to add an API key in strings */ getString(R.string.wu_api_key))
            );
            CurrentConditions response = null;
            if (source != null) {
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(source);
                try {
                    response = gson.fromJson(reader, CurrentConditions.class);
                    reader.close();
                } catch (Exception e) {
                    Log.w(TAG, String.format("Error: %1$s for URL %2$s", e.getMessage(), url));
                }
            }
            if (!this.isCancelled()) {
                return response;
            } else {
                return null;
            }
        }

        private InputStream retrieveStream(String url) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet getRequest;
            try {
                getRequest = new HttpGet(url);
                HttpResponse getResponse = client.execute(getRequest);
                HttpEntity getResponseEntity = getResponse.getEntity();
                return getResponseEntity.getContent();
            } catch (Exception e) {
                Log.w(TAG, String.format("Error for URL %1$s in retrieveStream()", url), e);
                return null;
            }
        }

    }

    private class GetForecastTask extends AsyncTask<Void, Void, Forecast>{
        private String url = "http://api.wunderground.com/api/{APIKEY}/forecast/q/SC/Charleston.json";

        @Override
        protected void onPostExecute(Forecast forecast) {
            super.onPostExecute(forecast);
            if (textViewStatus == null) {
                return;
            }
            if (forecast != null) {
                mTemperatureLow = forecast.getForecast().getSimpleforecast().getForecastday().get(0).getLow().getFahrenheit();
                mTemperatureHigh = forecast.getForecast().getSimpleforecast().getForecastday().get(0).getHigh().getFahrenheit();
                mWindSpeed = forecast.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getMph().toString();
                mWindDirection = forecast.getForecast().getSimpleforecast().getForecastday().get(0).getAvewind().getDir();

                textViewTemperatureLow.setText(mTemperatureLow);
                textViewTemperatureHigh.setText(mTemperatureHigh);
                textViewWindSpeed.setText(mWindSpeed);
                textViewWindDirection.setText(mWindDirection);
            } else {
                textViewStatus.setText(getString(R.string.error_something_went_wrong));
            }
        }

        @Override
        protected Forecast doInBackground(Void... params) {
            // TODO Add a progress bar to indicate "work" to the user.
            InputStream source = retrieveStream(
                    url.replace("{APIKEY}",
                     /* you need to add an API key in strings */ getString(R.string.wu_api_key))
            );
            Forecast response = null;
            if (source != null) {
                Gson gson = new Gson();
                Reader reader = new InputStreamReader(source);
                try {
                    response = gson.fromJson(reader, Forecast.class);
                    reader.close();
                } catch (Exception e) {
                    Log.w(TAG, String.format("Error: %1$s for URL %2$s", e.getMessage(), url));
                }
            }
            if (!this.isCancelled()) {
                return response;
            } else {
                return null;
            }
        }

        private InputStream retrieveStream(String url) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet getRequest;
            try {
                getRequest = new HttpGet(url);
                HttpResponse getResponse = client.execute(getRequest);
                HttpEntity getResponseEntity = getResponse.getEntity();
                return getResponseEntity.getContent();
            } catch (Exception e) {
                Log.w(TAG, String.format("Error for URL %1$s in retrieveStream()", url), e);
                return null;
            }
        }
    }
}
