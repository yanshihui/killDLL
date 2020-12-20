package com.example.killdll.subTask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.NewTaskActivity;
import com.example.killdll.R;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<SubTask> {

    private int resourceId;


    public TaskAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<SubTask> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SubTask subTask = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.taskContent = (EditText) view.findViewById(R.id.task_content);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        //EditText taskContent = (EditText) view.findViewById(R.id.task_content);
        //taskContent.setText(subTask.getTask());
        viewHolder.taskContent.setText(subTask.getTask());
        return view;
    }

    class ViewHolder{
        EditText taskContent;
    }
/*
    public void selectedItemPosition(int position) {
        this.selectedPositon = position;
    }

 */
}
