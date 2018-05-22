package com.sagar.sp.psiindicator;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.sagar.sp.psiindicator.http.api.PsiApi;
import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;
import com.sagar.sp.psiindicator.http.model.PsiResponse;
import com.sagar.sp.psiindicator.http.model.RegionPsiData;
import com.sagar.sp.psiindicator.util.DeviceUtils;
import com.sagar.sp.psiindicator.util.LogUtil;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.sagar.sp.psiindicator.http.api.PsiApi.BASE_URL;

public class MainActivity extends AppCompatActivity implements IRequestPsiCallback,
        OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        txtInfo = findViewById(R.id.txtInfo);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!DeviceUtils.isNetworkConnected(this)) {
            setOffline();
        } else {
            setLastUpdated();
        }
    }

    private void setOffline() {
        mapFragment.getView().setVisibility(View.GONE);
        txtInfo.setText(R.string.network_offline);
    }

    private void setLastUpdated() {
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
                mMap.clear();
                setLastUpdated();
                PsiRequestFactory.getPsiRequester(this).execute();
            } else {
                final View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, R.string.network_offline, Snackbar
                        .LENGTH_LONG)
                        .show();
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
        addMarkerOnMap(psiRegionSpecificData.getEast());
        addMarkerOnMap(psiRegionSpecificData.getWest());
        addMarkerOnMap(psiRegionSpecificData.getNorth());
        addMarkerOnMap(psiRegionSpecificData.getSouth());
        addMarkerOnMap(psiRegionSpecificData.getCentral());
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e(LogUtil.LOG_TAG, errorMessage);
        final View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, R.string.network_offline, Snackbar
                .LENGTH_LONG)
                .show();
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
}
