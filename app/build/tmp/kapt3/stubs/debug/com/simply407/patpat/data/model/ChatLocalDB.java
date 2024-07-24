package com.simply407.patpat.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u001c\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/simply407/patpat/data/model/ChatLocalDB;", "", "()V", "sharedPref", "Landroid/content/SharedPreferences;", "deleteChatList", "", "key", "", "getChatList", "", "Lcom/simply407/patpat/data/Ui_chat;", "init", "context", "Landroid/content/Context;", "saveChatList", "chatList", "", "app_debug"})
public final class ChatLocalDB {
    private static android.content.SharedPreferences sharedPref;
    @org.jetbrains.annotations.NotNull
    public static final com.simply407.patpat.data.model.ChatLocalDB INSTANCE = null;
    
    private ChatLocalDB() {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.simply407.patpat.data.Ui_chat> getChatList(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return null;
    }
    
    public final void saveChatList(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    java.util.List<com.simply407.patpat.data.Ui_chat> chatList) {
    }
    
    public final void deleteChatList(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
    }
}