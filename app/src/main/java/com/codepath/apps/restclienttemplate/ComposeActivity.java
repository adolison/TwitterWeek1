package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    EditText script;
    TwitterClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        script = (EditText) findViewById(R.id.writeTweet);
        client = TwitterApp.getRestClient();
//        getSupportActionBar().setTitle("Compose");
    }
    public void Clicker (View yeah){
        String var = script.getText().toString();
        client.sendTweet(var,new JsonHttpResponseHandler() {
            Tweet newTweet= null;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    newTweet = Tweet.fromJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent workRight = new Intent();
                workRight.putExtra("TwitKey", newTweet);
               setResult(RESULT_OK, workRight);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("Tweet Failure", errorResponse.toString());
            }
        });

    }
}
