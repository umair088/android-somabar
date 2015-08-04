package com.pyntail.somabar.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.ui.views.AnyTextView;
import com.pyntail.somabar.ui.views.TitleBar;

public class PostDrinkFragment extends BaseSupportFragment {
	
	private static final String TAG = PostDrinkFragment.class.getSimpleName();
	private View parentView;
	
	
	@InjectView(R.id.i_ingredientContainer)
	public LinearLayout i_ingredientContainer;

	@InjectView(R.id.ii_ingredientContainer)
	public LinearLayout ii_ingredientContainer;

	@InjectView(R.id.iii_ingredientContainer)
	public LinearLayout iii_ingredientContainer;

	@InjectView(R.id.iv_ingredientContainer)
	public LinearLayout iv_ingredientContainer;

	@InjectView(R.id.v_ingredientContainer)
	public LinearLayout v_ingredientContainer;

	@InjectView(R.id.binContainer)
	public RelativeLayout binContainer;

	@InjectView(R.id.ii_binContainer)
	public RelativeLayout ii_binContainer;

	@InjectView(R.id.iii_binContainer)
	public RelativeLayout iii_binContainer;

	@InjectView(R.id.iv_binContainer)
	public RelativeLayout iv_binContainer;

	@InjectView(R.id.v_binContainer)
	public RelativeLayout v_binContainer;

	@InjectView(R.id.i_ingrSeekbar)
	public SeekBar i_ingrSeekbar;

	@InjectView(R.id.ii_ingrSeekbar)
	public SeekBar ii_ingrSeekbar;

	@InjectView(R.id.iii_ingrSeekbar)
	public SeekBar iii_ingrSeekbar;

	@InjectView(R.id.iv_ingrSeekbar)
	public SeekBar iv_ingrSeekbar;

	@InjectView(R.id.v_ingrSeekbar)
	public SeekBar v_ingrSeekbar;

	@InjectView(R.id.i_txtCurrentValue)
	public AnyTextView i_txtCurrentValue;

	@InjectView(R.id.ii_txtCurrentValue)
	public AnyTextView ii_txtCurrentValue;

	@InjectView(R.id.iii_txtCurrentValue)
	public AnyTextView iii_txtCurrentValue;

	@InjectView(R.id.iv_txtCurrentValue)
	public AnyTextView iv_txtCurrentValue;

	@InjectView(R.id.v_txtCurrentValue)
	public AnyTextView v_txtCurrentValue;

	@InjectView(R.id.btnAdd)
	public AnyTextView btnAdd;
	
	
	
	@InjectView(R.id.btnCreateNewDrink)
	public ImageButton btnCreateNewDrink;
	
	
	@InjectView(R.id.chooseImageContainer)
	public LinearLayout chooseImageContainer;
	
	
	
	
	public static PostDrinkFragment newInstance() {
		return new PostDrinkFragment();
	}

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_post_drink, container,
					false);
			
			ButterKnife.inject(this, parentView);
		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListener();

	}

	private void setListener() {
		
		
		chooseImageContainer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activtyReference.addDockableSupportFragmentWithAnim(PickDrinkImageFragment.newInstance(), PickDrinkImageFragment.class.getSimpleName());
			}
		});
		
		
		btnAdd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtyReference.addDockableSupportFragmentWithAnim(AddIngredientsFragment.newInstance(false), AddIngredientsFragment.class.getSimpleName());
			}
		});
		btnCreateNewDrink.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtyReference.addDockableSupportFragmentWithAnim(AddIngredientsFragment.newInstance(false), AddIngredientsFragment.class.getSimpleName());
			}
		});

		i_ingrSeekbar.setProgress(22);
		i_txtCurrentValue.setText(String.valueOf(22));
		i_ingrSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				i_txtCurrentValue.setText(String.valueOf(progress));

			}
		});

		ii_ingrSeekbar.setProgress(30);
		ii_txtCurrentValue.setText(String.valueOf(30));
		ii_ingrSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						ii_txtCurrentValue.setText(String.valueOf(progress));

					}
				});
		iii_ingrSeekbar.setProgress(10);
		iii_txtCurrentValue.setText(String.valueOf(10));
		iii_ingrSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						iii_txtCurrentValue.setText(String.valueOf(progress));

					}
				});
		iv_ingrSeekbar.setProgress(60);
		iv_txtCurrentValue.setText(String.valueOf(60));
		iv_ingrSeekbar
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						iv_txtCurrentValue.setText(String.valueOf(progress));

					}
				});

		v_ingrSeekbar.setProgress(40);
		v_txtCurrentValue.setText(String.valueOf(40));
		v_ingrSeekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				v_txtCurrentValue.setText(String.valueOf(progress));

			}
		});

		i_ingredientContainer.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				if (binContainer.getVisibility() == View.GONE)
					binContainer.setVisibility(View.VISIBLE);
				else

					binContainer.setVisibility(View.GONE);

				return false;
			}
		});

		ii_ingredientContainer
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {

						if (ii_binContainer.getVisibility() == View.GONE)
							ii_binContainer.setVisibility(View.VISIBLE);
						else

							ii_binContainer.setVisibility(View.GONE);

						return false;
					}
				});

		iii_ingredientContainer
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {

						if (iii_binContainer.getVisibility() == View.GONE)
							iii_binContainer.setVisibility(View.VISIBLE);
						else

							iii_binContainer.setVisibility(View.GONE);

						return false;
					}
				});

		iv_ingredientContainer
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {

						if (iv_binContainer.getVisibility() == View.GONE)
							iv_binContainer.setVisibility(View.VISIBLE);
						else

							iv_binContainer.setVisibility(View.GONE);

						return false;
					}
				});

		v_ingredientContainer.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				if (v_binContainer.getVisibility() == View.GONE)
					v_binContainer.setVisibility(View.VISIBLE);
				else

					v_binContainer.setVisibility(View.GONE);

				return false;
			}
		});
	}

	
	@Override
	public void setTitleBar(TitleBar titleBar) {
		titleBar.setHeaderColor(this.getResources().getColor(
				R.color.milky_white));
		titleBar.setLeftButtonIcon(R.drawable.back_btn);
		titleBar.setRightTextOption("Post Drink");
		titleBar.setOnRightTextClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UIHelper.showLongToastInCenter(v.getContext(), "Drink has been posted.");
			}
		});
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				activtyReference.onBackPressed();
			}
		});
		titleBar.setHeadingText("Post A Drink");
		titleBar.hideAddButton();
		titleBar.hideRightButton();

	}
	
	
	@Override
	public void onPause() {
		
		if(getTitleBar()!=null)
		{
			getTitleBar().hideRightTextOption();
			
		}
		super.onPause();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;
	}
}
