# KILL DDL

## 主要功能



## Entity

- `Task`

```Java
public class Task{
    long startTime;
    long endTime;
    
    String theme;
    Map<String, bool>[] subTasks;
    String remarks;
    
    // schedule allocation
    Map taskAllocation;
    
    TaskState taskState;
}

public enum TaskState{
    Done, Draft;
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



