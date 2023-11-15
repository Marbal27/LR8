package com.example.lr8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button btn_fact;
    private TextView text_fact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_fact = findViewById(R.id.btn_next);
        text_fact = findViewById(R.id.text_fact);

        btn_fact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpHandler httpHandler = new OkHttpHandler();
                httpHandler.execute();

            }
        });
    }
    public class OkHttpHandler extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void ... voids) {
            Request.Builder builder = new Request.Builder();
            Request request = builder
                    .url("https://api.openweathermap.org/data/2.5/weather?lat=57.9194&lon=59.965&appid=ff41c1373d337fc2b98d2b185e517c68")
                    .build();
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            try {
                Response response = client.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(response.body().string());
                return jsonObject.getString("weather") + jsonObject.getString("main");
            } catch (IOException e){
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            text_fact.setText(o.toString());
        }
    }
}