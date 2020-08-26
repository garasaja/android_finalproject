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
import com.example.pproject.model.dto.ReviewRespDto;
import com.example.pproject.view.DetailStoreActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class StoreDetailReviewAdapter extends RecyclerView.Adapter<StoreDetailReviewAdapter.MyViewHolder> {
    private static final String TAG = "StoreDetailReviewAdapter";
    private List<ReviewRespDto> reviewRespDtos = new ArrayList<>();
    private DetailStoreActivity detailStoreActivity;

    public StoreDetailReviewAdapter() {
    }

    public StoreDetailReviewAdapter(DetailStoreActivity detailStoreActivity) {
        this.detailStoreActivity = detailStoreActivity;
    }

    public void addItem(ReviewRespDto reviewRespDto) {
        reviewRespDtos.add(reviewRespDto);
    }

    public void addItems(List<ReviewRespDto> reviewRespDtos) {
        this.reviewRespDtos = reviewRespDtos;
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
        ReviewRespDto reviewRespDto = reviewRespDtos.get(position);
        holder.storeDetailReviewId.setText(reviewRespDto.getId());
        //holder.storeDetailReviewDay.setText(reviewRespDto.);
        holder.storeDetailReviewPoint.setText(reviewRespDto.getRating());
        holder.storeDetailReviewContent.setText(reviewRespDto.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewRespDtos.size();
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
