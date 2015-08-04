package com.pyntail.somabar.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.DiscoverDrinkResponse;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.ui.views.TitleBar;

public class IngredientsFragment extends BaseSupportFragment {

	private View parentView;
	private final String TAG = this.getClass().getSimpleName();

	@InjectView(R.id.editDrink)
	public RelativeLayout editDrink;

	@InjectView(R.id.imgEditDrink)
	CheckBox imgEditDrink;

	@InjectView(R.id.txtDrinkTitle)
	TextView txtDrinkTitle;

	@InjectView(R.id.instructionContainer)
	RelativeLayout instructionContainer;

	@InjectView(R.id.itxtIngredient)
	TextView itxtIngredient;

	@InjectView(R.id.iitxtIngredient)
	TextView iitxtIngredient;

	@InjectView(R.id.iiitxtIngredient)
	TextView iiitxtIngredient;

	@InjectView(R.id.ivtxtIngredient)
	TextView ivtxtIngredient;

	@InjectView(R.id.vtxtIngredient)
	TextView vtxtIngredient;

	@InjectView(R.id.vitxtIngredient)
	TextView vitxtIngredient;

	@InjectView(R.id.iIngredientContainer)
	RelativeLayout iIngredientContainer;

	@InjectView(R.id.iiIngredientContainer)
	RelativeLayout iiIngredientContainer;

	@InjectView(R.id.iiiIngredientContainer)
	RelativeLayout iiiIngredientContainer;

	@InjectView(R.id.ivIngredientContainer)
	RelativeLayout ivIngredientContainer;

	@InjectView(R.id.vIngredientContainer)
	RelativeLayout vIngredientContainer;

	@InjectView(R.id.viIngredientContainer)
	RelativeLayout viIngredientContainer;

	@InjectView(R.id.chkBoxFav)
	CheckBox chkBoxFav;

	private DiscoverDrinkResponse selectedDrink;
	private MyDrinkItem object;

	public static IngredientsFragment newInstance() {
		return new IngredientsFragment();
	}

	private IngredientsFragment() {
	}

	public static IngredientsFragment newInstance(
			DiscoverDrinkResponse selectedDrink, MyDrinkItem object) {
		return new IngredientsFragment(selectedDrink, object);
	}

	private IngredientsFragment(DiscoverDrinkResponse selectedDrink,
			MyDrinkItem object) {
		this.selectedDrink = selectedDrink;
		this.object = object;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_ingredients,
					container, false);
			ButterKnife.inject(this, parentView);
		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		txtDrinkTitle.setText(selectedDrink.getName());
		if(object.isFavorite())
		chkBoxFav.setButtonDrawable(R.drawable.ingredients_star_active);
		else
			chkBoxFav.setButtonDrawable(R.drawable.ingredients_star);	
		
		if (object != null && !object.getIngredients().isEmpty())
			setIngredients(object.getIngredients());
		instructionContainer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				activtyReference.addDockableSupportFragmentWithAnim(
						InstructionPreparationFragment.newInstance(
								selectedDrink.getInstructions(),
								selectedDrink.getImageUrl(),
								selectedDrink.getName()),
						InstructionPreparationFragment.class.getSimpleName());
			}
		});

		imgEditDrink.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// activtyReference.addDockableSupportFragmentWithAnim(EditDrinkFragment.newInstance(),
				// EditDrinkFragment.class.getSimpleName());
				activtyReference.addDockableSupportFragmentWithAnim(
						RemakeDrinkFragment.newInstance(),
						RemakeDrinkFragment.class.getSimpleName());

			}
		});

	}

	private void setIngredients(List<Ingredient> ingredients) {
		/* SET INGREDIENTS */
		if (ingredients != null && ingredients.isEmpty()) {
			/*
			 * itxtIngredient.setVisibility(View.VISIBLE);
			 * itxtQuantity.setVisibility(View.VISIBLE);
			 * 
			 * itxtIngredient.setText("Not Available");
			 * itxtQuantity.setText("");
			 */
			return;
		}

		for (Ingredient _ingredient : ingredients) {
			switch (_ingredient.getIngredientIndex()) {
			case 0:

				iIngredientContainer.setVisibility(View.VISIBLE);
				itxtIngredient.setText(_ingredient.getLabel());

				break;

			case 1:

				iiIngredientContainer.setVisibility(View.VISIBLE);
				iitxtIngredient.setText(_ingredient.getLabel());

				break;

			case 2:
				iiiIngredientContainer.setVisibility(View.VISIBLE);
				iiitxtIngredient.setText(_ingredient.getLabel());
				break;

			case 3:

				ivIngredientContainer.setVisibility(View.VISIBLE);
				ivtxtIngredient.setText(_ingredient.getLabel());
				break;

			case 4:
				vIngredientContainer.setVisibility(View.VISIBLE);
				vtxtIngredient.setText(_ingredient.getLabel());
				break;

			case 5:
				viIngredientContainer.setVisibility(View.VISIBLE);
				vitxtIngredient.setText(_ingredient.getLabel());
				break;

			case 6:

				break;

			default:
				break;
			}

		}

	}

	@Override
	public void setTitleBar(TitleBar titleBar) {
		titleBar.setHeaderColor(this.getResources().getColor(
				R.color.milky_white));
		titleBar.setLeftButtonIcon(R.drawable.back_btn);

		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				activtyReference.onBackPressed();
			}
		});
		titleBar.setHeadingText("INGREDIENTS");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;
	}

}
