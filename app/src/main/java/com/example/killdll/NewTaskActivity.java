package com.example.killdll;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.killdll.subTask.SubTask;
import com.example.killdll.subTask.TaskAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewTaskActivity extends AppCompatActivity {

    private List<SubTask> subTaskList = new ArrayList<>();

    private Button addDate;
    private Button addTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        init();
        initTasks();
        TaskAdapter adapter = new TaskAdapter(NewTaskActivity.this,R.layout.task_item,subTaskList);
        ListView listView = (ListView) findViewById(R.id.task_list);
//        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SubTask subTask = subTaskList.get(position);
//            }
//        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCalendar = Calendar.getInstance();
                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH);
                final int day = mCalendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        addDate.setText(year+"年"+month+"月"+dayOfMonth+"日");
                    }
                },year,month,day).show();
            }
        });
        addTime.setOnClickListener(new View.OnClickListener() {
            Calendar calendar = Calendar.getInstance();
            @Override
            public void onClick(View v) {
                int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(NewTaskActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        addTime.setText(""+hourOfDay+":"+minute);
                    }
                },hourOfDay,minute,true).show();

            }
        });



    }

    private void init(){
        addDate = (Button) findViewById(R.id.add_date);
        addTime = (Button) findViewById(R.id.add_time);


    }

    private void initTasks() {

    }
}