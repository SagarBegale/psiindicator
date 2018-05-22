package com.sagar.sp.psiindicator.http.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionIndexData implements Serializable {

    private final int west;
    private final int east;
    private final int central;
    private final int south;
    private final int north;


    public RegionIndexData(@JsonProperty("west") int west, @JsonProperty("east") int east, @JsonProperty("central") int central, @JsonProperty("south") int south, @JsonProperty("north") int north) {
        this.west = west;
        this.east = east;
        this.central = central;
        this.south = south;
        this.north = north;
    }

    @JsonProperty("west")
    public int getWest() {
        return west;
    }

    @JsonProperty("east")
    public int getEast() {
        return east;
    }

    @JsonProperty("central")
    public int getCentral() {
        return central;
    }

    @JsonProperty("south")
    public int getSouth() {
        return south;
    }

    @JsonProperty("north")
    public int getNorth() {
        return north;
    }
}
