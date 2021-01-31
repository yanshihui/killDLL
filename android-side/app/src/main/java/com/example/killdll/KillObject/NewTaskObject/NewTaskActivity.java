package com.example.killdll.KillObject.NewTaskObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.killdll.KillObject.MainObject.MainActivity;
import com.example.killdll.R;
import com.example.killdll.storageSDK.AccessTask;
import com.example.killdll.storageSDK.entity.ContentNode;
import com.example.killdll.storageSDK.entity.SubTask;
import com.example.killdll.storageSDK.entity.Task;
//import com.example.killdll.subTask.TaskAdapter;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewTaskActivity extends AppCompatActivity {



    private Button addDate;
    private Button addTime;
    private ImageButton addPicture;
    private Button planProgress;
    private Button confirmButton;
    private Button cancelButton;
    private EditText edTheme;
    private EditText note;
    private Toolbar toolbar;
    private ImageButton reminderButton;
    private static final int PHOTO_SUCCESS = 1;
    private static final int CAMERA_SUCCESS = 2;


    //private RecyclerView mRecyclerView;
    //private TaskAdapter mAdapter;
    private List<String> mList = new ArrayList<>();
    private List<SubTask> mSubTaskList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    private RecyclerView.Adapter adapter;
    private SubTask subTask;



    private List<ContentNode> mRemarks;
    ContentNode mRemark;
    private static final int RESIZE_REQUEST_CODE = 2;



    private Task newTask;
    private String name;
    private long dateL;
    private AccessTask accessTask ;
    private String dailyReminderTime;
    private String reminderMotto;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        init();
        takePhoto();
        toolbar.setTitle("");
        setActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //TaskAdapter adapter = new TaskAdapter(NewTaskActivity.this,R.layout.task_item,subTaskList);
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

        //点击按钮进入提醒设置界面
        reminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    store();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Intent intent2 = new Intent(NewTaskActivity.this,SetReminderActivity.class);
                intent2.putExtra("id",newTask.getId());
                startActivityForResult(intent2,3);
            }
        });

        //确定按钮监听事件
        //点击后，检查必填项，全部完成，自动保存并返回主界面
        //未全部完成，弹出按钮提醒框并在空白处用醒目颜色标注
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTask.setTaskState("InProgress");
                try {
                    store();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

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
                        newTask.setTaskState("Draft");
                        try {
                            store();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                        startActivity(intent);
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
        initSubTask();


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
            /*
            case R.id.set:  //设置提醒键
                Intent intent=new Intent(NewTaskActivity.this,SetReminderActivity.class);
                startActivity(intent);
                break;

             */
            case android.R.id.home:  //返回键
                AlertDialog.Builder builder = new AlertDialog.Builder(NewTaskActivity.this);
                builder.setTitle("是否保存");
                //点击是，保存到草稿箱，并返回主界面
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newTask.setTaskState("Draft");
                        try {
                            store();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Intent intent3 = new Intent(NewTaskActivity.this, MainActivity.class);
                        startActivity(intent3);


                    }
                });
                //点击否，删除并返回主界面
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent1 = new Intent(NewTaskActivity.this, MainActivity.class);
                       startActivity(intent1);
                    }
                });
                builder.show();
            default:
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
       // subTaskList.clear();


    }

    private void init(){
        addDate = (Button) findViewById(R.id.add_date);
        addTime = (Button) findViewById(R.id.add_time);
        addPicture = (ImageButton) findViewById(R.id.add_picture);
        planProgress = (Button) findViewById(R.id.edit_plan);
        confirmButton = (Button) findViewById(R.id.confirm_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
        note = (EditText) findViewById(R.id.note);
        edTheme = (EditText) findViewById(R.id.edit_theme);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        reminderButton = (ImageButton) findViewById(R.id.set1);





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
                        .setItems(new String[]{"相机","图库"}, new DialogInterface.OnClickListener(){

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


                    //将uri转换成string 存储
                    resizeImage(originalUri);
                    //以下方法将获取的uri转为String类型哦！
                    String []imgs={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                    Cursor cursor=this.managedQuery(originalUri, imgs, null, null, null);
                    int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String img_url=cursor.getString(index);
                    //存数据库
                    mRemark.setContent(img_url);
                    mRemark.setType("Image");
                    mRemarks.add(mRemark);

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
                        int index1 = note.getSelectionStart(); //获取光标所在位置
                        Editable edit_text = note.getEditableText();
                        if(index1 <0 || index1 >= edit_text.length()){
                            edit_text.append(spannableString);
                        }else{
                            edit_text.insert(index1, spannableString);
                        }
                    }else{
                        Toast.makeText(NewTaskActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
                case 3:
                   dailyReminderTime = intent.getStringExtra("dailyReminderTime");
                   reminderMotto = intent.getStringExtra("reminderMotto");

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

    private  void store() throws ParseException {
        name = edTheme.getText().toString();
        newTask.setName(name);

        String date = addDate.getText().toString();
        String formatType = "yyyy年MM月dd日";
        dateL = stringToLong(date,formatType);
        newTask.setEndTime(dateL);

        newTask.setSubTasks(mSubTaskList);


        newTask.setDailyReminderTime(Integer.parseInt(dailyReminderTime));
        newTask.setRemainderMotto(reminderMotto);

        String remarkText = note.getText().toString();
        mRemark.setContent(remarkText);
        mRemark.setType("Text");
        mRemarks.add(mRemark);
        newTask.setRemarks(mRemarks);

        accessTask.storeTask(newTask);
        //int num = accessTask.loadAllDraftTaskNames().size();


    }


    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
        // date要转换的date类型的时间
        public static long dateToLong(Date date) {
            return date.getTime();
    }
/*
    private void initSubTask() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        for (int i = 0; i < 31; i++) {
            mList.add("第" + i + "行");
        }

        LinearLayoutManager mManager = new LinearLayoutManager(NewTaskActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new TaskAdapter(NewTaskActivity.this, mList);
        mRecyclerView.setAdapter(mAdapter);

    }

 */

    public void resizeImage(Uri uri) {//重塑图片大小
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//能够裁剪
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }


    private void initSubTask() {
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(llm = new LinearLayoutManager(NewTaskActivity.this, LinearLayoutManager.VERTICAL, false));
            //recyclerView.addItemDecoration(new DividerItemDecoration(NewTaskActivity.this, DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setAdapter(adapter = new RecyclerView.Adapter() {
                //输入法
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //edittext里的文字内容集合
                SparseArray<String> etTextAry = new SparseArray();
                //edittext的焦点位置
                int etFocusPos = -1;
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //每次修改文字后，保存在数据集合中
                        Log.e("tag", "index=" + etFocusPos + ",save=" + s.toString());
                        etTextAry.put(etFocusPos, s.toString());
                        subTask.setContent(etTextAry.get(etFocusPos));
                        subTask.setDone(false);
                        mSubTaskList.add(subTask);
                    }
                };

                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View itemLayout = LayoutInflater.from(NewTaskActivity.this).inflate(
                            R.layout.subtask_content, parent, false);
                    return new ItemHolder(itemLayout);
                }

                @Override
                public synchronized void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
                    Log.e("tag", "绑定Holder,index=" + i);
                    final int position = i;
                    ItemHolder viewHolder = (ItemHolder) holder;
                   // viewHolder.imb.set
                    viewHolder.et.setText(etTextAry.get(position));
                    viewHolder.et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View view, boolean b) {
                            if (b) {
                                etFocusPos = position;
                                Log.e("tag", "etFocusPos焦点选中-" + etFocusPos);
                            }
                        }
                    });
                }

                @Override
                public int getItemCount() {
                    return 50;
                }

                @Override
                public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
                    super.onViewDetachedFromWindow(holder);
                    Log.e("tag", "隐藏item=" + holder.getAdapterPosition());
                    ItemHolder viewHolder = (ItemHolder) holder;
                    viewHolder.et.removeTextChangedListener(textWatcher);
                    viewHolder.et.clearFocus();
                    if (etFocusPos == holder.getAdapterPosition()) {
                        inputMethodManager.hideSoftInputFromWindow(((ItemHolder) holder).et.getWindowToken(), 0);
                    }
                }

                @Override
                public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
                    super.onViewAttachedToWindow(holder);
                    Log.e("tag", "显示item=" + holder.getAdapterPosition());
                    ItemHolder viewHolder = (ItemHolder) holder;
                    viewHolder.et.addTextChangedListener(textWatcher);
                    if (etFocusPos == holder.getAdapterPosition()) {
                        viewHolder.et.requestFocus();
                        viewHolder.et.setSelection(viewHolder.et.getText().length());
                    }
                }

                class ItemHolder extends RecyclerView.ViewHolder {
                    private ImageButton imb;
                    private EditText et;

                    public ItemHolder(View itemView) {
                        super(itemView);
                        imb = (ImageButton) itemView.findViewById(R.id.click);
                        et = (EditText) itemView.findViewById(R.id.subTask_content);
                    }
                }
            });
        }




}