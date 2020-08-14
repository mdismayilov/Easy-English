// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
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

public class TestSignUpActivity_ViewBinding implements Unbinder {
  private TestSignUpActivity target;

  private View view7f0901a1;

  private View view7f09019d;

  @UiThread
  public TestSignUpActivity_ViewBinding(TestSignUpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TestSignUpActivity_ViewBinding(final TestSignUpActivity target, View source) {
    this.target = target;

    View view;
    target.test_sign_up_usernameET = Utils.findRequiredViewAsType(source, R.id.test_sign_up_usernameET, "field 'test_sign_up_usernameET'", TextInputEditText.class);
    target.test_sign_up_emailET = Utils.findRequiredViewAsType(source, R.id.test_sign_up_emailET, "field 'test_sign_up_emailET'", TextInputEditText.class);
    target.test_sign_up_passwordET = Utils.findRequiredViewAsType(source, R.id.test_sign_up_passwordET, "field 'test_sign_up_passwordET'", TextInputEditText.class);
    target.test_sign_up_mainCL = Utils.findRequiredViewAsType(source, R.id.test_sign_up_mainCL, "field 'test_sign_up_mainCL'", ConstraintLayout.class);
    target.test_sign_up_progressBar = Utils.findRequiredViewAsType(source, R.id.test_sign_up_progressBar, "field 'test_sign_up_progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.test_sign_up_signUpBTN, "method 'test_sign_up_signUpBTNClicked'");
    view7f0901a1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_sign_up_signUpBTNClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.test_sign_up_logInTV, "method 'test_sign_up_logInTVClicked'");
    view7f09019d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.test_sign_up_logInTVClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.no_internet_connection = res.getString(R.string.no_internet_connection);
    target.fill_empty_fields = res.getString(R.string.fill_empty_fields);
    target.username_too_long = res.getString(R.string.username_too_long);
    target.went_wrong = res.getString(R.string.went_wrong);
    target.we_sent_verification_code = res.getString(R.string.we_sent_verification_code);
  }

  @Override
  @CallSuper
  public void unbind() {
    TestSignUpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.test_sign_up_usernameET = null;
    target.test_sign_up_emailET = null;
    target.test_sign_up_passwordET = null;
    target.test_sign_up_mainCL = null;
    target.test_sign_up_progressBar = null;

    view7f0901a1.setOnClickListener(null);
    view7f0901a1 = null;
    view7f09019d.setOnClickListener(null);
    view7f09019d = null;
  }
}
