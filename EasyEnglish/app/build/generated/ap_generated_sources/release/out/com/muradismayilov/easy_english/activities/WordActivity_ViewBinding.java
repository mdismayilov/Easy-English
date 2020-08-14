// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WordActivity_ViewBinding implements Unbinder {
  private WordActivity target;

  private View view7f0901d4;

  @UiThread
  public WordActivity_ViewBinding(WordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WordActivity_ViewBinding(final WordActivity target, View source) {
    this.target = target;

    View view;
    target.word_wordRV = Utils.findRequiredViewAsType(source, R.id.word_wordRV, "field 'word_wordRV'", RecyclerView.class);
    target.word_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.word_bannerAdPlaceholderFL, "field 'word_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.word_startBTN, "method 'word_startBTNClicked'");
    view7f0901d4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.word_startBTNClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.easyenglish_words_banner = res.getString(R.string.easyenglish_words_banner);
  }

  @Override
  @CallSuper
  public void unbind() {
    WordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.word_wordRV = null;
    target.word_bannerAdPlaceholderFL = null;

    view7f0901d4.setOnClickListener(null);
    view7f0901d4 = null;
  }
}
