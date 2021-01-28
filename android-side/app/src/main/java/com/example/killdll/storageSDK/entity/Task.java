package com.example.killdll.storageSDK.entity;

import com.example.killdll.storageSDK.utils.GenerateId;

import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Task {

    public static final String StateFinalize = "Finalize";
    public static final String StateInProgress = "InProgress";
    public static final String StateDraft = "Draft";

    private String id;

    private long startTime;
    private long endTime;
    private String theme;

    // recording the sub-task and its completion situation
    private List<SubTask> subTasks;

    // save remarks as string
    // if involving picture, convert them by methods in BitmapUtils
    private List<ContentNode> remarks;

    private String taskState;

    private int dailyReminderTime;
    private String remainderMotto;

    // by default, it is equally distributed
    private List<Double> scheduleAllocation;

    public Task() {
        this.id = GenerateId.GenerateIdByItems();
        this.startTime = new Date().getTime();
        this.endTime = new Date().getTime() + 1;
        this.theme = "";
        this.subTasks = null;
        this.remarks = null;
        this.taskState = StateDraft;
        this.dailyReminderTime = 1;
        this.remainderMotto = "";

        // million seconds
        setMeanScheduleAllocation();
    }

    public void setMeanScheduleAllocation(){
        int day = (int) Math.ceil((this.endTime - this.startTime) / (1000d * 60d * 60d * 24d));
        this.scheduleAllocation = new ArrayList<>();
        double meanPartition = 1d / day;
        for(int i = 0; i < day; i++){
            scheduleAllocation.add(meanPartition);
        }
    }
}
