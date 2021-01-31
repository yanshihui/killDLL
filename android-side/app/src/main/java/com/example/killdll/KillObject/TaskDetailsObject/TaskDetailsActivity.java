package com.example.killdll.KillObject.TaskDetailsObject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.killdll.R;
import com.example.killdll.storageSDK.AccessTask;
import com.example.killdll.storageSDK.entity.ContentNode;
import com.example.killdll.storageSDK.entity.SubTask;
import com.example.killdll.storageSDK.entity.Task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.Math.max;

public class TaskDetailsActivity extends AppCompatActivity {

    private Button kill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        AccessTask accessTask = new AccessTask(getApplicationContext());

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        TextView detail_theme = (TextView) findViewById(R.id.detail_theme);
        TextView detail_time = (TextView) findViewById(R.id.detail_time);
        TextView detail_rest_time = (TextView) findViewById(R.id.detail_rest_time);
        TextView detail_fact = (TextView) findViewById(R.id.detail_fact);
        TextView detail_note = (TextView) findViewById(R.id.detail_note);

        Task task = accessTask.loadOneTaskById(id);

        detail_theme.setText(task.getName());
        detail_time.setText(String.valueOf(task.getEndTime()));
        long now = new Date().getTime();
        long unf_time = task.getEndTime()-max(task.getStartTime(),now);
        detail_rest_time.setText(String.valueOf(unf_time));
        detail_fact.setText("0");

        List<SubTask> subTasks = task.getSubTasks();
        List<ContentNode> note = task.getRemarks();

        detail_note.setText("");

        kill = (Button) findViewById(R.id.KILL);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar);
        // 设置Toolbar
        setSupportActionBar(mToolbar);
        // 显示NavigationIcon,这个方法是ActionBar的方法.Toolbar没有这个方法.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置icon
        mToolbar.setNavigationIcon(R.drawable.ic_return);
        //设置监听.必须在setSupportActionBar()之后调用
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_set:
                        Intent intent = new Intent(TaskDetailsActivity.this,SetActivity.class);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_task_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}