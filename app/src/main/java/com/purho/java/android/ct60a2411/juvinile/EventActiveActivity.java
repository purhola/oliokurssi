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

    private Button particb;
    private long id;


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


        //check if the event is active
        // if yes, get the participant count and add one

        String isactive = jevent.getActive();

        if(isactive.equals("yes")) {
            jevent.setParticipants(jevent.getParticipants()+1);
            Integer count=jevent.getParticipants();
            particb.setText("Event active \n Participants \n" + Integer.toString(count));
        }
        else particb.setText("Event not active \n Participants so far\n" + Integer.toString(jevent.getParticipants()));
    }

    public void activateEvent(View v) {

        id=0;

        jevent.setActive("yes");
        helper.updateJuvinileEventDetails(jevent.getEventid(),"active","yes");
        //particb.setTextColor(android.R.color.holo_green_dark);
        particb.setText("Event active \n Participants \n" + Integer.toString(jevent.getParticipants()));

    }

    public void deactivateEvent(View v) {

        jevent.setActive("no");
  //      long id = helper.updateJuvinileEventDetails(jevent.getEventid(),"active","no");
        particb.setText("Event not active \n Participants so far\n" + Integer.toString(jevent.getParticipants()));
    }

    public void pauseEvent(View v) {

        jevent.setActive("no");
    //    long id = helper.updateJuvinileEventDetails(jevent.getEventid(),"active","no");
        particb.setText("Event not active \n Participants so far\n" + Integer.toString(jevent.getParticipants()));
    }

    public void goToFeedback(View v){

        Intent intent = new Intent(this, FeedBackActivity.class);
        intent.putExtra("eventObject", (Serializable) jevent);
        startActivity(intent);


    }


}
