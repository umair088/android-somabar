// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class MainActivity$$ViewInjector<T extends com.pyntail.somabar.activities.MainActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165285, "field 'mainLayout'");
    target.mainLayout = finder.castView(view, 2131165285, "field 'mainLayout'");
    view = finder.findRequiredView(source, 2131165287, "field 'mainFrameLayout'");
    target.mainFrameLayout = finder.castView(view, 2131165287, "field 'mainFrameLayout'");
    view = finder.findRequiredView(source, 2131165288, "field 'popOverContainer'");
    target.popOverContainer = finder.castView(view, 2131165288, "field 'popOverContainer'");
    view = finder.findRequiredView(source, 2131165286, "field 'titleBar'");
    target.titleBar = finder.castView(view, 2131165286, "field 'titleBar'");
    view = finder.findRequiredView(source, 2131165289, "field 'progressBar'");
    target.progressBar = finder.castView(view, 2131165289, "field 'progressBar'");
  }

  @Override public void reset(T target) {
    target.mainLayout = null;
    target.mainFrameLayout = null;
    target.popOverContainer = null;
    target.titleBar = null;
    target.progressBar = null;
  }
}
