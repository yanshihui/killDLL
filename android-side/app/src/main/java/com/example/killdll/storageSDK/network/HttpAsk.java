package com.example.killdll.storageSDK.network;

import com.alibaba.fastjson.JSON;
import com.example.killdll.storageSDK.entity.Receive;
import com.example.killdll.storageSDK.entity.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpAsk {

    private static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
    private static final String KeyHost = "request.host";
    private static final String KeyLoginUrl = "request.login.url";
    private static final String KeyRegisterUrl = "request.register.url";
    private static final String KeyResourceTaskUrl = "request.resource.task.url";

    private static final OkHttpClient client = new OkHttpClient();

    // if request success, return Receive Object. else, return null.
    // Receive contains response code and service return message.
    public static Receive postForRegister(String username, String password) {
        String registerUrl = loadProperties(KeyRegisterUrl);

        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("username", username);
        jsonBody.put("password", password);
        String jsonBodyStr = JSON.toJSONString(jsonBody);
        RequestBody body = RequestBody.create(jsonBodyStr, jsonType);
        Request request = new Request.Builder()
                .url(registerUrl)
                .post(body)
                .build();

        return doRequest(request);
    }

    // if request success, return Receive Object. else, return null.
    // Receive contains response code and service return message.
    public static Receive postForLogin(String username, String password) {
        String loginUrl = loadProperties(KeyLoginUrl);

        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("username", username);
        jsonBody.put("password", password);
        String jsonBodyStr = JSON.toJSONString(jsonBody);
        RequestBody body = RequestBody.create(jsonBodyStr, jsonType);

        Request request = new Request.Builder()
                .url(loginUrl)
                .post(body)
                .build();

        return doRequest(request);
    }

    public static Receive postForAddTask(Task task) {
        String postUrl = loadProperties(KeyResourceTaskUrl);

        String jsonBodyStr = JSON.toJSONString(task);
        RequestBody body = RequestBody.create(jsonBodyStr, jsonType);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();

        return doRequest(request);
    }

    public static Receive deleteTaskByIds(List<String> ids){
        String deleteUrl = loadProperties(KeyResourceTaskUrl);

        TaskDeleteModel deleteModel = new TaskDeleteModel(ids);
        RequestBody body = RequestBody.create(JSON.toJSONString(deleteModel), jsonType);

        Request request = new Request.Builder()
                .url(deleteUrl)
                .delete(body)
                .build();

        return doRequest(request);
    }

    private static String loadProperties(String propertyName) {
        String host = "http://127.0.0.1:4567";
        switch (propertyName) {
            case KeyLoginUrl:
                return host + "/login";

            case KeyRegisterUrl:
                return host + "/register";

            case KeyResourceTaskUrl:
                return host + "/task";

            default:
                return "";
        }
    }

    private static Receive doRequest(Request request) {
        Receive receive = null;
        try (Response response = client.newCall(request).execute()) {

            String responseBodyStr = response.body().string();
            receive = JSON.parseObject(responseBodyStr, Receive.class);
            receive.setCode(response.code());
            return receive;
        } catch (IOException e) {
            return receive;
        }
    }

}

@Data
@AllArgsConstructor
class TaskDeleteModel{
    List<String> taskIds;
}