package com.keling.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.keling.app.data.local.Converters;
import com.keling.app.data.model.TaskRecord;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TaskRecordDao_Impl implements TaskRecordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TaskRecord> __insertionAdapterOfTaskRecord;

  public TaskRecordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTaskRecord = new EntityInsertionAdapter<TaskRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `task_records` (`id`,`taskId`,`taskTitle`,`completeTime`,`taskType`,`duration`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskRecord entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getTaskTitle());
        final String _tmp = Converters.INSTANCE.fromLocalDateTime(entity.getCompleteTime());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        if (entity.getTaskType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTaskType());
        }
        if (entity.getDuration() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getDuration());
        }
      }
    };
  }

  @Override
  public Object insertTaskRecord(final TaskRecord record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTaskRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TaskRecord>> getAllTaskRecords() {
    final String _sql = "SELECT * FROM task_records ORDER BY completeTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"task_records"}, new Callable<List<TaskRecord>>() {
      @Override
      @NonNull
      public List<TaskRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfTaskTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "taskTitle");
          final int _cursorIndexOfCompleteTime = CursorUtil.getColumnIndexOrThrow(_cursor, "completeTime");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final List<TaskRecord> _result = new ArrayList<TaskRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpTaskTitle;
            _tmpTaskTitle = _cursor.getString(_cursorIndexOfTaskTitle);
            final LocalDateTime _tmpCompleteTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCompleteTime);
            }
            final LocalDateTime _tmp_1 = Converters.INSTANCE.toLocalDateTime(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCompleteTime = _tmp_1;
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            _item = new TaskRecord(_tmpId,_tmpTaskId,_tmpTaskTitle,_tmpCompleteTime,_tmpTaskType,_tmpDuration);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TaskRecord>> getAll() {
    final String _sql = "SELECT * FROM task_records ORDER BY completeTime DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"task_records"}, new Callable<List<TaskRecord>>() {
      @Override
      @NonNull
      public List<TaskRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfTaskTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "taskTitle");
          final int _cursorIndexOfCompleteTime = CursorUtil.getColumnIndexOrThrow(_cursor, "completeTime");
          final int _cursorIndexOfTaskType = CursorUtil.getColumnIndexOrThrow(_cursor, "taskType");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final List<TaskRecord> _result = new ArrayList<TaskRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TaskRecord _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpTaskTitle;
            _tmpTaskTitle = _cursor.getString(_cursorIndexOfTaskTitle);
            final LocalDateTime _tmpCompleteTime;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCompleteTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCompleteTime);
            }
            final LocalDateTime _tmp_1 = Converters.INSTANCE.toLocalDateTime(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCompleteTime = _tmp_1;
            }
            final String _tmpTaskType;
            if (_cursor.isNull(_cursorIndexOfTaskType)) {
              _tmpTaskType = null;
            } else {
              _tmpTaskType = _cursor.getString(_cursorIndexOfTaskType);
            }
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            _item = new TaskRecord(_tmpId,_tmpTaskId,_tmpTaskTitle,_tmpCompleteTime,_tmpTaskType,_tmpDuration);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
