package com.example.killdll.storageSDK.entity;

import java.util.List;

import lombok.Data;

@Data
public class Receive {
    private int code;
    private String message;
    private String token;
    private List<Task> tasks;
}
