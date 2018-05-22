package com.sagar.sp.psiindicator.http.model;

/**
 * Aggregated data to be displayed within APP
 */
public class PsiRegionSpecificData {

    private final RegionPsiData east;
    private final RegionPsiData west;
    private final RegionPsiData north;
    private final RegionPsiData south;
    private final RegionPsiData central;

    public PsiRegionSpecificData(RegionPsiData east, RegionPsiData west, RegionPsiData north,
                                 RegionPsiData south, RegionPsiData central) {
        this.east = east;
        this.west = west;
        this.north = north;
        this.south = south;
        this.central = central;
    }

    /**
     * Returns region data for East
     *
     * @return
     */
    public RegionPsiData getEast() {
        return east;
    }

    /**
     * Returns region data for West
     *
     * @return
     */
    public RegionPsiData getWest() {
        return west;
    }

    /**
     * Returns region data for North
     *
     * @return
     */
    public RegionPsiData getNorth() {
        return north;
    }

    /**
     * Returns region data for South
     *
     * @return
     */
    public RegionPsiData getSouth() {
        return south;
    }

    /**
     * Returns region data for Central
     *
     * @return
     */
    public RegionPsiData getCentral() {
        return central;
    }
}
