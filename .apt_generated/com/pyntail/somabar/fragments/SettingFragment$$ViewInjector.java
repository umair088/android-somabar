// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SettingFragment$$ViewInjector<T extends com.pyntail.somabar.fragments.SettingFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165439, "field 'sliderLayer'");
    target.sliderLayer = finder.castView(view, 2131165439, "field 'sliderLayer'");
    view = finder.findRequiredView(source, 2131165428, "field 'measurmentContainer'");
    target.measurmentContainer = finder.castView(view, 2131165428, "field 'measurmentContainer'");
  }

  @Override public void reset(T target) {
    target.sliderLayer = null;
    target.measurmentContainer = null;
  }
}
