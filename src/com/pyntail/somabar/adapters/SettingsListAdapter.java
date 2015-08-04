package com.pyntail.somabar.adapters;

import java.util.ArrayList;

import com.pyntail.somabar.R;
import com.pyntail.somabar.ui.views.AnyTextView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 *
 * @author Lauri Nevala
 * 
 * Based on example by LAURA SUCIU
 * @see http://myandroidsolutions.blogspot.com/2012/08/android-expandable-list-example.html
 *
 */

public class SettingsListAdapter extends BaseExpandableListAdapter {
 
 
    private LayoutInflater inflater;
    private ArrayList<Category> mParent;
    private ExpandableListView accordion;
    public int lastExpandedGroupPosition;    
    
 
    public SettingsListAdapter(Context context, ArrayList<Category> parent, ExpandableListView accordion) {
        mParent = parent;        
        inflater = LayoutInflater.from(context);
        this.accordion = accordion;       
        
	}
 
 
    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }
 
    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).children.size();
    }
 
    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i);
    }
 
    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).children.get(i1);
    }
 
    @Override
    public long getGroupId(int i) {
        return i;
    }
 
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
 
    @Override
    public boolean hasStableIds() {
        return true;
    }
 
    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int i, boolean isExpanded, View view, ViewGroup viewGroup) {
    	
        if (view == null) {
            view = inflater.inflate(R.layout.settings_list_item_parent, viewGroup,false);
        }
        
        
        if (isExpanded) {
        	view.findViewById(R.id.parentIndicator).setBackgroundResource(R.drawable.filter_arrow_down);
        } else {
        	view.findViewById(R.id.parentIndicator).setBackgroundResource(R.drawable.filter_arrow_forward);
        }
        
        
        // set category name as tag so view can be found view later
        
        Category cat = (Category)getGroup(i);
        view.setTag(cat.toString());
        
        
        AnyTextView parentCounter = (AnyTextView)view.findViewById(R.id.parentCounter);
        
        parentCounter.setText(cat.getCounter());
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        
        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());
        
        TextView sub = (TextView) view.findViewById(R.id.list_item_text_subscriptions);        
        
        if(mParent.get(i).selection.size()>0) {
        	sub.setText(mParent.get(i).selection.toString());
        }
        else {
        	sub.setText("");
        }
        
        //return the entire view
        return view;
    }
    
 
    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.settings_list_item_child, viewGroup,false);
        }
 
        
        final CheckBox textView = (CheckBox) view.findViewById(R.id.list_item_text_child);
        AnyTextView txtChild = (AnyTextView)view.findViewById(R.id.list_item_child_view);
        AnyTextView childCounter = (AnyTextView)view.findViewById(R.id.childCounter);
        
        
        //"i" is the position of the parent/group in the list and 
        //"i1" is the position of the child
       // textView.setText(mParent.get(i).children.get(i1).name);        
 
        Category cat = (Category)mParent.get(i);
        txtChild.setText(cat.children.get(i1).name);
        childCounter.setText(cat.children.get(i1).counter);
        /*
        childCounter.setText(cat.getCounter());*/
        // set checked if parent category selection contains child category
        if(mParent.get(i).selection.contains(textView.getText().toString())) {
    		textView.setChecked(true);
        }
        else {
        	textView.setChecked(false);
        }
        
        //return the entire view
        return view;
    }
 
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    
    @Override
    /**
     * automatically collapse last expanded group
     * @see http://stackoverflow.com/questions/4314777/programmatically-collapse-a-group-in-expandablelistview
     */    
    public void onGroupExpanded(int groupPosition) {
    	
    	if(groupPosition != lastExpandedGroupPosition){
            accordion.collapseGroup(lastExpandedGroupPosition);
        }
    	
        super.onGroupExpanded(groupPosition);
     
        lastExpandedGroupPosition = groupPosition;
        
    }
    
    
    
}

