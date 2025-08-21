package com.ruler_hao.ctrl_me.data.api;

import android.net.Uri;
import android.util.Log;

import com.ruler_hao.ctrl_me.data.entity.parser.ApiParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiRequest {

    private static final String tag = ApiRequest.class.getSimpleName();

    private static final int TIMEOUT = 15000;

    private static ApiRequest instance;

    private ApiRequest() {  }

    public static ApiRequest getInstance() {
        if (instance == null) {
            instance = new ApiRequest();
        }
        return instance;
    }

    public <T> T performNetworkRequest(
            String url,
            Map<String, String> queryParams,
            Class<T> clazz
    ) {
        StringBuilder response = new StringBuilder();
        HttpURLConnection urlConnection = null;

        try {
            Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
            }

            URL mUrl = new URL(uriBuilder.build().toString());
            Log.d(tag, mUrl.toString());

            urlConnection = (HttpURLConnection) mUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setReadTimeout(TIMEOUT);

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                return ApiParser.parse(response.toString(), clazz);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
