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
        //TODO how can we get to the line clicked!!! need the id from there at least.. best would be if we could just move to a new place (ylläpito) directly

    }

    public void testReading(View v) {
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

        jevent=helper.getSingleEvent(5);

        tesArrayFill=jevent.getEventname() +" " + jevent.getEventid() +" "+ jevent.getPlanned_start() +" " + jevent.getPlanned_end();

        tesArray.add(tesArrayFill);



        // set up the RecyclerView and display the results
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
        System.out.println("populateInit1 XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX" );

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
        eventdata[0]="1";eventdata[1]="Ykskemut";eventdata[2]="2019-08-29 14:30:00";eventdata[3]="2109-08-29 16:30:00";eventdata[4]="10";eventdata[5]="12";eventdata[6]="NO";
        insertInitEvent(eventdata);
        //Second event
        eventdata[0]="1";eventdata[1]="Kakskemut";eventdata[2]="2019-08-30 16:30:00";eventdata[3]="2109-08-29 22:30:00";eventdata[4]="15";eventdata[5]="18";eventdata[6]="NO";
        insertInitEvent(eventdata);

        eventdata[0]="1";eventdata[1]="Kolmekemut";eventdata[2]="2019-08-31 16:30:00";eventdata[3]="2109-08-29 22:30:00";eventdata[4]="15";eventdata[5]="18";eventdata[6]="NO";
        insertInitEvent(eventdata);

        eventdata[0]="1";eventdata[1]="Nelkemut";eventdata[2]="2019-09-01 16:30:00";eventdata[3]="2109-08-29 22:30:00";eventdata[4]="15";eventdata[5]="18";eventdata[6]="NO";
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
        System.out.println("insert1   juvi YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
        long id = helper.insertDataJuvinile(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Juvinile Insertion Unsuccessful");
            //Name.setText("");
            System.out.println("JUVI INSERTION UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
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
            System.out.println("EVENT INSERTION UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Event Insertion Successful");
            System.out.println("EVENT INSERTION SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
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


