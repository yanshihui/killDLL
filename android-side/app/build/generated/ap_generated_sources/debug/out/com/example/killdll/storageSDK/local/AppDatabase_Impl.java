package com.example.killdll.storageSDK.local;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TaskDAO _taskDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DBTask` (`id` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER NOT NULL, `theme` TEXT, `subTasks` TEXT, `remarks` TEXT, `taskState` TEXT, `dailyReminderTime` INTEGER NOT NULL, `remainderMotto` TEXT, `scheduleAllocation` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '076eb2ce6b9c62a45daec8b197342dbb')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DBTask`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDBTask = new HashMap<String, TableInfo.Column>(10);
        _columnsDBTask.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("endTime", new TableInfo.Column("endTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("theme", new TableInfo.Column("theme", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("subTasks", new TableInfo.Column("subTasks", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("remarks", new TableInfo.Column("remarks", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("taskState", new TableInfo.Column("taskState", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("dailyReminderTime", new TableInfo.Column("dailyReminderTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("remainderMotto", new TableInfo.Column("remainderMotto", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDBTask.put("scheduleAllocation", new TableInfo.Column("scheduleAllocation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDBTask = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDBTask = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDBTask = new TableInfo("DBTask", _columnsDBTask, _foreignKeysDBTask, _indicesDBTask);
        final TableInfo _existingDBTask = TableInfo.read(_db, "DBTask");
        if (! _infoDBTask.equals(_existingDBTask)) {
          return new RoomOpenHelper.ValidationResult(false, "DBTask(com.example.killdll.storageSDK.entity.DBTask).\n"
                  + " Expected:\n" + _infoDBTask + "\n"
                  + " Found:\n" + _existingDBTask);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "076eb2ce6b9c62a45daec8b197342dbb", "804209ea091831f01b7411455829a703");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "DBTask");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DBTask`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public TaskDAO taskDAO() {
    if (_taskDAO != null) {
      return _taskDAO;
    } else {
      synchronized(this) {
        if(_taskDAO == null) {
          _taskDAO = new TaskDAO_Impl(this);
        }
        return _taskDAO;
      }
    }
  }
}
