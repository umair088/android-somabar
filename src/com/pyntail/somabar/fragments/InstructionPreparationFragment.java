package com.pyntail.somabar.fragments;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.pyntail.somabar.R;
import com.pyntail.somabar.activities.MainActivity;
import com.pyntail.somabar.entities.request.Instruction;
import com.pyntail.somabar.fragments.base.BaseSupportFragment;
import com.pyntail.somabar.helpers.UIHelper;
import com.pyntail.somabar.ui.views.TitleBar;
import com.squareup.picasso.Picasso;

public class InstructionPreparationFragment extends BaseSupportFragment {

	private View parentView;

	@InjectView(R.id.itxtDetail)
	TextView itxtDetail;
	
	
	@InjectView(R.id.itxtStep)
	TextView itxtStep;
	
	
	
	@InjectView(R.id.iitxtDetail)
	TextView iitxtDetail;
	
	
	@InjectView(R.id.iitxtStep)
	TextView iitxtStep;
	
	
	@InjectView(R.id.iiitxtDetail)
	TextView iiitxtDetail;
	
	
	@InjectView(R.id.iiitxtStep)
	TextView iiitxtStep;
	
	@InjectView(R.id.imgBackground)
	ImageButton imgBackground;
	
	
	List<Instruction> instructionCollection;
	String imgUrl,drinkName;
	private final String TAG = this.getClass().getSimpleName();

	public static InstructionPreparationFragment newInstance(
			List<Instruction> instructionCollection,final String imgUrl,final String drinkName) {
		return new InstructionPreparationFragment(instructionCollection,imgUrl,drinkName);
	}

	private InstructionPreparationFragment(
			List<Instruction> instructionCollection,String imgUrl,String drinkName) {

		this.instructionCollection = instructionCollection;
		this.imgUrl = imgUrl;
		this.drinkName = drinkName;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (parentView == null) {
			parentView = inflater.inflate(R.layout.fragment_preparation,
					container, false);
			ButterKnife.inject(this, parentView);
		} else
			((ViewGroup) parentView.getParent()).removeView(parentView);

		return parentView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setInstruction(this.instructionCollection);
		
		Picasso.with(activtyReference).load(imgUrl).fit().centerCrop()
		.into(imgBackground);
	}

	private void setInstruction(List<Instruction> instructionCollection) {
		
		for (int i = 0; i < instructionCollection.size(); i++) {
			
			Instruction instruction =instructionCollection.get(i);
			if(instruction!=null)
			{
				if(i==0)
				{
					
					itxtStep.setVisibility(View.VISIBLE);
					itxtDetail.setVisibility(View.VISIBLE);
					itxtStep.setText("Step " +instruction.getStep() );
					itxtDetail.setText(instruction.getDetail());
					
				}
				
				
				if(i==1)
				{
					iitxtStep.setVisibility(View.VISIBLE);
					iitxtDetail.setVisibility(View.VISIBLE);
					
					iitxtStep.setText("Step " +instruction.getStep() );
					iitxtDetail.setText(instruction.getDetail());
					
				}
				if(i==2)
				{
					
					iiitxtStep.setVisibility(View.VISIBLE);
					iiitxtDetail.setVisibility(View.VISIBLE);
					iiitxtStep.setText("Step " +instruction.getStep() );
					iiitxtDetail.setText(instruction.getDetail());
					
				}
				
				
			}
			
		}
		
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
		if(drinkName!=null)
		titleBar.setHeadingText(drinkName);
		else
			titleBar.setHeadingText("");
		titleBar.hideAddButton();
		titleBar.hideRightButton();

	}

}
