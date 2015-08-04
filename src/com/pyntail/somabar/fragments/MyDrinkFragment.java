package com.pyntail.somabar.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.DiscoverDrinkResponse;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.AnimationHelper;
import com.pyntail.somabar.recycler.AbsViewHolderAdapter;
import com.pyntail.somabar.recycler.AbsViewHolderAdapter.OnItemClickListener;
import com.pyntail.somabar.recycler.SimpleAdapter;
import com.pyntail.somabar.ui.viewholder.MyDrinkViewHolder;
import com.pyntail.somabar.ui.views.TitleBar;

public class MyDrinkFragment extends BaseSupportFragment{

	private final String TAG = this.getClass().getSimpleName();
	private View parentView;
	
	@InjectView(R.id.recycleView)
	public RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private SimpleAdapter<MyDrinkItem> myDrinksAdapter;

	
	@InjectView(R.id.noDataFound)
	public TextView noDataFound;

	
	public static MyDrinkFragment newInstance() {
		return new MyDrinkFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_my_drinks, container,
					false);
			ButterKnife.inject(this, parentView);

		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		activtyReference = (MainActivity) activity;
		

	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		initRecycle();
		final ArrayList<DiscoverDrinkResponse> discoverDrinksCollection = prefHelper.getSavedMyDrinks();
		if (discoverDrinksCollection != null &&discoverDrinksCollection.size()>0 )
		{
			
			//activtyReference.showSweetLoader();
			hideNoDataFound();
			bindDrinks(discoverDrinksCollection);
		}
		else
			showNoDataFound();
		
		
		//initRecycleViews();
	}
	
	private void initRecycle() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(activtyReference);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);
		layoutManager.canScrollVertically();
		recyclerView.setLayoutManager(layoutManager);
	}

	private void showNoDataFound() {

		AnimationHelper.hideWithAlpaAnimation(recyclerView, 1000);
		AnimationHelper.showWithAlpaAnimation(noDataFound,1100);
		
	}
	
	private void hideNoDataFound() {

		AnimationHelper.showWithAlpaAnimation(recyclerView, 1000);
		AnimationHelper.hideWithAlpaAnimation(noDataFound,1100);
		
	}
	
	/*public void initRecycleViews()
	{
		LinearLayoutManager layoutManager = new LinearLayoutManager(activtyReference);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);
		layoutManager.canScrollVertically();
		recyclerView.setLayoutManager(layoutManager);
		
		myDrinksAdapter = new SimpleAdapter<MyDrinkItem>(R.layout.item_my_drink,
				DrinkViewHolder.class, generateRandomDrinks());
		
		recyclerView.setAdapter(myDrinksAdapter);
		
		myDrinksAdapter.setOnItemClickListener(new OnItemClickListener<MyDrinkItem>() {

			@Override
			public void onItemClick(AbsViewHolderAdapter<MyDrinkItem> parent,
					View view, MyDrinkItem object, int position) {
				
				activtyReference.addDockableSupportFragmentWithAnim(IngredientsFragment.newInstance(), IngredientsFragment.class.getSimpleName());
			}
		});
	}*/
	
	private void bindDrinks(ArrayList<DiscoverDrinkResponse> discoverDrinksCollection) {

		/*LinearLayoutManager layoutManager = new LinearLayoutManager(activtyReference);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);
		layoutManager.canScrollVertically();
		recyclerView.setLayoutManager(layoutManager);*/

		myDrinksAdapter = new SimpleAdapter<MyDrinkItem>(
				R.layout.item_my_drink, MyDrinkViewHolder.class,iterateDrinks(discoverDrinksCollection));

		recyclerView.setAdapter(myDrinksAdapter);
		myDrinksAdapter
				.setOnItemClickListener(new OnItemClickListener<MyDrinkItem>() {

					@Override
					public void onItemClick(
							AbsViewHolderAdapter<MyDrinkItem> parent,
							View view, MyDrinkItem object, int position) {

						activtyReference.addDockableSupportFragmentWithAnim(
								IngredientsFragment.newInstance(),
								IngredientsFragment.class.getSimpleName());
					}
				});

	}
	
	private List<MyDrinkItem> iterateDrinks(ArrayList<DiscoverDrinkResponse> drinksCollection) {
		List<MyDrinkItem> drinkList = new ArrayList<MyDrinkItem>();
		MyDrinkItem drinkUiModel;
		for (DiscoverDrinkResponse _drink : drinksCollection) {

			drinkUiModel = new MyDrinkItem();

			
			
			// Recepe id
			drinkUiModel.setReciepeId(_drink.getRecipeId());
			
		
			drinkUiModel.setLikeCount(_drink.getNoOfLikes());
			
			
			
			// Drink Name
			if (_drink.getName() == null || _drink.getName().isEmpty())
				drinkUiModel.setDrinkName("Not defined");
			else
				drinkUiModel.setDrinkName(_drink.getName());

			// Drink Image
			if (_drink.getImageUrl() == null)
				drinkUiModel.setImgUrl("");
			else
				drinkUiModel.setImgUrl(_drink.getImageUrl());

			
			 /*INGRDIENTS*/	
			Ingredient tempIngre;
			int tempIngreIndex = 0;
			for (Ingredient _ingredientsOfDrink : _drink.getIngredients()) {
				tempIngre = _ingredientsOfDrink;
				tempIngre.setIngredientIndex(tempIngreIndex++);
				
				if (_ingredientsOfDrink.getLabel() == null || _drink.getName().isEmpty())
					tempIngre.setLabel("Not available");
				else
					tempIngre.setLabel(_ingredientsOfDrink.getLabel());
				
				
				if (_drink.getName().isEmpty())
					tempIngre.setQuantity(0.0f);
				else
					tempIngre.setQuantity(_ingredientsOfDrink.getQuantity());
				
				
				drinkUiModel.getIngredients().add(tempIngre);
			}
			
			
			drinkList.add(drinkUiModel);
			
			
			
			
			
			
		}

		
		return drinkList;
	}
	
	private List<MyDrinkItem> generateRandomDrinks() {
		List<MyDrinkItem> drinkList = new ArrayList<MyDrinkItem>();
		drinkList.add(new MyDrinkItem("Black Razz & Lemon \n Lime Soda", R.drawable.post_drinkimg2,"Lime"));
		drinkList.add(new MyDrinkItem("Artic Grap & Lemon-\nLime Soda", R.drawable.post_drinkimg1,"Grape"));
		drinkList.add(new MyDrinkItem("Black Razz & Lemon \n Lime Soda", R.drawable.post_drinkimg3,"Lime"));
		drinkList.add(new MyDrinkItem("Artic Grap & Lemon-\nLime Soda", R.drawable.post_drinkimg4,"Vodka"));
		drinkList.add(new MyDrinkItem("Black Razz & Lemon \n Lime Soda", R.drawable.post_drinkimg2,"Lime"));
		return drinkList;
	}
	
	
	@Override
	public void setTitleBar(TitleBar titleBar) {
		titleBar.showTitleBar();
		titleBar.setHeaderColor(this.getResources().getColor(R.color.milky_white));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_orange);
		titleBar.setOnLeftClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("My Drinks");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}
}
