// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class CommunityFragment$$ViewInjector<T extends com.pyntail.somabar.fragments.CommunityFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165352, "field 'btnCreateNewDrink'");
    target.btnCreateNewDrink = finder.castView(view, 2131165352, "field 'btnCreateNewDrink'");
    view = finder.findRequiredView(source, 2131165351, "field 'recyclerView'");
    target.recyclerView = finder.castView(view, 2131165351, "field 'recyclerView'");
  }

  @Override public void reset(T target) {
    target.btnCreateNewDrink = null;
    target.recyclerView = null;
  }
}
