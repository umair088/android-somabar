// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MyDrinkFragment$$ViewInjector<T extends com.pyntail.somabar.fragments.MyDrinkFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165351, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131165351, "field 'recyclerView'");
    view = finder.findRequiredView(source, 2131165353, "field 'noDataFound'");
    target.noDataFound = finder.castView(view, 2131165353, "field 'noDataFound'");
  }

  @Override public void reset(T target) {
    target.recyclerView = null;
    target.noDataFound = null;
  }
}
