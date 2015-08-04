package com.pyntail.somabar.activities;

import java.io.UnsupportedEncodingException;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.andreabaccega.widget.FormEditText;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.pyntail.somabar.R;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.Device;
import com.pyntail.somabar.entities.request.LoginObject;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.DateHelper;
import com.pyntail.somabar.helpers.DeveloperHelper;
import com.pyntail.somabar.helpers.OSHelper;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.views.AnyEditTextView;
import com.pyntail.somabar.ui.views.TitleBar;

public class ForgotPasswordActivity extends BaseActivity {

	
	public static final String TAG = ForgotPasswordActivity.class.getSimpleName();

	@InjectView(R.id.txtEmail)
	AnyEditTextView txtEmail;

	@InjectView(R.id.btnSend)
	ImageButton btnSend;
	
	@InjectView(R.id.header_main)
	public TitleBar titleBar;
	
	private SnackbarManager snackManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forgot_password);
		ButterKnife.inject(ForgotPasswordActivity.this);
		setTitleBar();
		setListener();
		snackManager = new SnackbarManager();

	}
	
	public void setTitleBar() {
		titleBar.hideAllButtons();
		titleBar.hideCenterTextAndIcon();
		titleBar.setHeaderColor(this.getResources().getColor(R.color.dark_grey));
		titleBar.setLeftButtonIcon(R.drawable.back_button_white);
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}
	
	private void doRecoverPassword() {

		FormEditText[] allFields = { txtEmail };
		boolean allValid = true;
		for (FormEditText field : allFields)
			allValid = field.testValidity() && allValid;

		if (allValid) {

			String uuid;
			LoginObject loginObject;
			String email = txtEmail.getText().toString();
			
			loginObject = new LoginObject();

			loginObject.setEmail(email);
			uuid= DeveloperHelper.getDeviceUuId(this);
			
			
			/* Device Info */
			/*if (uuid!= null) {
				loginObject.setDevice(new Device());
				loginObject.getDevice().setPlatform(OSHelper.getDeviceInfo());
				loginObject.getDevice().setDeviceType("android");
				loginObject.getDevice().setNickname(OSHelper.getDeviceName());
				loginObject.getDevice().setTimeZoneOffset(
						DateHelper.getTimeZoneOffsetAsInteger());
				loginObject.getDevice().setPushToken(prefHelper.getDeviceToken() == null ? "" : prefHelper
								.getDeviceToken());
				loginObject.getDevice().setUdid(uuid);
			}*/
		

			showSweetLoader();
			String encodedToken="";
			if(prefHelper.getEncodedSecurityToken()!=null)
				encodedToken= prefHelper.getEncodedSecurityToken();
			WebServiceFactory.getInstance().forgotPassword(encodedToken,
					loginObject, new CallbackRetrofit<User>() {

						@Override
						public void on200(User object, Response response) {
							hideSweetLoader();
							if (object != null) {
								
								showSweetSuccessAlert("Ok", "Cancel", "",
										"An email has been sent to your address.");

							} else
								Applog.Debug(TAG, "User can not be null");

						}

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
							Applog.Debug(TAG, "onNoContentFound");
							showSweetWarningAlert("Ok", "Cancel", "",
									"User Doesn't exist, please check your Email Or Password");

						}

					});
		}
	
	
		
	}
	
	@SuppressWarnings("static-access")
	public void showSnackBar(final String msgtoShow) {
		snackManager.show(Snackbar
				.with(ForgotPasswordActivity.this)
				.text(msgtoShow)
				.textTypeface(
						Typeface.createFromAsset(getResources().getAssets(),
								"fonts/raleway.ttf"))
				.duration(Snackbar.SnackbarDuration.LENGTH_SHORT));

	}

	private void setListener() {
		btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Applog.Debug(TAG, "onSend Clicked");
				doRecoverPassword();
			}
		});
		
	}
	
	
	
	
	

	
	
}
