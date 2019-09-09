package com.purho.java.android.ct60a2411.juvinile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;
import java.net.Inet4Address;
import java.sql.Timestamp;
import java.util.ArrayList;

//note to self: THE DB GOES TO
// data/data/APP_Name/databases/DATABASE_NAME


public class MyDbAdapter implements Serializable {

    private myDbHelper myhelper;

    private Integer eventid;
    private Integer juvilineid;
    private String juvilinename;
    private String eventname;
    private String plannedstarttime;
    private String plannedendtime;
    private Integer minage;
    private Integer maxage;
    private Integer participants;
    private String active;
    private String starttime;
    private String endtime;
    private String name;
    private String location; //could be also city
    private String address;
    private Integer feedbackid;
    private Integer grade;
    private String feedback;






     public MyDbAdapter(Context context)
     {
         myhelper = new myDbHelper(context);
     }

    //INSERT

    public long insertDataJuvinile(String[] data)
    {

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

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.JEVENTID, data[0]);
        contentValues.put(myDbHelper.GRADE, data[1]);
        contentValues.put(myDbHelper.FEEDBACK, data[2]);
        contentValues.put(myDbHelper.PARTICIPANT, data[3]);

        long insert = dbb.insert(myDbHelper.TABLE_FEEDBACK, null , contentValues);
        return insert;
    }


    //SELECT

    //lets fetch the list of JUVINILES
    public ArrayList<Juvinile> getJuvinileList() //the select query here as string parameter?
    {

        //list for storing the results
        ArrayList<Juvinile> juvinilesList = new ArrayList<Juvinile>();

        String selectQuery = "SELECT * FROM " + myDbHelper.TABLE_JUVINILE;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        //go through the whole table
        if(c.moveToFirst()) {
            do {
                Juvinile tempjuvi = new Juvinile();

                //read the db
                juvilineid=c.getInt(c.getColumnIndex(myDbHelper.JUVINILEID));
                name=c.getString(c.getColumnIndex(myDbHelper.JUVINILENAME));
                location=c.getString(c.getColumnIndex(myDbHelper.CITY));
                address=c.getString(c.getColumnIndex(myDbHelper.ADDRESS));

                //Assign the values to juviline object
                tempjuvi.setJuvinileID(juvilineid);
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
                if(starttime == null) starttime="";
                endtime= c.getString(c.getColumnIndex(myDbHelper.EVENTEND));
                if(endtime == null) endtime="";

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
                if(starttime == null) starttime="";
                endtime= c.getString(c.getColumnIndex(myDbHelper.EVENTEND));
                if(endtime == null) endtime="";

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


    //***********************************************************************************************************************
    //SELECT FEEDBACKS
    public ArrayList<EventFeedBack> getEventFeedBacks(Integer eventid){

        //Variables for storing the results
        String[] s_eventid={Integer.toString(eventid)};
        ArrayList<EventFeedBack> feedBackList = new ArrayList<EventFeedBack>();

        String selectQuery = "SELECT * FROM " + myDbHelper.TABLE_FEEDBACK + " WHERE " + myDbHelper.JEVENTID + " = " +
        eventid + " ORDER BY DBTIME ASC";

        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);
        //go through the whole table
        if(c.moveToFirst()) {
            do {
                EventFeedBack tempfeedback = new EventFeedBack();

                //read the db

                feedbackid=c.getInt(c.getColumnIndex(myDbHelper.FEEDBACKID));
                grade=c.getInt(c.getColumnIndex(myDbHelper.GRADE));
                feedback=c.getString(c.getColumnIndex(myDbHelper.FEEDBACK));
                name=c.getString(c.getColumnIndex(myDbHelper.PARTICIPANT));
                //coalesce broke the db cursor, so let's do it here.
                if(name == null) name="Anonymous";

                //Assign the values to a feedback object
                tempfeedback.setEventid(eventid);
                tempfeedback.setFeedbackid(feedbackid);
                tempfeedback.setGrade(Integer.toString(grade));
                tempfeedback.setFeedback(feedback);
                tempfeedback.setFbgiver(name);

                //add the object to the list
                feedBackList.add(tempfeedback);
            } while (c.moveToNext());
            Log.d("array",feedBackList.toString());
        }
        c.close();
        return feedBackList;

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


    //update Juvinile data
    public void updateJuvinileDetails(Integer juvinileid,String column,String new_value)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String sqlquery="UPDATE JUVINILE SET " + column + " = \'" + new_value + "\' , dbchange = DATETIME(CURRENT_TIMESTAMP, 'localtime') WHERE juvinileid = " + juvinileid  ;
        System.out.println("SQL QUERY JUVINILE LASSI " + sqlquery);
        Cursor c = db.rawQuery(sqlquery, null);
        c.moveToFirst();
        c.close();
    }


    //update juvinile Event data
    public void updateJuvinileEventDetails(Integer event_id,String column,String new_value)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String sqlquery="UPDATE EVENT SET " + column + " = \'" + new_value + "\' , dbchange = DATETIME(CURRENT_TIMESTAMP, 'localtime') WHERE eventid = " + event_id  ;
        System.out.println("SQL QUERY EVENT LASSI " + sqlquery);
        Cursor c = db.rawQuery(sqlquery, null);
        c.moveToFirst();
        c.close();
    }

    //update Feedbacks
    public void updateFeedback(Integer feedbackid,String column,String new_value)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String sqlquery="UPDATE FEEDBACK SET " + column + " = \'" + new_value + "\' , dbchange = DATETIME(CURRENT_TIMESTAMP, 'localtime') WHERE feedbackid = " + feedbackid  ;
        System.out.println("SQL QUERY EVENT LASSI " + sqlquery);
        Cursor c = db.rawQuery(sqlquery, null);
        c.moveToFirst();
        c.close();
    }


    //table variables and DATABASE VERSION and name
    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "playtodellax";    // Database Name
        private static final int DATABASE_Version = 1;    // Database Version

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


        private static final String CREATE_INDEX_JUVINILE = "CREATE UNIQUE INDEX idx_juvinile_name ON JUVINILE (juvinileid, name);";

        private static final String DROP_TABLE_JUVINILE = "DROP TABLE IF EXISTS " + TABLE_JUVINILE;

        //sentences to handle table event
        private static final String CREATE_TABLE_EVENT =
                "CREATE TABLE " + TABLE_EVENT + " ("
                        + EVENTID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + EJUVINILEID + " INTEGER NOT NULL, "
                        + EVENTNAME + " TEXT, "
                        + EVENTPLANNEDSTART + " DATETIME NOT NULL, "
                        + EVENTPLANNEDEND + " DATETIME NOT NULL, "
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



            try {
                db.execSQL(CREATE_TABLE_JUVINILE);
                db.execSQL(CREATE_TABLE_EVENT);
                db.execSQL(CREATE_TABLE_FEEDBACK);
                //db.execSQL(CREATE_INDEX_JUVINILE);

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
