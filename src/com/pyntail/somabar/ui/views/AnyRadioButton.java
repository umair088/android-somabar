package com.pyntail.somabar.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.andreabaccega.widget.FormEditText;

public class AnyRadioButton extends RadioButton {

	public AnyRadioButton(Context context) {
		super(context);

	}

	public AnyRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		if (!this.isInEditMode()) {
			Util.setTypeface(attrs, this);
		}
	}

	public AnyRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		if (!this.isInEditMode()) {
			Util.setTypeface(attrs, this);
		}

	}

}
