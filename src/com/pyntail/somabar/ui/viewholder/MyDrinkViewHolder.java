package com.pyntail.somabar.ui.viewholder;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.DockActivity;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.fragments.IngredientsFragment;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.recycler.AbsViewHolder;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.views.AnyTextView;
import com.squareup.picasso.Picasso;

public class MyDrinkViewHolder extends AbsViewHolder<MyDrinkItem>{


	private final String TAG = this.getClass().getSimpleName();
	MainActivity hostActivity;

	// LinearLayout mainLayout;
	public MyDrinkViewHolder(View itemView) {
		super(itemView);

	}

	@Override
	protected void updateView(final Context context, final MyDrinkItem object) {

		setIsRecyclable(false);
		hostActivity = (MainActivity) context;
		
		
		
		
	
		
		
		
		final ImageButton imgDelete = (ImageButton) findViewByIdEfficient(R.id.imgDelete);
final LinearLayout mainLayout = (LinearLayout)findViewByIdEfficient(R.id.mainLayout);
		final RelativeLayout deleteContainer = (RelativeLayout) findViewByIdEfficient(R.id.deleteContainer);
		
		deleteContainer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UIHelper.showLongToastInCenter(context, "onLikedClicked");
				

				String encodedToken=  hostActivity.prefHelper.getEncodedSecurityToken();
				hostActivity.showSweetLoader();
				WebServiceFactory.getInstance().deleteRecipes( object.getReciepeId(),encodedToken, new CallbackRetrofit<User>() {
					
					@Override
					public void onFailure(RetrofitError error) {
						hostActivity.hideSweetLoader();
					}
					
					@Override
					public void on502(RetrofitError error) {
						hostActivity.hideSweetLoader();
					}
					
					@Override
					public void on501(RetrofitError error) {
						hostActivity.hideSweetLoader();
						
					}
					
					@Override
					public void on500(RetrofitError error) {
						hostActivity.hideSweetLoader();
					}
					
					@Override
					public void on409(RetrofitError error) {
						hostActivity.hideSweetLoader();
						
					}
					
					@Override
					public void on408(RetrofitError error) {
						hostActivity.hideSweetLoader();
						
					}
					
					@Override
					public void on404(RetrofitError error) {
						hostActivity.hideSweetLoader();
					}
					
					@Override
					public void on401(RetrofitError error) {
						hostActivity.hideSweetLoader();
						
					}
					
					@Override
					public void on204(User value, Response response) {
						hostActivity.hideSweetLoader();
						
					}
					
					@Override
					public void on200(User value, Response response) {
						hostActivity.hideSweetLoader();
						//UIHelper.showLongToastInCenter(context, "onFavDone");
						
						imgDelete.setBackgroundResource(R.drawable.bin_icon);
						
						
					}
				});
				
			
				
			}
		});
		
		final RelativeLayout shareContainer = (RelativeLayout) findViewByIdEfficient(R.id.shareContainer);
		shareContainer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UIHelper.showLongToastInCenter(context, "onShareClicked");
				
			}
		});

		
		
		final AnyTextView txtDrinkTitle = (AnyTextView) findViewByIdEfficient(R.id.txtDrinkTitle);
		final ImageView imgDrink = (ImageView) findViewByIdEfficient(R.id.imgDrink);
		
		/*1st Ingredient*/
		final AnyTextView itxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.itxtIngredient);
		final AnyTextView itxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.itxtQuantity);
		
		/*2nd Ingredient*/
		final AnyTextView iitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.iitxtIngredient);
		final AnyTextView iitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.iitxtQuantity);

		
		/*3rd Ingredient*/
		final AnyTextView iiitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.iiitxtIngredient);
		final AnyTextView iiitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.iiitxtQuantity);

		/*4th Ingredient*/
		final AnyTextView ivtxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.ivtxtIngredient);
		final AnyTextView ivtxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.ivtxtQuantity);

		
		
		/*5th Ingredient*/
		final AnyTextView vtxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.vtxtIngredient);
		final AnyTextView vtxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.vtxtQuantity);
		
		
		/*6th Ingredient*/
		final AnyTextView vitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.vitxtIngredient);
		final AnyTextView vitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.vitxtQuantity);
		
		
		/*7th Ingredient*/
		final AnyTextView viitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.viitxtIngredient);
		final AnyTextView viitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.viitxtQuantity);



		txtDrinkTitle.setText(object.getDrinkName());
		//txtLikeCount.setText(String.valueOf(object.getLikeCount()));
		// imgDrink.setBackgroundResource(object.getResourceId());

		Picasso.with(hostActivity).load(object.getImgUrl()).fit().centerCrop()
				.into(imgDrink);

		/* SET INGREDIENTS */
		if(object.getIngredients()!=null && object.getIngredients().isEmpty())
		{
			itxtIngredient.setVisibility(View.VISIBLE);
			itxtQuantity.setVisibility(View.VISIBLE);
			
			
			itxtIngredient.setText("Not Available");
			itxtQuantity.setText("");
			return;
		}
		
		
		for (Ingredient _ingredient : object.getIngredients()) {
			switch (_ingredient.getIngredientIndex()) {
			case 0:
				
				itxtIngredient.setVisibility(View.VISIBLE);
				itxtQuantity.setVisibility(View.VISIBLE);
				
				
				itxtIngredient.setText(_ingredient.getLabel());
				itxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));
				break;

			case 1:
				
				iitxtIngredient.setVisibility(View.VISIBLE);
				iitxtQuantity.setVisibility(View.VISIBLE);
				
				iitxtIngredient.setText(_ingredient.getLabel());
				iitxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));

				break;

			case 2:
				iiitxtIngredient.setVisibility(View.VISIBLE);
				iiitxtQuantity.setVisibility(View.VISIBLE);
				
				iiitxtIngredient.setText(_ingredient.getLabel());
				iiitxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));
				break;

			case 3:
				
				ivtxtIngredient.setVisibility(View.VISIBLE);
				ivtxtQuantity.setVisibility(View.VISIBLE);
				ivtxtIngredient.setText(_ingredient.getLabel());
				ivtxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));
				break;
				

			case 4:
				vtxtIngredient.setVisibility(View.VISIBLE);
				vtxtQuantity.setVisibility(View.VISIBLE);
				vtxtIngredient.setText(_ingredient.getLabel());
				vtxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));
				break;
				

			case 5:
				vitxtIngredient.setVisibility(View.VISIBLE);
				vitxtQuantity.setVisibility(View.VISIBLE);
				vitxtIngredient.setText(_ingredient.getLabel());
				vitxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));
				break;
				

			case 6:
				viitxtIngredient.setVisibility(View.VISIBLE);
				viitxtQuantity.setVisibility(View.VISIBLE);
				viitxtIngredient.setText(_ingredient.getLabel());
				viitxtQuantity.setText(String.valueOf(_ingredient.getQuantity()));
				break;
				
			default:
				break;
			}

		}

	}

	@Override
	public boolean isClickable() {
		return true;
	}

	/*
	 * @Override public boolean isLongClickable() { return
	 * super.isLongClickable(); }
	 */



}
