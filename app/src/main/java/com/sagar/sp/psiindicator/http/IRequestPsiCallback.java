package com.sagar.sp.psiindicator.http;

import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;

public interface IRequestPsiCallback {

    void onSuccess(final PsiRegionSpecificData psiRegionSpecificData);

    void onFailure(final String errorMessage);
}
