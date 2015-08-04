package com.pyntail.somabar.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import butterknife.ButterKnife;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.components.IndexableListView;
import com.pyntail.somabar.components.StringMatcher;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.ui.views.TitleBar;

public class AddIngredientsFragment extends BaseSupportFragment {

	private ArrayList<String> mItems;
	private IndexableListView mListView;
	private boolean isFromDiscover;

	private View parentView;

	public static AddIngredientsFragment newInstance(
			final boolean isFromDiscover) {
		return new AddIngredientsFragment(isFromDiscover);
	}

	public AddIngredientsFragment(final boolean isFromDiscover) {
		this.isFromDiscover = isFromDiscover;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_add_ingredients,
					container, false);
			ButterKnife.inject(this, parentView);

		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initList();
	}

	private void initList() {
		mItems = new ArrayList<String>();
		mItems.add("Lime");
		mItems.add("Vodka");
		mItems.add("Mineral water");
		mItems.add("Wild Cherry Extract");
		mItems.add("Orange Juice");
		mItems.add("Preservatives");
		mItems.add("Flavors");
		mItems.add("Sweeteners");
		mItems.add("Intense sweeteners");
		mItems.add("Color");
		mItems.add("Natural flavor");
		mItems.add("Cola");
		mItems.add("Soda");
		mItems.add("Concentrated orange juice");
		mItems.add("Carbonated water");
		mItems.add("Sugar/Glucose-fructose");
		mItems.add("Citric acid");
		mItems.add("Modified corn starch");
		mItems.add("Sodium benzoate");
		mItems.add("Potassium sorbate");
		mItems.add("Sucrose acetate isobutyrate");
		mItems.add("Sodium citrate");
		Collections.sort(mItems);
		ContentAdapter adapter = new ContentAdapter(activtyReference,
				R.layout.item_add_ingredients, mItems);

		mListView = (IndexableListView) parentView.findViewById(R.id.listview);
		mListView.setAdapter(adapter);
		mListView.setFastScrollEnabled(true);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(isFromDiscover)
				{
					activtyReference.addDockableSupportFragmentWithAnim(
							EditDrinkFragment.newInstance(),
							EditDrinkFragment.class.getSimpleName());
					
				}
				else
				{
					activtyReference.onBackPressed();
					//activtyReference.addDockableSupportFragmentWithAnim(PostDrinkFragment.newInstance(), PostDrinkFragment.class.getSimpleName());
					
				}
				
			}
		});
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		activtyReference = (MainActivity) activity;
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
		titleBar.setHeadingText("Ingredient");
		titleBar.hideRightButton();
		titleBar.hideLeftButton();
		titleBar.hideAddButton();

	}

	private class ContentAdapter extends ArrayAdapter<String> implements
			SectionIndexer {

		private String mSections = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		public ContentAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public int getPositionForSection(int section) {
			for (int i = section; i >= 0; i--) {
				for (int j = 0; j < getCount(); j++) {
					if (i == 0) {
						// For numeric section
						for (int k = 0; k <= 9; k++) {
							if (StringMatcher.match(
									String.valueOf(getItem(j).charAt(0)),
									String.valueOf(k)))
								return j;
						}
					} else {
						if (StringMatcher.match(
								String.valueOf(getItem(j).charAt(0)),
								String.valueOf(mSections.charAt(i))))
							return j;
					}
				}
			}
			return 0;
		}

		@Override
		public int getSectionForPosition(int position) {
			return 0;
		}

		@Override
		public Object[] getSections() {
			String[] sections = new String[mSections.length()];
			for (int i = 0; i < mSections.length(); i++)
				sections[i] = String.valueOf(mSections.charAt(i));
			return sections;
		}
	}
}
