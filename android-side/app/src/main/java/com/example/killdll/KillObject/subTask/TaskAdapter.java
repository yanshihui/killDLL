package com.example.killdll.KillObject.subTask;

import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.R;
import com.example.killdll.storageSDK.entity.SubTask;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;


    public TaskAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHold viewHold = new ViewHold(mInflater.inflate(R.layout.subtask_content, parent, false));
        return viewHold;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);//不使用复用 防止数据多时 复用时  多个item中的EditText填写的数据一样
        ((ViewHold) holder).mEditText.setTag(position);
        ((ViewHold) holder).mEditText.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        private ImageButton imageButton;
        public EditText mEditText;

        public ViewHold(View itemView) {
            super(itemView);
            imageButton = (ImageButton) itemView.findViewById(R.id.click);
            mEditText = (EditText) itemView.findViewById(R.id.subTask_content);

        }
    }


}
