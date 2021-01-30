package com.example.killdll.KillObject.MainObject;

public class FinishedTask {

    private static String finished_title;

    public FinishedTask(String finished_title){
        this.finished_title = finished_title;
    }

    public static String getFinished_title() {
        return finished_title;
    }

    public void setFinished_title(String finished_title) {
        this.finished_title = finished_title;
    }
}
