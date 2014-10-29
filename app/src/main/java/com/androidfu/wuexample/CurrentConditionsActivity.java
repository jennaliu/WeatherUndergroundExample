package com.androidfu.wuexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.androidfu.wuexample.model.CurrentConditions;
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
 * our .gitignroe file.
 *
 * Other than that please know that this is NOT production worthy code and is intended to provide
 * a very basic solution for one of my coding challenges.  Proceed with caution ;)
 */
public class CurrentConditionsActivity extends Activity {

    private static final String TAG = CurrentConditionsActivity.class.getSimpleName();

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_conditions);

        textView = (TextView) findViewById(R.id.tv_currentConditions);

        GetCurrentConditionsTask getCurrentConditionsTask = new GetCurrentConditionsTask();
        getCurrentConditionsTask.execute();
    }

    private class GetCurrentConditionsTask extends AsyncTask<Void, Void, CurrentConditions> {

        private String url = "http://api.wunderground.com/api/{APIKEY}/conditions/q/SC/Charleston.json";

        @Override
        protected void onPostExecute(CurrentConditions currentConditions) {
            super.onPostExecute(currentConditions);
            if (textView == null) {
                return;
            }
            if (currentConditions != null) {
                StringBuilder conditionsBuilder = new StringBuilder();
                conditionsBuilder.append(currentConditions.getCurrentObservation().getDisplayLocation().getFull())
                        .append("\n")
                        .append(currentConditions.getCurrentObservation().getObservationTime())
                        .append("\n")
                        .append(currentConditions.getCurrentObservation().getTemperatureString());
                textView.setText(conditionsBuilder.toString());
            } else {
                textView.setText(getString(R.string.error_something_went_wrong));
            }
        }

        @Override
        protected CurrentConditions doInBackground(Void... params) {
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

}
