package com.sagar.sp.psiindicator.http.model.adapter;

import com.sagar.sp.psiindicator.http.model.LabelLocation;
import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;
import com.sagar.sp.psiindicator.http.model.PsiResponse;
import com.sagar.sp.psiindicator.http.model.RegionMetaData;
import com.sagar.sp.psiindicator.http.model.RegionPsiData;

public final class PsiRegionSpecificDataAdapter {


    public static PsiRegionSpecificData adaptPsiResponseData(final PsiResponse psiResponse) {
        RegionPsiData east = null;
        RegionPsiData west = null;
        RegionPsiData north = null;
        RegionPsiData south = null;
        RegionPsiData central = null;
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
                    north = new RegionPsiData("North", labelLocation.getLatitude(), labelLocation
                            .getLongitude(), psiResponse.getItem().get(0).getReading()
                            .getPsiTwentyFourHourly().getNorth());
                    break;
                case "south":
                    south = new RegionPsiData("South", labelLocation.getLatitude(), labelLocation
                            .getLongitude(), psiResponse.getItem().get(0).getReading()
                            .getPsiTwentyFourHourly().getSouth());
                    break;
                case "central":
                    central = new RegionPsiData("Central", labelLocation.getLatitude(),
                            labelLocation.getLongitude(), psiResponse.getItem().get(0).getReading
                            ().getPsiTwentyFourHourly().getCentral());
                    break;
            }
        }
        return new PsiRegionSpecificData(east, west, north, south, central);
    }
}
