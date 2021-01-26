package com.example.killdll.storageSDK;

import android.content.Context;

import androidx.room.Room;

import com.example.killdll.storageSDK.entity.Task;
import com.example.killdll.storageSDK.local.AppDatabase;
import com.example.killdll.storageSDK.local.CacheFile;
import com.example.killdll.storageSDK.local.DBOperator;
import com.example.killdll.storageSDK.network.HttpAsk;

import java.util.List;

// token is used in network requesting to authentication user
public class AccessTask {

    private final AppDatabase appDatabase;

    public AccessTask(Context context){
        this.appDatabase = Room.databaseBuilder(context, AppDatabase.class, "killDDL").build();
    }

    public final void storeTasks(List<Task> tasks) {
        DBOperator.addManyTasks(this.appDatabase, tasks);
    }

    public final void loadOneTaskById(String taskId) {

    }

    public final void loadAllUnfinishedTask() {

    }

}
