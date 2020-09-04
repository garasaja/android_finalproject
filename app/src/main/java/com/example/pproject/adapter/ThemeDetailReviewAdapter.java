package com.example.pproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pproject.R;
import com.example.pproject.model.ThemeReview;
import com.example.pproject.model.dto.ReviewRespDto;
import com.example.pproject.view.DetailStoreActivity;

import java.util.ArrayList;
import java.util.List;

public class ThemeDetailReviewAdapter extends RecyclerView.Adapter<ThemeDetailReviewAdapter.MyViewHolder> {
    private static final String TAG = "ThemeDetailReviewAdapter";
    private List<ThemeReview> themeReviews = new ArrayList<>();
    private DetailStoreActivity detailStoreActivity;

    public ThemeDetailReviewAdapter() {
    }

    public ThemeDetailReviewAdapter(DetailStoreActivity detailStoreActivity) {
        this.detailStoreActivity = detailStoreActivity;
    }

    public void addItem(ThemeReview themeReview) {
        themeReviews.add(themeReview);
    }

    public void addItems(List<ThemeReview> themeReviews) {
        this.themeReviews = themeReviews;
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
        ThemeReview themeReview = themeReviews.get(position);
        holder.storeDetailReviewId.setText(themeReview.getUserEmail());
        //holder.storeDetailReviewDay.setText(reviewRespDto.);
        holder.storeDetailReviewPoint.setText(themeReview.getRating());
        holder.storeDetailReviewContent.setText(themeReview.getContent());
    }

    @Override
    public int getItemCount() {
        return themeReviews.size();
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
