package com.lkyl.oceanframework.geo.entity;

public class SimplePoint {
    private double latitude;
    private double longitude;
    
    public SimplePoint(double lat, double lon){
        this.latitude = lat;
        this.longitude = lon;
    }
    public SimplePoint(SimplePoint pCoodinates) {
        this.latitude = pCoodinates.getLat();
        this.longitude = pCoodinates.getLon();
    }
    public double getLat() {
        return latitude;
    }

    public double getLon() {
        return longitude;
    }

    public void setLat(double lat) {
        this.latitude = lat;
    }

    public void setLon(double lon) {
        this.longitude = lon;
    }
    
    public SimplePoint substract(SimplePoint pCoordinates){
        double lat = this.latitude - pCoordinates.getLat();
        double lon = this.longitude - pCoordinates.getLon();
        return new SimplePoint(lat, lon);
    }
    
    public SimplePoint add(SimplePoint pCoordinates){
        double lat = this.latitude + pCoordinates.getLat();
        double lon = this.longitude + pCoordinates.getLon();
        
        return new SimplePoint(lat, lon);
    }
    
}