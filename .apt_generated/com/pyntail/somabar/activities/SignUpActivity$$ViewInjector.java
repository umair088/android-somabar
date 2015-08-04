// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SignUpActivity$$ViewInjector<T extends com.pyntail.somabar.activities.SignUpActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165293, "field 'txtEmail'");
    target.txtEmail = finder.castView(view, 2131165293, "field 'txtEmail'");
    view = finder.findRequiredView(source, 2131165299, "field 'txtUserName'");
    target.txtUserName = finder.castView(view, 2131165299, "field 'txtUserName'");
    view = finder.findRequiredView(source, 2131165286, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131165286, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131165296, "field 'imgUser'");
    target.imgUser = finder.castView(view, 2131165296, "field 'imgUser'");
    view = finder.findRequiredView(source, 2131165301, "field 'txtPassword'");
    target.txtPassword = finder.castView(view, 2131165301, "field 'txtPassword'");
    view = finder.findRequiredView(source, 2131165305, "field 'btnSignUp'");
    target.btnSignUp = finder.castView(view, 2131165305, "field 'btnSignUp'");
    view = finder.findRequiredView(source, 2131165297, "field 'txtFullName'");
    target.txtFullName = finder.castView(view, 2131165297, "field 'txtFullName'");
    view = finder.findRequiredView(source, 2131165303, "field 'txtDob'");
    target.txtDob = finder.castView(view, 2131165303, "field 'txtDob'");
  }

  @Override public void reset(T target) {
    target.txtEmail = null;
    target.txtUserName = null;
    target.titleBar = null;
    target.imgUser = null;
    target.txtPassword = null;
    target.btnSignUp = null;
    target.txtFullName = null;
    target.txtDob = null;
  }
}
