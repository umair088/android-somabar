package com.pyntail.somabar.activities;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.EventListener;
import com.pyntail.somabar.R;
import com.pyntail.somabar.constants.AppConstants;
import com.pyntail.somabar.entities.DiscoverDrinkResponse;
import com.pyntail.somabar.entities.User;
import com.pyntail.somabar.entities.request.UserRecipesObject;
import com.pyntail.somabar.fragments.CommunityFragment;
import com.pyntail.somabar.fragments.DiscoverDrinkFragment;
import com.pyntail.somabar.fragments.HomeFragment;
import com.pyntail.somabar.fragments.MakeDrinkFragment;
import com.pyntail.somabar.fragments.MyAccountFragment;
import com.pyntail.somabar.fragments.MyDrinkFragment;
import com.pyntail.somabar.fragments.SettingFragment;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.NetworkHelper;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.dialog.DialogFactory;
import com.pyntail.somabar.ui.side.menu.ResideMenu;
import com.pyntail.somabar.ui.side.menu.ResideMenuItem;
import com.pyntail.somabar.ui.views.RoundedImageView;
import com.pyntail.somabar.ui.views.TitleBar;
import com.squareup.picasso.Picasso;

public class MainActivity extends DockActivity implements OnClickListener {

	private static final String TAG = MainActivity.class.getSimpleName();
	private static final int MODE_ON_CACHE = 0;
	private static final int MODE_ON_NETWORK = 1;
	public static final int LOGIN_SUCCESS_CODE = 1;
	public static final int LOGIN_FAILED_CODE = 0;
	@SuppressWarnings("unused")
	private static int CURRENT_MODE_OF_APP;

	@InjectView(R.id.header_main)
	public TitleBar titleBar;

	@InjectView(R.id.progressBar)
	ProgressBar progressBar;

	@InjectView(R.id.mainFrameLayout)
	@Nullable
	FrameLayout mainFrameLayout;

	@InjectView(R.id.mainLayout)
	@Nullable
	RelativeLayout mainLayout;

	@InjectView(R.id.popOverContainer)
	@Nullable
	RelativeLayout popOverContainer;
	
	
	
	
	TextView txtUserTitle;
	
	
	RoundedImageView imgUser;
	
	

	@SuppressWarnings("unused")
	private boolean loading = false;
	public SnackbarManager snackManager = null;

	/*
	 * Side Menu
	 */
	public ResideMenu resideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemMakeDrink;
	private ResideMenuItem itemDiscoverDrink;
	private ResideMenuItem itemCommunitySocial;
	private ResideMenuItem itemMyDrinks;
	private ResideMenuItem itemSettings;
	private ResideMenuItem itemPreOrder;

	/*
	 * 
	 * Fragments
	 */
	private BaseSupportFragment homeFragment;
	private BaseSupportFragment makeDrinkFragment;
	private BaseSupportFragment discoverFragment;
	private BaseSupportFragment communityAndSocialFragment;
	private BaseSupportFragment myDrinksFragment;
	private BaseSupportFragment settingsFragment;
	private BaseSupportFragment myAccountFragment;
	
	/*App Dialog*/
	private SweetAlertDialog appDialog;
	private User user;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		boolean isSkipEnabled = false;
		final Bundle extras = getIntent().getExtras();

		/*if (extras != null
				&& getIntent().getExtras().containsKey(AppConstants.IS_SKIP)) {
			isSkipEnabled = getIntent().getExtras().getBoolean(
					AppConstants.IS_SKIP);
		}*/
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dock);
		ButterKnife.inject(MainActivity.this);
		FragmentManager.enableDebugLogging(false);
		initSideMenu();
		initFragments();
		setHomeFragment();
		setLayout(isSkipEnabled);
		setListener();
		startSync();

		snackManager = new SnackbarManager();
		
	}
	
	
	
	private void startSync() {
		showSweetLoader("Syncing please wait...");
		user = prefHelper.getUser();
		String securityToken="";
		int userId = 0;
		
		if(user!=null)
		{
			txtUserTitle.setText(user.getFullName());
			Picasso.with(MainActivity.this).load(user.getImageUrl()).fit().centerCrop()
			.into(imgUser);
			
			
			securityToken =user.getSecurityToken();	
			userId =user.getUserId();
			
			securityToken = userId+":" + securityToken;
			byte[] data = null;
			try {
				data = securityToken.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String base64 = Base64.encodeToString(data, Base64.DEFAULT);
			String encodedStrToBesend ="basic " +base64;
			prefHelper.putEncodedSecurityToken(encodedStrToBesend);
			Applog.Debug(TAG, encodedStrToBesend);
			
			getMyDrinks(encodedStrToBesend,userId,0,100);
		}
		
		getDiscoverRecipesForPremium();
		
		
	}

	private void getDiscoverRecipesForPremium() {

		
		if(user.getUserId()!=0)
		{
			//DecimalFormat formatter = new DecimalFormat("0");
			WebServiceFactory.getInstance().getRecipesForPremium(-1,10,0.0, new CallbackRetrofit<ArrayList<DiscoverDrinkResponse>>() {

				@Override
				public void on200(ArrayList<DiscoverDrinkResponse> value, Response response) {
					hideSweetLoader();
					Applog.Debug(TAG, "Got Discover Drinks");
					if(value!=null && value.size()>0)
					prefHelper.putDiscoverDrinks(value);
					
				}

				@Override
				public void on204(ArrayList<DiscoverDrinkResponse> value, Response response) {
					hideSweetLoader();
				}

				@Override
				public void on401(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void on404(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void on408(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void on409(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void on500(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void on501(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void on502(RetrofitError error) {
					hideSweetLoader();
				}

				@Override
				public void onFailure(RetrofitError error) {
					hideSweetLoader();
				}

				
			});
			
		}
		
	}

	public void getMyDrinks(final String securityToken,final int userId,final int isSkip , final int isTake) {
		
		
	
		
		WebServiceFactory.getInstance().getRecipesForMyDrinks(userId,securityToken, new CallbackRetrofit<ArrayList<DiscoverDrinkResponse>>() {
			
			@Override
			public void onFailure(RetrofitError error) {
				
			}
			
			@Override
			public void on502(RetrofitError error) {
				
			}
			
			@Override
			public void on501(RetrofitError error) {
				
			}
			
			@Override
			public void on500(RetrofitError error) {
				
			}
			
			@Override
			public void on409(RetrofitError error) {
				
			}
			
			@Override
			public void on408(RetrofitError error) {
				
			}
			
			@Override
			public void on404(RetrofitError error) {
				
			}
			
			@Override
			public void on401(RetrofitError error) {
				
			}
			
			@Override
			public void on204(ArrayList<DiscoverDrinkResponse> value, Response response) {
				
			}
			
			@Override
			public void on200(ArrayList<DiscoverDrinkResponse> value, Response response) {
				
				Applog.Debug(TAG, "Got Discover Drinks");
				if(value!=null && value.size()>0)
				prefHelper.putMyDrinks(value);
				
			}
		});
		/*
		
		if(user.getUserId()!=0)
		{
			DecimalFormat formatter = new DecimalFormat("0.00");
			WebServiceFactory.getInstance().getRecipesForSync(securityToken,-1,10000,formatter, new CallbackRetrofit<User>() {

				@Override
				public void on200(User value, Response response) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on204(User value, Response response) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on401(RetrofitError error) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on404(RetrofitError error) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on408(RetrofitError error) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on409(RetrofitError error) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on500(RetrofitError error) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void on501(RetrofitError error) {
					// TODO Auto-generated method stub
					
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
		
		
	*/}

	private void getDiscoverDrinks(final String securityToken) {
		UserRecipesObject recipe = new UserRecipesObject();
		

		WebServiceFactory.getInstance().getAllRecipes(securityToken,recipe, new CallbackRetrofit<User>() {
			
			@Override
			public void onFailure(RetrofitError error) {

				hideSweetLoader();
				

			
			}
			
			@Override
			public void on502(RetrofitError error) {
				hideSweetLoader();
			}
			
			@Override
			public void on501(RetrofitError error) {
				hideSweetLoader();
			}
			
			@Override
			public void on500(RetrofitError error) {
				hideSweetLoader();
			}
			
			@Override
			public void on409(RetrofitError error) {
				hideSweetLoader();
			}
			
			@Override
			public void on408(RetrofitError error) {
				hideSweetLoader();
			}
			
			@Override
			public void on404(RetrofitError error) {
				hideSweetLoader();
			}
			
			@Override
			public void on401(RetrofitError error) {
				
			}
			
			@Override
			public void on204(User value, Response response) {
				
			}
			
			@Override
			public void on200(User value, Response response) {
				
			}
		});
		
		
	}

	@TargetApi(19) 
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
	
	// A method to find height of the status bar
	public int getStatusBarHeight() {
	    int result = 0;
	    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
	    if (resourceId > 0) {
	        result = getResources().getDimensionPixelSize(resourceId);
	    }
	    return result;
	}

	private void setListener() {
		resideMenu.setOnUserTitleClickListener(this);
		resideMenu.setOnUserNameClickListener(this);
		resideMenu.setOnUserFacebookClickListener(this);
		resideMenu.setOnUserSignUnClickListener(this);
		resideMenu.setOnUserSignInClickListener(this);
	}

	private void setLayout(final boolean isSkipEnabled) {
		if (isSkipEnabled) {
			resideMenu.hideUserContainer();
			resideMenu.showConfigureUserContainer();
		} else {
			resideMenu.showUserContainer();
			resideMenu.hideConfigureUserContainer();
		}
	}

	private void initFragments() {

		homeFragment = HomeFragment.newInstance();
		makeDrinkFragment = MakeDrinkFragment.newInstance();
		discoverFragment = DiscoverDrinkFragment.newInstance();
		communityAndSocialFragment = CommunityFragment.newInstance();
		myDrinksFragment = MyDrinkFragment.newInstance();
		settingsFragment = SettingFragment.newInstance();
		myAccountFragment = MyAccountFragment.newInstance();

	}

	public void setHomeFragment() {
		if(getLastAddedSuppFragment() != homeFragment )
		{
		emptyBackStack();
		addDockableSupportFragment(homeFragment, homeFragment.getClass()
				.getSimpleName());
		}
	}

	public void setMakeADrinksFragment() {
		
		if(getLastAddedSuppFragment() != makeDrinkFragment )
		{
		emptyBackStack();
		addDockableSupportFragment(makeDrinkFragment, makeDrinkFragment
				.getClass().getSimpleName());
		}

	}

	public void setDiscoverFragment() {
		
		if(getLastAddedSuppFragment() != discoverFragment )
		{
		emptyBackStack();
		addDockableSupportFragment(discoverFragment, discoverFragment
				.getClass().getSimpleName());
		}
	}

	public void setCommunityAndSocialFragment() {
		
		if(getLastAddedSuppFragment() != communityAndSocialFragment )
		{
		emptyBackStack();
		addDockableSupportFragment(communityAndSocialFragment,
				communityAndSocialFragment.getClass().getSimpleName());
		}

	}

	public void setMyDrinksFragment() {
		
		if(getLastAddedSuppFragment() != myDrinksFragment )
		{
		emptyBackStack();
		addDockableSupportFragment(myDrinksFragment, myDrinksFragment
				.getClass().getSimpleName());
		}

	}

	public void setSettingsFragment() {
		if(getLastAddedSuppFragment() != settingsFragment )
		{
		emptyBackStack();
		addDockableSupportFragment(settingsFragment, settingsFragment
				.getClass().getSimpleName());
		}

	}

	private void initSideMenu() {
		
		
		
		

		/* attach to current activity */
		resideMenu = new ResideMenu(this);
		
		txtUserTitle = (TextView)resideMenu.findViewById(R.id.txtUserTitle);
		imgUser = (RoundedImageView)resideMenu.findViewById(R.id.imgUser);
		
		
		resideMenu.setBackground(R.drawable.background_black);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		/*
		 * valid scale factor is between 0.0f and 1.0f. leftmenu' width is
		 * 260dip.
		 */
		resideMenu.setScaleValue(0.8f);

		/* create menu items */
		itemHome = new ResideMenuItem(this, "Home", true);
		itemMakeDrink = new ResideMenuItem(this, "Make A Drink", true,false);
		itemDiscoverDrink = new ResideMenuItem(this, "Discover Drinks", true);
		itemCommunitySocial = new ResideMenuItem(this, "Community & Social",
				true);
		itemMyDrinks = new ResideMenuItem(this, "My Drinks", true);
		itemSettings = new ResideMenuItem(this, "Settings", true);
		itemPreOrder = new ResideMenuItem(this, "Pre-Order Somabar", false,true,true);
		
		

		/* set listener of side menu */
		itemHome.setOnClickListener(this);
		//itemMakeDrink.setOnClickListener(this);
		itemDiscoverDrink.setOnClickListener(this);
		itemCommunitySocial.setOnClickListener(this);
		itemMyDrinks.setOnClickListener(this);
		itemSettings.setOnClickListener(this);

		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemMakeDrink, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemDiscoverDrink, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemCommunitySocial, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemMyDrinks, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemPreOrder, ResideMenu.DIRECTION_LEFT);

		/* you can disable a direction by setting */
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);

		resideMenu.setOnUserImageClickListener(this);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {

			Applog.Debug(TAG, "onMenuOpen");
			changeBackgroundColor(R.drawable.background_rounded_shape);
			
		}

		@Override
		public void closeMenu() {
			Applog.Debug(TAG, "onMenuClosed");
			changeBackgroundColor(R.drawable.background_plain);
		}
	};

	public void openMenu() {
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}

	public void hideMenu() {
		resideMenu.closeMenu();
	}

	public void setMenuListener(ResideMenu.OnMenuListener menuListener) {
		resideMenu.setMenuListener(menuListener);
	}

	public void changeBackgroundColor(final int resourceId) {
		mainLayout.setBackgroundResource(resourceId);
	}
	
	
	
	
	
	public void changeFragmentBackgroundColor(final int resourceId) {
		mainFrameLayout.setBackgroundResource(resourceId);
	}
	
	public void changeBackgroundResource(final int resourceId) {
		mainLayout.setBackgroundResource(resourceId);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("static-access")
	public void showSnackBar(final String msgtoShow, EventListener callback) {

		snackManager.show(
				Snackbar.with(getApplicationContext())
						.text(msgtoShow)
						.textTypeface(
								Typeface.createFromAsset(getResources()
										.getAssets(), "fonts/raleway.ttf"))
						.duration(Snackbar.SnackbarDuration.LENGTH_SHORT),
				(Activity) callback);

	}

	@SuppressWarnings("static-access")
	public void showSnackBar(final String msgtoShow) {
		snackManager.show(Snackbar
				.with(getApplicationContext())
				.text(msgtoShow)
				.textTypeface(
						Typeface.createFromAsset(getResources().getAssets(),
								"fonts/raleway.ttf"))
				.duration(Snackbar.SnackbarDuration.LENGTH_SHORT));

	}

	@SuppressWarnings("static-access")
	public void hideSnackBar() {

		if (snackManager.getCurrentSnackbar() != null)
			snackManager.dismiss();

	}

	public void setMode() {
		if (NetworkHelper.isNetworkAvailable(MainActivity.this))
			CURRENT_MODE_OF_APP = MODE_ON_NETWORK;
		else
			CURRENT_MODE_OF_APP = MODE_ON_CACHE;
	}

	@Override
	public void onLoadingStarted() {

		if (mainFrameLayout != null) {

			mainFrameLayout.setVisibility(View.VISIBLE); //

			if(appDialog!=null)
				appDialog = null;
			
			appDialog =	DialogFactory.showSweetLoader(MainActivity.this,
						SweetAlertDialog.PROGRESS_TYPE, "Loading...");
			loading = true;
		}

	}

	@Override
	public void onLoadingFinished() {
		mainFrameLayout.setVisibility(View.VISIBLE);

		if(appDialog!=null)
		{
			appDialog.dismissWithAnimation();
		}
		loading = false;

	}
	
	public void showSweetLoader() {

		if (appDialog != null)
			appDialog = null;

		appDialog = DialogFactory.showSweetLoader(MainActivity.this,
				SweetAlertDialog.PROGRESS_TYPE, "Loading...");

	}
	
	public void showSweetLoader(final String msgToShow) {

		if (appDialog != null)
			appDialog = null;

		appDialog = DialogFactory.showSweetLoader(MainActivity.this,
				SweetAlertDialog.PROGRESS_TYPE, msgToShow);

	}
	
	
	public void showSweetWarningAlert(String btnPositive, String btnNegative,
			String title, String msgToShow) {

		if (appDialog != null) {
			appDialog.dismissWithAnimation();
			appDialog = null;
		}
		appDialog = DialogFactory.showSweetAlertDialogTwoChoice(
				MainActivity.this, SweetAlertDialog.WARNING_TYPE, msgToShow,
				title, btnPositive, btnNegative, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				}, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				});
		appDialog.show();
	}

	public void showSweetSuccessAlert(String btnPositive, String btnNegative,
			String title, String msgToShow) {

		if (appDialog != null) {
			appDialog.dismissWithAnimation();
			appDialog = null;
		}

		appDialog = DialogFactory.showSweetAlertDialogTwoChoice(
				MainActivity.this, SweetAlertDialog.SUCCESS_TYPE, msgToShow,
				title, btnPositive, btnNegative, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				}, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				});
		appDialog.show();
	}

	public void showSweetFailureAlert(String btnPositive, String btnNegative,
			String title, String msgToShow) {

		if (appDialog != null) {
			appDialog.dismissWithAnimation();
			appDialog = null;
		}

		appDialog = DialogFactory.showSweetAlertDialogTwoChoice(
				MainActivity.this, SweetAlertDialog.ERROR_TYPE, msgToShow,
				title, btnPositive, btnNegative, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				}, new OnSweetClickListener() {

					@Override
					public void onClick(SweetAlertDialog sweetAlertDialog) {
						appDialog.dismissWithAnimation();
					}
				});
		appDialog.show();
	}

	public void hideSweetLoader() {

		if (appDialog == null)
			return;

		appDialog.dismissWithAnimation();

	}
	
	
	
	

	@Override
	public void onProgressUpdated(int percentLoaded) {

	}

	@Override
	public int getDockFrameLayoutId() {
		return R.id.mainFrameLayout;
	}

	@Override
	public boolean isLoggedIn() {
		return false;
	}

	@Override
	public void onClick(View view) {

		if (view == itemHome) {
			Applog.Debug(TAG, "onHome");
			setHomeFragment();
		} else if (view == itemMakeDrink) {
			Applog.Debug(TAG, "onMakeDrinkS");
			setMakeADrinksFragment();
		} else if (view == itemDiscoverDrink) {
			Applog.Debug(TAG, "onDiscoverDrinks");
			setDiscoverFragment();
		} else if (view == itemCommunitySocial) {
			Applog.Debug(TAG, "onCommunitynSocial");
			//setCommunityAndSocialFragment();
		} else if (view == itemMyDrinks) {
			Applog.Debug(TAG, "onMyDrinks");
			setMyDrinksFragment();
		} else if (view == itemSettings) {
			Applog.Debug(TAG, "onSetting");
			setSettingsFragment();
		} else if (view.getId() == R.id.imgUser
				|| view.getId() == R.id.txtUserTitle
				|| view.getId() == R.id.txtUserName) {
			Applog.Debug(TAG, "onMyAccount");
			if(getLastAddedSuppFragment() != myAccountFragment )
			{
				emptyBackStack();
			addDockableSupportFragment(myAccountFragment, myAccountFragment
					.getClass().getSimpleName());
			}

		} else if (view.getId() == R.id.btn_facebook) {
			Applog.Debug(TAG, "onFacebook");
			UIHelper.showLongToastInCenter(MainActivity.this, "Coming Soon");

		} else if (view.getId() == R.id.btn_signin) {
			Applog.Debug(TAG, "onSignIn");
			startActivity(new Intent(MainActivity.this, SignInActivity.class));
			finish();
		} else if (view.getId() == R.id.btn_signup) {
			Applog.Debug(TAG, "onSignUp");
			startActivity(new Intent(MainActivity.this, SignUpActivity.class));
			finish();
		}

		resideMenu.closeMenu();

	}
}
