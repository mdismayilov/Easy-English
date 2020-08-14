// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoInternetDialogActivity_ViewBinding implements Unbinder {
  private NoInternetDialogActivity target;

  private View view7f090048;

  @UiThread
  public NoInternetDialogActivity_ViewBinding(NoInternetDialogActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NoInternetDialogActivity_ViewBinding(final NoInternetDialogActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.activity_no_internet_dialog_tryAgainBTN, "method 'activity_no_internet_dialog_tryAgainBTNClicked'");
    view7f090048 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.activity_no_internet_dialog_tryAgainBTNClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f090048.setOnClickListener(null);
    view7f090048 = null;
  }
}
