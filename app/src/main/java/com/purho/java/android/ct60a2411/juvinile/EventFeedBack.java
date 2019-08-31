package com.purho.java.android.ct60a2411.juvinile;

class EventFeedBack {

    private Integer eventid;
    private String grade;
    private String feedback;
    private String fbgiver;

    public EventFeedBack() {

        this.eventid=0;
        this.grade="";
        this.feedback="";
        this.fbgiver="";

    }

    public EventFeedBack(Integer eventid,String grade,String feedback,String fbgiver) {

        this.eventid=eventid;
        this.grade=grade;
        this.feedback=feedback;
        this.fbgiver=fbgiver;

    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFbgiver() {
        return fbgiver;
    }

    public void setFbgiver(String fbgiver) {
        this.fbgiver = fbgiver;
    }
}
