package com.example.killdll.storageSDK.local;

import com.example.killdll.MainActivity;
import com.example.killdll.storageSDK.entity.DBTask;
import com.example.killdll.storageSDK.entity.Task;

import java.util.ArrayList;
import java.util.List;

// before using methods below, add codes below
// AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "database-name").build();
public class DBOperator {

    public static void addOneTask(AppDatabase db, Task task) {
        db.taskDAO().insertOneDBTask(new DBTask(task));
    }

    public static void addManyTasks(AppDatabase db, List<Task> tasks) {
        List<DBTask> dbTasks = new ArrayList<>();
        for (Task task : tasks) {
            dbTasks.add(new DBTask(task));
        }
        db.taskDAO().insertManyDBTasks(dbTasks);
    }

    public static void deleteManyTasks(AppDatabase db, List<Task> tasks) {
        List<DBTask> dbTasks = new ArrayList<>();
        for (Task task : tasks) {
            dbTasks.add(new DBTask(task));
        }
        db.taskDAO().deleteManyDBTasks(dbTasks);
    }

    public static void deleteOneTask(AppDatabase db, Task task) {
        db.taskDAO().deleteOneDBTask(new DBTask(task));
    }

    public static void updateOneTask(AppDatabase db, Task task) {
        db.taskDAO().updateOneDBTask(new DBTask(task));
    }

    public static List<Task> queryTasksByTaskState(AppDatabase db, String taskState) {
        List<Task> tasks = new ArrayList<>();
        List<DBTask> dbTasks = db.taskDAO().getDBTaskByDBTaskState(taskState);
        for (DBTask dbTask : dbTasks) {
            tasks.add(dbTask.getTask());
        }
        return tasks;
    }

}
