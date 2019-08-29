package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminTools extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyDbAdapter helper;
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);

        helper = new MyDbAdapter(this);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
/*
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMaster);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
*/

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    public void testReading(View v) {

        TextView juviListStr;
        juviListStr = (TextView) findViewById(R.id.testiLista);

        ArrayList<Juvinile> juvinileArray = new ArrayList<Juvinile>();

        ArrayList<String> tesArray = new ArrayList<>();
        tesArray.add("joopatijoopajoo");

        juvinileArray = helper.getJuvinileList();

        //TODO kattelepa tasta eteenpain
        juviListStr.setText("");

        for (Juvinile tempjuvi:juvinileArray){
            juviListStr.setText(juviListStr.getText().toString()    + "\n" +
            tempjuvi.getName() +" " + tempjuvi.getCity() +" "+ tempjuvi.getAddress());
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMaster);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tesArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    public void populateInit(View v) {

        String[] juviniledata = new String[3];
        String[] eventdata = new String[7];
        String[] feedbackdata = new String[4];
        System.out.println("populateInit1");

        //String name=juviniledata[0];
        //String address=juviniledata[1];
        //String city=juviniledata[2];

        //First location
        juviniledata[0]="Betoni";juviniledata[1]="Harmaatie 12";juviniledata[2]="Kouvola";
        insertInitJuv(juviniledata);
        //Second loc
        juviniledata[0]="Nistilä";juviniledata[1]="Neulakuja 9 A 67";juviniledata[2]="Wanda";
        insertInitJuv(juviniledata);


        //String ejuvileid=data[0];
        //String ename=data[1];
        //String plannedStart=data[2];
        //String plannedEnd=data[3];
        //String minAge=data[4];
        //String maxAge=data[5];
        //String active=data[6];

        //First Event
        eventdata[0]="1";eventdata[1]="Ykskemut";eventdata[2]="2109-08-29 14:30:00";eventdata[3]="2109-08-29 16:30:00";eventdata[4]="10";eventdata[5]="12";eventdata[6]="NO";
        insertInitEvent(eventdata);
        //Second event
        eventdata[0]="1";eventdata[1]="Kakskemut";eventdata[2]="2109-08-30 16:30:00";eventdata[3]="2109-08-29 22:30:00";eventdata[4]="15";eventdata[5]="18";eventdata[6]="NO";
        insertInitEvent(eventdata);


        //String jeventid=data[0];
        //String grade=data[1];
        //String feedback=data[2];
        //String participant=data[3];

        //First feedback
        feedbackdata[0]="1";feedbackdata[1]="2";feedbackdata[2]="Ihan totaalista kuraa";feedbackdata[3]="Anon";
        insertInitFeedB(feedbackdata);
        //Second feedback
        feedbackdata[0]="2";feedbackdata[1]="5";feedbackdata[2]="Ihan mehua";feedbackdata[3]= null;
        insertInitFeedB(feedbackdata);


    }

    public void insertInitJuv(String[] data){
        //System.out.println("insert1");
        long id = helper.insertDataJuvinile(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Juvinile Insertion Unsuccessful");
            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Juvinile Insertion Successful");
            //Name.setText("");
            //Pass.setText("");
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



}


