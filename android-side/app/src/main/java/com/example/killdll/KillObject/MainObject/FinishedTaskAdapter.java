package com.example.killdll.KillObject.MainObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.R;

import java.util.List;

public class FinishedTaskAdapter extends RecyclerView.Adapter<FinishedTaskAdapter.ViewHolder>{

    private List<FinishedTask> mFinishedTaskList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView finished_title;

        public ViewHolder(@NonNull View view) {
            super(view);
            finished_title = (TextView) view.findViewById(R.id.Finished_title);
        }
    }

    public FinishedTaskAdapter(List<FinishedTask> mFinishedTaskList){
        this.mFinishedTaskList = mFinishedTaskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.finished_task,parent,false);
        FinishedTaskAdapter.ViewHolder viewHolder = new FinishedTaskAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinishedTask finishedTask = mFinishedTaskList.get(position);
        holder.finished_title.setText(FinishedTask.getFinished_title());
    }

    @Override
    public int getItemCount() {
        return mFinishedTaskList.size();
    }

}
