package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MasterDataActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener , Serializable {

    private MyRecyclerViewAdapter adapter;
    private MyDbAdapter helper;

    private EditText juvinilename;
    private EditText juvinilecity;
    private EditText juvinileaddress;
    private Integer juvinileid;
    private Integer arrayposition;
    private TextView tvName;

    //arraylist for getting the juviniles
    private  ArrayList<Juvinile> juvinileArray;

    //temp string for filling the temp array
    private String tempArrayFill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_data);

        helper = new MyDbAdapter(this);

        juvinilename=(EditText) findViewById(R.id.etJname);
        juvinilecity=(EditText) findViewById(R.id.etJcity);
        juvinileaddress=(EditText) findViewById(R.id.etJaddress);
        tvName= (TextView) findViewById(R.id.textView);
        juvinileid=0;
        arrayposition=0; //might be the same as it will be but doesn't actually matter
        tempArrayFill="";

        //temp array for displaying the event data
        ArrayList<String> tempJuvinileArray;
        tempJuvinileArray= new ArrayList<>();

        //let's make this so that it only fetches upcoming events
        //array for the JuvinileEvent objects
        //ArrayList<JuvinileEvent> juvinileEventArray = new ArrayList<JuvinileEvent>();



        juvinileArray = helper.getJuvinileList(); // method in dbadapter to fetch upcoming events


        //fill the array
        for (Juvinile tempjuvi:juvinileArray){
            tempArrayFill=tempjuvi.getName() +" " + tempjuvi.getCity()+" "+ tempjuvi.getAddress(); //this means the timestamp needs to be configured in the getter
            tempJuvinileArray.add(tempArrayFill);
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMaster);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, tempJuvinileArray);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        juvinilename.setText(juvinileArray.get(position).getName());
        juvinilecity.setText(juvinileArray.get(position).getCity());
        juvinileaddress.setText(juvinileArray.get(position).getAddress());
        juvinileid=juvinileArray.get(position).getJuvinileID();
        arrayposition=position;


    }


    public void createOrUpdateJuvinile(View v) {

        long insertid=0;
        //Integer juvilineid=0;

        //if the name on the line is the same clicked -> update. else insert a new one
        if(juvinileArray.get(arrayposition).getName().equals(juvinilename.getText().toString())){

            //updating the object itself is sort of useless because we're going to create new ones. let's do it for practice anyway..
            juvinileArray.get(arrayposition).setCity(juvinilecity.getText().toString());
            juvinileArray.get(arrayposition).setAddress(juvinileaddress.getText().toString());

            helper.updateJuvinileDetails(juvinileArray.get(arrayposition).getJuvinileID(),"city",juvinilecity.getText().toString());
            helper.updateJuvinileDetails(juvinileArray.get(arrayposition).getJuvinileID(),"address",juvinileaddress.getText().toString());
            Toast.makeText(this, "Juvinile updated", Toast.LENGTH_SHORT).show();
        }
        else if (juvinilename.getText().toString() != null && !juvinilename.getText().toString().trim().isEmpty() ){

            Juvinile newJuvinile = new Juvinile(juvinilename.getText().toString(),juvinilecity.getText().toString(),juvinileaddress.getText().toString());

            String[] sqlargs = {juvinilename.getText().toString(), juvinileaddress.getText().toString(), juvinilecity.getText().toString()};
            insertid = helper.insertDataJuvinile(sqlargs);
            Toast.makeText(this, "New Juvinile saved", Toast.LENGTH_SHORT).show();

        }
        else {
            tvName.setTextColor(Color.RED);
            System.out.println("nothing to do");
            Toast.makeText(this, "Juvinile needs to be select or input", Toast.LENGTH_SHORT).show();

        }
            //update the data

            juvinileArray = helper.getJuvinileList(); // method in dbadapter to fetch upcoming events

            //temp array for displaying the event data
            ArrayList<String> tempJuvinileArray = new ArrayList<>();

            //fill the array
            for (Juvinile tempjuvi : juvinileArray) {
                tempArrayFill = tempjuvi.getName() + " " + tempjuvi.getCity() + " " + tempjuvi.getAddress(); //this means the timestamp needs to be configured in the getter
                tempJuvinileArray.add(tempArrayFill);
            }

            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.rvMaster);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(this, tempJuvinileArray);
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        }


    }

    //would be nice to have transition to events of a certain location


