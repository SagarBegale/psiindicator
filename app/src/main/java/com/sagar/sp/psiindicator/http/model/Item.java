package com.sagar.sp.psiindicator.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

    private final Reading reading;

    public Item(@JsonProperty("readings") Reading readingList) {
        this.reading = readingList;
    }

    @JsonProperty("readings")
    public Reading getReading() {
        return reading;
    }
}
