// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserActivity_ViewBinding implements Unbinder {
  private UserActivity target;

  @UiThread
  public UserActivity_ViewBinding(UserActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserActivity_ViewBinding(UserActivity target, View source) {
    this.target = target;

    target.user_userRV = Utils.findRequiredViewAsType(source, R.id.user_userRV, "field 'user_userRV'", RecyclerView.class);

    Context context = source.getContext();
    Resources res = context.getResources();
    target.no_internet_connection = res.getString(R.string.no_internet_connection);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.user_userRV = null;
  }
}
