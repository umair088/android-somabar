package com.pyntail.somabar.ui.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.DockActivity;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.recycler.AbsViewHolder;
import com.pyntail.somabar.ui.views.AnyTextView;

public class PickImageViewHolder extends AbsViewHolder<MyDrinkItem>  {

	private final String TAG = this.getClass().getSimpleName();
	DockActivity hostActivity;
	LinearLayout mainLayout;
	public PickImageViewHolder(View itemView) {
		super(itemView);
		
	}

	@Override
	protected void updateView(Context context, MyDrinkItem object) {
																	 
		setIsRecyclable(false);
		hostActivity = (MainActivity) context;
		
		
		final AnyTextView txtDrinkTitle = (AnyTextView) findViewByIdEfficient(R.id.txtDrinkTitle);
		final ImageView imgDrink = (ImageView) findViewByIdEfficient(R.id.imgDrink);
		
		
		txtDrinkTitle.setText(object.getTitleOfProduct());
		imgDrink.setBackgroundResource(object.getResourceId());
	
		//mainLayout.setOnClickListener(this);
		
	}
	
	

	@Override
	public boolean isClickable() {
		return true;
	}

	/*@Override
	public boolean isLongClickable() {
		return super.isLongClickable();
	}*/

	
	
}
