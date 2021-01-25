package com.example.killdll.storageSDK.entity;

import java.util.List;

import lombok.Data;

@Data
public class Receive {
    int code;
    String message;
    String token;
    List<Task> tasks;
}
