package com.pyntail.somabar.activities;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedString;
import android.app.Activity;
import android.app.ActivityManager.AppTask;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.andreabaccega.widget.FormEditText;
import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.google.gson.Gson;
import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;
import com.pyntail.somabar.R;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.SignUpEntity;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.BasePreferenceHelper;
import com.pyntail.somabar.helpers.BitmapHelper;
import com.pyntail.somabar.helpers.CameraHelper;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebResponse;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.dialog.DialogFactory;
import com.pyntail.somabar.ui.views.AnyEditTextView;
import com.pyntail.somabar.ui.views.RoundedImageView;
import com.pyntail.somabar.ui.views.TitleBar;
import com.squareup.picasso.Picasso;

public class SignUpActivity extends BaseActivity implements OnClickListener,
		DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,
		ImageChooserListener {

	public static final String TAG = SignInActivity.class.getSimpleName();

	@InjectView(R.id.txtEmail)
	AnyEditTextView txtEmail;

	@InjectView(R.id.txtPassword)
	AnyEditTextView txtPassword;

	@InjectView(R.id.btnSignUp)
	ImageButton btnSignUp;

	@InjectView(R.id.header_main)
	public TitleBar titleBar;

	@InjectView(R.id.txtFullName)
	AnyEditTextView txtFullName;

	@InjectView(R.id.txtUserName)
	AnyEditTextView txtUserName;

	@InjectView(R.id.txtDob)
	AnyEditTextView txtDob;

	@InjectView(R.id.imgUser)
	RoundedImageView imgUser;

	private static final String TIME_PATTERN = "HH:mm";
	private Calendar calendar;
	private DateFormat dateFormat;

	
	

	/* Image Chooser */
	private ImageChooserManager imageChooserManager;
	//private ProgressBar pbar;
	private String filePath;
	private int chooserType;
	private boolean isActivityResultOver = false;
	private String originalFilePath;
	private String thumbnailFilePath;
	private String thumbnailSmallFilePath;
	Object ObjImgPath;
	
	private byte[] imgInBytes;
	private Bitmap selectedImageInBmp;
	private String imageURL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sign_up);
		ButterKnife.inject(SignUpActivity.this);

		
		calendar = Calendar.getInstance();
		dateFormat = DateFormat.getDateInstance(DateFormat.LONG,
				Locale.getDefault());
		update();
		setTitleBar();
		setListener();

	}

	private void update() {

		txtDob.setText(dateFormat.format(calendar.getTime()));

	}

	public void setTitleBar() {
		titleBar.hideAllButtons();
		titleBar.setHeadingText("Sign Up");
		titleBar.setHeaderColor(this.getResources().getColor(R.color.orange));
		titleBar.setLeftButtonIcon(R.drawable.back_button_white);
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
	}

	

	

	public void doRegistration(final String imgUrl) {
		FormEditText[] allFields = { txtFullName, txtEmail, txtUserName,
				txtPassword, txtDob };
		boolean allValid = true;
		for (FormEditText field : allFields)
			allValid = field.testValidity() && allValid;

		if (allValid) {
			/*
			 * startActivity(new Intent(this, MainActivity.class)); finish();
			 */
			SignUpEntity user;
			String email = txtEmail.getText().toString();
			String userName = txtUserName.getText().toString();
			String password = txtPassword.getText().toString();
			String fullName = txtFullName.getText().toString();
			String dob = txtDob.getText().toString();
			user = new SignUpEntity();

			user.setEmail(email);
			user.setUserName(userName);
			user.setPassword(password);
			user.setImageUrl("");
			user.setFullName(fullName);
			user.setDob(dob);
			showSweetLoader();
			WebServiceFactory.getInstance().registerUser("x-zumo-application",
					user, new CallbackRetrofit<User>() {

						@Override
						public void on200(User object, Response response) {
							hideSweetLoader();
							if (object != null) {
								

								showSweetSuccessAlert("Ok", "Cancel", "",
										"Your account has been created successfully.");

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
									"User already exist pick another.");

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
									"No such content found in the server.");
							
						}

						
					

					});

		}

	}

	private void setListener() {

		imgUser.setOnClickListener(this);
		btnSignUp.setOnClickListener(this);
		txtDob.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog.newInstance(SignUpActivity.this,
						calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH)).show(
						getFragmentManager(), "datePicker");
			}
		});
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnSignUp:
			Applog.Debug(TAG, "onSignUp Clicked");
			doRegistration("https://scontent-bru2-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/11667357_10206526680423237_3600290929345818174_n.jpg?oh=63465691efe5a5b50cd792de9bf7f9b4&oe=560EA708");
			//if(selectedImageInBmp!=null)
			//{
				
				//String imgIntoStr = BitmapHelper.getEncoded64ImageStringFromBitmap(selectedImageInBmp);
				//postImage("");
			//}
			//else
			//doRegistration("https://scontent-bru2-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/11667357_10206526680423237_3600290929345818174_n.jpg?oh=63465691efe5a5b50cd792de9bf7f9b4&oe=560EA708");
			break;

		case R.id.imgUser:
			Applog.Debug(TAG, "onImageUser Clicked");
			chooseImage();
			break;

		default:
			break;
		}
	}

	private void postImage(final String imgToPost) {
		
		 TypedString author = new TypedString("cURL");
		 File photoFile = new File(imageURL);
		 TypedFile photoTypedFile = new TypedFile("application/octet-stream", photoFile);
		
		WebServiceFactory.getInstance().uploadImage(photoTypedFile, new CallbackRetrofit<SignUpEntity>() {

			@Override
			public void on200(SignUpEntity value, Response response) {
				Applog.Debug(TAG, "onImageUploadedSuccess");
				
			}

			@Override
			public void on204(SignUpEntity value, Response response) {
				Applog.Debug(TAG, "onImageUploadedFailure");
				
			}

			@Override
			public void on401(RetrofitError error) {
				Applog.Debug(TAG, "onImageUploadedFailure");
				
			}

			@Override
			public void on404(RetrofitError error) {
				Applog.Debug(TAG, "onImageUploadedFailure");
				
			}

			@Override
			public void on408(RetrofitError error) {
				Applog.Debug(TAG, "onImageUploadedFailure");
				
			}

			@Override
			public void on409(RetrofitError error) {
				Applog.Debug(TAG, "onImageUploadedFailure");
			}

			@Override
			public void on500(RetrofitError error) {
				Applog.Debug(TAG, "onImageUploadedFailure");
				
			}

			@Override
			public void on501(RetrofitError error) {
				Applog.Debug(TAG, "onImageUploadedFailure");
				
			}

			@Override
			public void on502(RetrofitError error) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFailure(RetrofitError error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		update();

	}

	@Override
	public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear,
			int dayOfMonth) {
		calendar.set(year, monthOfYear, dayOfMonth);
		update();

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.i(TAG, "Saving Stuff");
		Log.i(TAG, "File Path: " + filePath);
		Log.i(TAG, "Chooser Type: " + chooserType);
		outState.putBoolean("activity_result_over", isActivityResultOver);
		outState.putInt("chooser_type", chooserType);
		outState.putString("media_path", filePath);
		outState.putString("orig", originalFilePath);
		outState.putString("thumb", thumbnailFilePath);
		outState.putString("thumbs", thumbnailSmallFilePath);
		super.onSaveInstanceState(outState);
	}

	private void chooseImage() {
		chooserType = ChooserType.REQUEST_PICK_PICTURE;
		imageChooserManager = new ImageChooserManager(this,
				ChooserType.REQUEST_PICK_PICTURE, true);
		imageChooserManager.setImageChooserListener(this);
		try {
			//pbar.setVisibility(View.VISIBLE);
			filePath = imageChooserManager.choose();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void takePicture() {
		chooserType = ChooserType.REQUEST_CAPTURE_PICTURE;
		imageChooserManager = new ImageChooserManager(this,
				ChooserType.REQUEST_CAPTURE_PICTURE, true);
		imageChooserManager.setImageChooserListener(this);
		try {
			//pbar.setVisibility(View.VISIBLE);
			filePath = imageChooserManager.choose();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Should be called if for some reason the ImageChooserManager is null (Due
	// to destroying of activity for low memory situations)
	private void reinitializeImageChooser() {
		imageChooserManager = new ImageChooserManager(this, chooserType, true);
		imageChooserManager.setImageChooserListener(this);
		imageChooserManager.reinitialize(filePath);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG, "OnActivityResult");
		Log.i(TAG, "File Path : " + filePath);
		Log.i(TAG, "Chooser Type: " + chooserType);
		if (resultCode == RESULT_OK
				&& (requestCode == ChooserType.REQUEST_PICK_PICTURE || requestCode == ChooserType.REQUEST_CAPTURE_PICTURE)) {
			if (imageChooserManager == null) {
				reinitializeImageChooser();
			}
			imageChooserManager.submit(requestCode, data);
		} else {
			//pbar.setVisibility(View.GONE);
		}
	}
	
	private TypedString convert(String str) {
		return new TypedString(str);
	}

	private TypedFile convert(File file) {
		return new TypedFile("image/*", file);
	}

	@Override
	public void onImageChosen(final ChosenImage image) {
		runOnUiThread(new Runnable() {

			

			@Override
			public void run() {
			
				
				Log.i(TAG, "Chosen Image: O - " + image.getFilePathOriginal());
				Log.i(TAG, "Chosen Image: T - " + image.getFileThumbnail());
				Log.i(TAG,
						"Chosen Image: Ts - " + image.getFileThumbnailSmall());
				isActivityResultOver = true;
				originalFilePath = image.getFilePathOriginal();
				thumbnailFilePath = image.getFileThumbnail();
				thumbnailSmallFilePath = image.getFileThumbnailSmall();
				//pbar.setVisibility(View.GONE);
				if (image != null) {
					Log.i(TAG, "Chosen Image: Is not null");
					// textViewFile.setText(image.getFilePathOriginal());
					loadImage(imgUser, image.getFileThumbnail());
					// loadImage(imageViewThumbSmall,
					// image.getFileThumbnailSmall());
					
					Uri selectedImage = Uri.parse(image.getFilePathOriginal());

					imageURL = CameraHelper.getRealPathFromURI(selectedImage,
							SignUpActivity.this);
					
					
					 SignUpActivity.this.imgInBytes =   BitmapHelper.getByteArrayFromPath(imageURL);
					 ObjImgPath = new TypedByteArray("image/jpeg", imgInBytes);
					//ObjImgPath = imageURL == null ? new TypedByteArray("image/jpeg", new byte[10]) : convert(new File(imageURL));
					
					 
					/* retrofitAdapter.uploadFile(author, photoTypedFile)
					                .subscribe(<...>);*/
				
				
				
				
				
				} else {
					Log.i(TAG, "Chosen Image: Is null");
				}
			}
		});
		
		
		

	}

	private void loadImage(ImageView iv, final String path) {
		Picasso.with(SignUpActivity.this).load(Uri.fromFile(new File(path)))
				.fit().centerInside().into(iv);
	}

	@Override
	public void onError(final String reason) {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Log.i(TAG, "OnError: " + reason);
				//pbar.setVisibility(View.GONE);

			}
		});
	}

}
