package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.quote_result);
        Button getButton = findViewById(R.id.get_button);

        mQueue = Volley.newRequestQueue(this);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearContent();
                jsonParse();
            }
        });
    }

    private void clearContent() {
        mTextViewResult.setText("");
    }

    private void jsonParse() {
        String url = "https://seinfeld-quotes.herokuapp.com/random";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String quote = null;
                        try {
                            quote = response.getString("quote");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String author = null;
                        try {
                            author = response.getString("author");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String season = null;
                        try {
                            season = response.getString("season");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String episode = null;
                        try {
                            episode = response.getString("episode");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mTextViewResult.append(quote + "\n\n" + "-" + author + "\n\n" + "season " + season + ", " + "episode " + episode);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}