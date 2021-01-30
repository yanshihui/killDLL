package com.example.killdll.KillObject.MainObject;

public class UnfinishedTask {

    private static String unfinished_title;
    private static String unfinished_ddl;
    private static String unfinished_time;
    private static String unfinished_progress;

    public UnfinishedTask(String unfinished_title, String unfinished_ddl, String unfinished_time, String unfinished_progress){
        this.unfinished_title = unfinished_title;
        this.unfinished_ddl = unfinished_ddl;
        this.unfinished_time = unfinished_time;
        this.unfinished_progress = unfinished_progress;
    }

    public static String getUnfinished_title() {
        return unfinished_title;
    }

    public void setUnfinished_title(String unfinished_title) {
        this.unfinished_title = unfinished_title;
    }

    public static String getUnfinished_ddl() {
        return unfinished_ddl;
    }

    public void setUnfinished_ddl(String unfinished_ddl) {
        this.unfinished_ddl = unfinished_ddl;
    }

    public static String getUnfinished_time() {
        return unfinished_time;
    }

    public void setUnfinished_time(String unfinished_time) {
        this.unfinished_time = unfinished_time;
    }

    public static String getUnfinished_progress() {
        return unfinished_progress;
    }

    public void setUnfinished_progress(String unfinished_progress) {
        this.unfinished_progress = unfinished_progress;
    }
}
