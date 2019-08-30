package com.purho.java.android.ct60a2411.juvinile;

import java.sql.Timestamp;

public class JuvinileEvent {

    private Integer eventid;
    private Integer juvinileid;
    private String juvinilename;
    private String eventname;
    private String planned_start;
    private String planned_end;
    private Integer minage;
    private Integer maxage;
    private String active;
    private Integer participants;
    private String start_time;
    private String end_time;

    private String eventinsertSQL;


    public void JuvinileEvent() {

        this.eventid=0;
        this.juvinileid=0;
        this.juvinilename="";
        this.eventname="";
        this.planned_start= "2010-01-01 00:00:00";
        this.planned_end= "2010-01-01 00:00:00";
        this.minage=0;
        this.maxage=0;
        this.active="no";
        this.participants=0;
        this.start_time= "2010-01-01 00:00:00"; //should be null or not taken to db at insert.
        this.end_time="2010-01-01 00:00:00"; //should be null or not taken to db at insert.


        //TODO got to change timestamps to human readable format here or at least before displaying.



    }

    //alla sais olla myös juvilineid, katotaan
    public void JuvinileEvent(String juvinilename, String eventname,String planned_start, String planned_end,Integer minage,Integer maxage) {

        //this.juvinileid=null; //hopefully we get this from the parameters. we can also use sql and the juvinile name, np.
        this.juvinilename=juvinilename;
        this.eventname=eventname;
        this.planned_start=planned_start;
        this.planned_end=planned_end;
        this.minage=minage;
        this.maxage=maxage;
        this.active="no";
        this.participants=0;
        this.start_time=null;
        this.end_time=null;

        //construct the sql for inserting the event into the database
        this.eventinsertSQL="";
        insertNewToDB(eventinsertSQL);

    }

    public void insertNewToDB(String eventinsertSQL){
        //could we prepare the statement here?
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getPlanned_start() {
        return planned_start;
    }

    public void setPlanned_start(String planned_start) {
        this.planned_start = planned_start;
    }

    public String getPlanned_end() {
        return planned_end;
    }

    public void setPlanned_end(String planned_end) {
        this.planned_end = planned_end;
    }

    public Integer getMinage() {
        return minage;
    }

    public void setMinage(Integer minage) {
        this.minage = minage;
    }

    public Integer getMaxage() {
        return maxage;
    }

    public void setMaxage(Integer maxage) {
        this.maxage = maxage;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public Integer getJuvinileid() {
        return juvinileid;
    }

    public void setJuvinileid(Integer juvinileid) {
        this.juvinileid = juvinileid;
    }

    public String getJuvinilename() {
        return juvinilename;
    }

    public void setJuvinilename(String juvinilename) {
        this.juvinilename = juvinilename;
    }
}
