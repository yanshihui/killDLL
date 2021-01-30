package com.example.killdll.KillObject.MainObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.R;

import java.util.List;

public class UnfinishedTaskAdapter extends RecyclerView.Adapter<UnfinishedTaskAdapter.ViewHolder> {

    private List<UnfinishedTask> mUnfinishedTaskList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView unfinished_title;
        TextView unfinished_ddl;
        TextView unfinished_time;
        TextView unfinished_progress;

        public ViewHolder(@NonNull View view) {
            super(view);
            unfinished_title = (TextView) view.findViewById(R.id.Unfinished_title);
            unfinished_ddl = (TextView) view.findViewById(R.id.Unfinished_ddl);
            unfinished_time = (TextView) view.findViewById(R.id.Unfinished_time);
            unfinished_progress = (TextView) view.findViewById(R.id.Unfinished_progress);
        }
    }

    public UnfinishedTaskAdapter(List<UnfinishedTask> mUnfinishedTaskList) {
        this.mUnfinishedTaskList = mUnfinishedTaskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.unfinished_task,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UnfinishedTaskAdapter.ViewHolder holder, int position) {
        UnfinishedTask unfinishedTask = mUnfinishedTaskList.get(position);
        holder.unfinished_title.setText(UnfinishedTask.getUnfinished_title());
        holder.unfinished_ddl.setText(UnfinishedTask.getUnfinished_ddl());
        holder.unfinished_time.setText(UnfinishedTask.getUnfinished_time());
        holder.unfinished_progress.setText(UnfinishedTask.getUnfinished_progress());
    }

    @Override
    public int getItemCount() {
        return mUnfinishedTaskList.size();
    }
}
