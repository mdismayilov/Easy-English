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
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestWordActivity_ViewBinding implements Unbinder {
  private TestWordActivity target;

  private View view7f0901a5;

  private View view7f0901aa;

  private View view7f0901a3;

  @UiThread
  public TestWordActivity_ViewBinding(TestWordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestWordActivity_ViewBinding(final TestWordActivity target, View source) {
    this.target = target;

    View view;
    target.test_word_scoreTV = Utils.findRequiredViewAsType(source, R.id.test_word_scoreTV, "field 'test_word_scoreTV'", MaterialTextView.class);
    target.test_word_questionTV = Utils.findRequiredViewAsType(source, R.id.test_word_questionTV, "field 'test_word_questionTV'", MaterialTextView.class);
    target.test_word_firstAnswerTV = Utils.findRequiredViewAsType(source, R.id.test_word_firstAnswerTV, "field 'test_word_firstAnswerTV'", MaterialTextView.class);
    target.test_word_secondAnswerTV = Utils.findRequiredViewAsType(source, R.id.test_word_secondAnswerTV, "field 'test_word_secondAnswerTV'", MaterialTextView.class);
    target.test_word_questionTranscriptionTV = Utils.findRequiredViewAsType(source, R.id.test_word_questionTranscriptionTV, "field 'test_word_questionTranscriptionTV'", MaterialTextView.class);
    view = Utils.findRequiredView(source, R.id.test_word_firstAnswerCV, "field 'test_word_firstAnswerCV' and method 'test_firstAnswerCVClicked'");
    target.test_word_firstAnswerCV = Utils.castView(view, R.id.test_word_firstAnswerCV, "field 'test_word_firstAnswerCV'", MaterialCardView.class);
    view7f0901a5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_firstAnswerCVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.test_word_secondAnswerCV, "field 'test_word_secondAnswerCV' and method 'test_secondAnswerCVClicked'");
    target.test_word_secondAnswerCV = Utils.castView(view, R.id.test_word_secondAnswerCV, "field 'test_word_secondAnswerCV'", MaterialCardView.class);
    view7f0901aa = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_secondAnswerCVClicked();
      }
    });
    target.test_word_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.test_word_bannerAdPlaceholderFL, "field 'test_word_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.test_word_awardIV, "field 'test_word_awardIV' and method 'test_word_awardIVClicked'");
    target.test_word_awardIV = Utils.castView(view, R.id.test_word_awardIV, "field 'test_word_awardIV'", ImageView.class);
    view7f0901a3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_word_awardIVClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.right_answer = res.getString(R.string.right_answer);
    target.wrong_answer = res.getString(R.string.wrong_answer);
    target.continue_test = res.getString(R.string.continue_test);
    target.your_score = res.getString(R.string.your_score);
    target.no_internet_connection = res.getString(R.string.no_internet_connection);
    target.didnt_save_score = res.getString(R.string.didnt_save_score);
    target.easyenglish_test_banner = res.getString(R.string.easyenglish_test_banner);
    target.easyenglish_test_interstitial = res.getString(R.string.easyenglish_test_interstitial);
    target.thank_you_for_watching_ad = res.getString(R.string.thank_you_for_watching_ad);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestWordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.test_word_scoreTV = null;
    target.test_word_questionTV = null;
    target.test_word_firstAnswerTV = null;
    target.test_word_secondAnswerTV = null;
    target.test_word_questionTranscriptionTV = null;
    target.test_word_firstAnswerCV = null;
    target.test_word_secondAnswerCV = null;
    target.test_word_bannerAdPlaceholderFL = null;
    target.test_word_awardIV = null;

    view7f0901a5.setOnClickListener(null);
    view7f0901a5 = null;
    view7f0901aa.setOnClickListener(null);
    view7f0901aa = null;
    view7f0901a3.setOnClickListener(null);
    view7f0901a3 = null;
  }
}
