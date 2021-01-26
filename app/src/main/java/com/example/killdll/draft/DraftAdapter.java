package com.example.killdll.draft;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.DraftsActivity;
import com.example.killdll.R;

import java.util.List;


public class DraftAdapter extends RecyclerView.Adapter<DraftAdapter.ViewHolder> {


    private List<String> mContentList;



    static class ViewHolder extends RecyclerView.ViewHolder{

        View draftView;
        TextView contentText;

        public ViewHolder(View view){
            super(view);
            contentText=(TextView) view.findViewById(R.id.show_content);
        }
    }

    public DraftAdapter(List<String> mContentList) {
        this.mContentList = mContentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.draft_content_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.draftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                String content=mContentList.get(position);
               // Intent intent=new Intent(DraftsActivity.class);
               // intent.putExtra(content);
               // parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contentText.setText(mContentList.get(position));

    }

    @Override
    public int getItemCount() {
        return mContentList.size();
    }

}
