// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.ui.side.menu;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ResideMenu$$ViewInjector<T extends com.pyntail.somabar.ui.side.menu.ResideMenu> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165310, "field 'btnFacebook'");
    target.btnFacebook = finder.castView(view, 2131165310, "field 'btnFacebook'");
    view = finder.findRequiredView(source, 2131165299, "field 'txtUserName'");
    target.txtUserName = finder.castView(view, 2131165299, "field 'txtUserName'");
    view = finder.findRequiredView(source, 2131165315, "field 'btnSignup'");
    target.btnSignup = finder.castView(view, 2131165315, "field 'btnSignup'");
    view = finder.findRequiredView(source, 2131165477, "field 'txtUserTitle'");
    target.txtUserTitle = finder.castView(view, 2131165477, "field 'txtUserTitle'");
    view = finder.findRequiredView(source, 2131165316, "field 'btnSignin'");
    target.btnSignin = finder.castView(view, 2131165316, "field 'btnSignin'");
  }

  @Override public void reset(T target) {
    target.btnFacebook = null;
    target.txtUserName = null;
    target.btnSignup = null;
    target.txtUserTitle = null;
    target.btnSignin = null;
  }
}
