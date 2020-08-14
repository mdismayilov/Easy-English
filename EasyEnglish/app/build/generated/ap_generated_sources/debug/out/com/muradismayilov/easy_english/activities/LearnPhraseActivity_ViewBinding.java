// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textview.MaterialTextView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LearnPhraseActivity_ViewBinding implements Unbinder {
  private LearnPhraseActivity target;

  private View view7f0900eb;

  private View view7f0900e7;

  private View view7f0900ea;

  private View view7f0900ed;

  @UiThread
  public LearnPhraseActivity_ViewBinding(LearnPhraseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LearnPhraseActivity_ViewBinding(final LearnPhraseActivity target, View source) {
    this.target = target;

    View view;
    target.swipeFlingAdapterView = Utils.findRequiredViewAsType(source, R.id.learn_phrase_swipeFLingAdapterView, "field 'swipeFlingAdapterView'", SwipeFlingAdapterView.class);
    target.learn_phrase_phraseMeaningTV = Utils.findRequiredViewAsType(source, R.id.learn_phrase_phraseMeaningTV, "field 'learn_phrase_phraseMeaningTV'", MaterialTextView.class);
    target.learn_phrase_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.learn_phrase_bannerAdPlaceholderFL, "field 'learn_phrase_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.learn_phrase_soundIV, "method 'learn_phrase_soundIVClicked'");
    view7f0900eb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_phrase_soundIVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.learn_phrase_moreIV, "method 'learn_phrase_moreIVClicked'");
    view7f0900e7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_phrase_moreIVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.learn_phrase_previousIV, "method 'learn_phrase_previousIVClicked'");
    view7f0900ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_phrase_previousIVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.learn_phrase_videoIV, "method 'learn_phrase_videoIVClicked'");
    view7f0900ed = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_phrase_videoIVClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.went_wrong = res.getString(R.string.went_wrong);
    target.copied = res.getString(R.string.copied);
    target.share = res.getString(R.string.share);
    target.download_the_app = res.getString(R.string.download_the_app);
    target.app_link = res.getString(R.string.app_link);
    target.app_full_name = res.getString(R.string.app_full_name);
    target.video_base_url = res.getString(R.string.video_base_url);
    target.easyenglish_learn_phrase_banner = res.getString(R.string.easyenglish_learn_phrase_banner);
    target.easyenglish_learn_phrase_interstitial = res.getString(R.string.easyenglish_learn_phrase_interstitial);
    target.we_need_ads = res.getString(R.string.we_need_ads);
  }

  @Override
  @CallSuper
  public void unbind() {
    LearnPhraseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeFlingAdapterView = null;
    target.learn_phrase_phraseMeaningTV = null;
    target.learn_phrase_bannerAdPlaceholderFL = null;

    view7f0900eb.setOnClickListener(null);
    view7f0900eb = null;
    view7f0900e7.setOnClickListener(null);
    view7f0900e7 = null;
    view7f0900ea.setOnClickListener(null);
    view7f0900ea = null;
    view7f0900ed.setOnClickListener(null);
    view7f0900ed = null;
  }
}
