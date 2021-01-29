package com.example.killdll.storageSDK;

import android.content.Context;

import androidx.room.Room;

import com.example.killdll.storageSDK.entity.Task;
import com.example.killdll.storageSDK.local.AppDatabase;
import com.example.killdll.storageSDK.local.CacheFile;
import com.example.killdll.storageSDK.local.DBOperator;
import com.example.killdll.storageSDK.network.HttpAsk;

import java.util.List;

public class AccessTask {

    private final AppDatabase appDatabase;

    public AccessTask(Context context){
        this.appDatabase = Room.databaseBuilder(context, AppDatabase.class, "killDDL").build();
    }

    public AccessTask(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    public final void storeTask(Task task){
        DBOperator.addOneTask(this.appDatabase, task);
    }

    public final void storeTask(List<Task> tasks) {
        DBOperator.addManyTasks(this.appDatabase, tasks);
    }

    public final Task loadOneTaskById(String taskId) {
        return DBOperator.queryTaskById(appDatabase, taskId);
    }


    public final List<Task> loadAllDraftTaskNames(){
        return DBOperator.queryAllTaskNamesByState(this.appDatabase, Task.StateDraft);
    }

    public final List<List<Double>> loadAllScheduleAllocation(){
        return DBOperator.queryAllScheduleAllocation(this.appDatabase);
    }

    public final List<Task> loadAllTaskByRestTimeInc(){
        return DBOperator.queryTasksByRestTimeInc(this.appDatabase);
    }

    public final void deleteTasksById(String id){
        DBOperator.deleteOneTaskById(this.appDatabase, id);
    }

    public final void deleteTaskById(List<String> ids){
        DBOperator.deleteManyTasksById(this.appDatabase, ids);
    }

    public final void updateTask(Task task){
        DBOperator.updateOneTask(this.appDatabase, task);
    }
}
