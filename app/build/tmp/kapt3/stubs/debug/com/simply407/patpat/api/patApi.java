package com.simply407.patpat.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\f"}, d2 = {"Lcom/simply407/patpat/api/patApi;", "", "deleteChat", "Lretrofit2/Call;", "Ljava/lang/Void;", "counselorId", "", "getChat", "Lcom/simply407/patpat/data/Chat_get;", "postChatSend", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/simply407/patpat/data/Chat_sse;", "app_debug"})
public abstract interface patApi {
    
    @retrofit2.http.GET(value = "api/v1/chats/{counselorId}")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.simply407.patpat.data.Chat_get> getChat(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId);
    
    @retrofit2.http.POST(value = "api/v1/chats/{counselorId}/messages")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.rxjava3.core.Observable<com.simply407.patpat.data.Chat_sse> postChatSend(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId);
    
    @retrofit2.http.DELETE(value = "api/v1/chats/{counselorId}/messages")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.lang.Void> deleteChat(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId);
}