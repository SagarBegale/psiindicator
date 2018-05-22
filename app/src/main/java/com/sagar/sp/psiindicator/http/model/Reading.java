package com.sagar.sp.psiindicator.http.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reading implements Serializable {

    private final RegionIndexData psiTwentyFourHourly;

    public Reading(@JsonProperty("psi_twenty_four_hourly") final RegionIndexData psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    @JsonProperty("psi_twenty_four_hourly")
    public RegionIndexData getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }
}
