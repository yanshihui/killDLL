package com.example.killdll.storageSDK.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.killdll.storageSDK.entity.DBTask;

import java.util.List;

@Dao
public interface TaskDAO {

    // in default, select 20
    @Query("SELECT * FROM DBTask WHERE taskState = :taskState ORDER by endTime DESC LIMIT 20")
    List<DBTask> getDBTaskByDBTaskState(String taskState);

    @Query("SELECT * FROM DBTask WHERE id = :id")
    DBTask getDBTaskById(String id);

    @Query("SELECT * FROM DBTask")
    List<DBTask> getAllDBTasks();

    @Query("SELECT id, name FROM DBTask WHERE taskState = :taskState ORDER BY endTime DESC")
    List<NameId> getAllTaskNamesByState(String taskState);

    @Query("SELECT scheduleAllocation FROM DBTask ORDER BY endTime DESC")
    List<String> getAllScheduleAllocation();

    @Query("SELECT *, endTime - MAX(:now, startTime) as gap FROM DBTask ORDER BY gap")
    List<DBTask> getAllDBTasksOrderByRestTimeInc(long now);

    @Query("DELETE FROM DBTask WHERE id = :id")
    void deleteDBTaskById(String id);

    @Query("DELETE FROM DBTask WHERE id IN (:ids)")
    void deleteDBTasksByIds(List<String> ids);

    @Update
    void updateOneDBTask(DBTask tasks);

    @Insert
    void insertManyDBTasks(List<DBTask> tasks);

    @Insert
    void insertOneDBTask(DBTask task);

    @Delete
    void deleteOneDBTask(DBTask task);

    @Delete
    void deleteManyDBTasks(List<DBTask> tasks);

    static class NameId {
        public String id;
        public String name;
    }
}
