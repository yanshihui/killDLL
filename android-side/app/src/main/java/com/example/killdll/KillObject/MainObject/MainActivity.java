package com.example.killdll.KillObject.MainObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.killdll.KillObject.DraftsObject.DraftsActivity;
import com.example.killdll.MyObject.MyActivity;
import com.example.killdll.KillObject.NewTaskObject.NewTaskActivity;
import com.example.killdll.R;
import com.example.killdll.KillObject.subTask.SubTask;
import com.example.killdll.storageSDK.AccessTask;
import com.example.killdll.storageSDK.entity.Task;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    AccessTask accessTask = new AccessTask(getApplicationContext());

    private List<SubTask> taskList = new ArrayList<>();
    private List<UnfinishedTask> unfinishedTaskList = new ArrayList<>();
    private List<FinishedTask> finishedTaskList = new ArrayList<>();
    public static FloatingActionsMenu fab;
    FloatingActionButton fab_newTask,fab_draft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUnfinishedTasks();
        initFinishedTasks();
        RecyclerView unfinished_recyclerView = (RecyclerView) findViewById(R.id.unfinished_view);
        RecyclerView finished_recyclerView = (RecyclerView) findViewById(R.id.finished_view);
        LinearLayoutManager unfinished_layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager finished_layoutManager = new LinearLayoutManager(this);
        unfinished_recyclerView.setLayoutManager(unfinished_layoutManager);
        finished_recyclerView.setLayoutManager(finished_layoutManager);
        UnfinishedTaskAdapter unfinishedTaskAdapter = new UnfinishedTaskAdapter(unfinishedTaskList);
        FinishedTaskAdapter finishedTaskAdapter = new FinishedTaskAdapter(finishedTaskList);
        unfinished_recyclerView.setAdapter(unfinishedTaskAdapter);
        finished_recyclerView.setAdapter(finishedTaskAdapter);
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
        if(false){
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

    private void initUnfinishedTasks(){

    }

    private void initFinishedTasks() {

    }
}