package com.example.laundrite.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrite.R;
import com.example.laundrite.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {
    private Context ctx;
    private List<CategoryModel> categoryModels;

    public CategoryAdapter(Context ctx, List<CategoryModel> categoryModels) {
        this.ctx = ctx;
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_categories,parent,false);
                return new CategoryAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryImage;
        private ImageView categoryTitle;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.iv_categoryImage);
            categoryTitle = itemView.findViewById(R.id.iv_categoryTitle);
        }
    }
}
