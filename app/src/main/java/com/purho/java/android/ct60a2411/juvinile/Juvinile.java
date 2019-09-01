package com.purho.java.android.ct60a2411.juvinile;

import java.io.Serializable;

class Juvinile implements Serializable {

    private Integer juvinileID;
    private String name;
    private String city;
    private String address;

    public Juvinile(){
        this.juvinileID=0;
        this.name="";
        this.city="";
        this.address="";

    }

    public Juvinile(String name,String city,String address) {

        this.juvinileID=0;
        this.name=name;
        this.city=city;
        this.address=address;

    }


    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getJuvinileID() {
        return juvinileID;
    }

    public void setJuvinileID(Integer juvinileID) {
        this.juvinileID = juvinileID;
    }
}
