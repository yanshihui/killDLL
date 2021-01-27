package com.example.killdll.storageSDK.local;

import com.alibaba.fastjson.JSON;
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

    public static void deleteOneTaskById(AppDatabase db, String id){
        db.taskDAO().deleteDBTaskById(id);
    }

    public static void deleteManyTasksById(AppDatabase db, List<String> ids){
        db.taskDAO().deleteDBTasksByIds(ids);
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

    public static Task queryTaskById(AppDatabase db, String id) {
        return db.taskDAO().getDBTaskById(id).getTask();
    }

    public static List<Task> queryTasksByRestTimeInc(AppDatabase db){
        List<Task> tasks = new ArrayList<>();
        List<DBTask> dbTasks = db.taskDAO().getAllDBTasksOrderByRestTimeInc(System.currentTimeMillis());
        for(DBTask dbTask : dbTasks){
            tasks.add(dbTask.getTask());
        }
        return tasks;
    }

    public static List<Task> queryAllTasks(AppDatabase db) {
        List<Task> tasks = new ArrayList<>();
        List<DBTask> dbTasks = db.taskDAO().getAllDBTasks();
        for (DBTask dbTask : dbTasks) {
            tasks.add(dbTask.getTask());
        }
        return tasks;
    }

    public static List<List<Double>> queryAllScheduleAllocation(AppDatabase db) {
        List<List<Double>> allocations = new ArrayList<>();
        List<String> jsonData = db.taskDAO().getAllScheduleAllocation();
        for (String json : jsonData) {
            allocations.add(JSON.parseArray(json, Double.class));
        }
        return allocations;
    }

    public static List<Task> queryAllTaskNamesByState(AppDatabase db, String taskState) {
        List<Task> tasks = new ArrayList<>();
        List<TaskDAO.NameId> NameIds = db.taskDAO().getAllTaskNamesByState(taskState);
        for (TaskDAO.NameId nameId : NameIds) {
            tasks.add(new Task(nameId.id, nameId.name));
        }
        return tasks;
    }

}
