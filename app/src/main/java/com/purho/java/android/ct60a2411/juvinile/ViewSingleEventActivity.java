package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;


public class ViewSingleEventActivity extends AppCompatActivity implements Serializable {

    private MyDbAdapter helper;
    private JuvinileEvent jevent;


    private TextView tveventplace;
    private EditText eteventname;
    private EditText etplannedstart;
    private EditText etplannedend;
    private EditText etminage;
    private EditText etmaxage;
    private EditText etparticcount;



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
            System.out.println("***************Can't find the event****************"); // TODO this could/should be something smarter, a msg or break or both..
        } else {
            jevent = (JuvinileEvent) i.getSerializableExtra("eventObject");
        }

        setUpDisplay();

    }


    public void getSingleEventData(Integer eventid){

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


        helper.updateJuvinileEventDetails(jevent.getEventid(),"eventname",jevent.getEventname());
        helper.updateJuvinileEventDetails(jevent.getEventid(),"plannedstart",jevent.getPlanned_startDB());
        helper.updateJuvinileEventDetails(jevent.getEventid(),"plannedend",jevent.getPlanned_endDB());
        helper.updateJuvinileEventDetails(jevent.getEventid(),"minage", String.valueOf(jevent.getMinage()));
        helper.updateJuvinileEventDetails(jevent.getEventid(),"maxage", String.valueOf(jevent.getMaxage()));
        helper.updateJuvinileEventDetails(jevent.getEventid(),"participants", String.valueOf(jevent.getParticipants()));



    }

    public void goLive(View v) {

        Intent intent = new Intent(this,EventActiveActivity.class);
        intent.putExtra("passEventObjectLive", jevent);
        startActivity(intent);

    }

    public void viewFeedbacks(View v) {

        Intent intent = new Intent(this,ViewEventFeedBacksActivity.class);
        intent.putExtra("passEventObjectLive", jevent);
        startActivity(intent);
    }

}
