package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ViewEventFeedBacksActivity extends AppCompatActivity {

    MyDbAdapter helper;
    JuvinileEvent jevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_feed_backs);

        helper = new MyDbAdapter(this);

        Intent i = getIntent();
        i.getSerializableExtra("eventObject");
        if (i.getSerializableExtra("eventObject") == null) {
            //jevent = new JuvinileEvent();
            System.out.println("***************Can't find the event****************"); // TODO this could/should be something smarter, a msg or break or both..
        } else {
            jevent = (JuvinileEvent) i.getSerializableExtra("eventObject");
        }

        getFeedBacks();

    }

    public void getFeedBacks(){

        //get the feedbacks from db and throw them to a recyclerview.

        //sql should be easy. select from feedback where eventid=jevent.getEventid.......

        //layout could be like in juvinile-listing
        //list, and below there would be shown the selected one.


    }


}
