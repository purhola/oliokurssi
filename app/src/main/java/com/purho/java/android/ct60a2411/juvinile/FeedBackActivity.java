package com.purho.java.android.ct60a2411.juvinile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class FeedBackActivity extends AppCompatActivity implements Serializable {

    MyDbAdapter helper;
    JuvinileEvent jevent;
    EventFeedBack feedback;

    private EditText txtfeedback;
    private EditText grade;
    private EditText name;
    private TextView txtviewgrade;

    private String[] sqlargs;

    private Button btnsavefeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        helper = new MyDbAdapter(this);

        txtfeedback=(EditText) findViewById(R.id.etFeedBack);
        grade=(EditText) findViewById(R.id.etGrade);
        name=(EditText) findViewById(R.id.etName);
        btnsavefeedback=(Button) findViewById(R.id.btnSaveFeedBack);
        txtviewgrade=(TextView) findViewById(R.id.tvGrade);


        Intent i = getIntent();
        i.getSerializableExtra("eventObject");
        if (i.getSerializableExtra("eventObject") == null) {
            //jevent = new JuvinileEvent();
            System.out.println("***************Can't find the event****************"); // TODO this could/should be something smarter, a msg or break or both..
        } else {
            jevent = (JuvinileEvent) i.getSerializableExtra("eventObject");
        }
    }


    public void saveFeedBack(View v){

    //TODO save to object, save to db
    // notify, that feedback is given

        String strfeedback;
        String strgrade;
        String strname;


        strgrade=grade.getText().toString();


        if (strgrade.equals("1") || strgrade.equals("2") ||strgrade.equals("3") ||strgrade.equals("4") ||strgrade.equals("5")){
            strfeedback = txtfeedback.getText().toString();
            if (strfeedback == null || strfeedback.isEmpty()) {
                strfeedback = "";
            }

            strname = name.getText().toString();
            if (strname == null || strname.isEmpty() || "Name".equals(strname)) {
                strname = "Anonymous";
            }

            //create a new object for this feedback
            feedback = new EventFeedBack(jevent.getEventid(), strgrade, strfeedback, strname);
            //
            sqlargs = new String[]{Integer.toString(feedback.getEventid()), feedback.getGrade(), feedback.getFeedback(), feedback.getFbgiver()};


            helper.insertDataFeedback(sqlargs);
            btnsavefeedback.setText("Saved");
            btnsavefeedback.setTextColor(Color.GRAY);
        }
        else {
            txtviewgrade.setTextColor(Color.RED);
        }



    }







}
