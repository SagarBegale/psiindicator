package com.sagar.sp.psiindicator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sagar.sp.psiindicator.http.api.PsiApi;
import com.sagar.sp.psiindicator.http.model.PsiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.sagar.sp.psiindicator.http.api.PsiApi.BASE_URL;

public class MainActivity extends AppCompatActivity implements Callback<PsiResponse> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {

    }

    @Override
    public void onResponse(Call<PsiResponse> call, Response<PsiResponse> response) {
        PsiResponse psiResponses = response.body();
        Log.d("PSITest", psiResponses.toString());
    }

    @Override
    public void onFailure(Call<PsiResponse> call, Throwable t) {
        Log.e("PSITest", t.getMessage());
    }
}
