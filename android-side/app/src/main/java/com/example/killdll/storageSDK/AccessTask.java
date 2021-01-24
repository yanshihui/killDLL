package com.example.killdll.storageSDK;

import android.content.Context;

import com.example.killdll.storageSDK.entity.Task;
import com.example.killdll.storageSDK.local.FileOperator;

import java.util.List;

// token is used in network requesting to authentication user
public class AccessTask {
    public final static String InLocalStorage = "inLocalStorage";
    public final static String InServer = "inServer";

    private final static String LocalFIleName = "data.txt";

    private final String saveType;
    private final static FileOperator fileOperator = new FileOperator(LocalFIleName);
    private List<Task> tasks;

    public AccessTask(Context context, String saveType) {
        this.saveType = saveType;
        if (saveType.equals(InLocalStorage)) {
            this.tasks = fileOperator.loadData(context);
        }
    }

    public final void done(Context context) {
        fileOperator.saveTasks(context, tasks);
    }

    public final void storeTask(Task[] tasks, String... token) {
        switch (this.saveType) {
            case InLocalStorage:

            case InServer:

            default:

        }
    }

    public final void loadOneTaskById(String taskId, String... token) {

    }

    public final void loadAllUnfinishedTask(String... token) {

    }

}
