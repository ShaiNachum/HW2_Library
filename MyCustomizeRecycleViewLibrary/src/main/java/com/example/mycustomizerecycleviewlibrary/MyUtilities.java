package com.example.mycustomizerecycleviewlibrary;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MyUtilities {
    // Utility method for adding item animations to RecyclerView
    public static void addItemAnimation(RecyclerView recyclerView, long duration) {
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(duration);
        animator.setRemoveDuration(duration);
        recyclerView.setItemAnimator(animator);
    }

    // Utility method for adding drag-and-drop support to RecyclerView
    public static void addDragAndDropSupport(RecyclerView recyclerView, ItemTouchHelper.Callback callback) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Utility method for adding swipe gestures to RecyclerView
    public static void addSwipeGestures(RecyclerView recyclerView, ItemTouchHelper.SimpleCallback callback) {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Utility method for adding a custom layout manager to RecyclerView
    public static void addCustomLayoutManager(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public static void addPaginationSupport(RecyclerView recyclerView, final PaginationListener listener) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                    if (!listener.isLoading() && !listener.isLastPage()) {
                        if (lastVisibleItemPosition == totalItemCount - 1) {
                            listener.loadMoreItems();
                        }
                    }
                }
            }
        });
    }

    // Interface for implementing pagination functionality
    public interface PaginationListener {
        void loadMoreItems();

        boolean isLoading();

        boolean isLastPage();
    }
}
