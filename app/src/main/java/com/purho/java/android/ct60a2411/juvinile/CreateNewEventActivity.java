package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateNewEventActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {


    MyDbAdapter helper;
    MyRecyclerViewAdapter adapter;
    //array for the Juvinile objects
    ArrayList<Juvinile> juvinileArray;
    Juvinile tempjuvi;
    //temp array for displaying the Juviniles
    ArrayList<String> tempJuvinileArray;

    TextView location;
    EditText eventname;
    EditText starttime;
    EditText endtime;
    EditText minage;
    EditText maxage;
    Integer posofjuvi;
    String juvinilename;

    //temp variables for checking and saving the given text
    String evname,plannedstime,plannedetime,strminage,strmaxage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);

        location= (TextView) findViewById(R.id.tvLocation);
        eventname= (EditText) findViewById(R.id.etEventName);
        starttime=(EditText) findViewById(R.id.etStartTime);
        endtime=(EditText) findViewById(R.id.etEndTime);
        minage=(EditText) findViewById(R.id.etMinAge);
        maxage=(EditText) findViewById(R.id.etMaxAge);


        helper = new MyDbAdapter(this);

        //events= (TextView) findViewById(R.id.tvEventsDisplayed);


        //temp array for displaying the Juviniles
        tempJuvinileArray = new ArrayList<>();
        //temp string for filling the temp array
        String tempArrayFill="";
        //array to put the juviniles into
        juvinileArray=new ArrayList<>();
        juvinileArray = helper.getJuvinileList(); // method in dbadapter to fetch upcoming events


        //fill the array
        for (Juvinile tempjuvi:juvinileArray){
            tempArrayFill=tempjuvi.getName() +" " + tempjuvi.getAddress() +" "+ tempjuvi.getCity();
            tempJuvinileArray.add(tempArrayFill);
        }

        // set up the RecyclerView and display the results
        RecyclerView recyclerView = findViewById(R.id.rvLocations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tempJuvinileArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    //@Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        //save the position / index
        posofjuvi=position;
        //set up the location data
        location.setText(adapter.getItem(position));



    }

    public void saveEvent(View v) {

        //integer which will be set to 1 if required info is missing -> do nothing until ok.
        Integer error=0;

        //check and read the information line by line
        if( eventname.getText().toString() != null && !eventname.getText().toString().trim().isEmpty())
            String evname=eventname.getText().toString();
        else {eventname.setTextColor(Color.RED); error=1;}

        if( starttime.getText().toString() != null && !starttime.getText().toString().trim().isEmpty())
            String plannedstime=starttime.getText().toString();
        else {starttime.setTextColor(Color.RED); error=1;}

        if( endtime.getText().toString() != null && !endtime.getText().toString().trim().isEmpty())
            String plannedetime=endtime.getText().toString();
        else {endtime.setTextColor(Color.RED); error=1;}

        if( minage.getText().toString() != null && !minage.getText().toString().trim().isEmpty())
            String strminage=minage.getText().toString();
        else {minage.setTextColor(Color.RED); error=1;}

        if( maxage.getText().toString() != null && !maxage.getText().toString().trim().isEmpty())
            String strmaxage=maxage.getText().toString();
        else {maxage.setTextColor(Color.RED); error=1;}


        juvinilename= juvinileArray.get(posofjuvi).getName();


        JuvinileEvent createEvent= new JuvinileEvent(juvinilename,evname,plannedstime,plannedetime,Integer.parseInt(strminage),Integer.parseInt(strmaxage);








        //create new JuvinileEvent object


        //write the db

    }

}
