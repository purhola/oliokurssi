package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MasterDataActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener , Serializable {

    MyRecyclerViewAdapter adapter;
    MyDbAdapter helper;

    private EditText juvinilename;
    private EditText juvinilecity;
    private EditText juvinileaddress;
    private Integer juvinileid;
    private Integer arrayposition;

    //arraylist for getting the juviniles
    private  ArrayList<Juvinile> juvinileArray;

    //temp string for filling the temp array
    String tempArrayFill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_data);

        helper = new MyDbAdapter(this);

        juvinilename=(EditText) findViewById(R.id.etJname);
        juvinilecity=(EditText) findViewById(R.id.etJcity);
        juvinileaddress=(EditText) findViewById(R.id.etJaddress);
        juvinileid=0;
        arrayposition=-1;
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

        long id=0;

        //if a name is given
        //update straigth to the database with "replace"
        //fetch the object list again after that
        String checkname=juvinilename.getText().toString();
        if(checkname != null && !checkname.isEmpty()) {
            String[] sqlargs = {juvinilename.getText().toString(), juvinileaddress.getText().toString(), juvinilecity.getText().toString()};
            id = helper.insertDataJuvinile(sqlargs);

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



        //to update or to make a new one. easier for the database..
        //let's just do it to the base and get them again.

        /*
        //mietis tämä nyt oikein.
        //jos lisätään uutta tai jos päivitetään vanhaa.
        //kantaan tulee aina uusi jos nimi vaihtuu, pitäisi siis varmaan myös
        //tehdä uusi olio.

        ArrayList<String> names = new ArrayList<>();
        String tempname="";

        //check if the given name (Juvinile) already exists
        for (int i=0;i=juvinileArray.size()-1;i++){
            tempname=juvinileArray.get(i).getName();
            names.add(tempname);
        }

        boolean cont = names.contains(juvinilename.getText().toString());

        //if it exists -> update


        //insert new juviline
        if(juvinileid == 0 || juvilin) {
          //juvinileArray.get(arrayposition).setName();

        }

        //else if ()
        //Juvinile newJuvinile= new Juvinile()
*/

    }

    //would be nice to have transition to events of a certain location

}
