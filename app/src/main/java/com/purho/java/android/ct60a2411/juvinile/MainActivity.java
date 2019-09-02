package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    MyDbAdapter helper;
    MyRecyclerViewAdapter adapter;
    //array for the JuvinileEvent objects
    ArrayList<JuvinileEvent> juvinileEventArray; // = new ArrayList<JuvinileEvent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MyDbAdapter(this);

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
        RecyclerView recyclerView = findViewById(R.id.rvFrontPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tempEventArray);
        recyclerView.setAdapter(adapter);


    }

    public void activity_admin_tools(View v){
        Intent intent = new Intent(this,AdminToolsActivity.class);
        startActivity(intent);
    }

    //does this start it over and over again each time you return back and forth or not?
    public void activity_event_data(View v){
        Intent intent = new Intent(this,EventDataActivity.class);
        startActivity(intent);
    }

    public void activity_master_data(View v){
        Intent intent = new Intent(this,MasterDataActivity.class);
        startActivity(intent);
    }


    /*public void activity_master_data(View v){
        Intent intent = new Intent(this,ViewSingleEventActivity.class)
        startActivity(intent);
    }
    */
    public void activity_new_event(View v){
        Intent intent = new Intent(this,CreateNewEventActivity.class);
        startActivity(intent);
    }


}
