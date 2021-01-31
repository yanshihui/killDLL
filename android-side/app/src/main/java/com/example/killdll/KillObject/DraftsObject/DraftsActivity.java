package com.example.killdll.KillObject.DraftsObject;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

//import com.example.killdll.draft.DraftContent;

import com.example.killdll.KillObject.MainObject.MainActivity;
import com.example.killdll.KillObject.NewTaskObject.NewTaskActivity;
import com.example.killdll.KillObject.TaskDetailsObject.TaskDetailsActivity;
import com.example.killdll.R;
import com.example.killdll.storageSDK.AccessTask;
import com.example.killdll.storageSDK.entity.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class DraftsActivity extends AppCompatActivity {

    private Toolbar toolbar1;

    private CheckListViewAdapter mAdapter;
    private List<Integer> mItems = new ArrayList<>();
    private List<String> mId = new ArrayList<>();
    private List<Task> mTask = new ArrayList<>();
    private AccessTask accessTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar_drafts);
        toolbar1.setTitle("");
        setActionBar(toolbar1);

        /*
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                // Intent intent1 = new Intent(DraftsActivity.this,MainActivity.class);
                // startActivity(intent1);
            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {

            }
        });

         */

        ListView listView = (ListView) findViewById(R.id.list_draft);
        mAdapter = new CheckListViewAdapter(this,
                R.layout.item_with_check_box,
                mItems);
        listView.setAdapter(mAdapter);
        listView.setMultiChoiceModeListener(new MultiChoiceModeListener(listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(DraftsActivity.this, "点击条目", Toast.LENGTH_SHORT).show();


                //???
                Intent intent = new Intent(DraftsActivity.this, TaskDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_drafts, menu);
        return true;
    }

    /*toolbar上的按钮功能实现
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    }

     */




    @Override
    protected void onStart() {
        super.onStart();
        mTask.addAll(accessTask.loadAllDraftTaskNames());
        mItems.addAll(getItems());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mItems.clear();
    }

    private List<Integer> getItems() {
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(i);
        }
        return items;
    }
    private class MultiChoiceModeListener implements AbsListView.MultiChoiceModeListener {
        private ListView mListView;
        private TextView mTitleTextView;
        private List<Integer> mSelectedItems = new ArrayList<>();

        private MultiChoiceModeListener(ListView listView) {
            mListView = listView;
        }

        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
            mSelectedItems.add(mAdapter.getItem(position));
            mId.add(mTask.get(mAdapter.getItem(position)).getId());
            mTitleTextView.setText("已选择 " + mListView.getCheckedItemCount() + " 项");
            mAdapter.notifyDataSetChanged();
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            //mode.getMenuInflater().inflate(R.menu.check_task_priority, menu);

            @SuppressLint("InflateParams")
            View multiSelectActionBarView = LayoutInflater.from(DraftsActivity.this)
                    .inflate(R.layout.action_mode_bar, null);
            mode.setCustomView(multiSelectActionBarView);
            mTitleTextView = (TextView)multiSelectActionBarView.findViewById(R.id.title);
            mTitleTextView.setText("已选择 0 项");

            mAdapter.setCheckable(true);
            mAdapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }



        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.return_new:
                    Intent intent1 = new Intent(DraftsActivity.this, MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.set1:
                    Collections.sort(mSelectedItems);
                    for (Integer selectedItem : mSelectedItems) {
                        mItems.remove(selectedItem);
                    }
                    mItems.addAll(0, mSelectedItems);

                    for(int i=0;i<20;i++) {

                    }

                    accessTask.deleteTaskById(mId);

                    break;
                default:
                    break;
            }

            mode.finish();
            return true;
        }




        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mSelectedItems.clear();
            mAdapter.setCheckable(false);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class CheckListViewAdapter extends ArrayAdapter<Integer> {

        private boolean mCheckable;

        private CheckListViewAdapter(@NonNull Context context,
                                     @LayoutRes int resource,
                                     @NonNull List<Integer> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_with_check_box, parent, false);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.show_content);
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.selected_check_box);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Integer item = getItem(position);
            if (item != null) {
                holder.textView.setText(item.toString());
            }

            //可见性和选中状态
            if (mCheckable) {
                holder.checkBox.setVisibility(View.VISIBLE);
            } else {
                holder.checkBox.setVisibility(View.INVISIBLE);
            }
            holder.checkBox.setChecked(((ListView) parent).isItemChecked(position));

            return convertView;
        }

        //用来设置是否CheckBox可见
        private void setCheckable(boolean checkable) {
            mCheckable = checkable;
        }

        private class ViewHolder {
            private TextView textView;
            private CheckBox checkBox;
        }
    }
}
