package com.simply407.patpat.ui.onboarding;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0018\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "clickListener", "Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter$OnboardingClickListener;", "(Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter$OnboardingClickListener;)V", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "OnboardingClickListener", "OnboardingViewHolderTypeOne", "OnboardingViewHolderTypeTwo", "app_debug"})
public final class OnboardingAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final com.simply407.patpat.ui.onboarding.OnboardingAdapter.OnboardingClickListener clickListener = null;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    @org.jetbrains.annotations.NotNull
    public static final com.simply407.patpat.ui.onboarding.OnboardingAdapter.Companion Companion = null;
    
    public OnboardingAdapter(@org.jetbrains.annotations.NotNull
    com.simply407.patpat.ui.onboarding.OnboardingAdapter.OnboardingClickListener clickListener) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter$Companion;", "", "()V", "TYPE_ONE", "", "TYPE_TWO", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&\u00a8\u0006\u0005"}, d2 = {"Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter$OnboardingClickListener;", "", "onOnboardingNextButtonClick", "", "onOnboardingStartButtonClick", "app_debug"})
    public static abstract interface OnboardingClickListener {
        
        public abstract void onOnboardingNextButtonClick();
        
        public abstract void onOnboardingStartButtonClick();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter$OnboardingViewHolderTypeOne;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/simply407/patpat/databinding/ItemOnboardingOneBinding;", "(Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter;Lcom/simply407/patpat/databinding/ItemOnboardingOneBinding;)V", "bind", "", "app_debug"})
    public final class OnboardingViewHolderTypeOne extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.simply407.patpat.databinding.ItemOnboardingOneBinding binding = null;
        
        public OnboardingViewHolderTypeOne(@org.jetbrains.annotations.NotNull
        com.simply407.patpat.databinding.ItemOnboardingOneBinding binding) {
            super(null);
        }
        
        public final void bind() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter$OnboardingViewHolderTypeTwo;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/simply407/patpat/databinding/ItemOnboardingTwoBinding;", "(Lcom/simply407/patpat/ui/onboarding/OnboardingAdapter;Lcom/simply407/patpat/databinding/ItemOnboardingTwoBinding;)V", "bind", "", "app_debug"})
    public final class OnboardingViewHolderTypeTwo extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.simply407.patpat.databinding.ItemOnboardingTwoBinding binding = null;
        
        public OnboardingViewHolderTypeTwo(@org.jetbrains.annotations.NotNull
        com.simply407.patpat.databinding.ItemOnboardingTwoBinding binding) {
            super(null);
        }
        
        public final void bind() {
        }
    }
}