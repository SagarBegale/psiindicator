package com.sagar.sp.psiindicator.http.model;

public class RegionPsiData {

    private final String regionLabel;
    private final double latitude;
    private final double longitude;
    private final int psiIndex;

    public RegionPsiData(String regionLabel, double latitude, double longitude, int psiIndex) {
        this.regionLabel = regionLabel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.psiIndex = psiIndex;
    }

    public String getRegionLabel() {
        return regionLabel;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPsiIndex() {
        return psiIndex;
    }
}
