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

public class PhraseActivity_ViewBinding implements Unbinder {
  private PhraseActivity target;

  private View view7f09013c;

  @UiThread
  public PhraseActivity_ViewBinding(PhraseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhraseActivity_ViewBinding(final PhraseActivity target, View source) {
    this.target = target;

    View view;
    target.phrase_phraseRV = Utils.findRequiredViewAsType(source, R.id.phrase_phraseRV, "field 'phrase_phraseRV'", RecyclerView.class);
    target.phrase_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.phrase_bannerAdPlaceholderFL, "field 'phrase_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.phrase_startBTN, "method 'phrase_startBTNClicked'");
    view7f09013c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.phrase_startBTNClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.easyenglish_phrases_banner = res.getString(R.string.easyenglish_phrases_banner);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhraseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.phrase_phraseRV = null;
    target.phrase_bannerAdPlaceholderFL = null;

    view7f09013c.setOnClickListener(null);
    view7f09013c = null;
  }
}
