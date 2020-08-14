// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MoreWordActivity_ViewBinding implements Unbinder {
  private MoreWordActivity target;

  @UiThread
  public MoreWordActivity_ViewBinding(MoreWordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MoreWordActivity_ViewBinding(MoreWordActivity target, View source) {
    this.target = target;

    target.more_word_wordTV = Utils.findRequiredViewAsType(source, R.id.more_word_wordTV, "field 'more_word_wordTV'", MaterialTextView.class);
    target.more_word_meaningTV = Utils.findRequiredViewAsType(source, R.id.more_word_meaningTV, "field 'more_word_meaningTV'", MaterialTextView.class);
    target.more_word_transcriptionTV = Utils.findRequiredViewAsType(source, R.id.more_word_transcriptionTV, "field 'more_word_transcriptionTV'", MaterialTextView.class);
    target.more_word_definitionTV = Utils.findRequiredViewAsType(source, R.id.more_word_definitionTV, "field 'more_word_definitionTV'", MaterialTextView.class);
    target.more_word_exampleTV = Utils.findRequiredViewAsType(source, R.id.more_word_exampleTV, "field 'more_word_exampleTV'", MaterialTextView.class);
    target.more_word_synonymTV = Utils.findRequiredViewAsType(source, R.id.more_word_synonymTV, "field 'more_word_synonymTV'", MaterialTextView.class);
    target.more_word_antonymTV = Utils.findRequiredViewAsType(source, R.id.more_word_antonymTV, "field 'more_word_antonymTV'", MaterialTextView.class);
    target.more_word_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.more_word_bannerAdPlaceholderFL, "field 'more_word_bannerAdPlaceholderFL'", FrameLayout.class);

    Context context = source.getContext();
    Resources res = context.getResources();
    target.went_wrong = res.getString(R.string.went_wrong);
    target.easyenglish_more_word_banner = res.getString(R.string.easyenglish_more_word_banner);
  }

  @Override
  @CallSuper
  public void unbind() {
    MoreWordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.more_word_wordTV = null;
    target.more_word_meaningTV = null;
    target.more_word_transcriptionTV = null;
    target.more_word_definitionTV = null;
    target.more_word_exampleTV = null;
    target.more_word_synonymTV = null;
    target.more_word_antonymTV = null;
    target.more_word_bannerAdPlaceholderFL = null;
  }
}
