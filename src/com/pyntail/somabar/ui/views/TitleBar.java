package com.pyntail.somabar.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.DockActivity;

public class TitleBar extends RelativeLayout {

	private RelativeLayout contTitleBar;
	private AnyTextView txtTitle,txt_rightOption;

	private ImageView btnLeft;
	private ImageButton btnRight, btnAdd;
	private ImageView imgTitle;
	private CheckBox checkBoxRight;

	private View.OnClickListener menuListener;
	private View.OnClickListener backListener;

	private Context context;

	boolean isLeftButtonMenu = true;
	protected static DockActivity myDockActivity;

	public TitleBar(Context context) {
		super(context);
		this.context = context;
		initLayout(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayout(context);
		if (attrs != null)
			initAttrs(context, attrs);
	}

	public TitleBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initLayout(context);
		if (attrs != null)
			initAttrs(context, attrs);
	}

	private void initAttrs(Context context, AttributeSet attrs) {
	}

	private void bindViews() {

		contTitleBar = (RelativeLayout) this.findViewById(R.id.header_layout);
		imgTitle = (ImageView) this.findViewById(R.id.img_mainHead);
		txtTitle = (AnyTextView) this.findViewById(R.id.txt_Heading);
		txt_rightOption = (AnyTextView) this.findViewById(R.id.txt_rightOption);
		
		btnRight = (ImageButton) this.findViewById(R.id.header_btn_right);
		btnAdd = (ImageButton) this.findViewById(R.id.header_btn_add);

		btnLeft = (ImageView) this.findViewById(R.id.header_btn_left);
		checkBoxRight = (CheckBox) this.findViewById(R.id.header_check_box_right);
		
	}

	private void initLayout(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.header_main, this);
		bindViews();

	}

	public void setHeaderColor(final int headerColor)
	{
		contTitleBar.setBackgroundColor(headerColor);
		
	}
	
	
	public void setRightTextOption(final String txtTobeSet)
	{
		txt_rightOption.setText(txtTobeSet);
		txt_rightOption.setVisibility(View.VISIBLE);
		btnRight.setVisibility(View.GONE);
		btnAdd.setVisibility(View.GONE);
		
	}
	
	public void setRightTextColor(final int color)
	{
		txt_rightOption.setTextColor(color);
		
		
	}
	
	
	
	public void hideRightTextOption()
	{
	
		txt_rightOption.setVisibility(View.GONE);
		btnRight.setVisibility(View.INVISIBLE);
		btnAdd.setVisibility(View.INVISIBLE);
		
	}
	
	
	
	public void setRightCheckboxOption(final int resourceId)
	{
		checkBoxRight.setButtonDrawable(resourceId);
		checkBoxRight.setVisibility(View.VISIBLE);
		btnRight.setVisibility(View.GONE);
		btnAdd.setVisibility(View.GONE);
		
	}
	
	
	public void setOnRightTextClickListener(View.OnClickListener listener) {
		txt_rightOption.setOnClickListener(listener);
	}
	public void setOnLeftClickListener(View.OnClickListener listener) {
		btnLeft.setOnClickListener(listener);
	}
	public void setLeftButtonIcon(final int resourceId) {
		btnLeft.setVisibility(View.VISIBLE);
		btnLeft.setImageResource(resourceId);
	}
	
	

	public void setOnRightClickListener(View.OnClickListener listener) {
		btnRight.setOnClickListener(listener);
	}

	public void getClickRightButton(boolean status) {
		btnRight.setClickable(status);
	}

	public void setOnAddButtonListener(View.OnClickListener listener) {
		btnAdd.setOnClickListener(listener);
	}

	public void setMenuListener(View.OnClickListener listener) {
		// menuListener = listener;
		setOnLeftClickListener(listener);
	}

	public void setBackListener(View.OnClickListener listener) {
		backListener = listener;
	}

	// public void setLeftButtonToMenu() {
	// btnLeft.setImageResource(R.drawable.menu);
	// isLeftButtonMenu = true;
	// // refreshListener();
	//
	// }

	public void setLeftButtonToBack() {
		// btnLeft.setImageResource(R.drawable.img_titlebar_back);
		isLeftButtonMenu = false;
		// refreshListener();

		// hideRightButton();
	}

	public View getLeftButton() {
		return btnLeft;
	}

	public ImageButton getRightButton() {
		return btnRight;
	}

	public ImageButton getAddButton() {
		return btnAdd;
	}

	public void showTitleBar() {
		this.setVisibility(View.VISIBLE);
	}

	public void hideTitleBar() {
		this.setVisibility(View.GONE);
	}
	
	public void hideAllButtons() {
		btnAdd.setVisibility(View.GONE);
		btnLeft.setVisibility(View.GONE);
		btnRight.setVisibility(View.GONE);
	}

	// public void setTitleBarBackground(int drawableResourse) {
	//
	// contTitleBar.setBackgroundResource(drawableResourse);
	// }

	public void hideLeftButton() {

		btnLeft.setVisibility(View.INVISIBLE);
	}

	public void hideAddButton() {

		btnAdd.setVisibility(View.INVISIBLE);
	}

	public void hideRightButton() {
		btnRight.setVisibility(View.INVISIBLE);
	}

	public void showRightButton(int drawable) {
		btnRight.setVisibility(View.VISIBLE);
		btnRight.setImageResource(drawable);
	}

	public void showLeftButton() {
		btnLeft.setVisibility(View.VISIBLE);
	}

	public void showAddButton() {
		btnAdd.setVisibility(View.VISIBLE);
	}

	public void showRightButton() {
		btnRight.setVisibility(View.VISIBLE);
	}

	public void setRightButtonIcon(int drawable) {
		btnRight.setVisibility(View.VISIBLE);
		btnRight.setBackgroundResource(drawable);
	}

	public void refreshListener() {
		// if (isLeftButtonMenu) {
		setOnLeftClickListener(menuListener);
		// } else {
		// setOnLeftClickListener(backListener);
		// }

	}

	public void setHeaderBackground(int drawable) {
		// contTitleBar.getLayoutParams().height = (int) convertDpToPixel(75);
		contTitleBar.setBackgroundResource(drawable);
	}

	private float Density() {
		// TODO Auto-generated method stub
		float size = getResources().getDisplayMetrics().density;
		return size;
	}

	public float convertDpToPixel(float dp) {
		Resources resources = getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		// Log.e("convertDpToPixel: " + dp, "" + px);
		return px;
	}

	public float convertPixelsToDp(float px) {
		Resources resources = getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		// Log.e("convertDpToPixel: " + dp, "" + px);
		return dp;
	}

	/*
	 * 
	 * 
	 * 
	 * /*
	 * #########################################################################
	 * Default Buttons and Actions.
	 * #########################################################################
	 */

	/**
	 * This is an initialization method that should be used in MainActivity so
	 * other fragments may not need to attached it again and again.
	 */

	public void setHeadingIcon(boolean status, String heading) {
		if (status) {
			imgTitle.setVisibility(View.INVISIBLE);
			txtTitle.setVisibility(View.VISIBLE);
			txtTitle.setText(heading);

		} else {
			imgTitle.setVisibility(View.VISIBLE);
			txtTitle.setVisibility(View.GONE);
		}

	}
	
	public void hideCenterTextAndIcon()
	{
		imgTitle.setVisibility(View.INVISIBLE);
		txtTitle.setVisibility(View.INVISIBLE);
	}

	public void setHeadingText(final String heading) {

		imgTitle.setVisibility(View.INVISIBLE);
		txtTitle.setVisibility(View.VISIBLE);
		txtTitle.setText(heading);

	}

}
