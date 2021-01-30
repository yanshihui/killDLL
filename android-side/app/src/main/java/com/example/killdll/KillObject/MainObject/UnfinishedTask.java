package com.example.killdll.KillObject.MainObject;

public class UnfinishedTask {

    private String unfinished_title;
    private String unfinished_ddl;
    private String unfinished_time;
    private String unfinished_progress;

    public UnfinishedTask(String unfinished_title, String unfinished_ddl, String unfinished_time, String unfinished_progress){
        this.unfinished_title = unfinished_title;
        this.unfinished_ddl = unfinished_ddl;
        this.unfinished_time = unfinished_time;
        this.unfinished_progress = unfinished_progress;
    }

    public String getUnfinished_title() {
        return unfinished_title;
    }

    public void setUnfinished_title(String unfinished_title) {
        this.unfinished_title = unfinished_title;
    }

    public String getUnfinished_ddl() {
        return unfinished_ddl;
    }

    public void setUnfinished_ddl(String unfinished_ddl) {
        this.unfinished_ddl = unfinished_ddl;
    }

    public String getUnfinished_time() {
        return unfinished_time;
    }

    public void setUnfinished_time(String unfinished_time) {
        this.unfinished_time = unfinished_time;
    }

    public String getUnfinished_progress() {
        return unfinished_progress;
    }

    public void setUnfinished_progress(String unfinished_progress) {
        this.unfinished_progress = unfinished_progress;
    }
}
