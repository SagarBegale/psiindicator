package com.sagar.sp.psiindicator.http.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PsiResponse {
    private final List<RegionMetaData> regionMetaDataList;
    private final List<Item> item;

    public PsiResponse(@JsonProperty("region_metadata")List<RegionMetaData> regionMetaDataList,@JsonProperty("items") List<Item> item) {
        this.regionMetaDataList = regionMetaDataList;
        this.item = item;
    }

    @JsonProperty("region_metadata")
    public List<RegionMetaData> getRegionMetaDataList() {
        return regionMetaDataList;
    }

    @JsonProperty("items")
    public List<Item> getItem() {
        return item;
    }
}
