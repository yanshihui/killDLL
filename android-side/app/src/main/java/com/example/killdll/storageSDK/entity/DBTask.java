package com.example.killdll.storageSDK.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alibaba.fastjson.JSON;

import org.jetbrains.annotations.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class DBTask {
    @PrimaryKey
    @NotNull
    private String id;
    private long startTime;
    private long endTime;
    private String theme;

    // stored by json
    private String subTasks;

    // stored by json
    private String remarks;

    private String taskState;

    private int dailyReminderTime;
    private String remainderMotto;

    // stored by json
    private String scheduleAllocation;

    public DBTask(Task task) {
        this.id = task.getId();
        this.startTime = task.getStartTime();
        this.endTime = task.getEndTime();
        this.theme = task.getTheme();
        this.taskState = task.getTaskState();
        this.dailyReminderTime = task.getDailyReminderTime();
        this.remainderMotto = task.getRemainderMotto();
        this.remarks = JSON.toJSONString(task.getRemarks());
        this.subTasks = JSON.toJSONString(task.getSubTasks());
        this.scheduleAllocation = JSON.toJSONString(task.getScheduleAllocation());
    }

    public Task getTask() {
        Task task = new Task();
        task.setId(this.id);
        task.setStartTime(this.startTime);
        task.setEndTime(this.endTime);
        task.setTheme(this.theme);
        task.setTaskState(this.taskState);
        task.setDailyReminderTime(this.dailyReminderTime);
        task.setRemainderMotto(this.remainderMotto);
        task.setRemarks(JSON.parseArray(this.remarks, ContentNode.class));
        task.setSubTasks(JSON.parseArray(this.subTasks, SubTask.class));
        task.setScheduleAllocation(JSON.parseArray(this.scheduleAllocation, Double.class));
        return task;
    }


}
