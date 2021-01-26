package com.example.killdll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.killdll.draft.DraftContent;

import java.util.List;

public class DraftsActivity extends AppCompatActivity {

    private Toolbar toolbar1;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar_drafts);
        toolbar1.setTitle("");
        setActionBar(toolbar1);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_drafts,menu);
        return true;
    }

    //toolbar上的按钮功能实现
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chooseAll:  //设置提醒键
                AlertDialog.Builder builder = new AlertDialog.Builder(DraftsActivity.this);
                builder.setTitle("是否清空");
                //点击是，清空
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //点击否，回到草稿箱界面
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
            case android.R.id.home:  //返回键
                //点击后返回主界面
                Intent intent = new Intent(DraftsActivity.this,MainActivity.class);
                startActivity(intent);
            default:
        }
        return true;
    }
}