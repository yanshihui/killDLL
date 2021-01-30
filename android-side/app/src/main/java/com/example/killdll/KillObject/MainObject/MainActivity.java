package com.example.killdll.KillObject.MainObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.killdll.KillObject.DraftsObject.DraftsActivity;
import com.example.killdll.MyObject.MyActivity;
import com.example.killdll.KillObject.NewTaskObject.NewTaskActivity;
import com.example.killdll.R;
import com.example.killdll.KillObject.subTask.SubTask;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<SubTask> taskList = new ArrayList<>();
    public static FloatingActionsMenu fab;
    FloatingActionButton fab_newTask,fab_draft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTasks();
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

    private void initTasks(){
    }
}