package com.sagar.sp.psiindicator.http;

/**
 * Factory to construct PSI request handler. This gives flexibility to easily change underlying
 * network handler library without impacting Activity
 */
public final class PsiRequestFactory {
    /**
     * Request RequestPsi interface which will handle request to fetch PSI
     * @param callback where result of request needs to be notified.
     * @return
     */
    public static IRequestPsi getPsiRequester(final IRequestPsiCallback callback) {
        return new RetrofitRequestPsiImpl(callback);
    }
}
