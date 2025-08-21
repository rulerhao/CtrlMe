package com.ruler_hao.ctrl_me.data.entity;

import java.util.Locale;

public class StationData {
    private int id;
    private String district;
    private String village;
    private String squad;
    private String bureauCode;
    private String vehicleNumber;
    private String route;
    private String vehicleTrip;
    private int arrivalTime;
    private int departureTime;
    private String location;
    private Float longitude;
    private Float latitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getBureauCode() {
        return bureauCode;
    }

    public void setBureauCode(String bureauCode) {
        this.bureauCode = bureauCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getVehicleTrip() {
        return vehicleTrip;
    }

    public void setVehicleTrip(String vehicleTrip) {
        this.vehicleTrip = vehicleTrip;
    }

    // Convert minutes to time format like "HH:mm"
    public String getArrivalTime() {
        int hours = arrivalTime / 60;
        int minutes = arrivalTime % 60;

        return String.format(Locale.US, "%02d:%02d", hours, minutes);
    }

    public int getArrivalTimeInt() {
        return arrivalTime;
    }

    // Convert HHmm to minutes
    public void setArrivalTime(String arrivalTime) {
        try {
            int hours = Integer.parseInt(arrivalTime.substring(0, 2));
            int minutes = Integer.parseInt(arrivalTime.substring(2, 4));
            this.arrivalTime = hours * 60 + minutes;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDepartureTime() {
        int hours = departureTime / 60;
        int minutes = departureTime % 60;

        return String.format(Locale.US, "%02d:%02d", hours, minutes);
    }

    public void setDepartureTime(String departureTime) {
        try {
            int hours = Integer.parseInt(departureTime.substring(0, 2));
            int minutes = Integer.parseInt(departureTime.substring(2, 4));
            this.departureTime = hours * 60 + minutes;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = Float.parseFloat(longitude);
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = Float.parseFloat(latitude);
    }

    @Override
    public String toString() {
        return "StationData{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", village='" + village + '\'' +
                ", squad='" + squad + '\'' +
                ", bureauCode='" + bureauCode + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", route='" + route + '\'' +
                ", vehicleTrip='" + vehicleTrip + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", location='" + location + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
