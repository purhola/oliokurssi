package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewEventFeedBacksActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener, Serializable {

    MyRecyclerViewAdapter adapter;
    MyDbAdapter helper;
    JuvinileEvent jevent;
    EventFeedBack singleFeedBack;
    ArrayList<EventFeedBack> feedBacksArray;

    TextView tvfbgiver;
    TextView tvgrade;
    EditText edtfeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_feed_backs);

        helper = new MyDbAdapter(this);
        //jevent= new JuvinileEvent();
        feedBacksArray = new ArrayList<>();

        tvfbgiver=(TextView) findViewById(R.id.tvFeedBacker);
        tvgrade=(TextView) findViewById(R.id.tvGradeGiven);
        edtfeedback=(EditText) findViewById(R.id.etFeedBack);

        Intent i = getIntent();
        i.getSerializableExtra("passEventObjectLive");
        if (i.getSerializableExtra("passEventObjectLive") == null) {

            System.out.println("***************Can't find the event****************"); // TODO this could/should be something smarter, a msg or break or both..
        } else {
            jevent = (JuvinileEvent) i.getSerializableExtra("passEventObjectLive");
        }
        //get the feedbacks from db and throw them to a recyclerview.

        feedBacksArray = helper.getEventFeedBacks(jevent.getEventid()); // method in dbadapter to fetch upcoming events
        if(feedBacksArray.size()>0) {
            ArrayList<String> tempFeedBackArray = new ArrayList<>();
            String tempArrayFill = "";

            //fill the array
            for (EventFeedBack tempfeedback : feedBacksArray) {
                tempArrayFill = tempfeedback.getGrade() + " " + tempfeedback.getFbgiver() + " " + tempfeedback.getFeedback();
                tempFeedBackArray.add(tempArrayFill);
            }

            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.rvFeedBack);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(this, tempFeedBackArray);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();


        tvfbgiver.setText(feedBacksArray.get(position).getFbgiver());
        tvgrade.setText(feedBacksArray.get(position).getGrade());
        edtfeedback.setText(feedBacksArray.get(position).getFeedback());


        /*
        TODO TBD
        juvinilename.setText(juvinileArray.get(position).getName());
        juvinilecity.setText(juvinileArray.get(position).getCity());
        juvinileaddress.setText(juvinileArray.get(position).getAddress());
        juvinileid=juvinileArray.get(position).getJuvinileID();
        arrayposition=position;


       */

    }

}
