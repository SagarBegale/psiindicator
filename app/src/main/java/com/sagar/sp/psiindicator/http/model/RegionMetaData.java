package com.sagar.sp.psiindicator.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionMetaData implements Serializable {
    private final String name;
    private final LabelLocation labelLocation;


    public RegionMetaData(@JsonProperty("name")String name, @JsonProperty("label_location")LabelLocation labelLocation) {
        this.name = name;
        this.labelLocation = labelLocation;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("label_location")
    public LabelLocation getLabelLocation() {
        return labelLocation;
    }
}
