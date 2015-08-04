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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.MyDrinkItem;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.Applog;
import com.pyntail.somabar.recycler.SimpleAdapter;
import com.pyntail.somabar.ui.viewholder.CommunityViewHolder;
import com.pyntail.somabar.ui.viewholder.DrinkViewHolder;
import com.pyntail.somabar.ui.views.TitleBar;

public class CommunityFragment extends BaseSupportFragment implements OnClickListener{

	
	
	
	private final String TAG = this.getClass().getSimpleName();
	private View parentView;
	
	@InjectView(R.id.recycleView)
	public RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private SimpleAdapter<MyDrinkItem> myDrinksAdapter;

	
	@InjectView(R.id.btnCreateNewDrink)
	ImageButton btnCreateNewDrink;
	

	PostDrinkFragment postDrinkFragment;
	public static CommunityFragment newInstance() {
		return new CommunityFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initFragment();
	}
	
	private void initFragment() {
		postDrinkFragment = PostDrinkFragment.newInstance();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_community, container,
					false);
			ButterKnife.inject(this, parentView);

		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}
	
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		btnCreateNewDrink.setOnClickListener(this);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		activtyReference = (MainActivity) activity;
		

	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initRecycleViews();
	}
	
	public void initRecycleViews()
	{
		LinearLayoutManager layoutManager = new LinearLayoutManager(activtyReference);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);
		layoutManager.canScrollVertically();
		recyclerView.setLayoutManager(layoutManager);
		
		myDrinksAdapter = new SimpleAdapter<MyDrinkItem>(R.layout.item_community_view,
				CommunityViewHolder.class, generateRandomDrinks());
		
		recyclerView.setAdapter(myDrinksAdapter);
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

		titleBar.setHeaderColor(this.getResources().getColor(R.color.milky_white));
		titleBar.setLeftButtonIcon(R.drawable.menu_icon_orange);
		titleBar.setOnLeftClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				activtyReference.openMenu();
			}
		});
		titleBar.setHeadingText("Community");
		titleBar.hideRightButton();
		titleBar.hideAddButton();

	}

	@Override
	public void onClick(View v) {
		
		
		switch (v.getId()) {
		case R.id.btnCreateNewDrink:
			Applog.Debug(TAG, "onPostNewDrink");
			activtyReference.addDockableSupportFragmentWithAnim(postDrinkFragment, postDrinkFragment.getClass().getSimpleName());
			break;

		default:
			break;
		}
		
	}
}
