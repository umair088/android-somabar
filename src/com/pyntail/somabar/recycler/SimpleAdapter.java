package com.pyntail.somabar.recycler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.widget.Filter;
import android.widget.Filterable;

import com.pyntail.somabar.entities.MyDrinkItem;

/**
 * A concrete AbsViewHolderAdapter that give you a list of homogeneous ViewHolder
 * @param <T> the king of object into this adapter
 */
public class SimpleAdapter<T> extends AbsViewHolderAdapter<T> implements Filterable{

    private Class<? extends AbsViewHolder<? extends T>> mViewHolderClass;

    private int mLayoutResId;
    private List<MyDrinkItem> orig;

    /**
     * Constructor
     * @param layoutResId layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects the objects to put in this adapter
     */
    public SimpleAdapter(int layoutResId,
            Class<? extends AbsViewHolder<? extends T>> viewHolderClass, T... objects) {
        this(layoutResId, viewHolderClass, new ArrayList<T>(Arrays.asList(objects)));
    }

    /**
     * Constructor
     * @param layoutResId layout resource id to inflate for all objects in this adapter
     * @param viewHolderClass the view holder class to instantiate for all objects
     * @param objects the objects to put in this adapter
     */
    public SimpleAdapter(int layoutResId,
            Class<? extends AbsViewHolder<? extends T>> viewHolderClass, List<T> objects) {
        super(objects);
        mViewHolderClass = viewHolderClass;
        mLayoutResId = layoutResId;
    }

    @Override
    protected Class<? extends AbsViewHolder<? extends T>> getViewHolderClass(int viewType) {
        return mViewHolderClass;
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return mLayoutResId;
    }

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Filter getFilter() {
		return new Filter() {
			
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				
			}
			
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				final FilterResults oReturn =new FilterResults();
				final List<MyDrinkItem> results = new ArrayList<MyDrinkItem>();
				if(orig==null)
				{
					orig = items;
					if(constraint !=null)
					{
						if(orig!=null && orig.size()>0)
						{
							
							for(final MyDrinkItem g : orig)
							{
								if(g.getTitleOfProduct().toLowerCase().contains(constraint.toString()))
									results.add(g);
								
							}
						}
						oReturn.values = results;
					}
					return oReturn;
				}
				
			}
		};
		
	}*/
}
