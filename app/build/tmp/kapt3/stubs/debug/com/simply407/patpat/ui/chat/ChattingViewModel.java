package com.simply407.patpat.ui.chat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005J\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0019\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001a"}, d2 = {"Lcom/simply407/patpat/ui/chat/ChattingViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_buttonState", "Landroidx/lifecycle/MutableLiveData;", "", "_items", "", "Lcom/simply407/patpat/data/Ui_chat;", "buttonState", "Landroidx/lifecycle/LiveData;", "getButtonState", "()Landroidx/lifecycle/LiveData;", "currentItems", "items", "getItems", "patApi", "Lcom/simply407/patpat/api/patApi;", "kotlin.jvm.PlatformType", "getPatApi", "()Lcom/simply407/patpat/api/patApi;", "activeBtn", "", "b", "addItem", "Companion", "app_debug"})
public final class ChattingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String SIGNAL_MESSAGE = "\uc785\ub825\uc911..";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String COUNSELORID = "8b5ec154-1346-4f2a-afbc-a83ea62b4288";
    private final com.simply407.patpat.api.patApi patApi = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.simply407.patpat.data.Ui_chat> currentItems = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.simply407.patpat.data.Ui_chat>> _items = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _buttonState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.simply407.patpat.ui.chat.ChattingViewModel.Companion Companion = null;
    
    public ChattingViewModel() {
        super();
    }
    
    public final com.simply407.patpat.api.patApi getPatApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.simply407.patpat.data.Ui_chat>> getItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> getButtonState() {
        return null;
    }
    
    public final void activeBtn(boolean b) {
    }
    
    public final void addItem(@org.jetbrains.annotations.NotNull
    com.simply407.patpat.data.Ui_chat items) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/simply407/patpat/ui/chat/ChattingViewModel$Companion;", "", "()V", "COUNSELORID", "", "SIGNAL_MESSAGE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}