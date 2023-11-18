package com.nishiket.jmc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nishiket.jmc.R;
import com.nishiket.jmc.model.ComplainModel;

import java.util.ArrayList;
import java.util.List;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainAdapter.viewHolder> {
    private List<ComplainModel> complainList;
    Context context;

    public ComplainAdapter(Context context) {
        this.context = context;
    }
    public void setActiveComplainList(List<ComplainModel> complainList){
        this.complainList = complainList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.complain_design,parent,false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ComplainModel model = complainList.get(position);
        holder.subject.setText(model.getSubject());
        holder.expextedDate.setText(model.getExpectedDate());
        holder.subject.setText(model.getSubject());
        holder.status.setText(model.getStatus());
        holder.details.setText(model.getDetails());
        holder.branch.setText(model.getBranch());
        holder.areaName.setText(model.getAreaName());
        holder.dateOfComplain.setText(model.getDateOfComplain());
        holder.complainNo.setText(model.getComplainNo());
        holder.workDone.setProgress(Integer.parseInt(model.getWorkDone()));
        holder.expendDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expendDown.getVisibility() == View.VISIBLE){
                    holder.expend.setVisibility(View.VISIBLE);
                    holder.expendDown.setVisibility(View.GONE);
                }else {
                    holder.expend.setVisibility(View.GONE);
                    holder.expendDown.setVisibility(View.VISIBLE);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        if(complainList == null){
            return 0;
        }
        else {
            return complainList.size();
        }
    }

    public class viewHolder extends RecyclerView.ViewHolder{
            TextView complainNo,dateOfComplain,wardNo,areaName,branch,subject,details,status,expextedDate;
            ProgressBar workDone;
            ImageView photo,expendDown;
            ImageButton reactionHeart,reactionHappy,reactionAngry,reactionThumb;
            LinearLayout expend;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            complainNo = itemView.findViewById(R.id.complainNo);
            dateOfComplain = itemView.findViewById(R.id.dateOfComplain);
            wardNo = itemView.findViewById(R.id.areaName);
            areaName = itemView.findViewById(R.id.areaName);
            branch = itemView.findViewById(R.id.branch);
            subject = itemView.findViewById(R.id.subject);
            details = itemView.findViewById(R.id.details);
            status = itemView.findViewById(R.id.status);
            expextedDate = itemView.findViewById(R.id.expectedDate);
            workDone = itemView.findViewById(R.id.workDone);
            photo = itemView.findViewById(R.id.photo);
            reactionHeart = itemView.findViewById(R.id.reactionHeart);
            reactionHappy = itemView.findViewById(R.id.reactionHappy);
            reactionAngry = itemView.findViewById(R.id.reactionAngry);
            reactionThumb = itemView.findViewById(R.id.reactionThumb);
            expendDown = itemView.findViewById(R.id.expendImg);
            expend = itemView.findViewById(R.id.expend);

        }
    }
}

