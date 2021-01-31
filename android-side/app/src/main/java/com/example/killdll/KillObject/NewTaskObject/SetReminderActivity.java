package com.example.killdll.KillObject.NewTaskObject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.killdll.R;
import com.example.killdll.storageSDK.AccessTask;
import com.example.killdll.storageSDK.entity.Task;

public class SetReminderActivity extends AppCompatActivity {

    Integer dailyReminderTime;
    String reminderMotto;

    TextView tv1;
    TextView tv2;
    EditText et;
    Button btn1,btn2,btn3,btn4,save,cancel;
    ImageButton back;

    AccessTask accessTask;
    Task mTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        final Intent intent = getIntent();
        final String mId = intent.getStringExtra("id");
        init();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = accessTask.loadOneTaskById(mId);
                dailyReminderTime = 1;
                mTask.setDailyReminderTime(dailyReminderTime);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = accessTask.loadOneTaskById(mId);
                dailyReminderTime = 2;
                mTask.setDailyReminderTime(dailyReminderTime);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = accessTask.loadOneTaskById(mId);
                dailyReminderTime = 3;
                mTask.setDailyReminderTime(dailyReminderTime);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTask = accessTask.loadOneTaskById(mId);
                dailyReminderTime = 4;
                mTask.setDailyReminderTime(dailyReminderTime);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderMotto = tv1.getText().toString();
                et.setText(reminderMotto);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminderMotto = tv2.getText().toString();
                et.setText(reminderMotto);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("dailyReminderTime",dailyReminderTime);
                intent1.putExtra("remainderMotto",reminderMotto);
                setResult(RESULT_OK,intent1);
                finish();

            }
        });





    }

    private void init () {
        tv1 = (TextView) findViewById(R.id.reminder1);
        tv2 = (TextView) findViewById(R.id.reminder2);
        et = (EditText) findViewById(R.id.reminder_words);
        btn1 = (Button) findViewById(R.id.one);
        btn2 = (Button) findViewById(R.id.two);
        btn3 = (Button) findViewById(R.id.three);
        btn4 = (Button) findViewById(R.id.more);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);
        back = (ImageButton) findViewById(R.id.return_new);

    }
}