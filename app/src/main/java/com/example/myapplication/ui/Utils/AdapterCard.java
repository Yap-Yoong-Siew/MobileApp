package com.example.myapplication.ui.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.ViewHolder> {
    private ArrayList<CategoryManage> dataModalArrayList;
    private Context context;

    // constructor class for our Adapter
    public AdapterCard(ArrayList<CategoryManage> dataModalArrayList, Context context) {
        this.dataModalArrayList = dataModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCard.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCard.ViewHolder(LayoutInflater.from(context).inflate(R.layout.worker_cat_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCard.ViewHolder holder, int position) {
        // setting data to our views in Recycler view items.
        final CategoryManage modal = dataModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getText());

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return dataModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView courseNameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing the views of recycler views.
            courseNameTV = itemView.findViewById(R.id.idTVtext);
        }
    }
}

