package com.sagar.sp.psiindicator.http.model;

public class PsiRegionSpecificData {

    private final RegionPsiData east;
    private final RegionPsiData west;
    private final RegionPsiData north;
    private final RegionPsiData south;
    private final RegionPsiData central;

    public PsiRegionSpecificData(RegionPsiData east, RegionPsiData west, RegionPsiData north, RegionPsiData south, RegionPsiData central) {
        this.east = east;
        this.west = west;
        this.north = north;
        this.south = south;
        this.central = central;
    }

    public RegionPsiData getEast() {
        return east;
    }

    public RegionPsiData getWest() {
        return west;
    }

    public RegionPsiData getNorth() {
        return north;
    }

    public RegionPsiData getSouth() {
        return south;
    }

    public RegionPsiData getCentral() {
        return central;
    }
}
