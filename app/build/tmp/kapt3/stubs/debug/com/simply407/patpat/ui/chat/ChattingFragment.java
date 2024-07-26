package com.simply407.patpat.ui.chat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 02\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0017J\u001a\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u001c2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010\'\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0015H\u0002J\b\u0010)\u001a\u00020\u0017H\u0002J \u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010/\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/simply407/patpat/ui/chat/ChattingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/simply407/patpat/databinding/FragmentChattingBinding;", "binding", "getBinding", "()Lcom/simply407/patpat/databinding/FragmentChattingBinding;", "chatlist", "", "Lcom/simply407/patpat/data/Ui_chat;", "imageResource", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "rvAdapter", "Lcom/simply407/patpat/ui/chat/ChattingAdapter;", "viewModel", "Lcom/simply407/patpat/ui/chat/ChattingViewModel;", "getThemeResource", "str", "", "loadChat", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "requestGetChat", "patApi", "Lcom/simply407/patpat/api/patApi;", "requestGetChatSend", "requestPostChatSend", "message", "saveChat", "setupSendButtonClickListener", "chatInput", "Landroid/widget/EditText;", "chatSendBtn", "Landroid/widget/ImageButton;", "setupTextChangedListener", "Companion", "app_debug"})
public final class ChattingFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.simply407.patpat.databinding.FragmentChattingBinding _binding;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.simply407.patpat.data.Ui_chat> chatlist;
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private com.simply407.patpat.ui.chat.ChattingAdapter rvAdapter;
    private com.simply407.patpat.ui.chat.ChattingViewModel viewModel;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String COUNSELORID = "8b5ec154-1346-4f2a-afbc-a83ea62b4288";
    @org.jetbrains.annotations.NotNull
    private static java.lang.String initMessage = "";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String ROOM1 = "\ubcf5\ub0a8\uc774";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String ROOM2 = "\ub2e5\ud130 \ub0c9\ucca0\ud55c";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String ROOM3 = "\uacfd\ub450\ud314";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String ROOM4 = "\ucf54\ucf54";
    private final int imageResource = 0;
    @org.jetbrains.annotations.NotNull
    public static final com.simply407.patpat.ui.chat.ChattingFragment.Companion Companion = null;
    
    public ChattingFragment() {
        super();
    }
    
    private final com.simply407.patpat.databinding.FragmentChattingBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.Q)
    @org.jetbrains.annotations.Nullable
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final int getThemeResource(java.lang.String str) {
        return 0;
    }
    
    private final void loadChat() {
    }
    
    private final void saveChat() {
    }
    
    private final void setupTextChangedListener(android.widget.EditText chatInput, android.widget.ImageButton chatSendBtn, com.simply407.patpat.ui.chat.ChattingViewModel viewModel) {
    }
    
    private final void setupSendButtonClickListener(android.widget.EditText chatInput, android.widget.ImageButton chatSendBtn, com.simply407.patpat.ui.chat.ChattingViewModel viewModel) {
    }
    
    private final void requestGetChat(com.simply407.patpat.api.patApi patApi) {
    }
    
    private final void requestGetChatSend(com.simply407.patpat.api.patApi patApi) {
    }
    
    private final void requestPostChatSend(com.simply407.patpat.api.patApi patApi, java.lang.String message) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/simply407/patpat/ui/chat/ChattingFragment$Companion;", "", "()V", "COUNSELORID", "", "ROOM1", "ROOM2", "ROOM3", "ROOM4", "initMessage", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}