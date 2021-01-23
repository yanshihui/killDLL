package com.example.killdll;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TaskDetailsActivity extends AppCompatActivity {

    private TextView mTextView;
    private EditText details_time;
    private EditText details_rest_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        mTextView = (TextView) findViewById(R.id.text);
        details_time = (EditText) findViewById(R.id.detail_time);
        details_rest_time = (EditText) findViewById(R.id.detail_rest_time);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

        toolbar.setTitle("任务详情");
        setSupportActionBar(toolbar);
        getActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        details_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCalendar = Calendar.getInstance();
                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH);
                final int day = mCalendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(TaskDetailsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        details_time.setText(year+"年"+month+"月"+dayOfMonth+"日");
                    }
                },year,month,day).show();
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}