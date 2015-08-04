// Generated code from Butter Knife. Do not modify!
package com.pyntail.somabar.activities;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class BaseFacebookActivity$$ViewInjector<T extends com.pyntail.somabar.activities.BaseFacebookActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165312, "field 'profilePictureView'");
    target.profilePictureView = finder.castView(view, 2131165312, "field 'profilePictureView'");
    view = finder.findRequiredView(source, 2131165314, "field 'postPhotoButton'");
    target.postPhotoButton = finder.castView(view, 2131165314, "field 'postPhotoButton'");
    view = finder.findRequiredView(source, 2131165310, "field 'btnFacebook'");
    target.btnFacebook = finder.castView(view, 2131165310, "field 'btnFacebook'");
    view = finder.findRequiredView(source, 2131165313, "field 'postStatusUpdateButton'");
    target.postStatusUpdateButton = finder.castView(view, 2131165313, "field 'postStatusUpdateButton'");
    view = finder.findRequiredView(source, 2131165311, "field 'greeting'");
    target.greeting = finder.castView(view, 2131165311, "field 'greeting'");
  }

  @Override public void reset(T target) {
    target.profilePictureView = null;
    target.postPhotoButton = null;
    target.btnFacebook = null;
    target.postStatusUpdateButton = null;
    target.greeting = null;
  }
}
