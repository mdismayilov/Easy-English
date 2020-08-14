// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.card.MaterialCardView;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  private View view7f0900c9;

  private View view7f0900d1;

  private View view7f0900cb;

  private View view7f0900cf;

  private View view7f0900cd;

  private View view7f0900c7;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(final HomeActivity target, View source) {
    this.target = target;

    View view;
    target.home_wordsIV = Utils.findRequiredViewAsType(source, R.id.home_wordsIV, "field 'home_wordsIV'", ImageView.class);
    target.home_phrasesIV = Utils.findRequiredViewAsType(source, R.id.home_phrasesIV, "field 'home_phrasesIV'", ImageView.class);
    target.home_testIV = Utils.findRequiredViewAsType(source, R.id.home_testIV, "field 'home_testIV'", ImageView.class);
    target.home_supportIV = Utils.findRequiredViewAsType(source, R.id.home_supportIV, "field 'home_supportIV'", ImageView.class);
    target.home_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.home_bannerAdPlaceholderFL, "field 'home_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.home_goPremiumCV, "field 'home_goPremiumCV' and method 'home_goPremiumCVClicked'");
    target.home_goPremiumCV = Utils.castView(view, R.id.home_goPremiumCV, "field 'home_goPremiumCV'", MaterialCardView.class);
    view7f0900c9 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.home_goPremiumCVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.home_wordsLL, "method 'home_wordsLLClicked'");
    view7f0900d1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.home_wordsLLClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.home_phrasesLL, "method 'home_phrasesLLClicked'");
    view7f0900cb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.home_phrasesLLClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.home_testLL, "method 'home_testLLCLicked'");
    view7f0900cf = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.home_testLLCLicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.home_supportLL, "method 'home_supportLLClicked'");
    view7f0900cd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.home_supportLLClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.home_aboutUsTV, "method 'home_aboutUsTVClicked'");
    view7f0900c7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.home_aboutUsTVClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.press_back_again = res.getString(R.string.press_back_again);
    target.app_link = res.getString(R.string.app_link);
    target.thank_you_for_watching_ad = res.getString(R.string.thank_you_for_watching_ad);
    target.easyenglish_home_banner = res.getString(R.string.easyenglish_home_banner);
    target.easyenglish_home_interstitial = res.getString(R.string.easyenglish_home_interstitial);
    target.payment_canceled = res.getString(R.string.payment_canceled);
    target.no_internet_connection = res.getString(R.string.no_internet_connection);
    target.went_wrong = res.getString(R.string.went_wrong);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.home_wordsIV = null;
    target.home_phrasesIV = null;
    target.home_testIV = null;
    target.home_supportIV = null;
    target.home_bannerAdPlaceholderFL = null;
    target.home_goPremiumCV = null;

    view7f0900c9.setOnClickListener(null);
    view7f0900c9 = null;
    view7f0900d1.setOnClickListener(null);
    view7f0900d1 = null;
    view7f0900cb.setOnClickListener(null);
    view7f0900cb = null;
    view7f0900cf.setOnClickListener(null);
    view7f0900cf = null;
    view7f0900cd.setOnClickListener(null);
    view7f0900cd = null;
    view7f0900c7.setOnClickListener(null);
    view7f0900c7 = null;
  }
}
