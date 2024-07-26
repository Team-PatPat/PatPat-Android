package com.simply407.patpat.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000fJ\u000e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/simply407/patpat/data/model/SharedPreferencesManager;", "", "()V", "FILE_NAME", "", "KEY_FIRST_JOIN_COMPLETE", "KEY_ONBOARDING_SHOWN", "KEY_USER_IS_LOGGED_IN", "sharedPref", "Landroid/content/SharedPreferences;", "init", "", "context", "Landroid/content/Context;", "isFirstJoinComplete", "", "isOnboardingShown", "isUserLoggedIn", "setFirstJoinComplete", "shown", "setOnboardingShown", "setUserIsLoggedIn", "app_debug"})
public final class SharedPreferencesManager {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String FILE_NAME = "user_info";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ONBOARDING_SHOWN = "onboarding_shown";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_USER_IS_LOGGED_IN = "user_is_logged_in";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_FIRST_JOIN_COMPLETE = "first_join_complete";
    private static android.content.SharedPreferences sharedPref;
    @org.jetbrains.annotations.NotNull
    public static final com.simply407.patpat.data.model.SharedPreferencesManager INSTANCE = null;
    
    private SharedPreferencesManager() {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    public final void setOnboardingShown(boolean shown) {
    }
    
    public final boolean isOnboardingShown() {
        return false;
    }
    
    public final void setUserIsLoggedIn(boolean shown) {
    }
    
    public final boolean isUserLoggedIn() {
        return false;
    }
    
    public final void setFirstJoinComplete(boolean shown) {
    }
    
    public final boolean isFirstJoinComplete() {
        return false;
    }
}