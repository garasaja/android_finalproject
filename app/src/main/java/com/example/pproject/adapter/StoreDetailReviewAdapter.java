package com.example.pproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.Review;
import com.example.pproject.model.Theme;
import com.example.pproject.view.DetailStoreActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailReviewAdapter extends RecyclerView.Adapter<StoreDetailReviewAdapter.MyViewHolder> {
    private static final String TAG = "StoreDetailReviewAdapter";
    private List<Review> reviewList = new ArrayList<>();
    private DetailStoreActivity detailStoreActivity;

    public StoreDetailReviewAdapter() {
    }

    public StoreDetailReviewAdapter(DetailStoreActivity detailStoreActivity) {
        this.detailStoreActivity = detailStoreActivity;
    }

    public void addItem(Review review) {
        reviewList.add(review);
    }

    public void addItems(List<Review> reviewList) {
        this.reviewList = reviewList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_store_detail_review,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.storeDetailReviewId.setText(review.getId());
        holder.storeDetailReviewDay.setText(review.getCreateDate().toString());
        holder.storeDetailReviewPoint.setText(review.getRating());
        holder.storeDetailReviewContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView storeDetailReviewId,storeDetailReviewDay,storeDetailReviewPoint,storeDetailReviewContent;

        public MyViewHolder(final View itemView) {
            super(itemView);

            storeDetailReviewId = itemView.findViewById(R.id.store_detail_review_id);
            storeDetailReviewDay = itemView.findViewById(R.id.store_detail_review_day);
            storeDetailReviewPoint = itemView.findViewById(R.id.store_detail_review_point);
            storeDetailReviewContent = itemView.findViewById(R.id.store_detail_review_content);

        }

    }
}
