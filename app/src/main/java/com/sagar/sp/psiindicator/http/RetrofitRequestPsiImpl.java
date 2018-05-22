package com.sagar.sp.psiindicator.http;

import com.sagar.sp.psiindicator.http.api.PsiApi;
import com.sagar.sp.psiindicator.http.model.PsiResponse;
import com.sagar.sp.psiindicator.http.model.adapter.PsiRegionSpecificDataAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.sagar.sp.psiindicator.http.api.PsiApi.BASE_URL;

public class RetrofitRequestPsiImpl implements IRequestPsi, Callback<PsiResponse> {

    private final IRequestPsiCallback requestPsiCallback;

    public RetrofitRequestPsiImpl(final IRequestPsiCallback requestPsiCallback) {
        this.requestPsiCallback = requestPsiCallback;
    }

    @Override
    public void execute() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory
                (JacksonConverterFactory
                        .create()).build();
        PsiApi psiApi = retrofit.create(PsiApi.class);
        Call<PsiResponse> call = psiApi.getPsi();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PsiResponse> call, Response<PsiResponse> response) {
        if (requestPsiCallback != null) {
            requestPsiCallback.onSuccess(PsiRegionSpecificDataAdapter.adaptPsiResponseData
                    (response.body()));
        }

    }

    @Override
    public void onFailure(Call<PsiResponse> call, Throwable t) {
        if (requestPsiCallback != null) {
            requestPsiCallback.onFailure(t.getMessage());
        }
    }
}
