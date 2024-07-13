package com.example.hw2_library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> dataList;
    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

//    public void addItem(String item) {
//        dataList.add(item);
//        notifyItemInserted(dataList.size() - 1);
//    }

    public void removeItem(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

//    public void updateList(ArrayList<String> pageList) {
//        dataList.addAll(pageList);
//        notifyDataSetChanged();
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView main_LBL_text;
        private ShapeableImageView main_BTN_remove;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main_LBL_text = itemView.findViewById(R.id.main_LBL_text);
            main_BTN_remove = itemView.findViewById(R.id.main_BTN_remove);
        }

        public void bind(int position) {
            main_LBL_text.setText(dataList.get(position));
            main_BTN_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
                }
            });
        }
    }
}