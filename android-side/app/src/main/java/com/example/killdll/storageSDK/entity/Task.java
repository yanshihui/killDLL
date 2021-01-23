package com.example.killdll.storageSDK.entity;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    String id;

    long startTime;
    long endTime;
    String theme;

    // recording the sub-task and its completion situation
    Map<String, Boolean>[] subTasks;
    String remarks;

    TaskState taskState;
}
