package com.example.killdll.storageSDK.local;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.example.killdll.storageSDK.entity.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Deprecated
public class FileOperator {

    private final String fileName;

    public List<Task> loadData(@NonNull Context context) {
        File file = new File(context.getFilesDir(), this.fileName);
        if (!file.exists()) {
            return new ArrayList<Task>();
        } else {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = context.openFileInput(this.fileName);
                int length = fileInputStream.available();
                byte byteStream[] = new byte[length];
                fileInputStream.read(byteStream);
                fileInputStream.close();
                String content = new String(byteStream, StandardCharsets.UTF_8);
                return JSON.parseArray(content, Task.class);
            } catch (IOException e) {
                // TODO:
                e.printStackTrace();
            }
        }

        return new ArrayList<Task>();
    }

    public void saveTasks(@NonNull Context context, List<Task> tasks) {
        File file = new File(context.getFilesDir(), fileName);
        if (!file.exists()){
            try{
                file.createNewFile();
                FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                fileOutputStream.write(JSON.toJSONString(tasks).getBytes(StandardCharsets.UTF_8));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
