package com.sagar.sp.psiindicator.http;

public final class PsiRequestFactory {
    public static IRequestPsi getPsiRequester(final IRequestPsiCallback callback) {
        return new RetrofitRequestPsiImpl(callback);
    }
}
