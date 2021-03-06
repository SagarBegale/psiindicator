package com.sagar.sp.psiindicator;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
import com.sagar.sp.psiindicator.util.DeviceUtils;
import com.sagar.sp.psiindicator.util.LogUtil;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements IRequestPsiCallback, OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private TextView txtInfo;
    private View overlay;
    private ContentLoadingProgressBar contentLoadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        overlay = findViewById(R.id.overlay);
        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(LogUtil.LOG_TAG, "onClick: OverlayView clicked. Blocking all touch events until map is ready");
            }
        });
        overlay.setVisibility(View.VISIBLE);
        contentLoadingProgressBar = findViewById(R.id.cntLoadingBar);
        txtInfo = findViewById(R.id.txtInfo);
        contentLoadingProgressBar.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!DeviceUtils.isNetworkConnected(this)) {
            setOffline();
            contentLoadingProgressBar.hide();
        }
    }

    private void setOffline() {
        mapFragment.getView().setVisibility(View.GONE);
        overlay.setVisibility(View.GONE);
        txtInfo.setText(R.string.network_offline);
    }

    void setLastUpdated() {
        txtInfo.setText(R.string.last_updated);
        txtInfo.append(Calendar.getInstance().getTime().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.reload == item.getItemId()) {
            if (DeviceUtils.isNetworkConnected(this)) {
                mapFragment.getView().setVisibility(View.VISIBLE);
                contentLoadingProgressBar.show();
                overlay.setVisibility(View.VISIBLE);
                mMap.clear();
                setLastUpdated();
                PsiRequestFactory.getPsiRequester(this).execute();
            } else {
                overlay.setVisibility(View.GONE);
                contentLoadingProgressBar.hide();
                final View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, R.string.network_offline, Snackbar.LENGTH_LONG).show();
                setOffline();
            }

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d(LogUtil.LOG_TAG, "onMapReady(): Requesting PSI");
        PsiRequestFactory.getPsiRequester(this).execute();
    }

    @Override
    public void onSuccess(PsiRegionSpecificData psiRegionSpecificData) {
        Log.i(LogUtil.LOG_TAG, "onSuccess");
        mMap.clear();

        setLastUpdated();
        for (RegionPsiData regionPsiData : psiRegionSpecificData.getRegionPsiDatas()) {
            addMarkerOnMap(regionPsiData);
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e(LogUtil.LOG_TAG, errorMessage);
        contentLoadingProgressBar.hide();
        overlay.setVisibility(View.GONE);
        final View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, R.string.unable_to_load_data, Snackbar.LENGTH_LONG).show();
    }

    void addMarkerOnMap(final RegionPsiData regionPsiData) {
        if (regionPsiData != null) {
            final String markerText = getString(R.string.markerText, regionPsiData.getRegionLabel(), regionPsiData
                    .getPsiIndex());
            LatLng latLng = new LatLng(regionPsiData.getLatitude(), regionPsiData.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(markerText));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(regionPsiData.getLatitude(),
                    regionPsiData.getLongitude()), 10));
            overlay.setVisibility(View.GONE);
            contentLoadingProgressBar.hide();
        }
    }
}
