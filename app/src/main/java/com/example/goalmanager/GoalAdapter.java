package com.example.goalmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.MyViewHolder>{
    Context context;
    private ArrayList<Goal> goalList;

    public GoalAdapter(Context context, ArrayList<Goal> goalList)
    {
        this.context = context;
        this.goalList = goalList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tmpTxt;

        public MyViewHolder(final View view) {
            super(view);
            tmpTxt = view.findViewById(R.id.adapterText);
        }
    }

    @NonNull
    @Override
    public GoalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalAdapter.MyViewHolder holder, int position) {
        String tmp = goalList.get(position).getText();
        holder.tmpTxt.setText(tmp);
    }

    @Override
    public int getItemCount() {
        return goalList.size();
    }
}
