package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class EventActiveActivity extends AppCompatActivity implements Serializable {

    MyDbAdapter helper;
    JuvinileEvent jevent;

    private Button particb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_active);

        helper = new MyDbAdapter(this);


        particb = (Button) findViewById(R.id.btnAddPartic);

        particb.setText("Participant \n counter \n *Click me*");

        Intent i = getIntent();
        i.getSerializableExtra("passEventObjectLive");
        if(i.getSerializableExtra("passEventObjectLive") == null) {
            jevent= new JuvinileEvent();
        } else {
            jevent = (JuvinileEvent)i.getSerializableExtra("passEventObjectLive");
        }

    }



    public void addParticipant(View x){


        //check if the event is active
        // if yes, get the participant count and add one

        String isactive = jevent.getActive();

        if(isactive.equals("YES")) {
            jevent.setParticipants(jevent.getParticipants()+1);
            Integer count=jevent.getParticipants();
            particb.setText("Event active \n Participants \n" + Integer.toString(count));
        }
        else particb.setText("Event not active \n Participants so far\n" + Integer.toString(jevent.getParticipants()));
    }

    public void activateEvent(View v) {

        //Starting means activate + update start time

        //create a timestamp for setting to the event
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String settime=now.format(df);

        jevent.setStart_time(settime);
        jevent.setActive("YES");

        particb.setText("Event active \n Participants \n" + Integer.toString(jevent.getParticipants()));

        //update the db
        helper.updateJuvinileEventDetails(jevent.getEventid(),"active","YES");
        helper.updateJuvinileEventDetails(jevent.getEventid(),"start",settime);

    }

    public void deactivateEvent(View v) {

        //stopping means deactivate + update end time

        //create a timestamp for setting to the event
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String settime=now.format(df);

        jevent.setEnd_time(settime);
        jevent.setActive("NO");

        particb.setText("Event not active \n Participants so far\n" + Integer.toString(jevent.getParticipants()));

        //update db
        helper.updateJuvinileEventDetails(jevent.getEventid(),"active","NO");
        helper.updateJuvinileEventDetails(jevent.getEventid(),"end",settime);

    }

    public void pauseEvent(View v) {

        //pausing means deactivate + no touching to start and end
        jevent.setActive("NO");
        particb.setText("Event paused \n Participants so far\n" + Integer.toString(jevent.getParticipants()));

        //update db
        helper.updateJuvinileEventDetails(jevent.getEventid(),"active","NO");

    }

    public void goToFeedback(View v){

        Intent intent = new Intent(this, FeedBackActivity.class);
        intent.putExtra("eventObject", (Serializable) jevent);
        startActivity(intent);


    }



}
