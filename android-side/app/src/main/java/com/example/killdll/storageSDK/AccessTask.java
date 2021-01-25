package com.example.killdll.storageSDK;

import com.example.killdll.storageSDK.entity.Task;

// token is used in network requesting to authentication user
public class AccessTask {
    public final String InLocalStorage = "inLocalStorage";
    public final String InServer = "inServer";

    public final void storeTask(String storeLocation, Task[] tasks, String...token) {
        switch (storeLocation){
            case InLocalStorage:

            case InServer:

            default:

        }
    }

    public final void loadOneTaskById(String taskId, String...token){

    }

    public final void loadAllUnfinishedTask(String...token){

    }

}
