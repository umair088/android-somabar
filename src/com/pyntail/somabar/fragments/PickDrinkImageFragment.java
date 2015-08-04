package com.pyntail.somabar.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.recycler.AbsViewHolderAdapter;
import com.pyntail.somabar.recycler.SimpleAdapter;
import com.pyntail.somabar.recycler.AbsViewHolderAdapter.OnItemClickListener;
import com.pyntail.somabar.ui.viewholder.DrinkViewHolder;
import com.pyntail.somabar.ui.viewholder.PickImageViewHolder;

public class PickDrinkImageFragment extends BaseSupportFragment {
	
	@InjectView(R.id.recycleView)
	public RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private SimpleAdapter<MyDrinkItem> myDrinksAdapter;
	private View parentView;
	
	
	
	public static PickDrinkImageFragment newInstance() {
		return new PickDrinkImageFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_pick_image, container,
					false);
			ButterKnife.inject(this, parentView);

		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecycleViews();
	}
	
	public void initRecycleViews()
	{
		layoutManager = new GridLayoutManager(activtyReference, 2);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);

		recyclerView.setLayoutManager(layoutManager);
		layoutManager.canScrollVertically();
		
		
		
		myDrinksAdapter = new SimpleAdapter<MyDrinkItem>(
				R.layout.item_pick_image, PickImageViewHolder.class,
				generateRandomDrinks());
		recyclerView.setAdapter(myDrinksAdapter);
		
		
		myDrinksAdapter.setOnItemClickListener(new OnItemClickListener<MyDrinkItem>() {

			@Override
			public void onItemClick(AbsViewHolderAdapter<MyDrinkItem> parent,
					View view, MyDrinkItem object, int position) {
				
				activtyReference.onBackPressed();
			}
		});
		
	}
	
	
	private List<MyDrinkItem> generateRandomDrinks() {
		List<MyDrinkItem> drinkList = new ArrayList<MyDrinkItem>();
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg2,"Lime"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg1,"Grape"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg3,"Lime"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg5,"Vodka"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg2,"Lime"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg2,"Lime"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg4,"Grape"));
		drinkList.add(new MyDrinkItem("Gin Tonic", R.drawable.post_drinkimg3,"Lime"));
		
		return drinkList;
	}
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		activtyReference = (MainActivity) activity;
		

	}

}
