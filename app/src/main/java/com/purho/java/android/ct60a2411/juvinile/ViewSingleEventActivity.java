package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class ViewSingleEventActivity extends AppCompatActivity {

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

        //This is all about retracting the juvinile-eventid in order to fetch correct data.
        String toparse;
        tveventplace= findViewById(R.id.tvPlace);
        eteventname= findViewById(R.id.etEventName);
        etplannedstart= findViewById(R.id.etStart);
        etplannedend= findViewById(R.id.etEnd);
        etminage= findViewById(R.id.etMinAge);
        etmaxage= findViewById(R.id.etMaxAge);
        etparticcount=findViewById(R.id.etParticCount);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                toparse= "";
            } else {
                toparse= extras.getString("eventidrow");
            }
        } else {
            toparse= (String) savedInstanceState.getSerializable("eventidrow");
        }

        Scanner sc = new Scanner(toparse);
        //and here it is
        eventid=sc.nextInt();
        getSingleEventData(eventid);

    }


    public void getSingleEventData(Integer eventid){

        //System.out.println("eventid on " + eventid + " RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");

        //create an object for the event data and get it from the DB

        jevent=helper.getSingleEvent(eventid);




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
        //TODO!! loses times, must check
        id=helper.updateJuvinileEventDetails(eventid,"eventname",jevent.getEventname());

        id=helper.updateJuvinileEventDetails(eventid,"plannedstart",jevent.getPlanned_start());
        id=helper.updateJuvinileEventDetails(eventid,"plannedend",jevent.getPlanned_end());
        id=helper.updateJuvinileEventDetails(eventid,"minage", String.valueOf(jevent.getMinage()));
        id=helper.updateJuvinileEventDetails(eventid,"maxage", String.valueOf(jevent.getMaxage()));
        id=helper.updateJuvinileEventDetails(eventid,"participants", String.valueOf(jevent.getParticipants()));



    }

    public void goLive(View v) {

        Intent intent = new Intent(this,EventActiveAcitivity.class);
        intent.putExtra("event_id", eventid);
        startActivity(intent);

    }



}
