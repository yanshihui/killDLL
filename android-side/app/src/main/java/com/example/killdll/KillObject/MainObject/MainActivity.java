package com.example.killdll.KillObject.MainObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.KillObject.DraftsObject.DraftsActivity;
import com.example.killdll.KillObject.NewTaskObject.NewTaskActivity;
import com.example.killdll.KillObject.TaskDetailsObject.TaskDetailsActivity;
import com.example.killdll.MyObject.MyActivity;
import com.example.killdll.R;
import com.example.killdll.storageSDK.AccessTask;
import com.example.killdll.storageSDK.entity.Task;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

import static java.lang.Math.max;

public class MainActivity extends AppCompatActivity {

    AccessTask accessTask = new AccessTask(getApplicationContext());

    public static FloatingActionsMenu fab;
    FloatingActionButton fab_newTask,fab_draft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUnfinishedTasks();
        initFinishedTasks();

        // Kill、我的 按钮
        Button button_ddl = (Button) findViewById(R.id.button_ddl);
        Button button_my = (Button) findViewById(R.id.button_my);
        button_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });

        // 悬浮按钮菜单
        fab_newTask = findViewById(R.id.fab_newTask);
        fab_draft = findViewById(R.id.fab_draft);

        // 判断草稿箱内是否有任务
        int draft_num = accessTask.loadAllDraftTaskNames().size();
        if(draft_num==0){
            fab_draft.setAlpha(.5f);
            fab_draft.setEnabled(false);
        }else{
            fab_draft.setEnabled(true);
        }
        fab_newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });
        fab_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DraftsActivity.class);
                startActivity(intent);
            }
        });
    }

    // 初始化未完成任务的recyclerview
    private void initUnfinishedTasks(){
        List<UnfinishedTask> unfinishedTaskList = new ArrayList<>();
        final List<Task> unfinishedName = accessTask.loadAllInProgressTaskNames();
        int unfinished_num = unfinishedName.size();
        RecyclerView unfinished_recyclerView = (RecyclerView) findViewById(R.id.unfinished_view);
        LinearLayoutManager unfinished_layoutManager = new LinearLayoutManager(this);
        unfinished_recyclerView.setLayoutManager(unfinished_layoutManager);
        UnfinishedTaskAdapter unfinishedTaskAdapter = new UnfinishedTaskAdapter(unfinishedTaskList);
        unfinished_recyclerView.setAdapter(unfinishedTaskAdapter);
        TextView unfinished_number = (TextView)findViewById(R.id.unfinished_num);
        unfinished_number.setText(String.valueOf(unfinished_num));

        for(int i=0;i<unfinished_num;i++){
            String un_title,un_ddl,un_time,un_progress;
            un_title = unfinishedName.get(i).getName();
            un_ddl = String.valueOf(unfinishedName.get(i).getEndTime());
            long now = new Date().getTime();
            long unf_time = unfinishedName.get(i).getEndTime()-max(unfinishedName.get(i).getStartTime(),now);
            un_time = String.valueOf(unf_time);
            un_progress = String.valueOf(0);
            UnfinishedTask unfinishedTask = new UnfinishedTask(un_title,un_ddl,un_time,un_progress);
            unfinishedTaskList.add(unfinishedTask);
        }

        unfinishedTaskAdapter.setOnItemClickListener(new UnfinishedTaskAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view,int position) {
                Intent intent = new Intent(MainActivity.this, TaskDetailsActivity.class);
                intent.putExtra("id",unfinishedName.get(position).getId());
                startActivity(intent);
            }
        });
    }
    // 初始化已完成任务的recyclerview
    private void initFinishedTasks() {
        List<FinishedTask> finishedTaskList = new ArrayList<>();
        List<Task> finishedName = accessTask.loadAllFinalizeTaskNames();
        int finished_num = finishedName.size();
        RecyclerView finished_recyclerView = (RecyclerView) findViewById(R.id.finished_view);
        LinearLayoutManager finished_layoutManager = new LinearLayoutManager(this);
        finished_recyclerView.setLayoutManager(finished_layoutManager);
        FinishedTaskAdapter finishedTaskAdapter = new FinishedTaskAdapter(finishedTaskList);
        finished_recyclerView.setAdapter(finishedTaskAdapter);
        for(int i=0;i<finished_num;i++){
            String fi_title;
            fi_title = finishedName.get(i).getName();
            FinishedTask finishedTask = new FinishedTask(fi_title);
        }
        finishedTaskAdapter.setOnItemClickListener(new FinishedTaskAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view,int position) {
                Intent intent = new Intent(MainActivity.this, TaskDetailsActivity.class);
            }
        });
    }
}