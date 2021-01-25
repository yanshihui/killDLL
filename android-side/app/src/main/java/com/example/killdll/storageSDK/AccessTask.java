package com.example.killdll.storageSDK;

import com.example.killdll.storageSDK.entity.Task;
import com.example.killdll.storageSDK.local.DBOperator;

import java.util.List;

// token is used in network requesting to authentication user
public class AccessTask {
    public final static String InLocalStorage = "inLocalStorage";
    public final static String InServer = "inServer";
    private final static String LocalFIleName = "data.txt";

    private List<Task> tasks;

    public final void storeTask(String saveType, List<Task> tasks, String... token) {
        switch (saveType) {
            case InLocalStorage:
                // FIXME: no safe
                DBOperator.addManyTasks(null, tasks);

            case InServer:

            default:

        }
    }

    public final void loadOneTaskById(String taskId) {

    }

    public final void loadAllUnfinishedTask() {

    }

}
