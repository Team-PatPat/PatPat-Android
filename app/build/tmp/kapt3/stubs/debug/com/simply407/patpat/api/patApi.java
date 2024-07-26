package com.simply407.patpat.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u000f"}, d2 = {"Lcom/simply407/patpat/api/patApi;", "", "deleteChat", "Lretrofit2/Call;", "Ljava/lang/Void;", "counselorId", "", "getChat", "Lcom/simply407/patpat/data/ChatGet;", "getChatSend", "getCounselor", "postChatSend", "Lcom/simply407/patpat/data/ChatSse;", "body", "Lcom/simply407/patpat/data/messageBody;", "app_debug"})
public abstract interface patApi {
    
    @retrofit2.http.GET(value = "api/v1/chats/{counselorId}")
    @retrofit2.http.Headers(value = {"Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI"})
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.simply407.patpat.data.ChatGet> getChat(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId);
    
    @retrofit2.http.GET(value = "/api/v1/counselors")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.lang.Void> getCounselor();
    
    @retrofit2.http.GET(value = "api/v1/chats/{counselorId}/messages")
    @retrofit2.http.Headers(value = {"Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI"})
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.lang.Void> getChatSend(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId);
    
    @retrofit2.http.POST(value = "api/v1/chats/{counselorId}/messages")
    @retrofit2.http.Headers(value = {"Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjgwYzEzYmI3LTBiNzgtNGViYS1hZWYwLTliOGU2NGY2ZWE0MyIsImVtYWlsIjoidGVzdGVyQHBhdHBhdC5jb20iLCJuYW1lIjoidGVzdGVyIiwiYXZhdGFyVXJsIjpudWxsLCJpYXQiOjE3MjE5MTIyNDAsImV4cCI6MTczOTkxMjI0MH0.NFFD8mQ47m6MX_slJZP4T3bu98tgBjRzsSkkIuMT2fI"})
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<com.simply407.patpat.data.ChatSse> postChatSend(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId, @retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.simply407.patpat.data.messageBody body);
    
    @retrofit2.http.DELETE(value = "api/v1/chats/{counselorId}/messages")
    @org.jetbrains.annotations.NotNull
    public abstract retrofit2.Call<java.lang.Void> deleteChat(@retrofit2.http.Path(value = "counselorId")
    @org.jetbrains.annotations.NotNull
    java.lang.String counselorId);
}