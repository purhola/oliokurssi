package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Scanner;

public class ViewSingleEventActivity extends AppCompatActivity implements Serializable {

    MyDbAdapter helper;
    JuvinileEvent jevent;


    TextView tveventplace;
    EditText eteventname;
    EditText etplannedstart;
    EditText etplannedend;
    EditText etminage;
    EditText etmaxage;
    EditText etparticcount;
    Integer eventid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_event);

        helper = new MyDbAdapter(this);


        tveventplace = (TextView) findViewById(R.id.tvPlace);
        eteventname = (EditText) findViewById(R.id.etEventName);
        etplannedstart = (EditText) findViewById(R.id.etStart);
        etplannedend = (EditText) findViewById(R.id.etEnd);
        etminage = (EditText) findViewById(R.id.etMinAge);
        etmaxage = (EditText) findViewById(R.id.etMaxAge);
        etparticcount = (EditText) findViewById(R.id.etParticCount);




        Intent i = getIntent();
        i.getSerializableExtra("eventObject");
        if (i.getSerializableExtra("eventObject") == null) {
            //jevent = new JuvinileEvent();
            System.out.println("***************Can't find the event****************");
        } else {
            jevent = (JuvinileEvent) i.getSerializableExtra("eventObject");
        }

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + jevent.getEventname());

        setUpDisplay();

    }


    public void getSingleEventData(Integer eventid){

        //create an object for the event data and get it from the DB

        // jevent=helper.getSingleEvent(eventid); //we don't want to do this as the object should be already passed here




        String eventplace=jevent.getJuvinilename(); //this goes to uneditable textview
        //Integer eventid=jevent.getEventid();
        String eventname=jevent.getEventname();
        String plannedstart=jevent.getPlanned_start();
        String plannedend=jevent.getPlanned_end();
        Integer minage=jevent.getMinage();
        Integer maxage=jevent.getMaxage();
        Integer participants=jevent.getParticipants();

        //display the fetched data
        tveventplace.setText(eventplace);
        eteventname.setText(eventname);
        etplannedstart.setText(plannedstart);
        etplannedend.setText(plannedend);
        etminage.setText(Integer.toString(minage));
        etmaxage.setText(Integer.toString(maxage));
        etparticcount.setText(Integer.toString(participants));


    }

    public void setUpDisplay(){


        String eventplace=jevent.getJuvinilename(); //this goes to uneditable textview
        String eventname=jevent.getEventname();
        String plannedstart=jevent.getPlanned_start();
        String plannedend=jevent.getPlanned_end();
        Integer minage=jevent.getMinage();
        Integer maxage=jevent.getMaxage();
        Integer participants=jevent.getParticipants();

        //display the fetched data
        tveventplace.setText(eventplace);
        eteventname.setText(eventname);
        etplannedstart.setText(plannedstart);
        etplannedend.setText(plannedend);
        etminage.setText(Integer.toString(minage));
        etmaxage.setText(Integer.toString(maxage));
        etparticcount.setText(Integer.toString(participants));
    }



    public void updateEventDetails(View w) {

        Integer ueventid= eventid;
        String eventname=eteventname.getText().toString();
        String plannedstart=etplannedstart.getText().toString();
        String plannedend=etplannedend.getText().toString();
        Integer minage= Integer.valueOf(etminage.getText().toString());
        Integer maxage= Integer.valueOf(etmaxage.getText().toString());
        Integer participants= Integer.valueOf(etparticcount.getText().toString());

        jevent.setEventname(eventname);
        jevent.setPlanned_startUI(plannedstart);
        jevent.setPlanned_endUI(plannedend);
        jevent.setMinage(minage);
        jevent.setMaxage(maxage);
        jevent.setParticipants(participants);

        long id;
        //TODO should we make the DB updates from here or from the object itself..
        //create a message if any fail, also msg if all are saved ok

        id=helper.updateJuvinileEventDetails(eventid,"eventname",jevent.getEventname());
        id=helper.updateJuvinileEventDetails(eventid,"plannedstart",jevent.getPlanned_startDB());
        id=helper.updateJuvinileEventDetails(eventid,"plannedend",jevent.getPlanned_endDB());
        id=helper.updateJuvinileEventDetails(eventid,"minage", String.valueOf(jevent.getMinage()));
        id=helper.updateJuvinileEventDetails(eventid,"maxage", String.valueOf(jevent.getMaxage()));
        id=helper.updateJuvinileEventDetails(eventid,"participants", String.valueOf(jevent.getParticipants()));



    }

    public void goLive(View v) {

        Intent intent = new Intent(this,EventActiveActivity.class);
        intent.putExtra("passEventObjectLive", jevent);
        startActivity(intent);

    }



}
