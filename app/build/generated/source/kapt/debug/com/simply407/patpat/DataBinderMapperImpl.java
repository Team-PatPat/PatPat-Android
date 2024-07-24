package com.simply407.patpat;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.simply407.patpat.databinding.FragmentChattingBindingImpl;
import com.simply407.patpat.databinding.ItemChatfromAiBindingImpl;
import com.simply407.patpat.databinding.ItemChattoAiBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_FRAGMENTCHATTING = 1;

  private static final int LAYOUT_ITEMCHATFROMAI = 2;

  private static final int LAYOUT_ITEMCHATTOAI = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.simply407.patpat.R.layout.fragment_chatting, LAYOUT_FRAGMENTCHATTING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.simply407.patpat.R.layout.item_chatfrom_ai, LAYOUT_ITEMCHATFROMAI);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.simply407.patpat.R.layout.item_chatto_ai, LAYOUT_ITEMCHATTOAI);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_FRAGMENTCHATTING: {
          if ("layout/fragment_chatting_0".equals(tag)) {
            return new FragmentChattingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_chatting is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCHATFROMAI: {
          if ("layout/item_chatfrom_ai_0".equals(tag)) {
            return new ItemChatfromAiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_chatfrom_ai is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMCHATTOAI: {
          if ("layout/item_chatto_ai_0".equals(tag)) {
            return new ItemChattoAiBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_chatto_ai is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/fragment_chatting_0", com.simply407.patpat.R.layout.fragment_chatting);
      sKeys.put("layout/item_chatfrom_ai_0", com.simply407.patpat.R.layout.item_chatfrom_ai);
      sKeys.put("layout/item_chatto_ai_0", com.simply407.patpat.R.layout.item_chatto_ai);
    }
  }
}
