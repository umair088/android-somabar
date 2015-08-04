package com.pyntail.somabar.ui.viewholder;

import java.io.File;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.FavoriteObject;
import com.pyntail.somabar.entities.LikeObject;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.recycler.AbsViewHolder;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.views.AnyTextView;
import com.squareup.picasso.Picasso;

public class CommunityViewHolder extends AbsViewHolder<MyDrinkItem> {

	private final String TAG = this.getClass().getSimpleName();
	MainActivity hostActivity;

	// LinearLayout mainLayout;
	public CommunityViewHolder(View itemView) {
		super(itemView);

	}

	private void shareImage() {
		Intent share = new Intent(Intent.ACTION_SEND);

		// If you want to share a png image only, you can do:
		// setType("image/png"); OR for jpeg: setType("image/jpeg");
		share.setType("image/*");

		// Make sure you put example png image named myImage.png in your
		// directory
		String imagePath = Environment.getExternalStorageDirectory()
				+ "/myImage.png";

		/*
		 * String pathofBmp =
		 * Images.Media.insertImage(hostActivity.getContentResolver(),
		 * bitmap,"title", null); Uri bmpUri = Uri.parse(pathofBmp); final
		 * Intent emailIntent1 = new Intent(
		 * android.content.Intent.ACTION_SEND);
		 * emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);
		 * emailIntent1.setType("image/png");
		 */

		File imageFileToShare = new File(imagePath);

		Uri uri = Uri.fromFile(imageFileToShare);
		share.putExtra(Intent.EXTRA_STREAM, uri);

	
		
		hostActivity.startActivity(Intent.createChooser(share, "Share Image!"));
	}

	
	@Override
	protected void updateView(final Context context, final MyDrinkItem object) {

		setIsRecyclable(false);
		hostActivity = (MainActivity) context;

		
	

		final TextView txtLikeCount = (TextView) findViewByIdEfficient(R.id.txtLikeCount);
		final RelativeLayout likeContainer = (RelativeLayout) findViewByIdEfficient(R.id.likeContainer);
		likeContainer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// UIHelper.showLongToastInCenter(context, "onLikedClicked");
				final String encodedToken = hostActivity.prefHelper
						.getEncodedSecurityToken();
				if (!object.isLike()) {

					
					hostActivity.showSweetLoader();
					WebServiceFactory.getInstance().markLike(
							object.getReciepeId(), encodedToken,
							new CallbackRetrofit<LikeObject>() {
								@Override
								public void onFailure(RetrofitError error) {
									hostActivity.hideSweetLoader();
									hostActivity
											.showSweetFailureAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on404(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onNotFound");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on401(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onUnAuthorized");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on409(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onConflict");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on408(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onResuestTimeOut");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on500(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onInternalServerError");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on501(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onNotImplemented");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on502(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onBadGatewayError");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on204(LikeObject value,
										Response response) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onNoContentFound");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on200(LikeObject value,
										Response response) {
									hostActivity.hideSweetLoader();
									if (value != null) {
										int likeCount = value.getCount();
										//likeCount = likeCount++;
										txtLikeCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_orange, 0, 0, 0);
										txtLikeCount.setText(String
												.valueOf(likeCount));
										txtLikeCount.invalidate();
										object.setIsLike(true);
									}

									final User user = hostActivity.prefHelper
											.getUser();
									int userId = 0;
									if (user != null) {
										userId = user.getUserId();
										String encodedToken = hostActivity.prefHelper
												.getEncodedSecurityToken();
										hostActivity.getMyDrinks(encodedToken,
												userId, 0, 100);
									}

								}
							});
				}
				else
				{
					
					hostActivity.showSweetLoader();
					WebServiceFactory.getInstance().markUnLike(
							object.getReciepeId(), encodedToken,
							new CallbackRetrofit<LikeObject>() {
								@Override
								public void onFailure(RetrofitError error) {
									hostActivity.hideSweetLoader();
									hostActivity
											.showSweetFailureAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on404(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onNotFound");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on401(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onUnAuthorized");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on409(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onConflict");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on408(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onResuestTimeOut");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on500(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onInternalServerError");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on501(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onNotImplemented");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on502(RetrofitError error) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onBadGatewayError");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");
								}

								@Override
								public void on204(LikeObject value,
										Response response) {
									hostActivity.hideSweetLoader();
									Applog.Debug(TAG, "onNoContentFound");
									hostActivity
											.showSweetWarningAlert("Ok",
													"Cancel", "",
													"Could not connect to server, Please try again later.");

								}

								@Override
								public void on200(LikeObject value,
										Response response) {
									hostActivity.hideSweetLoader();
									if (value != null) {
										int likeCount = value.getCount();
										
										txtLikeCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_icon, 0, 0, 0);
										txtLikeCount.setText(String
												.valueOf(likeCount));
										txtLikeCount.invalidate();
										object.setIsLike(false);
									}

									final User user = hostActivity.prefHelper
											.getUser();
									int userId = 0;
									if (user != null) {
										userId = user.getUserId();
										String encodedToken = hostActivity.prefHelper
												.getEncodedSecurityToken();
										hostActivity.getMyDrinks(encodedToken,
												userId, 0, 100);
									}

								}
							});
				
					
					
					
					
					
				}
			}
		});
		// final LoginButton btnFacebook = (LoginButton)
		// findViewByIdEfficient(R.id.btn_facebook);
		final RelativeLayout shareContainer = (RelativeLayout) findViewByIdEfficient(R.id.shareContainer);
		shareContainer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				shareImage();

			}
		});

		final AnyTextView txtDrinkTitle = (AnyTextView) findViewByIdEfficient(R.id.txtDrinkTitle);
		final ImageView imgDrink = (ImageView) findViewByIdEfficient(R.id.imgDrink);

		/* 1st Ingredient */
		final AnyTextView itxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.itxtIngredient);
		final AnyTextView itxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.itxtQuantity);

		/* 2nd Ingredient */
		final AnyTextView iitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.iitxtIngredient);
		final AnyTextView iitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.iitxtQuantity);

		/* 3rd Ingredient */
		final AnyTextView iiitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.iiitxtIngredient);
		final AnyTextView iiitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.iiitxtQuantity);

		/* 4th Ingredient */
		final AnyTextView ivtxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.ivtxtIngredient);
		final AnyTextView ivtxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.ivtxtQuantity);

		/* 5th Ingredient */
		final AnyTextView vtxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.vtxtIngredient);
		final AnyTextView vtxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.vtxtQuantity);

		/* 6th Ingredient */
		final AnyTextView vitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.vitxtIngredient);
		final AnyTextView vitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.vitxtQuantity);

		/* 7th Ingredient */
		final AnyTextView viitxtIngredient = (AnyTextView) findViewByIdEfficient(R.id.viitxtIngredient);
		final AnyTextView viitxtQuantity = (AnyTextView) findViewByIdEfficient(R.id.viitxtQuantity);

		txtDrinkTitle.setText(object.getDrinkName());
		
		
		
		if(object.isLike())
			txtLikeCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_orange, 0, 0, 0);
		else
			txtLikeCount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_icon, 0, 0, 0);
		txtLikeCount.setText(String.valueOf(object.getLikeCount()));

		Picasso.with(hostActivity).load(object.getImgUrl()).fit().centerCrop()
				.into(imgDrink);

		/* SET INGREDIENTS */
		if (object.getIngredients() != null
				&& object.getIngredients().isEmpty()) {
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
				iitxtQuantity
						.setText(String.valueOf(_ingredient.getQuantity()));

				break;

			case 2:
				iiitxtIngredient.setVisibility(View.VISIBLE);
				iiitxtQuantity.setVisibility(View.VISIBLE);

				iiitxtIngredient.setText(_ingredient.getLabel());
				iiitxtQuantity
						.setText(String.valueOf(_ingredient.getQuantity()));
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
				vitxtQuantity
						.setText(String.valueOf(_ingredient.getQuantity()));
				break;

			case 6:
				viitxtIngredient.setVisibility(View.VISIBLE);
				viitxtQuantity.setVisibility(View.VISIBLE);
				viitxtIngredient.setText(_ingredient.getLabel());
				viitxtQuantity
						.setText(String.valueOf(_ingredient.getQuantity()));
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