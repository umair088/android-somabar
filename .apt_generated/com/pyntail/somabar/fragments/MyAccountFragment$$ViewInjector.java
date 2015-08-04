// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MyAccountFragment$$ViewInjector<T extends com.pyntail.somabar.fragments.MyAccountFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165417, "field 'btnUpdate'");
    target.btnUpdate = finder.castView(view, 2131165417, "field 'btnUpdate'");
    view = finder.findRequiredView(source, 2131165418, "field 'txtLogOut'");
    target.txtLogOut = finder.castView(view, 2131165418, "field 'txtLogOut'");
  }

  @Override public void reset(T target) {
    target.btnUpdate = null;
    target.txtLogOut = null;
  }
}
