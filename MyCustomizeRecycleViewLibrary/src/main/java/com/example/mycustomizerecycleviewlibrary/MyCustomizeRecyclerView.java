package com.example.mycustomizerecycleviewlibrary;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class MyCustomizeRecyclerView extends RecyclerView{
    public MyCustomizeRecyclerView(Context context) {
        super(context);
        initializeRecyclerView();
    }

    public MyCustomizeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeRecyclerView();
    }

    public MyCustomizeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        addLinearLayoutManager(true); // default
        setAdapter(getAdapter());
    }

    private void addItemDecoration() {
        addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    public void addPaginationSupport(final MyUtilities.PaginationListener paginationListener) {
        MyUtilities.addPaginationSupport(this, paginationListener);
    }

    public void addItemAnimation(long duration) {
        MyUtilities.addItemAnimation(this, duration);
    }

    public void addDragAndDropSupport(ItemTouchHelper.Callback callback) {
        MyUtilities.addDragAndDropSupport(this, callback);
    }

    public void addSwipeGestures(ItemTouchHelper.SimpleCallback simpleCallback) {
        MyUtilities.addSwipeGestures(this, simpleCallback);
    }

    public void addLinearLayoutManager(boolean isVertical) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), getOrientation(isVertical), false);
        setLayoutManager(linearLayoutManager);
    }

    public void addGridLayoutManager(boolean isVertical, int spanCount) {
        if (spanCount <= 0) return;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), spanCount, getOrientation(isVertical), false);
        setLayoutManager(gridLayoutManager);
    }


    public Parcelable saveScrollPosition() {
        if (getLayoutManager() == null)
            return null;
        return getLayoutManager().onSaveInstanceState();
    }

    public void restoreScrollPosition(Parcelable state) {
        if (getLayoutManager() == null)
            return;
        getLayoutManager().onRestoreInstanceState(state);
    }

    public void addDividerItemDecoration(boolean isVertical) {
        int orientation = isVertical ? DividerItemDecoration.VERTICAL : DividerItemDecoration.HORIZONTAL;
        addItemDecoration(new DividerItemDecoration(getContext(), orientation));
    }

    private int getOrientation(boolean isVertical) {
        return isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL;
    }
}
