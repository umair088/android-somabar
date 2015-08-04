package com.pyntail.somabar.activities;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import butterknife.ButterKnife;
import butterknife.InjectView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.andreabaccega.widget.FormEditText;
import com.pyntail.somabar.R;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.Device;
import com.pyntail.somabar.entities.request.LoginObject;
import com.pyntail.somabar.entities.request.SignUpEntity;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.DateHelper;
import com.pyntail.somabar.helpers.DeveloperHelper;
import com.pyntail.somabar.helpers.OSHelper;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.dialog.DialogFactory;
import com.pyntail.somabar.ui.views.AnyEditTextView;
import com.pyntail.somabar.ui.views.AnyTextView;
import com.pyntail.somabar.ui.views.TitleBar;

public class SignInActivity extends BaseActivity implements OnClickListener {

	public static final String TAG = SignInActivity.class.getSimpleName();

	@InjectView(R.id.txtEmail)
	AnyEditTextView txtEmail;

	@InjectView(R.id.txtPassword)
	AnyEditTextView txtPassword;

	@InjectView(R.id.txtForgotPassword)
	AnyTextView txtForgotPassword;

	@InjectView(R.id.btnSignin)
	ImageButton btnSignin;

	@InjectView(R.id.header_main)
	public TitleBar titleBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signin);
		ButterKnife.inject(SignInActivity.this);
		setTitleBar();
		setListener();

	}

	public void setTitleBar() {
		titleBar.hideAllButtons();
		titleBar.setHeadingText("SignIn");
		titleBar.setHeaderColor(this.getResources().getColor(R.color.orange));
		titleBar.setLeftButtonIcon(R.drawable.back_button_white);
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	private void setListener() {
		btnSignin.setOnClickListener(this);
		txtForgotPassword.setOnClickListener(this);
	}

	private void doLogin() {

		FormEditText[] allFields = { txtEmail, txtPassword };
		boolean allValid = true;
		for (FormEditText field : allFields)
			allValid = field.testValidity() && allValid;

		if (allValid) {

			String uuid;
			LoginObject loginObject;
			String email = txtEmail.getText().toString();
			String password = txtPassword.getText().toString();
			loginObject = new LoginObject();

			loginObject.setEmail(email);
			loginObject.setPassword(password);
			uuid= DeveloperHelper.getDeviceUuId(this);
			
			
			/* Device Info */
			if (uuid!= null) {
				loginObject.setDevice(new Device());
				loginObject.getDevice().setPlatform(OSHelper.getDeviceInfo());
				loginObject.getDevice().setDeviceType("android");
				loginObject.getDevice().setNickname(OSHelper.getDeviceName());
				loginObject.getDevice().setTimeZoneOffset(
						DateHelper.getTimeZoneOffsetAsInteger());
				loginObject.getDevice().setPushToken(prefHelper.getDeviceToken() == null ? "" : prefHelper
								.getDeviceToken());
				loginObject.getDevice().setUdid(uuid);
			}

			showSweetLoader();

			WebServiceFactory.getInstance().loginUser("x-zumo-application",
					loginObject, new CallbackRetrofit<User>() {

						@Override
						public void on200(User object, Response response) {
							hideSweetLoader();
							if (object != null) {
								prefHelper.clear();
								prefHelper.putUser(object);

								Intent intent = new Intent(
										getApplicationContext(),
										MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								ActivityCompat
										.finishAffinity(SignInActivity.this);
								startActivity(intent);

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

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnSignin:
			Applog.Debug(TAG, "onSignIn Clicked");
			doLogin();
			break;

		case R.id.txtForgotPassword:
			Applog.Debug(TAG, "onForgotPassword Clicked");
			startActivity(new Intent(this, ForgotPasswordActivity.class));
			break;

		default:
			break;
		}
	}
}
