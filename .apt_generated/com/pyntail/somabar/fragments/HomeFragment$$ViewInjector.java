// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class HomeFragment$$ViewInjector<T extends com.pyntail.somabar.fragments.HomeFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165389, "field 'txtCommunity'");
    target.txtCommunity = finder.castView(view, 2131165389, "field 'txtCommunity'");
    view = finder.findRequiredView(source, 2131165386, "field 'rippleBackground'");
    target.rippleBackground = finder.castView(view, 2131165386, "field 'rippleBackground'");
    view = finder.findRequiredView(source, 2131165390, "field 'txtMyDrinks'");
    target.txtMyDrinks = finder.castView(view, 2131165390, "field 'txtMyDrinks'");
    view = finder.findRequiredView(source, 2131165387, "field 'btnStatus'");
    target.btnStatus = view;
    view = finder.findRequiredView(source, 2131165388, "field 'txtDiscoverDrinks'");
    target.txtDiscoverDrinks = finder.castView(view, 2131165388, "field 'txtDiscoverDrinks'");
  }

  @Override public void reset(T target) {
    target.txtCommunity = null;
    target.rippleBackground = null;
    target.txtMyDrinks = null;
    target.btnStatus = null;
    target.txtDiscoverDrinks = null;
  }
}
