package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

public class FeedBackActivity extends AppCompatActivity implements Serializable {

    MyDbAdapter helper;
    JuvinileEvent jevent;
    EventFeedBack feedback;

    private EditText feedback;
    private EditText grade;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        helper = new MyDbAdapter(this);

        feedback=(EditText) findViewById(R.id.etFeedBack);
        grade=(EditText) findViewById(R.id.etGrade);
        name=(EditText) findViewById(R.id.etName);


        Intent i = getIntent();
        i.getSerializableExtra("eventObject");
        if (i.getSerializableExtra("eventObject") == null) {
            //jevent = new JuvinileEvent();
            System.out.println("***************Can't find the event****************"); // TODO this could/should be something smarter, a msg or break or both..
        } else {
            jevent = (JuvinileEvent) i.getSerializableExtra("eventObject");
        }
    }


    public void saveFeedBack(View v){

    //TODO save to object, save to db
    // notify, that feedback is given

    }







}
