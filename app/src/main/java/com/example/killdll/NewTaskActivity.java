package com.example.killdll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.killdll.subTask.SubTask;
import com.example.killdll.subTask.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewTaskActivity extends AppCompatActivity {

    private List<SubTask> subTaskList = new ArrayList<>();

    private Button addDate;
    private Button addTime;
    private Button addPicture;
    private Button planProgress;
    private Button confirmButton;
    private Button cancelButton;
    private EditText note;
    private Toolbar toolbar;
    private static final int PHOTO_SUCCESS = 1;
    private static final int CAMERA_SUCCESS = 2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        init();
        initTasks();
        takePhoto();
        toolbar.setTitle("");
        setActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        TaskAdapter adapter = new TaskAdapter(NewTaskActivity.this,R.layout.task_item,subTaskList);
        ListView listView = (ListView) findViewById(R.id.recycler_view);
//        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SubTask subTask = subTaskList.get(position);
//            }
//        });

        //点击按钮进入计划进度分配界面
        planProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTaskActivity.this,PlanProgressActivity.class);
                startActivity(intent);
            }
        });

        //确定按钮监听事件
        //点击后，检查必填项，全部完成，自动保存并返回主界面
        //未全部完成，弹出按钮提醒框并在空白处用醒目颜色标注
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //取消按钮监听事件
        //点击后，弹出确认框是否保存
        //是，保存到草稿箱，并返回主界面
        //否，删除并返回主界面
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NewTaskActivity.this);
                builder.setTitle("是否保存");
                //点击是，保存到草稿箱，并返回主界面
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //点击否，删除并返回主界面
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(NewTaskActivity.this,MainActivity.class);
                        startActivity(intent1);
                    }
                });
                builder.show();
            }
        });

        //设置日期
        setDate();
        //摄制时间
        setTime();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //toolbar上的按钮功能实现
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set:  //设置提醒键
                Intent intent=new Intent(NewTaskActivity.this,SetReminderActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:  //返回键
                AlertDialog.Builder builder = new AlertDialog.Builder(NewTaskActivity.this);
                builder.setTitle("是否保存");
                //点击是，保存到草稿箱，并返回主界面
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                //点击否，删除并返回主界面
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent1 = new Intent(NewTaskActivity.this,MainActivity.class);
                       startActivity(intent1);
                    }
                });
                builder.show();
            default:
        }
        return true;
    }

    private void init(){
        addDate = (Button) findViewById(R.id.add_date);
        addTime = (Button) findViewById(R.id.add_time);
        addPicture = (Button) findViewById(R.id.add_picture);
        planProgress = (Button) findViewById(R.id.edit_plan);
        confirmButton = (Button) findViewById(R.id.confirm_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        note = (EditText) findViewById(R.id.note);
        toolbar = (Toolbar) findViewById(R.id.toolbar);



    }

    private void initTasks() {

    }

    private void setDate() {
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
    }

    private void setTime() {
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

    private void takePhoto() {
        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(NewTaskActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("选择图片：")
                        //设置两个item
                        .setItems(new String[]{"相机","图库"}, new android.content.DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0://拍照
                                        Intent getImageByCamera= new Intent("android.media.action.IMAGE_CAPTURE");
                                        startActivityForResult(getImageByCamera, CAMERA_SUCCESS);
                                       // pickPicFromCam();
                                        break;
                                    case 1://从相册中选择
                                        Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                                        getImage.addCategory(Intent.CATEGORY_OPENABLE);
                                        getImage.setType("image/*");
                                        startActivityForResult(getImage, PHOTO_SUCCESS);
                                       // pickPicFromPic();
                                        break;
                                    default:
                                        break;
                                }

                            }});
                builder.create().show();
            }
        });
    }

    /*
    //拍照
    private void pickPicFromCam() {


    }

    //从相册中选择
    private void pickPicFromPic() {

    }

     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {

        //插入图片
        ContentResolver resolver = getContentResolver();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_SUCCESS:
                    //获得图片的uri
                    Uri originalUri = intent.getData();
                    Bitmap bitmap = null;
                    try {
                        Bitmap originalBitmap = BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
                        bitmap = resizeImage(originalBitmap, 200, 200);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    if(bitmap != null){
                        //根据Bitmap对象创建ImageSpan对象
                        ImageSpan imageSpan = new ImageSpan(NewTaskActivity.this, bitmap);
                        //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                        SpannableString spannableString = new SpannableString("[local]"+1+"[/local]");
                        //  用ImageSpan对象替换face
                        spannableString.setSpan(imageSpan, 0, "[local]1[local]".length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //将选择的图片追加到EditText中光标所在位置
                        int index = note.getSelectionStart(); //获取光标所在位置
                        Editable edit_text = note.getEditableText();
                        if(index <0 || index >= edit_text.length()){
                            edit_text.append(spannableString);
                        }else{
                            edit_text.insert(index, spannableString);
                        }
                    }else{
                        Toast.makeText(NewTaskActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CAMERA_SUCCESS:
                    Bundle extras = intent.getExtras();
                    Bitmap originalBitmap1 = (Bitmap) extras.get("data");
                    if(originalBitmap1 != null){
                        bitmap = resizeImage(originalBitmap1, 200, 200);
                        //根据Bitmap对象创建ImageSpan对象
                        ImageSpan imageSpan = new ImageSpan(NewTaskActivity.this, bitmap);
                        //创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
                        SpannableString spannableString = new SpannableString("[local]"+1+"[/local]");
                        //  用ImageSpan对象替换face
                        spannableString.setSpan(imageSpan, 0, "[local]1[local]".length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //将选择的图片追加到EditText中光标所在位置
                        int index = note.getSelectionStart(); //获取光标所在位置
                        Editable edit_text = note.getEditableText();
                        if(index <0 || index >= edit_text.length()){
                            edit_text.append(spannableString);
                        }else{
                            edit_text.insert(index, spannableString);
                        }
                    }else{
                        Toast.makeText(NewTaskActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //插入图片功能
    private Bitmap resizeImage(Bitmap originalBitmap, int newWidth, int newHeight){
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        //定义欲转换成的宽、高
//      int newWidth = 200;
//      int newHeight = 200;
        //计算宽、高缩放率
        float scanleWidth = (float)newWidth/width;
        float scanleHeight = (float)newHeight/height;
        //创建操作图片用的matrix对象 Matrix
        Matrix matrix = new Matrix();
        // 缩放图片动作
        matrix.postScale(scanleWidth,scanleHeight);
        //旋转图片 动作
        //matrix.postRotate(45);
        // 创建新的图片Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap,0,0,width,height,matrix,true);
        return resizedBitmap;
    }




}