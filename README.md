# KILL DDL

## 主要功能



## Entity

- `Task`

```Java
public class Task{
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
}

public class SubTask {
    private String content;
    private boolean done;
}


public class ContentNode {
    public static final String TypeText = "Text";
    public static final String TypeImage = "Image";

    private String type;
    private String content;
}
```

- `Receive`

  model for receiving server return

```go
type Receive struct {
	Message string `json:"message"`
	Token   string `json:"token"`
	Tasks   []Task `json:"tasks"`
}
```

---

## 具体任务分工

- 存储部分
  - 负责人： @xylonx
    - 负责部分
      - 网络存储部分（第二版）
      - `SDK`
- 页面部分
  - 负责人： @yanshuhui 
    - 负责部分
      - 页面
  - 负责人：  @maspe125
    - 负责部分
      - 页面



