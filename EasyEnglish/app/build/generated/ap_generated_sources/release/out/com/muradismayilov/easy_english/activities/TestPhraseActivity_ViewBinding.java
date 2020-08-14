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

public class TestPhraseActivity_ViewBinding implements Unbinder {
  private TestPhraseActivity target;

  private View view7f090194;

  private View view7f090199;

  private View view7f090192;

  @UiThread
  public TestPhraseActivity_ViewBinding(TestPhraseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestPhraseActivity_ViewBinding(final TestPhraseActivity target, View source) {
    this.target = target;

    View view;
    target.test_phrase_scoreTV = Utils.findRequiredViewAsType(source, R.id.test_phrase_scoreTV, "field 'test_phrase_scoreTV'", MaterialTextView.class);
    target.test_phrase_questionTV = Utils.findRequiredViewAsType(source, R.id.test_phrase_questionTV, "field 'test_phrase_questionTV'", MaterialTextView.class);
    target.test_phrase_firstAnswerTV = Utils.findRequiredViewAsType(source, R.id.test_phrase_firstAnswerTV, "field 'test_phrase_firstAnswerTV'", MaterialTextView.class);
    target.test_phrase_secondAnswerTV = Utils.findRequiredViewAsType(source, R.id.test_phrase_secondAnswerTV, "field 'test_phrase_secondAnswerTV'", MaterialTextView.class);
    target.test_phrase_questionTranscriptionTV = Utils.findRequiredViewAsType(source, R.id.test_phrase_questionTranscriptionTV, "field 'test_phrase_questionTranscriptionTV'", MaterialTextView.class);
    view = Utils.findRequiredView(source, R.id.test_phrase_firstAnswerCV, "field 'test_phrase_firstAnswerCV' and method 'test_firstAnswerCVClicked'");
    target.test_phrase_firstAnswerCV = Utils.castView(view, R.id.test_phrase_firstAnswerCV, "field 'test_phrase_firstAnswerCV'", MaterialCardView.class);
    view7f090194 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_firstAnswerCVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.test_phrase_secondAnswerCV, "field 'test_phrase_secondAnswerCV' and method 'test_secondAnswerCVClicked'");
    target.test_phrase_secondAnswerCV = Utils.castView(view, R.id.test_phrase_secondAnswerCV, "field 'test_phrase_secondAnswerCV'", MaterialCardView.class);
    view7f090199 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_secondAnswerCVClicked();
      }
    });
    target.test_phrase_bannerAdPlaceholderFL = Utils.findRequiredViewAsType(source, R.id.test_phrase_bannerAdPlaceholderFL, "field 'test_phrase_bannerAdPlaceholderFL'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.test_phrase_awardIV, "field 'test_phrase_awardIV' and method 'test_phrase_awardIVClicked'");
    target.test_phrase_awardIV = Utils.castView(view, R.id.test_phrase_awardIV, "field 'test_phrase_awardIV'", ImageView.class);
    view7f090192 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_phrase_awardIVClicked();
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
    TestPhraseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.test_phrase_scoreTV = null;
    target.test_phrase_questionTV = null;
    target.test_phrase_firstAnswerTV = null;
    target.test_phrase_secondAnswerTV = null;
    target.test_phrase_questionTranscriptionTV = null;
    target.test_phrase_firstAnswerCV = null;
    target.test_phrase_secondAnswerCV = null;
    target.test_phrase_bannerAdPlaceholderFL = null;
    target.test_phrase_awardIV = null;

    view7f090194.setOnClickListener(null);
    view7f090194 = null;
    view7f090199.setOnClickListener(null);
    view7f090199 = null;
    view7f090192.setOnClickListener(null);
    view7f090192 = null;
  }
}
