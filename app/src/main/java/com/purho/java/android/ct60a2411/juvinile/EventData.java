package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EventData extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyDbAdapter helper;
    MyRecyclerViewAdapter adapter;

    TextView events;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_data);

        helper = new MyDbAdapter(this);

        events= (TextView) findViewById(R.id.tvEventsDisplayed);

        //let's make this so that it only fetches upcoming events
        //array for the JuvinileEvent objects
        ArrayList<JuvinileEvent> juvinileEventArray = new ArrayList<JuvinileEvent>();
        //temp array for displaying the event data
        ArrayList<String> tempEventArray = new ArrayList<>();
        //temp string for filling the temp array
        String tempArrayFill="";

        juvinileEventArray = helper.getNewEventData(); // dbadapetrin metodi

        //fill the array
        for (JuvinileEvent tempevent:juvinileEventArray){
            tempArrayFill=tempevent.getJuvinilename() +" " + tempevent.getEventname() +" "+ tempevent.getPlanned_start() + " Tap.Nro: " + tempevent.getEventid(); //this means the timestamp needs to be configured in the getter
            tempEventArray.add(tempArrayFill);
        }

        // set up the RecyclerView and display the results
        RecyclerView recyclerView = findViewById(R.id.rvAllEvents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tempEventArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);









    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        System.out.println("ja positiohan on" + position);
        //TODO how can we get to the line clicked!!! need the id from there at least.. best would be if we could just move to a new place (ylläpito) directly

    }

    public void fetchAgain(View v) {
        //array for the JuvinileEvent objects
        ArrayList<JuvinileEvent> juvinileEventArray = new ArrayList<JuvinileEvent>();
        //temp array for displaying the event data
        ArrayList<String> tempEventArray = new ArrayList<>();
        //temp string for filling the temp array
        String tempArrayFill="";

        juvinileEventArray = helper.getEventData(); // dbadapetrin metodi

        //fill the array
        for (JuvinileEvent tempevent:juvinileEventArray){
            tempArrayFill=tempevent.getJuvinilename() +" " + tempevent.getEventname() +" "+ tempevent.getPlanned_start() + " Tap.Nro: " + tempevent.getEventid(); //this means the timestamp needs to be configured in the getter
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








}
