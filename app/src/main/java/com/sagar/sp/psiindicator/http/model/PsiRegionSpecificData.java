package com.sagar.sp.psiindicator.http.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregated data to be displayed within APP
 */
public class PsiRegionSpecificData {

    private final List<RegionPsiData> regionPsiDataList;

    public PsiRegionSpecificData(RegionPsiData east, RegionPsiData west, RegionPsiData north, RegionPsiData south,
                                 RegionPsiData central) {
        regionPsiDataList = new ArrayList<>();
        regionPsiDataList.add(east);
        regionPsiDataList.add(west);
        regionPsiDataList.add(north);
        regionPsiDataList.add(south);
        regionPsiDataList.add(central);
    }

    public RegionPsiData[] getRegionPsiDatas() {
        final RegionPsiData[] regionPsiData = new RegionPsiData[regionPsiDataList.size()];
        return regionPsiDataList.toArray(regionPsiData);
    }
}
