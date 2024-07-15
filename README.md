# MyCustomizeRecyclerView Library for Android

## This Library Made During "Advance Android Development" Course.

## About The Library:
  MyCustomizeRecyclerView is a customizable RecyclerView library for Android that simplifies
  RecyclerView setup and enhances functionality with built-in support for pagination, item
  animations, drag-and-drop, swipe gestures, and various layout managers.

### Features:
  * Pagination Support: Easily implement pagination in RecyclerViews with a listener interface.
  * Item Animations: Add smooth animations to RecyclerView item changes.
  * Drag-and-Drop Support: Enable drag-and-drop functionality within RecyclerViews.
  * Swipe Gestures: Implement swipe gestures for RecyclerView items.
  * Flexible Layout Managers: Support for LinearLayoutManager, GridLayoutManager, and
    StaggeredGridLayoutManager.
  * Item Decoration: Optional divider item decoration for better visual separation.
  * State Saving: Save and restore RecyclerView scroll position across configuration changes.

### Installation:
  To integrate MyCustomizeRecyclerView into your Android project add the library dependency to your app module's build.gradle:

```java
dependencies {
    implementation(project(":MyCustomizeRecycleViewLibrary"))
}

```

### Usage:
Add MyCustomizeRecyclerView to your layout XML:

```xml
    <com.example.mycustomizerecycleviewlibrary.MyCustomizeRecyclerView
        android:id="@+id/myRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

Initialize in your Activity or Fragment:

```java
public class MainActivity extends AppCompatActivity {
    private MyCustomizeRecyclerView myRecyclerView;
    private MyAdapter myAdapter;
    private List<String> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myRecyclerView = findViewById(R.id.myRecyclerView);

        // Prepare data
        testList = new ArrayList<>();

        // Set up adapter
        myAdapter = new MyAdapter(testList);
        myRecyclerView.setAdapter(myAdapter);
        
    }
```


### Configurations Example:

```java
// Configure RecyclerView
        myRecyclerView.addLinearLayoutManager(true); // LinearLayoutManager, vertical

        myRecyclerView.addDividerItemDecoration(true); // DividerItemDecoration, vertical

        myRecyclerView.addItemAnimation(300); // Item animations with duration 300ms

        myRecyclerView.addGridLayoutManager(true, 2); // Set grid layout
        

```

dragAndDrop

```java
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

```

Swipe Gestures

```java
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
```

Pagination Support

```java
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
```

Save Scroll Position

```java
@Override
    protected void onPause() {
        super.onPause();
        recyclerViewState = myRecyclerView.saveScrollPosition();
    }
```


Restore Scroll Position

```java
@Override
    protected void onResume() {
        super.onResume();
        if (recyclerViewState != null) {
            myRecyclerView.restoreScrollPosition(recyclerViewState);
        }
    }
```

### Video Example:

https://github.com/user-attachments/assets/6a7bc67d-8122-4e61-9fb5-85f97fe920eb



### License:





