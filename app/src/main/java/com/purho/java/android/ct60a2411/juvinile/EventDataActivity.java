package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class EventDataActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, Serializable {

    private MyDbAdapter helper;
    private MyRecyclerViewAdapter adapter;
    //array for the JuvinileEvent objects
    private ArrayList<JuvinileEvent> juvinileEventArray; // = new ArrayList<JuvinileEvent>();

    private TextView events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        helper = new MyDbAdapter(this);

        events= (TextView) findViewById(R.id.tvEventsDisplayed);

        //let's make this so that it only fetches upcoming events

        //temp array for displaying the event data
        ArrayList<String> tempEventArray = new ArrayList<>();
        //temp string for filling the temp array
        String tempArrayFill="";

        juvinileEventArray = helper.getNewEventData(); // method in dbadapter to fetch upcoming events


        //fill the array
        for (JuvinileEvent tempevent:juvinileEventArray){
            tempArrayFill=tempevent.getJuvinilename() +" " + tempevent.getEventname() +" "+ tempevent.getPlanned_start(); //this means the timestamp needs to be configured in the getter
            tempEventArray.add(tempArrayFill);
        }

        // set up the RecyclerView and display the results
        RecyclerView recyclerView = findViewById(R.id.rvAllEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tempEventArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    public void fetchAgain(View v) {

        //temp array for displaying the event data
        ArrayList<String> tempEventArray = new ArrayList<>();
        //temp string for filling the temp array
        String tempArrayFill="";

        juvinileEventArray = helper.getEventData(); // method in dbadapter to fetch all events

        //fill the array
        for (JuvinileEvent tempevent:juvinileEventArray){
            tempArrayFill=tempevent.getJuvinilename() +" " + tempevent.getEventname() +" "+ tempevent.getPlanned_start(); //this means the timestamp needs to be configured in the getter
            tempEventArray.add(tempArrayFill);
        }

        // set up the RecyclerView and display the results
        RecyclerView recyclerView = findViewById(R.id.rvAllEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tempEventArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        events.setText("All Events");

    }


    //@Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        //new object fo passing the selected event
        JuvinileEvent passEventObject = new JuvinileEvent();

        passEventObject=juvinileEventArray.get(position);

        //open the next activity and pass the selected event
        Intent intent = new Intent(this, ViewSingleEventActivity.class);
        intent.putExtra("eventObject", (Serializable) passEventObject);
        startActivity(intent);
    }

    public void createNewEvent(View v) {

        //open the next activity
        Intent intent = new Intent(this, CreateNewEventActivity.class);
        startActivity(intent);



    }



}
