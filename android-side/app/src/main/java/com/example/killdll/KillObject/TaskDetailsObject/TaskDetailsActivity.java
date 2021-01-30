package com.example.killdll.KillObject.TaskDetailsObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.killdll.R;

public class TaskDetailsActivity extends AppCompatActivity {

    private Button kill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

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