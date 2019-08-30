package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Scanner;

public class ViewSingleEventActivity extends AppCompatActivity {

    MyDbAdapter helper;
    JuvinileEvent jevent= new JuvinileEvent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_event);

        helper = new MyDbAdapter(this);

        //This is all about retracting the juvinile-eventid in order to fetch correct data.
        String toparse;
        Integer eventid;


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

        TextView tveventplace= findViewById(R.id.tvPlace);
        EditText eteventname= findViewById(R.id.etEventName);
        EditText etplannedstart= findViewById(R.id.etStart);
        EditText etplannedend= findViewById(R.id.etEnd);
        EditText etminage= findViewById(R.id.etMinAge);
        EditText etmaxage= findViewById(R.id.etMaxAge);
        EditText etparticcount=findViewById(R.id.etParticCount);


        String eventplace=jevent.getJuvinilename(); //this goes to uneditable textview
        //Integer eventid=jevent.getEventid();
        String eventname=jevent.getEventname();
        String plannedstart=jevent.getPlanned_start();
        String plannedend=jevent.getPlanned_end();
        Integer minage=jevent.getMinage();
        Integer maxage=jevent.getMaxage();
        Integer participants=jevent.getParticipants();

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + eventname );

        tveventplace.setText(eventplace);
        eteventname.setText(eventname);
        etplannedstart.setText(plannedstart);
        etplannedend.setText(plannedend);
        etminage.setText(Integer.toString(minage));
        etmaxage.setText(Integer.toString(maxage));
        etparticcount.setText(Integer.toString(participants));


    }

}
