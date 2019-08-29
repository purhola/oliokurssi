package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class AdminTools extends AppCompatActivity {

    MyDbAdapter helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_tools);

        helper = new MyDbAdapter(this);


    }

    public void populateInit(View v) {

        String[] juviniledata = new String[3];
        String[] eventdata = new String[6];
        String[] feedbackdata = new String[4];


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

        //First Event


        //String jeventid=data[0];
        //String grade=data[1];
        //String feedback=data[2];
        //String participant=data[3];



    }

    public void insertInitJuv(String[] data){

        long id = helper.insertDataJuvinile(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Insertion Unsuccessful");
            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Insertion Successful");
            //Name.setText("");
            //Pass.setText("");
        }

    }

    public void insertInitEvent(String[] data){

        long id = helper.insertDataEvents(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Insertion Unsuccessful");
            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Insertion Successful");
            //Name.setText("");
            //Pass.setText("");
        }

    }

    public void insertInitFeedB(String[] data){

        long id = helper.insertDataFeedback(data);
        if(id<=0)
        {
            Message.message(getApplicationContext(),"Insertion Unsuccessful");
            //Name.setText("");
            //Pass.setText("");
        } else
        {
            Message.message(getApplicationContext(),"Insertion Successful");
            //Name.setText("");
            //Pass.setText("");
        }

    }



}


