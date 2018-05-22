package com.sagar.sp.psiindicator.http.api;

import com.sagar.sp.psiindicator.http.model.PsiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PsiApi {

       String BASE_URL = "https://api.data.gov.sg/v1/environment/";

        @Headers({
                "Accept: application/json",
        })
        @GET("psi")
        public Call<PsiResponse> getPsi();
}
