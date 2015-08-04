package com.pyntail.somabar.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
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
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;
import com.pyntail.somabar.R;

public abstract class BaseFacebookActivity extends BaseActivity {

	
	public static final String TAG = BaseFacebookActivity.class.getSimpleName();
	protected abstract void postPhoto();
	protected abstract void postStatusUpdate();
	protected abstract void updateUI();
	protected abstract void onSuccess(LoginResult loginResult,Profile profile );
	protected abstract void onCancel();
	protected abstract void onError(FacebookException exception);
	
	
	
	
	@InjectView(R.id.btn_facebook)
	protected LoginButton btnFacebook;

	@InjectView(R.id.postStatusUpdateButton)
	protected Button postStatusUpdateButton;

	@InjectView(R.id.postPhotoButton)
	protected Button postPhotoButton;

	@InjectView(R.id.profilePicture)
	protected ProfilePictureView profilePictureView;
	
	

	@InjectView(R.id.greeting)
	protected TextView greeting;
	
	
	
	
	/* FACEBOOK */
	protected final String[] PERMISSION = {"publish_actions","email"};
	protected final Location SEATTLE_LOCATION = new Location("") {
		{
			setLatitude(47.6097);
			setLongitude(-122.3331);
		}
	};

	private final String PENDING_ACTION_BUNDLE_KEY = "com.pyntail.somabar:PendingAction";

	protected PendingAction pendingAction = PendingAction.NONE;
	protected boolean canPresentShareDialog;
	protected boolean canPresentShareDialogWithPhotos;
	protected CallbackManager callbackManager;
	protected ProfileTracker profileTracker;
	protected ShareDialog shareDialog;
	protected FacebookCallback<Sharer.Result> shareCallback = new FacebookCallback<Sharer.Result>() {
		@Override
		public void onCancel() {
			Log.d("HelloFacebook", "Canceled");
		}

		@Override
		public void onError(FacebookException error) {
			Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
			String title = getString(R.string.error);
			String alertMessage = error.getMessage();
			showResult(title, alertMessage);
		}

		@Override
		public void onSuccess(Sharer.Result result) {
			Log.d("HelloFacebook", "Success!");
			if (result.getPostId() != null) {
				String title = getString(R.string.success);
				String id = result.getPostId();
				String alertMessage = getString(
						R.string.successfully_posted_post, id);
				showResult(title, alertMessage);
			}
		}

		private void showResult(String title, String alertMessage) {
			new AlertDialog.Builder(BaseFacebookActivity.this).setTitle(title)
					.setMessage(alertMessage)
					.setPositiveButton(R.string.ok, null).show();
		}
	};
	
	
	

	protected enum PendingAction {
		NONE, POST_PHOTO, POST_STATUS_UPDATE
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* FACEBOOK */
		FacebookSdk.sdkInitialize(this.getApplicationContext());

		callbackManager = CallbackManager.Factory.create();

		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
					@Override
					public void onSuccess(final LoginResult loginResult) {
						handlePendingAction();
						updateUI();
						
						final Profile profile = Profile.getCurrentProfile();
						if(loginResult!=null)
						{
						/*	 GraphRequestAsyncTask request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
					                @Override
					                public void onCompleted(JSONObject user, GraphResponse graphResponse) {
					                    final String userEmail =user.optString("email");
					                    Applog.Debug(TAG, "UserFbEmail = " + userEmail);
					                    
					                    //BaseFacebookActivity.this.onSuccess(loginResult,profile );
					                }
					            }).executeAsync();*/
							
							
							
							
							if( profile!=null)
							BaseFacebookActivity.this.onSuccess(loginResult,profile );
							else
								BaseFacebookActivity.this.onSuccess(loginResult,null );
						}
						else
							BaseFacebookActivity.this.onError(new FacebookException("Could not Fetch Profile, Please try again"));
						
					}

					

					@Override
					public void onCancel() {
						if (pendingAction != PendingAction.NONE) {
							showAlert();
							pendingAction = PendingAction.NONE;
						}
						updateUI();
						BaseFacebookActivity.this.onCancel();
						
						
								
					}

					@Override
					public void onError(FacebookException exception) {
						if (pendingAction != PendingAction.NONE
								&& exception instanceof FacebookAuthorizationException) {
							showAlert();
							pendingAction = PendingAction.NONE;
						}
						updateUI();
						BaseFacebookActivity.this.onError(exception);
						
					}

					private void showAlert() {
						new AlertDialog.Builder(BaseFacebookActivity.this)
								.setTitle(R.string.cancelled)
								.setMessage(R.string.permission_not_granted)
								.setPositiveButton(R.string.ok, null).show();
					}
				});

		shareDialog = new ShareDialog(this);
		shareDialog.registerCallback(callbackManager, shareCallback);

		if (savedInstanceState != null) {
			String name = savedInstanceState
					.getString(PENDING_ACTION_BUNDLE_KEY);
			pendingAction = PendingAction.valueOf(name);
		}

		/* FACEBOOK */
		
	}
	
	protected boolean hasPublishPermission() {
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		return accessToken != null
				&& accessToken.getPermissions().contains("publish_actions");
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();

		// Call the 'activateApp' method to log an app event for use in
		// analytics and advertising
		// reporting. Do so in the onResume methods of the primary Activities
		// that an app may be
		// launched into.
		AppEventsLogger.activateApp(this);

		updateUI();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();

		// Call the 'deactivateApp' method to log an app event for use in
		// analytics and advertising
		// reporting. Do so in the onPause methods of the primary Activities
		// that an app may be
		// launched into.
		AppEventsLogger.deactivateApp(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		if(profileTracker!=null)
		profileTracker.stopTracking();
	}

	void onClickPostStatusUpdate() {
		performPublish(PendingAction.POST_STATUS_UPDATE, canPresentShareDialog);
	}

	private void performPublish(PendingAction action, boolean allowNoToken) {
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		if (accessToken != null || allowNoToken) {
			pendingAction = action;
			handlePendingAction();
		}
	}
	
	
	 void onClickPostPhoto() {
	        performPublish(PendingAction.POST_PHOTO, canPresentShareDialogWithPhotos);
	    }
	

	void handlePendingAction() {
		PendingAction previouslyPendingAction = pendingAction;
		// These actions may re-set pendingAction if they are still pending, but
		// we assume they
		// will succeed.
		pendingAction = PendingAction.NONE;

		switch (previouslyPendingAction) {
		case NONE:
			break;
		case POST_PHOTO:
			postPhoto();
			break;
		case POST_STATUS_UPDATE:
			postStatusUpdate();
			break;
		}
	}
	
	
	
}
