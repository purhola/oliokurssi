package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminToolsActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyDbAdapter helper;
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);

        helper = new MyDbAdapter(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        System.out.println("ja positiohan on" + position);


    }

   /* public void testReading(View v) {
        //array for the juviline objects
        ArrayList<Juvinile> juvinileArray = new ArrayList<Juvinile>();
        //temp array for displaying the juviline data
        ArrayList<String> tesArray = new ArrayList<>();
        //temp string for filling the tesArray
        String tesArrayFill="";

        juvinileArray = helper.getJuvinileList();

        //fill the array
        for (Juvinile tempjuvi:juvinileArray){
            tesArrayFill=tempjuvi.getName() +" " + tempjuvi.getCity() +" "+ tempjuvi.getAddress();
        //    tesArray.add(tesArrayFill);
        }



        JuvinileEvent jevent= new JuvinileEvent();

        //jevent=helper.getSingleEvent(5);

        tesArrayFill=jevent.getEventname() +" " + jevent.getEventid() +" "+ jevent.getPlanned_start() +" " + jevent.getPlanned_end();

        tesArray.add(tesArrayFill);



        // set up the RecyclerView and display the results
        RecyclerView recyclerView = findViewById(R.id.rvMaster);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tesArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }


    */
    public void populateInit(View v) {

        String[] juviniledata = new String[3];
        String[] eventdata = new String[7];
        String[] feedbackdata = new String[4];


        //First location. These could've been assigned differently, but now it's done like this
        juviniledata[0]="Betoniviidakko";juviniledata[1]="Harmaatie 12";juviniledata[2]="Kouvola";
        insertInitJuv(juviniledata);
        //Second loc
        juviniledata[0]="Vantaan huoltotila";juviniledata[1]="Neulakuja 9 A 67";juviniledata[2]="Wanda";
        insertInitJuv(juviniledata);
        //and so on
        juviniledata[0]="Lappeen nuorisovankila";juviniledata[1]="Konnunsuontie 101";juviniledata[2]="lappeen Ranta";
        insertInitJuv(juviniledata);
        juviniledata[0]="Ivalon kylmäkammio";juviniledata[1]="Igluhotelli 2";juviniledata[2]="Hanko";
        insertInitJuv(juviniledata);


        //String ejuvileid=data[0];
        //String ename=data[1];
        //String plannedStart=data[2];
        //String plannedEnd=data[3];
        //String minAge=data[4];
        //String maxAge=data[5];
        //String active=data[6];

        //First Event
        eventdata[0]="1";eventdata[1]="Itsehillintää";eventdata[2]="2019-08-29 14:30:00";eventdata[3]="2109-08-29 16:30:00";eventdata[4]="15";eventdata[5]="16";eventdata[6]="NO";
        insertInitEvent(eventdata);
        //Second event
        eventdata[0]="1";eventdata[1]="Siivousharjoituksia";eventdata[2]="2019-08-30 16:30:00";eventdata[3]="2109-08-29 22:30:00";eventdata[4]="10";eventdata[5]="11";eventdata[6]="NO";
        insertInitEvent(eventdata);

        eventdata[0]="1";eventdata[1]="Epämehu rock";eventdata[2]="2019-09-20 16:30:00";eventdata[3]="2109-09-22 22:30:00";eventdata[4]="15";eventdata[5]="18";eventdata[6]="NO";
        insertInitEvent(eventdata);

        eventdata[0]="1";eventdata[1]="Ohjaajien pikkujoulut";eventdata[2]="2019-09-12 16:30:00";eventdata[3]="2109-09-22 22:30:00";eventdata[4]="15";eventdata[5]="18";eventdata[6]="NO";
        insertInitEvent(eventdata);


        //String jeventid=data[0];
        //String grade=data[1];
        //String feedback=data[2];
        //String participant=data[3];

        //First feedback
        feedbackdata[0]="1";feedbackdata[1]="2";feedbackdata[2]="Ihan totaalista kuraa";feedbackdata[3]="Anon";
        insertInitFeedB(feedbackdata);
        //Second feedback
        feedbackdata[0]="2";feedbackdata[1]="5";feedbackdata[2]="Hyvin meni mutta oisitte ees yrittäny näyttää iloisilta";feedbackdata[3]= null;
        insertInitFeedB(feedbackdata);
        //and so on
        feedbackdata[0]="4";feedbackdata[1]="4";feedbackdata[2]="Hitsin jee";feedbackdata[3]= null;
        insertInitFeedB(feedbackdata);
        feedbackdata[0]="1";feedbackdata[1]="1";feedbackdata[2]="Kun ei osaa niin ei osaa.";feedbackdata[3]= null;
        insertInitFeedB(feedbackdata);


    }

    public void insertInitJuv(String[] data){

        long id = helper.insertDataJuvinile(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Juvinile Insertion Unsuccessful");
        } else
        {
            Message.message(getApplicationContext(),"Juvinile Insertion Successful");
        }


    }

    public void insertInitEvent(String[] data){

        long id = helper.insertDataEvents(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Event Insertion Unsuccessful");

            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Event Insertion Successful");

            //Name.setText("");
            //Pass.setText("");
        }

    }

    public void insertInitFeedB(String[] data){

        long id = helper.insertDataFeedback(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Feedback Insertion Unsuccessful");
            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Feedback Insertion Successful");
            //Name.setText("");
            //Pass.setText("");
        }

    }

    public void testEventRead(View v) {

        helper.updateJuvinileEventDetails(5,"eventname","paivitetty");

                //JuvinileEvent jevent= new JuvinileEvent();

        //jevent=helper.getSingleEvent(5);
    }

    public void jotain(View r) {

    }


}


