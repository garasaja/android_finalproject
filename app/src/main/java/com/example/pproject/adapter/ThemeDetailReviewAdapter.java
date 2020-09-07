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
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class ThemeDetailReviewAdapter extends RecyclerView.Adapter<ThemeDetailReviewAdapter.MyViewHolder> {
    private static final String TAG = "ThemeDetailReviewAdapter";
    private List<ThemeReview> themeReviews = new ArrayList<>();
    private Context context;

    public ThemeDetailReviewAdapter() {
    }

    public ThemeDetailReviewAdapter(List<ThemeReview> themeReviews, Context context) {
        this.themeReviews = themeReviews;
        this.context = context;
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
        View view = inflater.inflate(R.layout.item_theme_detail_review,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ThemeReview themeReview = themeReviews.get(position);
        holder.themeDetailReviewId.setText(themeReview.getUserEmail());
        //holder.themeDetailReviewDay.setText(reviewRespDto.);
        holder.themeDetailReviewPoint.setText(themeReview.getRating());
        holder.themeDetailReviewContent.setText(themeReview.getContent());
    }

    @Override
    public int getItemCount() {
        return themeReviews.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView themeDetailReviewId;
        private TextView themeDetailReviewDay;
        private TextView themeDetailReviewPoint;
        private TextView themeDetailReviewContent;

        public MyViewHolder(final View itemView) {
            super(itemView);

            themeDetailReviewId = itemView.findViewById(R.id.theme_detail_review_id);
            themeDetailReviewDay = itemView.findViewById(R.id.theme_detail_review_day);
            themeDetailReviewPoint = itemView.findViewById(R.id.theme_detail_review_point);
            themeDetailReviewContent = itemView.findViewById(R.id.theme_detail_review_content);

        }

    }
}
