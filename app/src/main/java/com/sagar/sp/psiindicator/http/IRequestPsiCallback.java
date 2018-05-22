package com.sagar.sp.psiindicator.http;

import com.sagar.sp.psiindicator.http.model.PsiRegionSpecificData;

/**
 * Callback to notify result of PSI Request
 */
public interface IRequestPsiCallback {

    /**
     * Callback method to notify success
     * @param psiRegionSpecificData retrieved data from the call
     */
    void onSuccess(final PsiRegionSpecificData psiRegionSpecificData);

    /**
     * Callback method to notify failure
     * @param errorMessage indicated cause of issue.
     */
    void onFailure(final String errorMessage);
}
