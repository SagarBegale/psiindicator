package com.sagar.sp.psiindicator;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sagar.sp.psiindicator.http.IRequestPsiCallback;
import com.sagar.sp.psiindicator.http.PsiRequestFactory;
import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;
import com.sagar.sp.psiindicator.http.model.RegionPsiData;
import com.sagar.sp.psiindicator.util.LogUtil;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        IRequestPsiCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d(LogUtil.LOG_TAG, "onMapReady(): Requesting PSI");
        PsiRequestFactory.getPsiRequester(this).execute();
    }

    @Override
    public void onSuccess(PsiRegionSpecificData psiRegionSpecificData) {
        Log.i(LogUtil.LOG_TAG, "onSuccess");
        addMarkerOnMap(psiRegionSpecificData.getEast());
        addMarkerOnMap(psiRegionSpecificData.getWest());
        addMarkerOnMap(psiRegionSpecificData.getNorth());
        addMarkerOnMap(psiRegionSpecificData.getSouth());
        addMarkerOnMap(psiRegionSpecificData.getCentral());

    }



    void addMarkerOnMap(final RegionPsiData regionPsiData) {
        if (regionPsiData != null) {
            final String markerText = getString(R.string.markerText, regionPsiData.getRegionLabel
                    (), regionPsiData.getPsiIndex());
            LatLng latLng = new LatLng(regionPsiData.getLatitude(), regionPsiData.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(markerText));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(regionPsiData
                    .getLatitude
                    (), regionPsiData.getLongitude()), 10));
        }
    }

    @Override
    public void onFailure(final String errorMessage) {
        Log.e(LogUtil.LOG_TAG, errorMessage);
        final View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Failed to retrieve PSI index. Please try again.", Snackbar
                .LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color
                .holo_red_light))
                .show();
    }
}
