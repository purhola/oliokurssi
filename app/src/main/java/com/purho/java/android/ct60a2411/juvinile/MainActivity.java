package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    //public void activity_new_event(View v){
    //    Intent intent = new Intent(this,NewJuvinileEvent.class);
    //    startActivity(intent);
    //}


}
