// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SignInActivity$$ViewInjector<T extends com.pyntail.somabar.activities.SignInActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165286, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131165286, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131165307, "field 'btnSignin'");
    target.btnSignin = finder.castView(view, 2131165307, "field 'btnSignin'");
    view = finder.findRequiredView(source, 2131165291, "field 'txtForgotPassword'");
    target.txtForgotPassword = finder.castView(view, 2131165291, "field 'txtForgotPassword'");
    view = finder.findRequiredView(source, 2131165293, "field 'txtEmail'");
    target.txtEmail = finder.castView(view, 2131165293, "field 'txtEmail'");
    view = finder.findRequiredView(source, 2131165301, "field 'txtPassword'");
    target.txtPassword = finder.castView(view, 2131165301, "field 'txtPassword'");
  }

  @Override public void reset(T target) {
    target.titleBar = null;
    target.btnSignin = null;
    target.txtForgotPassword = null;
    target.txtEmail = null;
    target.txtPassword = null;
  }
}
