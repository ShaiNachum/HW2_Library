package com.example.hw2_library;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycustomizerecycleviewlibrary.MyCustomizeRecyclerView;
import com.example.mycustomizerecycleviewlibrary.MyUtilities;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyCustomizeRecyclerView myRecyclerView;
    private MyAdapter myAdapter;
    private List<String> testList;
    private Parcelable recyclerViewState;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
    private final int pageSize = 10; // Number of items per page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        // Prepare data
        testList = new ArrayList<>();
        // Initially load the first page
        loadNextPage();

        // Set up adapter
        myAdapter = new MyAdapter(testList);
        myRecyclerView.setAdapter(myAdapter);

        // Configure RecyclerView
        myRecyclerView.addLinearLayoutManager(true); // LinearLayoutManager, vertical

        myRecyclerView.addDividerItemDecoration(true); // DividerItemDecoration, vertical

        myRecyclerView.addItemAnimation(300); // Item animations with duration 300ms

        // Add drag-and-drop support
        ItemTouchHelper.Callback dragAndDropCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                String movedItem = testList.remove(fromPosition);
                testList.add(toPosition, movedItem);
                myAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Do nothing
            }
        };
        myRecyclerView.addDragAndDropSupport(dragAndDropCallback);

        // Add swipe gestures
        ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                testList.remove(position);
                myAdapter.notifyItemRemoved(position);
            }
        };
        myRecyclerView.addSwipeGestures(swipeCallback);

        // Add pagination support
        myRecyclerView.addPaginationSupport(new MyUtilities.PaginationListener() {
            @Override
            public void loadMoreItems() {
                // Load next page of data
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    private void loadNextPage() {
        isLoading = true;
        // Simulated data loading for demonstration
        myRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int start = (currentPage - 1) * pageSize + 1;
                int end = currentPage * pageSize;
                for (int i = start; i <= end; i++) {
                    testList.add("Item " + i);
                }
                myAdapter.notifyDataSetChanged();
                isLoading = false;
                currentPage++;
                checkIfLastPage();

                // Show toast message when new page is loaded
                Toast.makeText(MainActivity.this, "Page " + currentPage + " loaded", Toast.LENGTH_SHORT).show();
            }
        }, 350); // Simulate delay
    }

    private void checkIfLastPage() {
        // Simulate last page condition for demonstration
        if (testList.size() >= 50) { // Assuming 50 is the total number of items to display
            isLastPage = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerViewState = myRecyclerView.saveScrollPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerViewState != null) {
            myRecyclerView.restoreScrollPosition(recyclerViewState);
        }
    }
}
