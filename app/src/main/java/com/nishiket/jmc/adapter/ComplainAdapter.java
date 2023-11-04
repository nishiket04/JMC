package com.nishiket.jmc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private List<ComplainModel> complainList = new ArrayList<>();
    Context context;

    public ComplainAdapter(Context context) {
        this.context = context;
    }
    public void setActiveComplainList(List<ComplainModel> complainList){
        this.complainList = complainList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_active,parent,false);
        viewHolder viewHolder = new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ComplainModel complain = complainList.get(position);
        holder.bind(complain,holder);
    }

    @Override
    public int getItemCount() {
        return complainList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
            TextView complainNo,dateOfComplain,wardNo,areaName,branch,subject,details,status,expextedDate;
            ProgressBar workDone;
            ImageView photo;
            ImageButton reactionHeart,reactionHappy,reactionAngry,reactionThumb;

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
        }

        public void bind(ComplainModel complain, viewHolder viewHolder){
            complainNo.setText(complain.getComplainNo());
            dateOfComplain.setText((CharSequence) complain.getDateOfComplain());
            wardNo.setText(complain.getWardNo());
            areaName.setText(complain.getAreaName());
            branch.setText(complain.getBranch());
            subject.setText(complain.getSubject());
            details.setText(complain.getDetails());
            status.setText(complain.getStatus());
            expextedDate.setText((CharSequence) complain.getExpectedDate());
            workDone.setProgress((int) complain.getWorkDone());
//            Glide.with(context).load(complain.getPhoto()).into(photo);
        }
    }
}

