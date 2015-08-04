package com.pyntail.somabar.ui.side.menu;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pyntail.somabar.R;

public class ResideMenuItem extends LinearLayout{

   
    /** menu item  title */
    private TextView tv_title;
    
    private TextView tv_title_bold;
    
    
    private View imgSeperator;

    public ResideMenuItem(Context context) {
        super(context);
        initViews(context);
    }

    
    public ResideMenuItem(Context context, String title , boolean isWithSeperator) {
        super(context);
        initViews(context);
        tv_title.setText(title);
        
        if(isWithSeperator)
        	imgSeperator.setVisibility(View.VISIBLE);
        else
        	imgSeperator.setVisibility(View.GONE);
        
    }
    
    
    public ResideMenuItem(Context context, String title , boolean isWithSeperator,boolean isEnabled) {
        super(context);
        initViews(context);
        tv_title.setText(title);
        
        if(isWithSeperator)
        	imgSeperator.setVisibility(View.VISIBLE);
        else
        	imgSeperator.setVisibility(View.GONE);
        
        
        if(!isEnabled)
        {
        	tv_title.setTextColor(context.getResources().getColor(R.color.menu_seperator_color));
        	tv_title.setEnabled(isEnabled);
        	tv_title.setClickable(false);

        }
        
    }
    
    
    public ResideMenuItem(Context context, String title , boolean isWithSeperator,boolean isEnabled, boolean isBold) {
        super(context);
        initViews(context);
        tv_title.setText(title);
        
        if(isWithSeperator)
        	imgSeperator.setVisibility(View.VISIBLE);
        else
        	imgSeperator.setVisibility(View.GONE);
        
        
        if(!isEnabled)
        {
        	tv_title.setTextColor(context.getResources().getColor(R.color.menu_seperator_color));
        	tv_title.setEnabled(isEnabled);
        	tv_title.setClickable(false);

        }
        
        if(isBold)
        {
        	tv_title.setVisibility(View.GONE);
        	tv_title_bold.setVisibility(View.VISIBLE);
        	tv_title_bold.setText(title);
        }
        
    }

    private void initViews(Context context){
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.residemenu_item, this);
        tv_title = (TextView) findViewById(R.id.tv_title);
        imgSeperator = (View) findViewById(R.id.imgSeperator);
        
        tv_title_bold=(TextView) findViewById(R.id.tv_title_bold);
    }

    

    /**
     * set the title with resource
     * ;
     * @param title
     */
    public void setTitle(int title){
        tv_title.setText(title);
    }

    /**
     * set the title with string;
     *
     * @param title
     */
    public void setTitle(String title){
        tv_title.setText(title);
    }
}
