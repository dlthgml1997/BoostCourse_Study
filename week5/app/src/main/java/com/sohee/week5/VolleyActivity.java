package com.sohee.week5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.sohee.boostcourse_pjt.network.AppHelper;
import com.sohee.boostcourse_pjt.ui.movie.item.getMovieListResponse;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        textView = (TextView) findViewById(R.id.textView3);

        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        if (AppHelper.requestQueue == null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void sendRequest() {

        String url = "http://www.google.co.kr";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);

                    //응답왔을 때 처리

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error);
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        //매번 받은 결과를 그대로 보여주세요
        request.setShouldCache(false);

        AppHelper.requestQueue.add(request);
        println("요청 보냄.");
    }

    private void processResponse(String response) {
        Gson gson = new Gson();
        getMovieListResponse getMovieListResponse =gson.fromJson(response, getMovieListResponse.class);

        if(getMovieListResponse != null){
            int countMovie = getMovieListResponse.result.size();
            println("cc");
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}
