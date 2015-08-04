package com.pyntail.somabar.ui.viewholder;

import android.content.Context;
import android.view.View;

/**
 * Class to hold reference of views referenced in more than one places. This can
 * help redundancy and abstract behaviour.
 * 
 */
public abstract class ViewHolder {

	Context ctx;

	public ViewHolder(View view, Context ctx) {
		this.ctx = ctx;
		initViews(view);
	}

	public abstract void initViews(View view);

}
