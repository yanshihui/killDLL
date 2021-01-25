package com.example.killdll.storageSDK.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.killdll.storageSDK.entity.DBTask;

@Database(entities = {DBTask.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDAO taskDAO();
}
