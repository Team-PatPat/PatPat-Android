package com.simply407.patpat.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000eJ\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/simply407/patpat/data/model/SharedPreferencesManager;", "", "()V", "FILE_NAME", "", "KEY_ONBOARDING_SHOWN", "KEY_USER_IS_LOGGED_IN", "sharedPref", "Landroid/content/SharedPreferences;", "init", "", "context", "Landroid/content/Context;", "isOnboardingShown", "", "isUserLoggedIn", "setOnboardingShown", "shown", "setUserIsLoggedIn", "app_debug"})
public final class SharedPreferencesManager {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String FILE_NAME = "user_info";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ONBOARDING_SHOWN = "onboarding_shown";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_USER_IS_LOGGED_IN = "user_is_logged_in";
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
}