package com.sagar.sp.psiindicator.http.model.adapter;

import com.sagar.sp.psiindicator.http.model.LabelLocation;
import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;
import com.sagar.sp.psiindicator.http.model.PsiResponse;
import com.sagar.sp.psiindicator.http.model.RegionMetaData;
import com.sagar.sp.psiindicator.http.model.RegionPsiData;

/**
 * Adapter to adapt response from HTTP call. This will allow to have clear segregation between
 * 3rd party library and internal representation
 */
public final class PsiRegionSpecificDataAdapter {


    public static PsiRegionSpecificData adaptPsiResponseData(final PsiResponse psiResponse) {
        RegionPsiData east = null;
        RegionPsiData west = null;
        RegionPsiData north = null;
        RegionPsiData south = null;
        RegionPsiData central = null;
        if (psiResponse != null) {
            for (final RegionMetaData regionMetaData : psiResponse.getRegionMetaDataList()) {
                final LabelLocation labelLocation = regionMetaData.getLabelLocation();
                switch (regionMetaData.getName()) {
                    case "east":
                        east = new RegionPsiData("East", labelLocation.getLatitude(), labelLocation
                                .getLongitude(), psiResponse.getItem().get(0).getReading()
                                .getPsiTwentyFourHourly().getEast());
                        break;
                    case "west":
                        west = new RegionPsiData("West", labelLocation.getLatitude(), labelLocation
                                .getLongitude(), psiResponse.getItem().get(0).getReading()
                                .getPsiTwentyFourHourly().getWest());

                        break;
                    case "north":
                        north = new RegionPsiData("North", labelLocation.getLatitude(),
                                labelLocation

                                        .getLongitude(), psiResponse.getItem().get(0).getReading()
                                .getPsiTwentyFourHourly().getNorth());
                        break;
                    case "south":
                        south = new RegionPsiData("South", labelLocation.getLatitude(),
                                labelLocation
                                        .getLongitude(), psiResponse.getItem().get(0).getReading()
                                .getPsiTwentyFourHourly().getSouth());
                        break;
                    case "central":
                        central = new RegionPsiData("Central", labelLocation.getLatitude(),
                                labelLocation.getLongitude(), psiResponse.getItem().get(0)
                                .getReading
                                        ().getPsiTwentyFourHourly().getCentral());
                        break;
                }
            }
        }
        return new PsiRegionSpecificData(east, west, north, south, central);
    }

    //ADD more adapter based on the library and API used.
}
