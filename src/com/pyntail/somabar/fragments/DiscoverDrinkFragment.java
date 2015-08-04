package com.pyntail.somabar.fragments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.adapters.Category;
import com.pyntail.somabar.adapters.SettingsListAdapter;
import com.pyntail.somabar.entities.DiscoverDrinkResponse;
import com.pyntail.somabar.entities.FavoriteObject;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.entities.request.Ingredient;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.AnimationHelper;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.helpers.Utils;
import com.pyntail.somabar.recycler.AbsViewHolderAdapter;
import com.pyntail.somabar.recycler.AbsViewHolderAdapter.OnItemClickListener;
import com.pyntail.somabar.recycler.SimpleAdapter;
import com.pyntail.somabar.retrofit.CallbackRetrofit;
import com.pyntail.somabar.retrofit.WebServiceFactory;
import com.pyntail.somabar.ui.viewholder.DrinkViewHolder;
import com.pyntail.somabar.ui.views.TitleBar;

public class DiscoverDrinkFragment extends BaseSupportFragment {

	private final String TAG = this.getClass().getSimpleName();
	private View parentView;

	@InjectView(R.id.recycleView)
	public RecyclerView recyclerView;

	@InjectView(R.id.editTxtSearchKeyword)
	public EditText editTxtSearchKeyword;

	private LinearLayoutManager layoutManager;
	private SimpleAdapter<MyDrinkItem> myDrinksAdapter;

	private SettingsListAdapter adapter;
	private ExpandableListView categoriesList;
	private ArrayList<Category> categories;
	protected Context mContext;
	@InjectView(R.id.discoverDrinkContainer)
	public RelativeLayout discoverDrinkContainer;

	@InjectView(R.id.noDataFound)
	public TextView noDataFound;

	List<MyDrinkItem> drinkList;

	public static DiscoverDrinkFragment newInstance() {
		return new DiscoverDrinkFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_discover_drink,
					container, false);
			mContext = getActivity();
			categoriesList = (ExpandableListView) parentView
					.findViewById(R.id.categories);
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
		final ArrayList<DiscoverDrinkResponse> discoverDrinksCollection = prefHelper
				.getSavedDiscoverDrinks();
		if (discoverDrinksCollection != null
				&& discoverDrinksCollection.size() > 0) {

			// activtyReference.showSweetLoader();
			hideNoDataFound();
			bindDrinks(discoverDrinksCollection);
		} else
			showNoDataFound();

		// initRecycleViews();
		initExpandableFilters();
		editTxtSearchKeyword
				.setOnEditorActionListener(new OnEditorActionListener() {

					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_SEARCH) {
							if (Utils.isEmptyOrNull(v.getText().toString())) {
								activtyReference
										.showSnackBar("Write anything to search.");
								return true;
							}
							// filterable

						}
						return false;
					}
				});

	}

	private void initRecycle() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(
				activtyReference);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);
		layoutManager.canScrollVertically();
		recyclerView.setLayoutManager(layoutManager);
	}

	private void showNoDataFound() {

		AnimationHelper.hideWithAlpaAnimation(recyclerView, 1000);
		AnimationHelper.showWithAlpaAnimation(noDataFound, 1100);

	}

	private void hideNoDataFound() {

		AnimationHelper.showWithAlpaAnimation(recyclerView, 1000);
		AnimationHelper.hideWithAlpaAnimation(noDataFound, 1100);

	}

	private void bindDrinks(
			final ArrayList<DiscoverDrinkResponse> discoverDrinksCollection) {

		iterateDrinks(discoverDrinksCollection);
		myDrinksAdapter = new SimpleAdapter<MyDrinkItem>(
				R.layout.item_discover_drink_view, DrinkViewHolder.class,
				drinkList);

		recyclerView.setAdapter(myDrinksAdapter);
		myDrinksAdapter
				.setOnItemClickListener(new OnItemClickListener<MyDrinkItem>() {

					@Override
					public void onItemClick(
							AbsViewHolderAdapter<MyDrinkItem> parent,
							View view, MyDrinkItem object, int position) {

						DiscoverDrinkResponse selectedDrink = discoverDrinksCollection
								.get(position);
						if (selectedDrink != null && object != null) {
							activtyReference
									.addDockableSupportFragmentWithAnim(
											IngredientsFragment.newInstance(
													selectedDrink, object),
											IngredientsFragment.class
													.getSimpleName());
						}
					}
				});

	}

	private void initExpandableFilters() {
		categories = Category.getCategories();
		adapter = new SettingsListAdapter(activtyReference, categories,
				categoriesList);
		categoriesList.setAdapter(adapter);

		categoriesList.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				CheckBox checkbox = (CheckBox) v
						.findViewById(R.id.list_item_text_child);
				checkbox.toggle();

				// find parent view by tag
				View parentView = categoriesList.findViewWithTag(categories
						.get(groupPosition).name);
				if (parentView != null) {
					TextView sub = (TextView) parentView
							.findViewById(R.id.list_item_text_subscriptions);

					if (sub != null) {
						Category category = categories.get(groupPosition);
						if (checkbox.isChecked()) {
							// add child category to parent's selection list
							category.selection.add(checkbox.getText()
									.toString());

							// sort list in alphabetical order
							// Collections.sort(category.selection, new
							// CustomComparator());
						} else {
							// remove child category from parent's selection
							// list
							category.selection.remove(checkbox.getText()
									.toString());
						}

						// display selection list
						sub.setText(category.selection.toString());
					}
				}
				return true;
			}
		});

	}

	public void searchRecipies(final int recipeId, final int count,
			final String searchText) {

		WebServiceFactory.getInstance().searchRecipies(recipeId, count,
				searchText, new CallbackRetrofit<FavoriteObject>() {

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
					public void on204(FavoriteObject value, Response response) {

					}

					@Override
					public void on200(FavoriteObject value, Response response) {
						UIHelper.showLongToastInCenter(activtyReference,
								"got it");
					}
				});

	}

	private void iterateDrinks(ArrayList<DiscoverDrinkResponse> drinksCollection) {

		if (drinkList == null) {
			drinkList = new ArrayList<MyDrinkItem>();
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
				
				
				/* INGRDIENTS */
				Ingredient tempIngre;
				int tempIngreIndex = 0;
				for (Ingredient _ingredientsOfDrink : _drink.getIngredients()) {
					tempIngre = _ingredientsOfDrink;
					tempIngre.setIngredientIndex(tempIngreIndex++);

					if (_ingredientsOfDrink.getLabel() == null
							|| _drink.getName().isEmpty())
						tempIngre.setLabel("Not available");
					else
						tempIngre.setLabel(_ingredientsOfDrink.getLabel());

					if (_drink.getName().isEmpty())
						tempIngre.setQuantity(0.0f);
					else
						tempIngre
								.setQuantity(_ingredientsOfDrink.getQuantity());

					drinkUiModel.getIngredients().add(tempIngre);
				}
				

				drinkList.add(drinkUiModel);

				

			}
		}

	}

	@Override
	public void setTitleBar(final TitleBar titleBar) {
		titleBar.setHeaderColor(this.getResources().getColor(
				R.color.milky_white));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_orange);
		titleBar.setRightButtonIcon(R.drawable.fillter_orange);
		titleBar.setOnRightClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				discoverDrinkContainer.setVisibility(View.GONE);
				categoriesList.setVisibility(View.VISIBLE);

				titleBar.setRightTextOption("Apply");
				// titleBar.setRightTextColor(this.getResources().getColor(R.color.setting_grey));
				titleBar.setOnRightTextClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						UIHelper.showLongToastInCenter(v.getContext(),
								"Filter Applied");
						discoverDrinkContainer.setVisibility(View.VISIBLE);
						categoriesList.setVisibility(View.GONE);
						titleBar.hideRightTextOption();
						titleBar.setOnRightTextClickListener(null);
						setTitleBar(titleBar);
					}
				});
			}
		});
		titleBar.setOnLeftClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("Discover Drinks");
		titleBar.hideAddButton();

	}

	@Override
	public void onPause() {
		super.onPause();

		discoverDrinkContainer.setVisibility(View.VISIBLE);
		categoriesList.setVisibility(View.GONE);
	}

	public class CustomComparator implements Comparator<String> {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	}

}
