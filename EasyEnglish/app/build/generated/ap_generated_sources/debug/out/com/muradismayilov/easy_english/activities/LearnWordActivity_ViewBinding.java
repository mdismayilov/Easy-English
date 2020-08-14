// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LearnWordActivity_ViewBinding implements Unbinder {
  private LearnWordActivity target;

  private View view7f0900f2;

  private View view7f0900ef;

  private View view7f0900f1;

  private View view7f0900f0;

  @UiThread
  public LearnWordActivity_ViewBinding(LearnWordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LearnWordActivity_ViewBinding(final LearnWordActivity target, View source) {
    this.target = target;

    View view;
    target.learn_word_swipeFLingAdapterView = Utils.findRequiredViewAsType(source, R.id.learn_word_swipeFLingAdapterView, "field 'learn_word_swipeFLingAdapterView'", SwipeFlingAdapterView.class);
    target.learn_word_wordMeaningTV = Utils.findRequiredViewAsType(source, R.id.learn_word_wordMeaningTV, "field 'learn_word_wordMeaningTV'", TextView.class);
    target.learn_word_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.learn_word_bannerAdPlaceholderFL, "field 'learn_word_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.learn_word_soundIV, "method 'learn_word_soundIVClicked'");
    view7f0900f2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_word_soundIVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.learn_word_moreIV, "method 'learn_word_moreIVClicked'");
    view7f0900ef = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_word_moreIVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.learn_word_previousIV, "method 'learn_word_previousIVClicked'");
    view7f0900f1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_word_previousIVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.learn_word_moreWordTV, "method 'learn_word_moreWordTVClicked'");
    view7f0900f0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.learn_word_moreWordTVClicked();
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
    target.no_more_detail = res.getString(R.string.no_more_detail);
    target.easyenglish_learn_word_banner = res.getString(R.string.easyenglish_learn_word_banner);
    target.easyenglish_learn_word_interstitial = res.getString(R.string.easyenglish_learn_word_interstitial);
    target.we_need_ads = res.getString(R.string.we_need_ads);
  }

  @Override
  @CallSuper
  public void unbind() {
    LearnWordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.learn_word_swipeFLingAdapterView = null;
    target.learn_word_wordMeaningTV = null;
    target.learn_word_bannerAdPlaceholderFL = null;

    view7f0900f2.setOnClickListener(null);
    view7f0900f2 = null;
    view7f0900ef.setOnClickListener(null);
    view7f0900ef = null;
    view7f0900f1.setOnClickListener(null);
    view7f0900f1 = null;
    view7f0900f0.setOnClickListener(null);
    view7f0900f0 = null;
  }
}
