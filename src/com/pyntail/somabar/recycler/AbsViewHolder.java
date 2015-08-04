package com.pyntail.somabar.recycler;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;

public abstract class AbsViewHolder<T> extends RecyclerView.ViewHolder {

    private SparseArray<SparseArray<View>> mSparseSparseArrayView
            = new SparseArray<SparseArray<View>>();

    private View mView;

    private T mObject;

    public AbsViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    /**
     * Method called when we need to update the view hold by this class.
     *
     * @param object the object subject of this update
     */
    public void onBindView(Object object) {
        mObject = (T) object;
        updateView(mView.getContext(), mObject);
    }

    /**
     * Method called when we need to update the view hold by this class.
     *
     * @param context context of the root view
     * @param object  the object subject of this update
     */
    protected abstract void updateView(Context context, T object);

    /**
     * Get the last object set to this viewholder
     */
    public T getObject() {
        return mObject;
    }

    /**
     * Get the root view for the viewholder (the one passed into the constructor)
     *
     * @return The viewholder's root view, or null if it has no layout.
     */
    public View getView() {
        return mView;
    }

    /**
     * Returns the context the view is running in, through which it can
     * access the current theme, resources, etc.
     *
     * @return The view's Context.
     */
    public Context getContext() {
        return mView.getContext();
    }

    /**
     * Returns the resources associated with this view.
     *
     * @return Resources object.
     */
    public Resources getResources() {
        return mView.getResources();
    }

    /**
     * Called when a view created by the adapter has been recycled.
     */
    public void onViewRecycled() {
    }

    /**
     * Called when a view created by the adapter has been attached to a window.
     */
    public void onViewAttachedToWindow() {
    }

    /**
     * Called when a view created by the adapter has been detached from its window.
     */
    public void onViewDetachedFromWindow() {
    }

    /**
     * Determine if a click listener should be automatically added to the view of this view holder
     *
     * @return true you want to have this view clickable
     */
    public boolean isClickable() {
        return true;
    }

    /**
     * Determine if a long click listener should be automatically added to the view of this view
     * holder
     *
     * @return true you want to have this view clickable
     */
    public boolean isLongClickable() {
        return true;
    }

    /**
     * Allow to clear the cache of view retrieved
     */
    public void clearViewsCached() {
        mSparseSparseArrayView.clear();
    }

    /**
     * Allow to clear the cache of view retrieved
     */
    public void clearViewCached(int viewId) {
        clearViewCached(0, viewId);
    }

    /**
     * Allow to clear the cache of view retrieved
     */
    public void clearViewCached(int parentId, int viewId) {
        SparseArray<View> sparseArrayViewsParent = mSparseSparseArrayView.get(parentId);
        if (sparseArrayViewsParent != null) {
            sparseArrayViewsParent.remove(viewId);
        }
    }

    /**
     * Look for a child view with the given id.  If this view has the given
     * id, return this view.
     * <p/>
     * The method is efficient, if you had already called it, it will be faster than a normal
     * "findViewById" thanks to a cache system
     *
     * @param id The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    public <T extends View> T findViewByIdEfficient(int id) {
        return (T) findViewByIdEfficient(0, id);
    }

    /**
     * Look for a child view of the parent view id with the given id.  If this view has the given
     * id, return this view.
     * <p/>
     * The method is efficient, if you had already called it, it will be faster than a normal
     * "findViewById" thanks to a cache system
     *
     * @param id The id to search for.
     * @return The view that has the given id in the hierarchy or null
     */
    public <T extends View> T findViewByIdEfficient(int parentId, int id) {
        View viewRetrieve = retrieveFromCache(parentId, id);
        if (viewRetrieve == null) {
            viewRetrieve = findViewById(parentId, id);
            if (viewRetrieve != null) {
                storeView(parentId, id, viewRetrieve);
            }
        }
        return (T) viewRetrieve;
    }

    private void storeView(int parentId, int id, View viewRetrieve) {
        SparseArray<View> sparseArrayViewsParent = mSparseSparseArrayView.get(parentId);
        if (sparseArrayViewsParent == null) {
            sparseArrayViewsParent = new SparseArray<View>();
            mSparseSparseArrayView.put(parentId, sparseArrayViewsParent);
        }
        sparseArrayViewsParent.put(id, viewRetrieve);
    }

    private View findViewById(int parentId, int id) {
        if (parentId == 0) {
            return mView.findViewById(id);
        } else {
            View parent = findViewByIdEfficient(parentId);
            if (parent != null) {
                return parent.findViewById(id);
            } else {
                return null;
            }
        }
    }

    private View retrieveFromCache(int parentId, int id) {
        SparseArray<View> sparseArrayViewsParent = mSparseSparseArrayView.get(parentId);
        if (sparseArrayViewsParent != null) {
            View viewRetrieve = sparseArrayViewsParent.get(id);
            if (viewRetrieve == null) {
                // dead reference
                sparseArrayViewsParent.remove(id);
            } else {
                return viewRetrieve;
            }
        }
        if (parentId == 0) {
            return retrieveFromCache(id);
        } else {
            return null;
        }
    }

    private View retrieveFromCache(int id) {
        for (int i = 0; i < mSparseSparseArrayView.size(); i++) {
            int parentId = mSparseSparseArrayView.keyAt(i);
            if (parentId != 0) {
                View viewRetrieve = retrieveFromCache(parentId, id);
                if (viewRetrieve != null) {
                    return viewRetrieve;
                }
            }
        }
        return null;
    }
    
    
 

}
