package com.purho.java.android.ct60a2411.juvinile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;
import java.util.ArrayList;

//note to self: THE DB GOES TO
// data/data/APP_Name/databases/DATABASE_NAME

//TODO stringsql
//compileStatement()
//Compiles an SQL statement into a reusable pre-compiled statement object.
////Use this method as below
////compileStatement(String sql)
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

    //lets fetch the list of JUVINILES
    // - need to make one for events, which would be combining
    //also need one for feedbacks concerning one event at  time

    public ArrayList<Juvinile> getJuvinileList() //the select query here as string parameter?
    {

        //list for storing the results
        ArrayList<Juvinile> juvinilesList = new ArrayList<Juvinile>();
        Integer juvilineID;
        String name="";
        String location=""; //could be also city
        String address="";
        String selectQuery = "SELECT * FROM " + myDbHelper.TABLE_JUVINILE; //suppose this could be generic and provided via string?

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        //go through the whole table
        if(c.moveToFirst()) {
            do {
                Juvinile tempjuvi = new Juvinile();

                //read the db
                juvilineID=c.getInt(c.getColumnIndex(myDbHelper.JUVINILEID));
                name=c.getString(c.getColumnIndex(myDbHelper.JUVINILENAME));
                location=c.getString(c.getColumnIndex(myDbHelper.CITY));
                address=c.getString(c.getColumnIndex(myDbHelper.ADDRESS));

                //Assign the values to juviline object
                tempjuvi.setJuvinileID(juvilineID);
                tempjuvi.setAddress(address);
                tempjuvi.setName(name);
                tempjuvi.setCity(location);

                //add the object to the list
                juvinilesList.add(tempjuvi);
            } while (c.moveToNext());
            Log.d("array",juvinilesList.toString());
        }
        return juvinilesList;
    }

    //SELECT EVENTDATA -ALL
    // NOTE that when fetching the upcoming ones we don't need all the data like starttime and endtime, they don't exist..
    public ArrayList<JuvinileEvent> getEventData() //the select query here as string parameter?
    {

        //list for storing the results
        ArrayList<JuvinileEvent> eventDataList = new ArrayList<JuvinileEvent>();
        Integer eventid;
        Integer juvilineid;
        String juvilinename="";
        String eventname="";
        String plannedstarttime= "2010-01-01 00:00:00";
        String plannedendtime= "2010-01-01 00:00:00";
        Integer minage=0;
        Integer maxage=0;
        Integer participants=0;
        String active="";
        String starttime= "2010-01-01 00:00:00";
        String endtime= "2010-01-01 00:00:00";

        //suppose this could be generic and provided via string?
        //on the other hand does it matter where it's generated..
        //CANT I REALLY USE JOINS, BUT HAVE TO GO THE OLD WAY??!!
        /*String selectQuery = "SELECT * " +
                "event.eventid, juvinile.juvinileid, juvinile.juvinilename, " +
                "event.eventname, event.plannedstart, event.plannedend, event.minage, event.maxage, event.participants " +
                "event.active, event.start, event.end " +
                "FROM juvinile INNNER JOIN event on juvinile.juvinileid=event.juvinileid";
        */

        String selectQuery = "SELECT " +
        myDbHelper.EVENTID + ", " +
        myDbHelper.TABLE_JUVINILE + "." + myDbHelper.JUVINILEID + ", " +
        myDbHelper.JUVINILENAME + ", " +
        myDbHelper.EVENTNAME + ", " +
        myDbHelper.EVENTPLANNEDSTART + ", " +
        myDbHelper.EVENTPLANNEDEND + ", " +
        myDbHelper.MINAGE + ", " +
        myDbHelper.MAXAGE  + ", " +
        myDbHelper.PARTICIPANTS_COUNT + ", " +
        myDbHelper.ACTIVE + ", " +
        myDbHelper.EVENTSTART + ", " +
        myDbHelper.EVENTEND +
        " FROM " + myDbHelper.TABLE_JUVINILE + ", " + myDbHelper.TABLE_EVENT +
        " WHERE " + myDbHelper.TABLE_JUVINILE +"." + myDbHelper.JUVINILEID + " = " + myDbHelper.TABLE_EVENT +"." + myDbHelper.EJUVINILEID +
        " ORDER BY " + myDbHelper.JUVINILENAME + ", " + myDbHelper.EVENTPLANNEDSTART + " ASC";;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        //go through the whole table
        if(c.moveToFirst()) {
            do {
                JuvinileEvent tempevent = new JuvinileEvent();

                //read the db
                eventid = c.getInt(c.getColumnIndex(myDbHelper.EVENTID));
                juvilineid =  c.getInt(c.getColumnIndex(myDbHelper.JUVINILEID));
                juvilinename=  c.getString(c.getColumnIndex(myDbHelper.JUVINILENAME));
                eventname= c.getString(c.getColumnIndex(myDbHelper.EVENTNAME));
                plannedstarttime= c.getString(c.getColumnIndex(myDbHelper.EVENTPLANNEDSTART));
                plannedendtime= c.getString(c.getColumnIndex(myDbHelper.EVENTPLANNEDEND));
                minage= c.getInt(c.getColumnIndex(myDbHelper.MINAGE));
                maxage= c.getInt(c.getColumnIndex(myDbHelper.MAXAGE));
                participants= c.getInt(c.getColumnIndex(myDbHelper.PARTICIPANTS_COUNT));
                active= c.getString(c.getColumnIndex(myDbHelper.ACTIVE));
                starttime= c.getString(c.getColumnIndex(myDbHelper.EVENTSTART));
                endtime= c.getString(c.getColumnIndex(myDbHelper.EVENTEND));

                //Assign the values to eventdata object
                tempevent.setEventid(eventid);
                tempevent.setJuvinileid(juvilineid);
                tempevent.setJuvinilename(juvilinename);
                tempevent.setEventname(eventname);
                tempevent.setPlanned_start(plannedstarttime);
                tempevent.setPlanned_end(plannedendtime);
                tempevent.setMinage(minage);
                tempevent.setMaxage(maxage);
                tempevent.setParticipants(participants);
                tempevent.setActive(active);
                tempevent.setStart_time(starttime);
                tempevent.setEnd_time(endtime);

                //add the object to the list
                eventDataList.add(tempevent);
            } while (c.moveToNext());
            Log.d("array",eventDataList.toString());
        }
        return eventDataList;
    }

    //SELECT ONLY NEW EVENTS
    public ArrayList<JuvinileEvent> getNewEventData()
    {

        //list for storing the results
        ArrayList<JuvinileEvent> eventDataList = new ArrayList<JuvinileEvent>();
        Integer eventid;
        Integer juvilineid;
        String juvilinename="";
        String eventname="";
        String plannedstarttime= "2010-01-01 00:00:00";
        String plannedendtime= "2010-01-01 00:00:00";
        Integer minage=0;
        Integer maxage=0;
        Integer participants=0;
        String active="";
        String starttime= "2010-01-01 00:00:00";
        String endtime= "2010-01-01 00:00:00";

        //create the query
        String selectQuery = "SELECT " +
                myDbHelper.EVENTID + ", " +
                myDbHelper.TABLE_JUVINILE + "." + myDbHelper.JUVINILEID + ", " +
                myDbHelper.JUVINILENAME + ", " +
                myDbHelper.EVENTNAME + ", " +
                myDbHelper.EVENTPLANNEDSTART + ", " +
                myDbHelper.EVENTPLANNEDEND + ", " +
                myDbHelper.MINAGE + ", " +
                myDbHelper.MAXAGE  + ", " +
                myDbHelper.PARTICIPANTS_COUNT + ", " +
                myDbHelper.ACTIVE + ", " +
                myDbHelper.EVENTSTART + ", " +
                myDbHelper.EVENTEND +
                " FROM " + myDbHelper.TABLE_JUVINILE + ", " + myDbHelper.TABLE_EVENT +
                " WHERE " + myDbHelper.TABLE_JUVINILE +"." + myDbHelper.JUVINILEID + " = " + myDbHelper.TABLE_EVENT +"." + myDbHelper.EJUVINILEID +
                " AND date(" + myDbHelper.EVENTPLANNEDSTART + ") >= date('now') AND " +myDbHelper.EVENTEND + " is null" +
                " ORDER BY " + myDbHelper.JUVINILENAME + ", " + myDbHelper.EVENTPLANNEDSTART + " ASC";

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        //go through the whole table
        if(c.moveToFirst()) {
            do {
                JuvinileEvent tempevent = new JuvinileEvent();

                //read the db
                eventid = c.getInt(c.getColumnIndex(myDbHelper.EVENTID));
                juvilineid =  c.getInt(c.getColumnIndex(myDbHelper.JUVINILEID));
                juvilinename=  c.getString(c.getColumnIndex(myDbHelper.JUVINILENAME));
                eventname= c.getString(c.getColumnIndex(myDbHelper.EVENTNAME));
                plannedstarttime= c.getString(c.getColumnIndex(myDbHelper.EVENTPLANNEDSTART));
                plannedendtime= c.getString(c.getColumnIndex(myDbHelper.EVENTPLANNEDEND));
                minage= c.getInt(c.getColumnIndex(myDbHelper.MINAGE));
                maxage= c.getInt(c.getColumnIndex(myDbHelper.MAXAGE));
                participants= c.getInt(c.getColumnIndex(myDbHelper.PARTICIPANTS_COUNT));
                active= c.getString(c.getColumnIndex(myDbHelper.ACTIVE));
                starttime= c.getString(c.getColumnIndex(myDbHelper.EVENTSTART));
                endtime= c.getString(c.getColumnIndex(myDbHelper.EVENTEND));

                //Assign the values to eventdata object
                tempevent.setEventid(eventid);
                tempevent.setJuvinileid(juvilineid);
                tempevent.setJuvinilename(juvilinename);
                tempevent.setEventname(eventname);
                tempevent.setPlanned_start(plannedstarttime);
                tempevent.setPlanned_end(plannedendtime);
                tempevent.setMinage(minage);
                tempevent.setMaxage(maxage);
                tempevent.setParticipants(participants);
                tempevent.setActive(active);
                tempevent.setStart_time(starttime);
                tempevent.setEnd_time(endtime);

                //add the object to the list
                eventDataList.add(tempevent);
            } while (c.moveToNext());
            Log.d("array",eventDataList.toString());
        }
        return eventDataList;
    }

    //SELECT ONE SINGLE EVENT
    public JuvinileEvent getSingleEvent(Integer e_eventid) {

        //Variables for storing the results
        String[] s_eventid={Integer.toString(e_eventid)};
        Integer eventid;
        Integer juvilineid;
        String juvilinename = "";
        String eventname = "";
        String plannedstarttime = "2010-01-01 00:00:00";
        String plannedendtime = "2010-01-01 00:00:00";
        Integer minage = 0;
        Integer maxage = 0;
        Integer participants = 0;
        String active = "";
        String starttime = "2010-01-01 00:00:00";
        String endtime = "2010-01-01 00:00:00";

        //create the query
        String selectQuery = "SELECT " +
                myDbHelper.EVENTID + ", " +
                myDbHelper.TABLE_JUVINILE + "." + myDbHelper.JUVINILEID + ", " +
                myDbHelper.JUVINILENAME + ", " +
                myDbHelper.EVENTNAME + ", " +
                myDbHelper.EVENTPLANNEDSTART + ", " +
                myDbHelper.EVENTPLANNEDEND + ", " +
                myDbHelper.MINAGE + ", " +
                myDbHelper.MAXAGE + ", " +
                myDbHelper.PARTICIPANTS_COUNT + ", " +
                myDbHelper.ACTIVE + ", " +
                myDbHelper.EVENTSTART + ", " +
                myDbHelper.EVENTEND +
                " FROM " + myDbHelper.TABLE_JUVINILE + ", " + myDbHelper.TABLE_EVENT +
                " WHERE " + myDbHelper.TABLE_JUVINILE + "." + myDbHelper.JUVINILEID + " = " + myDbHelper.TABLE_EVENT + "." + myDbHelper.EJUVINILEID +
                " AND " + myDbHelper.EVENTID + " = ?";

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, s_eventid);

        //go through the whole table
        JuvinileEvent tempevent = null;
        if (c.moveToFirst()) {
            do {
                tempevent = new JuvinileEvent();

                //read the db
                eventid = c.getInt(c.getColumnIndex(myDbHelper.EVENTID));
                juvilineid = c.getInt(c.getColumnIndex(myDbHelper.JUVINILEID));
                juvilinename = c.getString(c.getColumnIndex(myDbHelper.JUVINILENAME));
                eventname = c.getString(c.getColumnIndex(myDbHelper.EVENTNAME));
                plannedstarttime = c.getString(c.getColumnIndex(myDbHelper.EVENTPLANNEDSTART));
                plannedendtime = c.getString(c.getColumnIndex(myDbHelper.EVENTPLANNEDEND));
                minage = c.getInt(c.getColumnIndex(myDbHelper.MINAGE));
                maxage = c.getInt(c.getColumnIndex(myDbHelper.MAXAGE));
                participants = c.getInt(c.getColumnIndex(myDbHelper.PARTICIPANTS_COUNT));
                active = c.getString(c.getColumnIndex(myDbHelper.ACTIVE));
                starttime = c.getString(c.getColumnIndex(myDbHelper.EVENTSTART));
                endtime = c.getString(c.getColumnIndex(myDbHelper.EVENTEND));

                //Assign the values to eventdata object
                tempevent.setEventid(eventid);
                tempevent.setJuvinileid(juvilineid);
                tempevent.setJuvinilename(juvilinename);
                tempevent.setEventname(eventname);
                tempevent.setPlanned_start(plannedstarttime);
                tempevent.setPlanned_end(plannedendtime);
                tempevent.setMinage(minage);
                tempevent.setMaxage(maxage);
                tempevent.setParticipants(participants);
                tempevent.setActive(active);
                tempevent.setStart_time(starttime);
                tempevent.setEnd_time(endtime);


            } while (c.moveToNext());
            Log.d("object", "single event");
        }
        return tempevent;
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

    public int updateJuvinileEventDetails(Integer event_id,String column,String new_value)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column,new_value);
        contentValues.put(myDbHelper.EDBCHANGE, "DATETIME(TIMESTAMP, 'localtime')");
        String[] whereArgs= {Integer.toString(event_id)};
        int count =db.update(myDbHelper.TABLE_EVENT,contentValues, myDbHelper.EVENTID+" = ?",whereArgs );
        return count; //paluukoodi jee

    }


    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "playtodella4";    // Database Name
        private static final int DATABASE_Version = 4;    // Database Version

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
                        + JUVINILENAME + " VARCHAR(20),"
                        + ADDRESS + " VARCHAR(100), "
                        + CITY + " VARCHAR(20),"
                        + JDBTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
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
                        + EDBTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                        + EDBCHANGE + " DATETIME DEFAULT CURRENT_TIMESTAMP "
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
                        + FDBTIME + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
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

            System.out.println("PITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISIPITÄISI");

            try {
                db.execSQL(CREATE_TABLE_JUVINILE);
                db.execSQL(CREATE_TABLE_EVENT);
                db.execSQL(CREATE_TABLE_FEEDBACK);
                System.out.println("tehtiinkohan jotain");
            } catch (Exception e) {
                Message.message(context, "" + e);
                System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE" + e);
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
