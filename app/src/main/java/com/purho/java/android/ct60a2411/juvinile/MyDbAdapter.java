package com.purho.java.android.ct60a2411.juvinile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

//note to self: THE DB GOES TO
// data/data/APP_Name/databases/DATABASE_NAME

//TODO stringsql
//compileStatement()
//Compiles an SQL statement into a reusable pre-compiled statement object.
//Use this method as below
//compileStatement(String sql)
//Pass sql statement as a parameter in string the format.

public class MyDbAdapter {

    myDbHelper myhelper;

     public MyDbAdapter(Context context)
     {
         myhelper = new myDbHelper(context);
     }

    //INSERT

    //this is originally an example insert of one column. need one for each table TODO
    //here data needs to be name, address, city
    public long insertDataJuvinile(String[] data)
    {
        //String name=data[0];
        //String address=data[1];
        //String city=data[2];

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.JUVINILENAME, data[0]);
        contentValues.put(myDbHelper.ADDRESS, data[1]);
        contentValues.put(myDbHelper.CITY, data[2]);

        long insert = dbb.insert(myDbHelper.TABLE_JUVINILE, null , contentValues);
        return insert;
    }

    public long insertDataEvents(String[] data)
    {
        //String ejuvileid=data[0];
        //String ename=data[1];
        //String plannedStart=data[2];
        //String plannedEnd=data[3];
        //String minAge=data[4];
        //String maxAge=data[5];

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.EJUVINILEID, data[0]); //needs to figured out by now
        contentValues.put(myDbHelper.EVENTNAME, data[1]);
        contentValues.put(myDbHelper.EVENTPLANNEDSTART, data[2]);
        contentValues.put(myDbHelper.EVENTPLANNEDEND, data[3]);
        contentValues.put(myDbHelper.MINAGE, data[4]);
        contentValues.put(myDbHelper.MAXAGE, data[5]);
        contentValues.put(myDbHelper.ACTIVE, data[6]);

        long insert = dbb.insert(myDbHelper.TABLE_EVENT, null , contentValues);
        return insert;
    }

    public long insertDataFeedback(String[] data)
    {
        //String jeventid=data[0];
        //String grade=data[1];
        //String feedback=data[2];
        //String participant=data[3];

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.JEVENTID, data[0]); //needs to figured out by now
        contentValues.put(myDbHelper.GRADE, data[1]);
        contentValues.put(myDbHelper.FEEDBACK, data[2]);
        contentValues.put(myDbHelper.PARTICIPANT, data[3]); //default Anonymous gets perhaps overwritten if this is null, so needs to be figured out by this time

        long insert = dbb.insert(myDbHelper.TABLE_FEEDBACK, null , contentValues);
        return insert;
    }


    //now this is for reading. should be interesting.
    //below for table juvinile, need to make this generic for joins
    /*
    public String getJuvinileData()

    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.TEXT,myDbHelper.TEXT2};
        Cursor cursor =db.query(myDbHelper.TABLE_JUVINILE,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String tekstin =cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT));
            String  lisatekstin =cursor.getString(cursor.getColumnIndex(myDbHelper.TEXT2));
            buffer.append(cid+ "   " + tekstin + "   " + lisatekstin +" \n");
        }
        return buffer.toString();
    }*/


    //SELECT

    //lets fetch the list of places - need to make one for events, which would be combining
    //also need one for feedbacks concerning one event at  time
    // TODO find a way to store results of multiple columns
    public ArrayList<String> getJuvinileList() //the select query here as string parameter?
    {

        //list for storing the results
        ArrayList<String> juvinilesList = new ArrayList<String>();
        String name=""; //could be also location
        String selectQuery = "SELECT * FROM " + myDbHelper.TABLE_JUVINILE; //suppose this coulb be generic and provided via string?

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        //go through the whole table
        if(c.moveToFirst()) {
            do {
                name=c.getString(c.getColumnIndex(myDbHelper.JUVINILENAME));
                //add the row to the list
                juvinilesList.add(name);
            } while (c.moveToNext());
            Log.d("array",juvinilesList.toString());
        }
        return juvinilesList;
    }


    //DELETE

    //need many of these, or preferably make this delete a more generic taking only a fully prepared sql as parameter
    public  int delete(String[] ID) //poistetaan id ID taulusta TABLE_NAME
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();


        int count =db.delete(myDbHelper.TABLE_JUVINILE ,myDbHelper.JUVINILEID + " = ?", ID);
        return  count;
    }

    //UPDATE

    //Need many of these. 1 update juvinile, 2 update event, 3 update feedback
    //or should we try and make one generic updater that only takes a fully prepared string as parameter
    //below a version that simply updates the name of the juvinile, which is mainly useless
    public int updateName(String oldText , String newText)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.JUVINILENAME,newText);
        String[] whereArgs= {oldText};
        int count =db.update(myDbHelper.TABLE_JUVINILE,contentValues, myDbHelper.JUVINILENAME+" = ?",whereArgs );
        return count; //paluukoodi jee
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "playtodella3";    // Database Name
        private static final int DATABASE_Version = 2;    // Database Version

        //table juvinile
        private static final String TABLE_JUVINILE = "juvinile";   // table juvinile as in nuorisotila
        private static final String JUVINILEID = "juvinileid";     // col id (Primary Key) (INTEGER PRIMARY KEY AUTOINCREMENT)
        private static final String JUVINILENAME = "name";    //col name for the juvinile varchar(50)
        private static final String ADDRESS = "address";    //col address line varchar(50)
        private static final String CITY = "city";    //col city varchar(20)
        private static final String JDBTIME = "dbtime"; // col time when created timestamp
        private static final String JDBCHANGE = "dbchange"; // col time when changed timestamp

        //table event
        private static final String TABLE_EVENT = "event";   // table event as in tapahtuma
        private static final String EVENTID = "eventid";   // col id (Primary Key) (INTEGER PRIMARY KEY AUTOINCREMENT)
        private static final String EJUVINILEID = "juvinileid";   // col foreign id to location
        private static final String EVENTNAME = "eventname";   // col foreign id to location
        private static final String EVENTPLANNEDSTART = "plannedstart";   // col start timestamp (yyyy-mm-dd hh:mm:00) or whatever format goes nicely
        private static final String EVENTPLANNEDEND = "plannedend";   // col start timestamp (yyyy-mm-dd hh:mm:00) or whatever format goes nicely
        private static final String EVENTSTART = "start";   // col start timestamp default null
        private static final String EVENTEND = "end";   // col end timestamp default null
        private static final String MINAGE = "minage";   // col minimum age of participants int
        private static final String MAXAGE = "maxage";   // col maximum age of participants int
        private static final String PARTICIPANTS_COUNT = "participants";   // col number of participants int
        private static final String ACTIVE = "active";   // col active yes/no
        private static final String EDBTIME = "dbtime"; // col time when created timestamp
        private static final String EDBCHANGE = "dbchange"; // col time when changed timestamp

        //table feedback
        private static final String TABLE_FEEDBACK = "feedback";   // table feedback as in palaute
        private static final String FEEDBACKID = "feedbackid";   // col id (Primary Key)  (INTEGER PRIMARY KEY AUTOINCREMENT)
        private static final String JEVENTID = "eventid";   // col foreign id to feedback target event
        private static final String GRADE = "grade";   // col grade for the event int (1-5 restricted maybe)
        private static final String FEEDBACK = "feedback";   // col for the free form feedback varchar(1000)
        private static final String PARTICIPANT = "participant"; // col feedback giver varchar(50) default null/anonymous
        private static final String FDBTIME = "dbtime"; // col time when created timestamp
        private static final String FDBCHANGE = "dbchange"; // col time when changed timestamp


        //sentences to handle table juvinile
        private static final String CREATE_TABLE_JUVINILE =
                "CREATE TABLE " + TABLE_JUVINILE + " ("
                        + JUVINILEID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + JUVINILENAME + " TEXT,"
                        + ADDRESS + " TEXT, "
                        + CITY + " TEXT,"
                        + JDBTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                        + JDBCHANGE + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ");";
        private static final String DROP_TABLE_JUVINILE = "DROP TABLE IF EXISTS " + TABLE_JUVINILE;

        //sentences to handle table event
        private static final String CREATE_TABLE_EVENT =
                "CREATE TABLE " + TABLE_EVENT + " ("
                        + EVENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + EJUVINILEID + " INTEGER NOT NULL, "
                        + EVENTNAME + " TEXT, "
                        + EVENTPLANNEDSTART + " DATETIME NOT NULL, "
                        + EVENTPLANNEDEND + " DATETIME NOT NULL, " //could be not null as could the previous
                        + EVENTSTART + " DATETIME DEFAULT NULL, "
                        + EVENTEND + " DATETIME DEFAULT NULL,"
                        + MINAGE + " INTEGER, "
                        + MAXAGE + " INTEGER, "
                        + PARTICIPANTS_COUNT + " INTEGER DEFAULT 0, "
                        + ACTIVE + " TEXT, "
                        + EDBTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                        + EDBCHANGE + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ");";
        private static final String DROP_TABLE_EVENT = "DROP TABLE IF EXISTS " + TABLE_EVENT;

        //sentences to handle table feedback
        private static final String CREATE_TABLE_FEEDBACK =
                "CREATE TABLE " + TABLE_FEEDBACK + " ("
                        + FEEDBACKID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + JEVENTID + " INTEGER NOT NULL,"
                        + GRADE + " INTEGER,"
                        + FEEDBACK + " TEXT, "
                        + PARTICIPANT + " TEXT DEFAULT 'Anonymous', "
                        + FDBTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                        + FDBCHANGE + " DATETIME DEFAULT CURRENT_TIMESTAMP "
                        + ");";
        private static final String DROP_TABLE_FEEDBACK = "DROP TABLE IF EXISTS " + TABLE_FEEDBACK;

        //**************************************************************************************
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE_JUVINILE);
                db.execSQL(CREATE_TABLE_EVENT);
                db.execSQL(CREATE_TABLE_FEEDBACK);
                //System.out.println("tehtiinkohan jotain");
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE_JUVINILE);
                db.execSQL(DROP_TABLE_EVENT);
                db.execSQL(DROP_TABLE_FEEDBACK);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }


    }
}
