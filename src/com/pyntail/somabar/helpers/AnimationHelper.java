package com.pyntail.somabar.helpers;


import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.pyntail.somabar.R;


public class AnimationHelper {
	
	
	public static Animation getScaleDownToHalfAnimation(Context context){
		return AnimationUtils.loadAnimation(context, R.anim.scale_down_half);
	}
	
	public static Animation getScaleUpAnimaation(Context context){
		return AnimationUtils.loadAnimation(context, R.anim.scale_up);
	}
	
	public static void animateRise( final ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 250 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, -1.0f );
		animation.setDuration( 500 );
		set.addAnimation( animation );
		
		animation.setAnimationListener( new AnimationListener() {
			
			@Override
			public void onAnimationStart( Animation animation ) {
			}
			
			@Override
			public void onAnimationRepeat( Animation animation ) {
			}
			
			@Override
			public void onAnimationEnd( Animation animation ) {
				mLayout.setVisibility( View.INVISIBLE );
			}
		} );
		
		mLayout.startAnimation( set );
		
	}
	
	public static void animateFall( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 250 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		animation.setDuration( 500 );
		set.addAnimation( animation );
		
		mLayout.startAnimation( set );
		
	}
	
	public static void animateLayoutSlideDown( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 250 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		animation.setDuration( 150 );
		set.addAnimation( animation );
		
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.25f );
		mLayout.setLayoutAnimation( controller );
		
	}
	
	public static void animateLayoutSlideToRight( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 750 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		
		animation.setDuration( 750 );
		set.addAnimation( animation );
		
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.25f );
		mLayout.setLayoutAnimation( controller );
		
	}
	
	public static void animateLayoutSlideFromRight( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 750 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		
		animation.setDuration( 750 );
		set.addAnimation( animation );
		
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.25f );
		mLayout.setLayoutAnimation( controller );
		
	}
	
	public static void animateLayoutSlideToLeft( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 750 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		
		animation.setDuration( 750 );
		set.addAnimation( animation );
		
		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.25f );
		mLayout.setLayoutAnimation( controller );
		
	}
	
	public static void animateFromRight( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 250 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		animation.setDuration( 500 );
		set.addAnimation( animation );
		
		mLayout.startAnimation( set );
		
	}
	
	public static void animateToRight( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		Animation animation = new AlphaAnimation( 0.0f, 1.0f );
		animation.setDuration( 250 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		animation.setDuration( 500 );
		set.addAnimation( animation );
		
		mLayout.startAnimation( set );
		
	}
	
	public static void animateScaleUp( ViewGroup mLayout ) {
		
		AnimationSet set = new AnimationSet( true );
		
		
		//scale animation
		Animation animation = new ScaleAnimation(1, 1.5f, 1, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration( 250 );
		set.addAnimation( animation );
		
		animation = new TranslateAnimation( Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, 0.0f );
		animation.setDuration( 500 );
		set.addAnimation( animation );
		
		//start animation
		mLayout.startAnimation( set );
		
	}
	
	/**
	 * 
	 * 
	 **/
	
	// for the previous movement
	public static Animation inFromRightAnimation( long animtime ) {
		
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f );
		inFromRight.setDuration( animtime );
		inFromRight.setInterpolator( new AccelerateInterpolator() );
		return inFromRight;
	}
	
	public static Animation inFromBottomAnimation( long animtime ) {
		
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f );
		inFromRight.setDuration( animtime );
		inFromRight.setInterpolator( new AccelerateInterpolator() );
		return inFromRight;
	}
	
	public static Animation inFromTopAnimation( long animtime ) {
		
		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f );
		inFromRight.setDuration( animtime );
		inFromRight.setInterpolator( new AccelerateInterpolator() );
		return inFromRight;
	}
	
	public static Animation outToLeftAnimation( long animtime ) {
		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f );
		outtoLeft.setDuration( animtime );
		outtoLeft.setInterpolator( new AccelerateInterpolator() );
		return outtoLeft;
	}
	
	// for the next movement
	public static Animation inFromLeftAnimation( long animtime ) {
		Animation inFromLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f );
		inFromLeft.setDuration( animtime );
		inFromLeft.setInterpolator( new AccelerateInterpolator() );
		return inFromLeft;
	}
	
	public static Animation outToRightAnimation( long animtime ) {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f );
		outtoRight.setDuration( animtime );
		outtoRight.setInterpolator( new AccelerateInterpolator() );
		return outtoRight;
	}
	
	public static Animation outToTopAnimation( long animtime ) {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f );
		outtoRight.setDuration( animtime );
		outtoRight.setInterpolator( new AccelerateInterpolator() );
		return outtoRight;
	}
	
	public static Animation outToBottomAnimation( long animtime ) {
		Animation outtoRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, +1.0f );
		outtoRight.setDuration( animtime );
		outtoRight.setInterpolator( new AccelerateInterpolator() );
		return outtoRight;
	}
	
	


	public static void showWithAlpaAnimation(final View view,int duration) {
		AlphaAnimation animation = new AlphaAnimation(0, 1);
		animation.setDuration(duration);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationStart(Animation animation) {			
					view.setVisibility(View.VISIBLE);
					}
		});
		view.startAnimation(animation);

	}
	
	public static AlphaAnimation getClickEffectAnimation()
	{
		return new AlphaAnimation(1F, 0.8F);
		
	}
	
	
	public static void showWithAlpaAnimation(final ViewGroup view,int duration) {
		AlphaAnimation animation = new AlphaAnimation(0, 1);
		animation.setDuration(duration);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationStart(Animation animation) {			
					view.setVisibility(View.VISIBLE);
					}
		});
		view.startAnimation(animation);

	}

	public static void hideWithAlpaAnimation(final View view,final int duration) {
		AlphaAnimation animation = new AlphaAnimation(1, 0);
		animation.setDuration(duration);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				
					view.setVisibility(View.GONE);
				
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
		view.startAnimation(animation);

	}

	public void expandImageAnimation(Activity activity,
			final ImageView targetImageView) {

		int startingWidth, startingHeight, startingXValue, finalWidth, finalHeight, finalXValue = 0;

		final Point p = new Point();
		activity.getWindowManager().getDefaultDisplay().getSize(p);

		startingWidth = targetImageView.getWidth();
		startingHeight = targetImageView.getHeight();
		startingXValue = (int) targetImageView.getX();

		finalWidth = p.x / 2;

		finalHeight = startingHeight + 400;

		final ValueAnimator vaW = ValueAnimator
				.ofInt(startingWidth, finalWidth);

		vaW.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int newWidth = (Integer) vaW.getAnimatedValue();

				ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) targetImageView
						.getLayoutParams();
				lp.width = newWidth;
				targetImageView.setLayoutParams(lp);
			}
		});

		final ValueAnimator vaH = ValueAnimator.ofInt(startingHeight,
				finalHeight);

		vaW.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				int newHeight = (Integer) vaH.getAnimatedValue();

				ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) targetImageView
						.getLayoutParams();
				lp.height = newHeight;
				targetImageView.setLayoutParams(lp);
			}
		});

		ObjectAnimator oa = ObjectAnimator.ofFloat(targetImageView, "X",
				startingXValue, finalXValue);
		AnimatorSet as = new AnimatorSet();
		as.playTogether(vaW, vaH, oa);
		as.setDuration(1200);

		as.start();
	}

	
	
	
	
	
	
	
	
}
