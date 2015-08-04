// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class StarterActivity$$ViewInjector<T extends com.pyntail.somabar.activities.StarterActivity> extends com.pyntail.somabar.activities.BaseFacebookActivity$$ViewInjector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    super.inject(finder, target, source);

    View view;
    view = finder.findRequiredView(source, 2131165316, "field 'btnSignin'");
    target.btnSignin = finder.castView(view, 2131165316, "field 'btnSignin'");
    view = finder.findRequiredView(source, 2131165315, "field 'btnSignup'");
    target.btnSignup = finder.castView(view, 2131165315, "field 'btnSignup'");
    view = finder.findRequiredView(source, 2131165317, "field 'txtSkip'");
    target.txtSkip = finder.castView(view, 2131165317, "field 'txtSkip'");
    view = finder.findRequiredView(source, 2131165309, "field 'imgFacebook'");
    target.imgFacebook = finder.castView(view, 2131165309, "field 'imgFacebook'");
  }

  @Override public void reset(T target) {
    super.reset(target);

    target.btnSignin = null;
    target.btnSignup = null;
    target.txtSkip = null;
    target.imgFacebook = null;
  }
}
