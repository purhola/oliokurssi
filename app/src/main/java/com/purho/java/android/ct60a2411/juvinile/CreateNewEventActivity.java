package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateNewEventActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {


    private MyDbAdapter helper;
    private MyRecyclerViewAdapter adapter;
    //array for the Juvinile objects
    private ArrayList<Juvinile> juvinileArray;
    private Juvinile tempjuvi;
    //temp array for displaying the Juviniles
    private ArrayList<String> tempJuvinileArray;

    private TextView location;
    private TextView tvname;
    private TextView tvstart;
    private TextView tvend;
    private TextView tvminage;
    private TextView tvmaxage;

    private EditText eventname;
    private EditText starttime;
    private EditText endtime;
    private EditText minage;
    private EditText maxage;
    private Integer posofjuvi;
    private String juvinilename;

    //temp variables for checking and saving the given text
    private String evname,plannedstime,plannedetime,strminage,strmaxage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //or SOFT_INPUT_ADJUST_NOTHING)

        location= (TextView) findViewById(R.id.tvLocation);

        tvname= (TextView) findViewById(R.id.tvNewEventName);
        tvstart= (TextView) findViewById(R.id.tvPlannedStart);
        tvend= (TextView) findViewById(R.id.tvPlannedEnd);
        tvminage= (TextView) findViewById(R.id.tvMinAge);
        tvmaxage= (TextView) findViewById(R.id.tvMaxAge);


        eventname= (EditText) findViewById(R.id.etNewEventName);
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
            tempArrayFill=tempjuvi.getName() +", " + tempjuvi.getAddress() +" "+ tempjuvi.getCity();
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
        //if a data is null or empty mark the corresponding textview red
        Integer error=0;

        //check and read the information line by line
        if(location.getText().toString() != null && !location.getText().toString().trim().isEmpty())
        {}
        else {location.setTextColor(Color.RED);error=1;}

        if( eventname.getText().toString() != null && !eventname.getText().toString().trim().isEmpty())
            evname=eventname.getText().toString();
        else {tvname.setTextColor(Color.RED); error=1;}

        if( starttime.getText().toString() != null && !starttime.getText().toString().trim().isEmpty())
            plannedstime=starttime.getText().toString();
        else {tvstart.setTextColor(Color.RED); error=1;}

        if( endtime.getText().toString() != null && !endtime.getText().toString().trim().isEmpty())
            plannedetime=endtime.getText().toString();
        else {tvend.setTextColor(Color.RED); error=1;}

        if( minage.getText().toString() != null && !minage.getText().toString().trim().isEmpty())
            strminage=minage.getText().toString();
        else {tvminage.setTextColor(Color.RED); error=1;}

        if( maxage.getText().toString() != null && !maxage.getText().toString().trim().isEmpty())
            strmaxage=maxage.getText().toString();
        else {tvmaxage.setTextColor(Color.RED); error=1;}

        juvinilename= juvinileArray.get(posofjuvi).getName();
        String strjuvinileid = Integer.toString(juvinileArray.get(posofjuvi).getJuvinileID());

        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String start_to_object="";
        String end_to_object="";

        try {
            Date tempStart  = dftarg.parse(plannedstime);
            start_to_object=dforig.format(tempStart);

        } catch (ParseException ex) {System.out.println("DateConversionFailed in createnewactivity");}
        finally {}

        try {
            Date tempEnd  = dftarg.parse(plannedetime);
            end_to_object=dforig.format(tempEnd);

        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}






        if(error==0) {
            //create new JuvinileEvent object
            JuvinileEvent createEvent= new JuvinileEvent(juvinilename,evname,plannedstime,plannedetime,Integer.parseInt(strminage),Integer.parseInt(strmaxage));

            //write the db
            String[] sqlargs={strjuvinileid,evname,start_to_object,end_to_object,strminage,strmaxage,"NO"};
            //System.out.println("SQL STRINGI " + sqlargs.toString());
            helper.insertDataEvents(sqlargs);

        }



    }

}
