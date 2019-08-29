package com.purho.java.android.ct60a2411.juvinile;

import android.content.Context;

public class SQLOperations {

    MyDbAdapter helper= new MyDbAdapter(this);

    //INSERTIT

    public long insertJuv(String[] data){
        //System.out.println("insert1");
        long id = helper.insertDataJuvinile(data);
        return id;
    }

    public long insertEvent(String[] data){

        long id = helper.insertDataEvents(data);
        return id;
    }

    public long insertFeedB(String[] data){

        long id = helper.insertDataFeedback(data);
        return id;
    }

}
