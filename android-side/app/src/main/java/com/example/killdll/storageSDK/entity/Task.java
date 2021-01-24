package com.example.killdll.storageSDK.entity;

import java.util.List;
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
    List<Map<String, Boolean>> subTasks;

    // save remarks as string
    // if involving picture, convert them by methods in BitmapUtils
    List<String> remarks;

    TaskState taskState;
}
