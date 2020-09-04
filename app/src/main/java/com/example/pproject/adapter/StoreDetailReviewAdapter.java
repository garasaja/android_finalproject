package com.example.pproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.StoreReview;
import com.example.pproject.model.dto.ReviewRespDto;
import com.example.pproject.view.DetailStoreActivity;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailReviewAdapter extends RecyclerView.Adapter<StoreDetailReviewAdapter.MyViewHolder> {
    private static final String TAG = "StoreDetailReviewAdapter";
    private List<StoreReview> storeReviews = new ArrayList<>();
    private DetailStoreActivity detailStoreActivity;

    public StoreDetailReviewAdapter() {
    }

    public StoreDetailReviewAdapter(DetailStoreActivity detailStoreActivity) {
        this.detailStoreActivity = detailStoreActivity;
    }

    public void addItem(StoreReview storeReview) {
        storeReviews.add(storeReview);
    }

    public void addItems(List<StoreReview> storeReviews) {
        this.storeReviews = storeReviews;
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
        StoreReview storeReview = storeReviews.get(position);
        holder.storeDetailReviewId.setText(storeReview.getUserEmail());
        //holder.storeDetailReviewDay.setText(reviewRespDto.);
        holder.storeDetailReviewPoint.setText(storeReview.getRating());
        holder.storeDetailReviewContent.setText(storeReview.getContent());
    }

    @Override
    public int getItemCount() {
        return storeReviews.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView storeDetailReviewId;
        private TextView storeDetailReviewDay;
        private TextView storeDetailReviewPoint;
        private TextView storeDetailReviewContent;

        public MyViewHolder(final View itemView) {
            super(itemView);

            storeDetailReviewId = itemView.findViewById(R.id.store_detail_review_id);
            storeDetailReviewDay = itemView.findViewById(R.id.store_detail_review_day);
            storeDetailReviewPoint = itemView.findViewById(R.id.store_detail_review_point);
            storeDetailReviewContent = itemView.findViewById(R.id.store_detail_review_content);

        }

    }
}
