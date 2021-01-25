package com.example.killdll.storageSDK.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentNode {
    public static final String TypeText = "Text";
    public static final String TypeImage = "Image";

    private String type;
    private String content;
}