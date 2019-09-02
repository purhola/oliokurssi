package com.purho.java.android.ct60a2411.juvinile;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JuvinileEvent implements Serializable {

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


    public JuvinileEvent() {

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


    }

    //alla sais olla myös juvilineid, katotaan
    public JuvinileEvent(String juvinilename, String eventname,String planned_start, String planned_end,Integer minage,Integer maxage) {

        //this.juvinileid=null; //hopefully we get this from the parameters. we can also use sql and the juvinile name, np.
        this.juvinilename=juvinilename;
        this.eventname=eventname;


        //here we want to change from dd.mm.yy hh:mm to yyyy-mm-dd hh:mm:ss
        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String planned_to_object="";

        try {
            Date tempStart  = dftarg.parse(planned_start);
            planned_to_object=dforig.format(tempStart);

        } catch (ParseException ex) {System.out.println("DateConversionFailed in creating juvinileEvent object");}
        finally {}

        System.out.println("PVM NÄYTTÄÄ " + planned_to_object.toString());

        this.planned_start = planned_to_object;

        //here we want to change from dd.mm.yy hh:mm to yyyy-mm-dd hh:mm:ss



        try {
            Date tempEnd  = dftarg.parse(planned_end);
            planned_to_object=dforig.format(tempEnd);

        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}

        this.planned_end = planned_to_object;


        this.minage=minage;
        this.maxage=maxage;
        this.active="NO";
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

        //here we want to change from yyyy-mm-dd hh:mm:ss to dd.mm.yy hh:mm
        //just to be able to show a readable datetime

        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String readable="";

        try {
            Date tempStart  = dforig.parse(planned_start);
            readable=dftarg.format(tempStart);
        } catch (ParseException ex) {System.out.println("DateConversionFailed" + ex);}
        finally {}
        return readable;
    }

    public String getPlanned_startDB() {

        return planned_start;
    }

    public void setPlanned_start(String planned_start) {

        //this is done from the DB and we want it unchanged
        this.planned_start = planned_start;
    }

    public void setPlanned_startUI(String planned_start) {
        //here we want to change from dd.mm.yy hh:mm to yyyy-mm-dd hh:mm:ss
        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String planned_to_object="";

        try {
            Date tempStart  = dftarg.parse(planned_start);
            planned_to_object=dforig.format(tempStart);

        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}

        this.planned_start = planned_to_object;

    }

    public String getPlanned_end() {

        //here we want to change from yyyy-mm-dd hh:mm:ss to dd.mm.yy hh:mm
        //just to be able to show a readable datetime

        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String readable="";

        try {
            Date tempStart  = dforig.parse(planned_end);
            readable=dftarg.format(tempStart);

        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}
        return readable;
    }

    public String getPlanned_endDB() {

        return planned_end;
    }

    public void setPlanned_end(String planned_end) {

        //this is done from the DB and we want it unchanged
        this.planned_end=planned_end;

    }

    public void setPlanned_endUI(String planned_end){

        //here we want to change from dd.mm.yy hh:mm to yyyy-mm-dd hh:mm:ss

        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String planned_to_object="";

        try {
            Date tempEnd  = dftarg.parse(planned_end);
            planned_to_object=dforig.format(tempEnd);

        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}

        this.planned_end = planned_to_object;

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

        //here we want to change from yyyy-mm-dd hh:mm:ss to dd.mm.yy hh:mm
        //just to be able to show a readable datetime

        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String readable="";

        try {
            Date tempStart  = dforig.parse(start_time);
            readable=dftarg.format(tempStart);
            //    finally return readable;
        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}
        return readable;
    }

    public void setStart_time(String start_time) {

        //here we want to take the current timestamp so we don't actually need the input parameter

        LocalDateTime start = LocalDateTime.now();

        this.start_time = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    public String getEnd_time() {

        //here we want to change from yyyy-mm-dd hh:mm:ss to dd.mm.yy hh:mm
        //just to be able to show a readable datetime

        SimpleDateFormat dforig = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dftarg = new SimpleDateFormat("dd.MM.yy HH:mm");

        String readable="";

        try {
            Date tempEnd  = dforig.parse(end_time);
            readable=dftarg.format(tempEnd);
            //    finally return readable;
        } catch (ParseException ex) {System.out.println("DateConversionFailed");}
        finally {}
        return readable;
    }

    public void setEnd_time(String end_time) {

        LocalDateTime stop = LocalDateTime.now();

        this.end_time = stop.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
