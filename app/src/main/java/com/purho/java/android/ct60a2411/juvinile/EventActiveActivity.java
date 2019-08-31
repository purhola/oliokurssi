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
    Integer count;
    Integer participants;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_active);

        String toparse;

        Intent i = getIntent();
        i.getSerializableExtra("passEventObjectLive");
        if(i.getSerializableExtra("passEventObjectLive") == null) {
            jevent= new JuvinileEvent();
        } else {
            jevent = (JuvinileEvent)i.getSerializableExtra("passEventObjectLive");
        }

        /*
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                toparse= "";
            } else {
                toparse= extras.getString("event_id");
            }
        } else {
            toparse= (String) savedInstanceState.getSerializable("event_id");
        }

        Scanner sc = new Scanner(toparse);
        //and here it is
        eventid=sc.nextInt();
        System.out.println("EVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTIDEVENTID " + eventid);
  //      getSingleEventDatal(eventid); //TODO. ei tykkää hakea tässä tuota!!
*/

    }



    public void addParticipant(View x){

        particb = (Button) findViewById(R.id.btnAddPartic);

        count+=1;

                //jevent.getParticipants()+1;

        particb.setText("eeeiiiikä");





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
