package com.example.cr7.Model;

import java.io.Serializable;

/**
 * Created by CR7 on 3/13/2018.
 */

public class Expert implements Serializable {

    private String idExpert;

    private String password;

    private String lName;

    private String fName;

    private String sdt;

    private String career;

    private String country;

    private String image;

    private Integer isOnline;

    private double lat;

    private double lon;


    public Expert() {
        super();
    }

    public Expert(String idExpert, String password, String lName, String fName,String sdt, String career, String country, String image, Integer isOnline, double lat, double lon) {
        this.idExpert = idExpert;
        this.password = password;
        this.lName = lName;
        this.fName = fName;
        this.sdt = sdt;
        this.career = career;
        this.country = country;
        this.image = image;
        this.isOnline = isOnline;
        this.lat = lat;
        this.lon = lon;
    }
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getIdExpert() {
        return idExpert;
    }

    public void setIdExpert(String idExpert) {
        this.idExpert = idExpert;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
