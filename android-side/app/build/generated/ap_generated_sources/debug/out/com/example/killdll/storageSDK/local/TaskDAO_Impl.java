package com.example.killdll.storageSDK.local;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.killdll.storageSDK.entity.DBTask;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskDAO_Impl implements TaskDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DBTask> __insertionAdapterOfDBTask;

  private final EntityDeletionOrUpdateAdapter<DBTask> __deletionAdapterOfDBTask;

  private final EntityDeletionOrUpdateAdapter<DBTask> __updateAdapterOfDBTask;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDBTaskById;

  public TaskDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDBTask = new EntityInsertionAdapter<DBTask>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DBTask` (`id`,`startTime`,`endTime`,`name`,`subTasks`,`remarks`,`taskState`,`dailyReminderTime`,`remainderMotto`,`scheduleAllocation`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DBTask value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getStartTime());
        stmt.bindLong(3, value.getEndTime());
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getSubTasks() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSubTasks());
        }
        if (value.getRemarks() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRemarks());
        }
        if (value.getTaskState() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTaskState());
        }
        stmt.bindLong(8, value.getDailyReminderTime());
        if (value.getRemainderMotto() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getRemainderMotto());
        }
        if (value.getScheduleAllocation() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getScheduleAllocation());
        }
      }
    };
    this.__deletionAdapterOfDBTask = new EntityDeletionOrUpdateAdapter<DBTask>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DBTask` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DBTask value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfDBTask = new EntityDeletionOrUpdateAdapter<DBTask>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DBTask` SET `id` = ?,`startTime` = ?,`endTime` = ?,`name` = ?,`subTasks` = ?,`remarks` = ?,`taskState` = ?,`dailyReminderTime` = ?,`remainderMotto` = ?,`scheduleAllocation` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DBTask value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        stmt.bindLong(2, value.getStartTime());
        stmt.bindLong(3, value.getEndTime());
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        if (value.getSubTasks() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSubTasks());
        }
        if (value.getRemarks() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRemarks());
        }
        if (value.getTaskState() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTaskState());
        }
        stmt.bindLong(8, value.getDailyReminderTime());
        if (value.getRemainderMotto() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getRemainderMotto());
        }
        if (value.getScheduleAllocation() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getScheduleAllocation());
        }
        if (value.getId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteDBTaskById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM DBTask WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void insertManyDBTasks(final List<DBTask> tasks) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDBTask.insert(tasks);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOneDBTask(final DBTask task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDBTask.insert(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteOneDBTask(final DBTask task) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDBTask.handle(task);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteManyDBTasks(final List<DBTask> tasks) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDBTask.handleMultiple(tasks);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateOneDBTask(final DBTask tasks) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDBTask.handle(tasks);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDBTaskById(final String id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDBTaskById.acquire();
    int _argIndex = 1;
    if (id == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, id);
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteDBTaskById.release(_stmt);
    }
  }

  @Override
  public List<DBTask> getDBTaskByDBTaskState(final String taskState) {
    final String _sql = "SELECT * FROM DBTask WHERE taskState = ? ORDER by endTime DESC LIMIT 20";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (taskState == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, taskState);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSubTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subTasks");
      final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
      final int _cursorIndexOfTaskState = CursorUtil.getColumnIndexOrThrow(_cursor, "taskState");
      final int _cursorIndexOfDailyReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyReminderTime");
      final int _cursorIndexOfRemainderMotto = CursorUtil.getColumnIndexOrThrow(_cursor, "remainderMotto");
      final int _cursorIndexOfScheduleAllocation = CursorUtil.getColumnIndexOrThrow(_cursor, "scheduleAllocation");
      final List<DBTask> _result = new ArrayList<DBTask>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBTask _item;
        _item = new DBTask();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpStartTime;
        _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
        _item.setStartTime(_tmpStartTime);
        final long _tmpEndTime;
        _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
        _item.setEndTime(_tmpEndTime);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpSubTasks;
        _tmpSubTasks = _cursor.getString(_cursorIndexOfSubTasks);
        _item.setSubTasks(_tmpSubTasks);
        final String _tmpRemarks;
        _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
        _item.setRemarks(_tmpRemarks);
        final String _tmpTaskState;
        _tmpTaskState = _cursor.getString(_cursorIndexOfTaskState);
        _item.setTaskState(_tmpTaskState);
        final int _tmpDailyReminderTime;
        _tmpDailyReminderTime = _cursor.getInt(_cursorIndexOfDailyReminderTime);
        _item.setDailyReminderTime(_tmpDailyReminderTime);
        final String _tmpRemainderMotto;
        _tmpRemainderMotto = _cursor.getString(_cursorIndexOfRemainderMotto);
        _item.setRemainderMotto(_tmpRemainderMotto);
        final String _tmpScheduleAllocation;
        _tmpScheduleAllocation = _cursor.getString(_cursorIndexOfScheduleAllocation);
        _item.setScheduleAllocation(_tmpScheduleAllocation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DBTask getDBTaskById(final String id) {
    final String _sql = "SELECT * FROM DBTask WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSubTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subTasks");
      final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
      final int _cursorIndexOfTaskState = CursorUtil.getColumnIndexOrThrow(_cursor, "taskState");
      final int _cursorIndexOfDailyReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyReminderTime");
      final int _cursorIndexOfRemainderMotto = CursorUtil.getColumnIndexOrThrow(_cursor, "remainderMotto");
      final int _cursorIndexOfScheduleAllocation = CursorUtil.getColumnIndexOrThrow(_cursor, "scheduleAllocation");
      final DBTask _result;
      if(_cursor.moveToFirst()) {
        _result = new DBTask();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _result.setId(_tmpId);
        final long _tmpStartTime;
        _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
        _result.setStartTime(_tmpStartTime);
        final long _tmpEndTime;
        _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
        _result.setEndTime(_tmpEndTime);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpSubTasks;
        _tmpSubTasks = _cursor.getString(_cursorIndexOfSubTasks);
        _result.setSubTasks(_tmpSubTasks);
        final String _tmpRemarks;
        _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
        _result.setRemarks(_tmpRemarks);
        final String _tmpTaskState;
        _tmpTaskState = _cursor.getString(_cursorIndexOfTaskState);
        _result.setTaskState(_tmpTaskState);
        final int _tmpDailyReminderTime;
        _tmpDailyReminderTime = _cursor.getInt(_cursorIndexOfDailyReminderTime);
        _result.setDailyReminderTime(_tmpDailyReminderTime);
        final String _tmpRemainderMotto;
        _tmpRemainderMotto = _cursor.getString(_cursorIndexOfRemainderMotto);
        _result.setRemainderMotto(_tmpRemainderMotto);
        final String _tmpScheduleAllocation;
        _tmpScheduleAllocation = _cursor.getString(_cursorIndexOfScheduleAllocation);
        _result.setScheduleAllocation(_tmpScheduleAllocation);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DBTask> getAllDBTasks() {
    final String _sql = "SELECT * FROM DBTask";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSubTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subTasks");
      final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
      final int _cursorIndexOfTaskState = CursorUtil.getColumnIndexOrThrow(_cursor, "taskState");
      final int _cursorIndexOfDailyReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyReminderTime");
      final int _cursorIndexOfRemainderMotto = CursorUtil.getColumnIndexOrThrow(_cursor, "remainderMotto");
      final int _cursorIndexOfScheduleAllocation = CursorUtil.getColumnIndexOrThrow(_cursor, "scheduleAllocation");
      final List<DBTask> _result = new ArrayList<DBTask>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBTask _item;
        _item = new DBTask();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpStartTime;
        _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
        _item.setStartTime(_tmpStartTime);
        final long _tmpEndTime;
        _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
        _item.setEndTime(_tmpEndTime);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpSubTasks;
        _tmpSubTasks = _cursor.getString(_cursorIndexOfSubTasks);
        _item.setSubTasks(_tmpSubTasks);
        final String _tmpRemarks;
        _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
        _item.setRemarks(_tmpRemarks);
        final String _tmpTaskState;
        _tmpTaskState = _cursor.getString(_cursorIndexOfTaskState);
        _item.setTaskState(_tmpTaskState);
        final int _tmpDailyReminderTime;
        _tmpDailyReminderTime = _cursor.getInt(_cursorIndexOfDailyReminderTime);
        _item.setDailyReminderTime(_tmpDailyReminderTime);
        final String _tmpRemainderMotto;
        _tmpRemainderMotto = _cursor.getString(_cursorIndexOfRemainderMotto);
        _item.setRemainderMotto(_tmpRemainderMotto);
        final String _tmpScheduleAllocation;
        _tmpScheduleAllocation = _cursor.getString(_cursorIndexOfScheduleAllocation);
        _item.setScheduleAllocation(_tmpScheduleAllocation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<TaskDAO.NameId> getAllTaskNamesByState(final String taskState) {
    final String _sql = "SELECT id, name FROM DBTask WHERE taskState = ? ORDER BY endTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (taskState == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, taskState);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<TaskDAO.NameId> _result = new ArrayList<TaskDAO.NameId>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final TaskDAO.NameId _item;
        _item = new TaskDAO.NameId();
        _item.id = _cursor.getString(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getAllScheduleAllocation() {
    final String _sql = "SELECT scheduleAllocation FROM DBTask ORDER BY endTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<DBTask> getAllDBTasksOrderByRestTimeInc(final long now) {
    final String _sql = "SELECT *, endTime - MAX(?, startTime) as gap FROM DBTask ORDER BY gap";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, now);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
      final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfSubTasks = CursorUtil.getColumnIndexOrThrow(_cursor, "subTasks");
      final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
      final int _cursorIndexOfTaskState = CursorUtil.getColumnIndexOrThrow(_cursor, "taskState");
      final int _cursorIndexOfDailyReminderTime = CursorUtil.getColumnIndexOrThrow(_cursor, "dailyReminderTime");
      final int _cursorIndexOfRemainderMotto = CursorUtil.getColumnIndexOrThrow(_cursor, "remainderMotto");
      final int _cursorIndexOfScheduleAllocation = CursorUtil.getColumnIndexOrThrow(_cursor, "scheduleAllocation");
      final List<DBTask> _result = new ArrayList<DBTask>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DBTask _item;
        _item = new DBTask();
        final String _tmpId;
        _tmpId = _cursor.getString(_cursorIndexOfId);
        _item.setId(_tmpId);
        final long _tmpStartTime;
        _tmpStartTime = _cursor.getLong(_cursorIndexOfStartTime);
        _item.setStartTime(_tmpStartTime);
        final long _tmpEndTime;
        _tmpEndTime = _cursor.getLong(_cursorIndexOfEndTime);
        _item.setEndTime(_tmpEndTime);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpSubTasks;
        _tmpSubTasks = _cursor.getString(_cursorIndexOfSubTasks);
        _item.setSubTasks(_tmpSubTasks);
        final String _tmpRemarks;
        _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
        _item.setRemarks(_tmpRemarks);
        final String _tmpTaskState;
        _tmpTaskState = _cursor.getString(_cursorIndexOfTaskState);
        _item.setTaskState(_tmpTaskState);
        final int _tmpDailyReminderTime;
        _tmpDailyReminderTime = _cursor.getInt(_cursorIndexOfDailyReminderTime);
        _item.setDailyReminderTime(_tmpDailyReminderTime);
        final String _tmpRemainderMotto;
        _tmpRemainderMotto = _cursor.getString(_cursorIndexOfRemainderMotto);
        _item.setRemainderMotto(_tmpRemainderMotto);
        final String _tmpScheduleAllocation;
        _tmpScheduleAllocation = _cursor.getString(_cursorIndexOfScheduleAllocation);
        _item.setScheduleAllocation(_tmpScheduleAllocation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public void deleteDBTasksByIds(final List<String> ids) {
    __db.assertNotSuspendingTransaction();
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("DELETE FROM DBTask WHERE id IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
    int _argIndex = 1;
    for (String _item : ids) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, _item);
      }
      _argIndex ++;
    }
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }
}
