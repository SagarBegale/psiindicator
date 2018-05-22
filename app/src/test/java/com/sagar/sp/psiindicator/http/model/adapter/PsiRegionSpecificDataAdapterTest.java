package com.sagar.sp.psiindicator.http.model.adapter;

import com.sagar.sp.psiindicator.http.model.Item;
import com.sagar.sp.psiindicator.http.model.LabelLocation;
import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;
import com.sagar.sp.psiindicator.http.model.PsiResponse;
import com.sagar.sp.psiindicator.http.model.Reading;
import com.sagar.sp.psiindicator.http.model.RegionIndexData;
import com.sagar.sp.psiindicator.http.model.RegionMetaData;
import com.sagar.sp.psiindicator.http.model.RegionPsiData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PsiRegionSpecificDataAdapterTest {

    @Test
    public void validResponse_NoExceptionThrown() {
        List<RegionMetaData> regionMetaDataList =new ArrayList<>();
        regionMetaDataList.add(new RegionMetaData("east",new LabelLocation(102.1,103.2)));
        regionMetaDataList.add(new RegionMetaData("west",new LabelLocation(100.1,107.2)));
        regionMetaDataList.add(new RegionMetaData("north",new LabelLocation(103.1,105.2)));
        regionMetaDataList.add(new RegionMetaData("south",new LabelLocation(107.1,101.2)));
        regionMetaDataList.add(new RegionMetaData("central",new LabelLocation(108.1,102.2)));
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(new Reading(new RegionIndexData(1,2,3,5,7))));
        PsiResponse psiResponse = new PsiResponse(regionMetaDataList,itemList);
        PsiRegionSpecificData regionSpecificData = PsiRegionSpecificDataAdapter.adaptPsiResponseData(psiResponse);
        RegionPsiData east = regionSpecificData.getEast();
        RegionPsiData west = regionSpecificData.getWest();
        RegionPsiData north = regionSpecificData.getNorth();
        RegionPsiData south = regionSpecificData.getSouth();
        RegionPsiData central = regionSpecificData.getCentral();
        assertEquals(east.getLatitude(),regionMetaDataList.get(0).getLabelLocation().getLatitude(),0.000001);
        assertEquals(east.getLongitude(),regionMetaDataList.get(0).getLabelLocation().getLongitude(),0.000001);
        assertEquals(east.getPsiIndex(), itemList.get(0).getReading().getPsiTwentyFourHourly().getEast());

        assertEquals(west.getLatitude(),regionMetaDataList.get(1).getLabelLocation().getLatitude(),0.000001);
        assertEquals(west.getLongitude(),regionMetaDataList.get(1).getLabelLocation().getLongitude(),0.000001);
        assertEquals(west.getPsiIndex(), itemList.get(0).getReading().getPsiTwentyFourHourly().getWest());

        assertEquals(north.getLatitude(),regionMetaDataList.get(2).getLabelLocation().getLatitude(),0.000001);
        assertEquals(north.getLongitude(),regionMetaDataList.get(2).getLabelLocation().getLongitude(),0.000001);
        assertEquals(north.getPsiIndex(), itemList.get(0).getReading().getPsiTwentyFourHourly().getNorth());

        assertEquals(south.getLatitude(),regionMetaDataList.get(3).getLabelLocation().getLatitude(),0.000001);
        assertEquals(south.getLongitude(),regionMetaDataList.get(3).getLabelLocation().getLongitude(),0.000001);
        assertEquals(south.getPsiIndex(), itemList.get(0).getReading().getPsiTwentyFourHourly().getSouth());

        assertEquals(central.getLatitude(),regionMetaDataList.get(4).getLabelLocation().getLatitude(),0.000001);
        assertEquals(central.getLongitude(),regionMetaDataList.get(4).getLabelLocation().getLongitude(),0.000001);
        assertEquals(central.getPsiIndex(), itemList.get(0).getReading().getPsiTwentyFourHourly().getCentral());

    }

    @Test
    public void inValidResponse_NoExceptionThrown() {

        PsiRegionSpecificData regionSpecificData = PsiRegionSpecificDataAdapter.adaptPsiResponseData(null);
        RegionPsiData east = regionSpecificData.getEast();
        RegionPsiData west = regionSpecificData.getWest();
        RegionPsiData north = regionSpecificData.getNorth();
        RegionPsiData south = regionSpecificData.getSouth();
        RegionPsiData central = regionSpecificData.getNorth();
        assertEquals(east, null);
        assertEquals(west, null);
        assertEquals(north, null);
        assertEquals(south, null);
        assertEquals(central, null);

    }

}