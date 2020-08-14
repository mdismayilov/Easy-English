// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textfield.TextInputEditText;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TestLogInActivity_ViewBinding implements Unbinder {
  private TestLogInActivity target;

  private View view7f09018c;

  private View view7f09018d;

  private View view7f090191;

  @UiThread
  public TestLogInActivity_ViewBinding(TestLogInActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestLogInActivity_ViewBinding(final TestLogInActivity target, View source) {
    this.target = target;

    View view;
    target.test_log_in_emailET = Utils.findRequiredViewAsType(source, R.id.test_log_in_emailET, "field 'test_log_in_emailET'", AutoCompleteTextView.class);
    target.test_log_in_passwordET = Utils.findRequiredViewAsType(source, R.id.test_log_in_passwordET, "field 'test_log_in_passwordET'", TextInputEditText.class);
    target.test_log_in_progressBar = Utils.findRequiredViewAsType(source, R.id.test_log_in_progressBar, "field 'test_log_in_progressBar'", ProgressBar.class);
    target.test_log_in_mainCL = Utils.findRequiredViewAsType(source, R.id.test_log_in_mainCL, "field 'test_log_in_mainCL'", ConstraintLayout.class);
    view = Utils.findRequiredView(source, R.id.test_log_in_forgotPasswordTV, "method 'test_log_in_forgotPasswordTVClicked'");
    view7f09018c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_log_in_forgotPasswordTVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.test_log_in_logInBTN, "method 'test_log_in_logInBTNClicked'");
    view7f09018d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_log_in_logInBTNClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.test_log_in_signUpTV, "method 'test_log_in_signUpTVClicked'");
    view7f090191 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_log_in_signUpTVClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.no_internet_connection = res.getString(R.string.no_internet_connection);
    target.fill_empty_fields = res.getString(R.string.fill_empty_fields);
    target.went_wrong = res.getString(R.string.went_wrong);
    target.verify_your_email_address = res.getString(R.string.verify_your_email_address);
    target.enter_your_email = res.getString(R.string.enter_your_email);
    target.check_your_email_to_reset_password = res.getString(R.string.check_your_email_to_reset_password);
    target.you_are_not_premium = res.getString(R.string.you_are_not_premium);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestLogInActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.test_log_in_emailET = null;
    target.test_log_in_passwordET = null;
    target.test_log_in_progressBar = null;
    target.test_log_in_mainCL = null;

    view7f09018c.setOnClickListener(null);
    view7f09018c = null;
    view7f09018d.setOnClickListener(null);
    view7f09018d = null;
    view7f090191.setOnClickListener(null);
    view7f090191 = null;
  }
}
