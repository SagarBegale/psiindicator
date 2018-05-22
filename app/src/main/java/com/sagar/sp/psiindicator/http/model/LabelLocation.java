package com.sagar.sp.psiindicator.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelLocation implements Serializable {
    private final double latitude;
    private final double longitude;

    public LabelLocation(@JsonProperty("latitude")double latitude, @JsonProperty("longitude")double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @JsonProperty("latitude")
    public double getLatitude() {
        return latitude;
    }
    @JsonProperty("longitude")
    public double getLongitude() {
        return longitude;
    }
}
