package com.example.killdll.KillObject.MainObject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.R;

import java.util.List;

public class FinishedTaskAdapter extends RecyclerView.Adapter<FinishedTaskAdapter.ViewHolder> implements View.OnClickListener {

    private List<FinishedTask> mFinishedTaskList;
    private OnItemClickListener mOnItemClickListener;

    @Override
    public void onClick(View view) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }


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
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinishedTask finishedTask = mFinishedTaskList.get(position);
        holder.finished_title.setText (FinishedTask.getFinished_title());
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mFinishedTaskList.size();
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

}