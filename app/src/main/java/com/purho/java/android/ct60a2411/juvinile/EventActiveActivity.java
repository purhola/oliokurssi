package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.Scanner;

public class EventActiveActivity extends AppCompatActivity implements Serializable {

    MyDbAdapter helper;
    JuvinileEvent jevent;
    Integer eventid=0;
    Button particb;
    //Integer count=0;
    //Integer participants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_active);

        String toparse;
        particb = (Button) findViewById(R.id.btnAddPartic);

        Intent i = getIntent();
        i.getSerializableExtra("passEventObjectLive");
        if(i.getSerializableExtra("passEventObjectLive") == null) {
            jevent= new JuvinileEvent();
        } else {
            jevent = (JuvinileEvent)i.getSerializableExtra("passEventObjectLive");
        }

    }



    public void addParticipant(View x){





        Integer count=jevent.getParticipants()+1;

        particb.setText(Integer.toString(count));





    }

    public void startEvent(View v) {


    }

    public void endEvent(View v) {


    }


   public void getSingleEventDatal(Integer eventid){


        //create an object for the event data and get it from the DB

       System.out.println("EVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDBBB " + eventid);

        //jeventl= new JuvinileEvent();
        //jeventl=helper.getSingleEvent(eventid);

        //String eventplace=jevent.getJuvinilename(); //this goes to uneditable textview
        //Integer eventid=jevent.getEventid();
        //String eventname=jevent.getEventname();
        //String plannedstart=jevent.getPlanned_start();
        //String plannedend=jevent.getPlanned_end();
        //Integer minage=jevent.getMinage();
        //Integer maxage=jevent.getMaxage();
        //participants=jeventl.getParticipants();

}




}
