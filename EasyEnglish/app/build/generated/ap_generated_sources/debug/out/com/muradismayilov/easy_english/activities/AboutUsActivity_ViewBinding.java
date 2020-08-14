// Generated code from Butter Knife. Do not modify!
package com.muradismayilov.easy_english.activities;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.google.android.material.textview.MaterialTextView;
import com.muradismayilov.easy_english.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AboutUsActivity_ViewBinding implements Unbinder {
  private AboutUsActivity target;

  private View view7f09000d;

  private View view7f09000e;

  private View view7f090011;

  private View view7f090013;

  private View view7f090012;

  private View view7f09000c;

  private View view7f09000f;

  private View view7f090010;

  @UiThread
  public AboutUsActivity_ViewBinding(AboutUsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutUsActivity_ViewBinding(final AboutUsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.about_us_facebookIV, "field 'about_us_facebookIV' and method 'contact_us_facebookIVCLicked'");
    target.about_us_facebookIV = Utils.castView(view, R.id.about_us_facebookIV, "field 'about_us_facebookIV'", ImageView.class);
    view7f09000d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_facebookIVCLicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.about_us_instagramIV, "field 'about_us_instagramIV' and method 'contact_us_instagramIVCLicked'");
    target.about_us_instagramIV = Utils.castView(view, R.id.about_us_instagramIV, "field 'about_us_instagramIV'", ImageView.class);
    view7f09000e = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_instagramIVCLicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.about_us_twitterIV, "field 'about_us_twitterIV' and method 'contact_us_twitterIVCLicked'");
    target.about_us_twitterIV = Utils.castView(view, R.id.about_us_twitterIV, "field 'about_us_twitterIV'", ImageView.class);
    view7f090011 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_twitterIVCLicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.about_us_whatsappIV, "field 'about_us_whatsappIV' and method 'contact_us_whatsappIVCLicked'");
    target.about_us_whatsappIV = Utils.castView(view, R.id.about_us_whatsappIV, "field 'about_us_whatsappIV'", ImageView.class);
    view7f090013 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_whatsappIVCLicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.about_us_websiteIV, "field 'about_us_websiteIV' and method 'contact_us_websiteIVCLicked'");
    target.about_us_websiteIV = Utils.castView(view, R.id.about_us_websiteIV, "field 'about_us_websiteIV'", ImageView.class);
    view7f090012 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_websiteIVCLicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.about_us_emailIV, "field 'about_us_emailIV' and method 'contact_us_emailIVCLicked'");
    target.about_us_emailIV = Utils.castView(view, R.id.about_us_emailIV, "field 'about_us_emailIV'", ImageView.class);
    view7f09000c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_emailIVCLicked();
      }
    });
    target.about_us_copyrightTV = Utils.findRequiredViewAsType(source, R.id.about_us_copyrightTV, "field 'about_us_copyrightTV'", MaterialTextView.class);
    view = Utils.findRequiredView(source, R.id.about_us_otherAppsTV, "method 'about_us_otherAppsTVClicked'");
    view7f09000f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.about_us_otherAppsTVClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.about_us_privacyPolicyTV, "method 'contact_us_privacyPolicyTVClicked'");
    view7f090010 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.contact_us_privacyPolicyTVClicked();
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.send_via = res.getString(R.string.send_via);
    target.copyright_2 = res.getString(R.string.copyright_2);
    target.copyright_3 = res.getString(R.string.copyright_3);
    target.facebook = res.getString(R.string.facebook);
    target.instagram = res.getString(R.string.instagram);
    target.twitter = res.getString(R.string.twitter);
    target.whatsapp = res.getString(R.string.whatsapp);
    target.website = res.getString(R.string.website);
    target.email = res.getString(R.string.email);
    target.google_play = res.getString(R.string.google_play);
    target.app_full_name = res.getString(R.string.app_full_name);
    target.privacy_policy_link = res.getString(R.string.privacy_policy_link);
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutUsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.about_us_facebookIV = null;
    target.about_us_instagramIV = null;
    target.about_us_twitterIV = null;
    target.about_us_whatsappIV = null;
    target.about_us_websiteIV = null;
    target.about_us_emailIV = null;
    target.about_us_copyrightTV = null;

    view7f09000d.setOnClickListener(null);
    view7f09000d = null;
    view7f09000e.setOnClickListener(null);
    view7f09000e = null;
    view7f090011.setOnClickListener(null);
    view7f090011 = null;
    view7f090013.setOnClickListener(null);
    view7f090013 = null;
    view7f090012.setOnClickListener(null);
    view7f090012 = null;
    view7f09000c.setOnClickListener(null);
    view7f09000c = null;
    view7f09000f.setOnClickListener(null);
    view7f09000f = null;
    view7f090010.setOnClickListener(null);
    view7f090010 = null;
  }
}
