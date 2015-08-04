package com.pyntail.somabar.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.pyntail.somabar.R;
import com.pyntail.somabar.constants.AppConstants;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.Facebook;
import com.pyntail.somabar.entities.request.SignUpEntity;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.DeveloperHelper;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.helpers.Utils;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;

public class StarterActivity extends BaseFacebookActivity implements
		OnClickListener {

	public static final String TAG = StarterActivity.class.getSimpleName();
	@InjectView(R.id.btn_signup)
	ImageButton btnSignup;

	@InjectView(R.id.btn_signin)
	ImageButton btnSignin;

	@InjectView(R.id.imgFacebook)
	ImageView imgFacebook;

	@InjectView(R.id.txtSkip)
	TextView txtSkip;

	protected void updateUI() {
		boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

		postStatusUpdateButton.setEnabled(enableButtons
				|| canPresentShareDialog);
		postPhotoButton.setEnabled(enableButtons
				|| canPresentShareDialogWithPhotos);

		Profile profile = Profile.getCurrentProfile();
		if (enableButtons && profile != null) {
			profilePictureView.setProfileId(profile.getId());
			greeting.setText(getString(R.string.hello_user,
					profile.getFirstName()));
		} else {
			profilePictureView.setProfileId(null);
			greeting.setText(null);
		}
	}

	protected void postPhoto() {
		Bitmap image = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.icon);
		SharePhoto sharePhoto = new SharePhoto.Builder().setBitmap(image)
				.build();
		ArrayList<SharePhoto> photos = new ArrayList<>();
		photos.add(sharePhoto);

		SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder()
				.setPhotos(photos).build();
		if (canPresentShareDialogWithPhotos) {
			shareDialog.show(sharePhotoContent);
		} else if (hasPublishPermission()) {
			ShareApi.share(sharePhotoContent, shareCallback);
		} else {
			pendingAction = PendingAction.POST_PHOTO;
			// We need to get new permissions, then complete the action when we
			// get called back.
			LoginManager.getInstance().logInWithPublishPermissions(this,
					Arrays.asList(PERMISSION));
		}
	}

	protected void postStatusUpdate() {
		Profile profile = Profile.getCurrentProfile();

		ShareLinkContent linkContent = new ShareLinkContent.Builder()
				.setContentTitle("Hello Facebook")
				.setContentDescription(
						"The 'Hello Facebook' sample  showcases simple Facebook integration")
				.setContentUrl(
						Uri.parse("http://developers.facebook.com/docs/android"))
				.build();
		if (canPresentShareDialog) {
			shareDialog.show(linkContent);
		} else if (profile != null && hasPublishPermission()) {
			ShareApi.share(linkContent, shareCallback);
		} else {
			pendingAction = PendingAction.POST_STATUS_UPDATE;
		}
	}

	private SnackbarManager snackManager;

	private void initFbViews() {
		btnFacebook = (LoginButton) findViewById(R.id.btn_facebook);
		postStatusUpdateButton = (Button) findViewById(R.id.postStatusUpdateButton);
		postStatusUpdateButton = (Button) findViewById(R.id.postStatusUpdateButton);
		postPhotoButton = (Button) findViewById(R.id.postPhotoButton);
		profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
		greeting = (TextView) findViewById(R.id.greeting);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (prefHelper.getUser() != null) {
			Applog.Debug(TAG, "onSkip Clicked");
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			intent.putExtra(AppConstants.IS_SKIP, true);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			ActivityCompat.finishAffinity(this);
			startActivity(intent);

		} else {

			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_starter);
			ButterKnife.inject(StarterActivity.this);
			DeveloperHelper.logKeyHash(this);
			initFbViews();
			setListener();

			snackManager = new SnackbarManager();

			/* FB */
			profileTracker = new ProfileTracker() {
				@Override
				protected void onCurrentProfileChanged(Profile oldProfile,
						Profile currentProfile) {
					updateUI();
					// It's possible that we were waiting for Profile to be
					// populated in order to
					// post a status update.
					handlePendingAction();
				}
			};

			profilePictureView = (ProfilePictureView) findViewById(R.id.profilePicture);
			greeting = (TextView) findViewById(R.id.greeting);

			postStatusUpdateButton = (Button) findViewById(R.id.postStatusUpdateButton);
			postStatusUpdateButton
					.setOnClickListener(new View.OnClickListener() {
						public void onClick(View view) {
							onClickPostStatusUpdate();
						}
					});

			postPhotoButton = (Button) findViewById(R.id.postPhotoButton);
			postPhotoButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
					onClickPostPhoto();
				}
			});

			// Can we present the share dialog for regular links?
			canPresentShareDialog = ShareDialog.canShow(ShareLinkContent.class);

			// Can we present the share dialog for photos?
			canPresentShareDialogWithPhotos = ShareDialog
					.canShow(SharePhotoContent.class);

		}

	}

	private void setListener() {
		btnSignin.setOnClickListener(this);
		btnSignup.setOnClickListener(this);
		txtSkip.setOnClickListener(this);
		imgFacebook.setOnClickListener(this);
	}

	@SuppressWarnings("static-access")
	public void showSnackBar(final String msgtoShow) {
		snackManager.show(Snackbar
				.with(StarterActivity.this)
				.text(msgtoShow)
				.textTypeface(
						Typeface.createFromAsset(getResources().getAssets(),
								"fonts/raleway.ttf"))
				.duration(Snackbar.SnackbarDuration.LENGTH_SHORT));

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.imgFacebook:
			Applog.Debug(TAG, "onFacebook Clicked");
			if (btnFacebook != null) {

				btnFacebook.performClick();
			}
			break;

		case R.id.btn_signin:
			Applog.Debug(TAG, "onSignin Clicked");
			startActivity(new Intent(this, SignInActivity.class));
			break;

		case R.id.btn_signup:
			Applog.Debug(TAG, "onSignUp Clicked");
			startActivity(new Intent(this, SignUpActivity.class));
			break;

		case R.id.txtSkip:
			Applog.Debug(TAG, "onSkip Clicked");
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			intent.putExtra(AppConstants.IS_SKIP, true);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			ActivityCompat.finishAffinity(this);
			startActivity(intent);

			break;

		default:
			break;
		}

	}

	public void loginByFacebook(Profile profile, LoginResult loginResult) {

		showSweetLoader();

		final Facebook fbObject = new Facebook();

		if (profile != null) {
			if (!Utils.isEmptyOrNull(profile.getFirstName()))
				fbObject.setFbUsername(profile.getFirstName());

			if (!Utils.isEmptyOrNull(profile.getId()))
				fbObject.setFbId(profile.getId());
		}

		if (!Utils.isEmptyOrNull(loginResult.getAccessToken().getToken()))
			fbObject.setFbAccessToken(loginResult.getAccessToken().getToken());

		WebServiceFactory.getInstance().loginUserByFacebook(
				"x-zumo-application", fbObject, new CallbackRetrofit<User>() {

					@Override
					public void onFailure(RetrofitError error) {
						hideSweetLoader();
						showSweetFailureAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");

					}

					@Override
					public void on404(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onNotFound");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");

					}

					@Override
					public void on401(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onUnAuthorized");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");

					}

					@Override
					public void on409(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onConflict");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");

					}

					@Override
					public void on408(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onResuestTimeOut");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");
					}

					@Override
					public void on500(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onInternalServerError");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");
					}

					@Override
					public void on501(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onNotImplemented");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");
					}

					@Override
					public void on502(RetrofitError error) {
						hideSweetLoader();
						Applog.Debug(TAG, "onBadGatewayError");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");
					}

					@Override
					public void on204(User value, Response response) {
						hideSweetLoader();
						Applog.Debug(TAG, "onAccountLoginFailure");
						showSweetWarningAlert("Ok", "Cancel", "",
								"Could not connect to server, Please try again later.");

					}

					@Override
					public void on200(User value, Response response) {
						hideSweetLoader();
						Applog.Debug(TAG, "onAccountLoggedInByFb");

						prefHelper.clear();
						prefHelper.putUser(value);

						Intent intent = new Intent(getApplicationContext(),
								StarterActivity.class);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						ActivityCompat.finishAffinity(StarterActivity.this);
						startActivity(intent);

					}
				});

	}

	@Override
	protected void onSuccess(LoginResult loginResult, Profile profile) {
		loginByFacebook(profile, loginResult);

	}

	@Override
	protected void onCancel() {
		showSweetWarningAlert("Ok", "Cancel", "",
				"Facebook logging cancelled by user.");

	}

	@Override
	protected void onError(FacebookException exception) {
		if (exception != null && exception.getMessage() != null)
			showSweetWarningAlert("Ok", "Cancel", "",
					"Could not connect to facebook,Try again later.");
	}

}
