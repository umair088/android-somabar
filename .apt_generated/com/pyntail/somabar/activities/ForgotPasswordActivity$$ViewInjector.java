// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ForgotPasswordActivity$$ViewInjector<T extends com.pyntail.somabar.activities.ForgotPasswordActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165293, "field 'txtEmail'");
    target.txtEmail = finder.castView(view, 2131165293, "field 'txtEmail'");
    view = finder.findRequiredView(source, 2131165286, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131165286, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131165295, "field 'btnSend'");
    target.btnSend = finder.castView(view, 2131165295, "field 'btnSend'");
  }

  @Override public void reset(T target) {
    target.txtEmail = null;
    target.titleBar = null;
    target.btnSend = null;
  }
}
