package com.keling.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.keling.app.data.local.Converters;
import com.keling.app.data.model.StudySession;
import com.keling.app.data.model.Task;
import com.keling.app.data.model.TaskDifficulty;
import com.keling.app.data.model.TaskProgress;
import com.keling.app.data.model.TaskStatus;
import com.keling.app.data.model.TaskType;
import com.keling.app.data.model.TeamTask;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
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
public final class TaskDao_Impl implements TaskDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Task> __insertionAdapterOfTask;

  private final EntityInsertionAdapter<TaskProgress> __insertionAdapterOfTaskProgress;

  private final EntityInsertionAdapter<TeamTask> __insertionAdapterOfTeamTask;

  private final EntityInsertionAdapter<StudySession> __insertionAdapterOfStudySession;

  private final EntityDeletionOrUpdateAdapter<Task> __deletionAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<Task> __updateAdapterOfTask;

  private final EntityDeletionOrUpdateAdapter<TaskProgress> __updateAdapterOfTaskProgress;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTaskStatus;

  private final SharedSQLiteStatement __preparedStmtOfCompleteTask;

  public TaskDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTask = new EntityInsertionAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tasks` (`id`,`title`,`description`,`courseId`,`chapterId`,`order`,`type`,`difficulty`,`status`,`experienceReward`,`coinReward`,`estimatedMinutes`,`estimatedDuration`,`progress`,`actionType`,`actionPayload`,`targetGrade`,`source`,`createdAt`,`deadline`,`completedAt`,`isCompleted`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getCourseId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCourseId());
        }
        if (entity.getChapterId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getChapterId());
        }
        if (entity.getOrder() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getOrder());
        }
        final String _tmp = Converters.INSTANCE.fromTaskType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = Converters.INSTANCE.fromTaskDifficulty(entity.getDifficulty());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = Converters.INSTANCE.fromTaskStatus(entity.getStatus());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        if (entity.getExperienceReward() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getExperienceReward());
        }
        if (entity.getCoinReward() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getCoinReward());
        }
        if (entity.getEstimatedMinutes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getEstimatedMinutes());
        }
        if (entity.getEstimatedDuration() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getEstimatedDuration());
        }
        statement.bindDouble(14, entity.getProgress());
        if (entity.getActionType() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getActionType());
        }
        if (entity.getActionPayload() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getActionPayload());
        }
        if (entity.getTargetGrade() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getTargetGrade());
        }
        if (entity.getSource() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getSource());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.getCreatedAt());
        }
        if (entity.getDeadline() == null) {
          statement.bindNull(20);
        } else {
          statement.bindLong(20, entity.getDeadline());
        }
        if (entity.getCompletedAt() == null) {
          statement.bindNull(21);
        } else {
          statement.bindLong(21, entity.getCompletedAt());
        }
        final int _tmp_3 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(22, _tmp_3);
      }
    };
    this.__insertionAdapterOfTaskProgress = new EntityInsertionAdapter<TaskProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `task_progress` (`id`,`taskId`,`userId`,`progress`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskProgress entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getProgress());
      }
    };
    this.__insertionAdapterOfTeamTask = new EntityInsertionAdapter<TeamTask>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `team_tasks` (`id`,`teamId`,`title`,`description`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TeamTask entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTeamId());
        statement.bindString(3, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
      }
    };
    this.__insertionAdapterOfStudySession = new EntityInsertionAdapter<StudySession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `study_sessions` (`id`,`dayKey`,`source`,`taskId`,`durationMinutes`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudySession entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getDayKey());
        statement.bindString(3, entity.getSource());
        if (entity.getTaskId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTaskId());
        }
        statement.bindLong(5, entity.getDurationMinutes());
      }
    };
    this.__deletionAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tasks` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfTask = new EntityDeletionOrUpdateAdapter<Task>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`description` = ?,`courseId` = ?,`chapterId` = ?,`order` = ?,`type` = ?,`difficulty` = ?,`status` = ?,`experienceReward` = ?,`coinReward` = ?,`estimatedMinutes` = ?,`estimatedDuration` = ?,`progress` = ?,`actionType` = ?,`actionPayload` = ?,`targetGrade` = ?,`source` = ?,`createdAt` = ?,`deadline` = ?,`completedAt` = ?,`isCompleted` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getCourseId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCourseId());
        }
        if (entity.getChapterId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getChapterId());
        }
        if (entity.getOrder() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getOrder());
        }
        final String _tmp = Converters.INSTANCE.fromTaskType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = Converters.INSTANCE.fromTaskDifficulty(entity.getDifficulty());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = Converters.INSTANCE.fromTaskStatus(entity.getStatus());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        if (entity.getExperienceReward() == null) {
          statement.bindNull(10);
        } else {
          statement.bindLong(10, entity.getExperienceReward());
        }
        if (entity.getCoinReward() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getCoinReward());
        }
        if (entity.getEstimatedMinutes() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getEstimatedMinutes());
        }
        if (entity.getEstimatedDuration() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getEstimatedDuration());
        }
        statement.bindDouble(14, entity.getProgress());
        if (entity.getActionType() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getActionType());
        }
        if (entity.getActionPayload() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getActionPayload());
        }
        if (entity.getTargetGrade() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getTargetGrade());
        }
        if (entity.getSource() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getSource());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(19);
        } else {
          statement.bindLong(19, entity.getCreatedAt());
        }
        if (entity.getDeadline() == null) {
          statement.bindNull(20);
        } else {
          statement.bindLong(20, entity.getDeadline());
        }
        if (entity.getCompletedAt() == null) {
          statement.bindNull(21);
        } else {
          statement.bindLong(21, entity.getCompletedAt());
        }
        final int _tmp_3 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(22, _tmp_3);
        statement.bindString(23, entity.getId());
      }
    };
    this.__updateAdapterOfTaskProgress = new EntityDeletionOrUpdateAdapter<TaskProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `task_progress` SET `id` = ?,`taskId` = ?,`userId` = ?,`progress` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskProgress entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getProgress());
        statement.bindLong(5, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateTaskStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET status = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfCompleteTask = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tasks SET status = ?, completedAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTask(final Task task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTask.insert(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTasks(final List<Task> tasks, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTask.insert(tasks);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTaskProgress(final TaskProgress progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTaskProgress.insert(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTeamTask(final TeamTask teamTask,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTeamTask.insert(teamTask);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertStudySession(final StudySession session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStudySession.insert(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTask(final Task task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTask.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTask(final Task task, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTask.handle(task);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTaskProgress(final TaskProgress progress,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTaskProgress.handle(progress);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTaskStatus(final String taskId, final TaskStatus status,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTaskStatus.acquire();
        int _argIndex = 1;
        final String _tmp = Converters.INSTANCE.fromTaskStatus(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        _stmt.bindString(_argIndex, taskId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateTaskStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object completeTask(final String taskId, final TaskStatus status, final long completedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCompleteTask.acquire();
        int _argIndex = 1;
        final String _tmp = Converters.INSTANCE.fromTaskStatus(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, completedAt);
        _argIndex = 3;
        _stmt.bindString(_argIndex, taskId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfCompleteTask.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getTaskById(final String taskId, final Continuation<? super Task> $completion) {
    final String _sql = "SELECT * FROM tasks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, taskId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Task>() {
      @Override
      @Nullable
      public Task call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final Task _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _result = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Task> getTaskByIdFlow(final String taskId) {
    final String _sql = "SELECT * FROM tasks WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, taskId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<Task>() {
      @Override
      @Nullable
      public Task call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final Task _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _result = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
          } else {
            _result = null;
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
  public Flow<List<Task>> getTasksByStatus(final TaskStatus status) {
    final String _sql = "SELECT * FROM tasks WHERE status = ? ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = Converters.INSTANCE.fromTaskStatus(status);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp_1);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_2);
            final TaskStatus _tmpStatus;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_4 = Converters.INSTANCE.toTaskStatus(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_4;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_5 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
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
  public Flow<List<Task>> getTasksByType(final TaskType type) {
    final String _sql = "SELECT * FROM tasks WHERE type = ? ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = Converters.INSTANCE.fromTaskType(type);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp_1);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_2);
            final TaskStatus _tmpStatus;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_4 = Converters.INSTANCE.toTaskStatus(_tmp_3);
            if (_tmp_4 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_4;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_5 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
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
  public Flow<List<Task>> getActiveTasks() {
    final String _sql = "SELECT * FROM tasks WHERE status IN ('PENDING', 'IN_PROGRESS') ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
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
  public Flow<List<Task>> getTasksByCourse(final String courseId) {
    final String _sql = "SELECT * FROM tasks WHERE courseId = ? ORDER BY `order`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, courseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
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
  public Flow<List<Task>> getActiveTasksForGrade(final String grade) {
    final String _sql = "SELECT * FROM tasks WHERE (targetGrade IS NULL OR targetGrade = ?) AND status IN ('PENDING', 'IN_PROGRESS') ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, grade);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
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
  public Flow<List<Task>> getTasksForGrade(final String grade) {
    final String _sql = "SELECT * FROM tasks WHERE (targetGrade IS NULL OR targetGrade = ?) ORDER BY deadline ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, grade);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
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
  public Object getExpiredTasks(final long timestamp,
      final Continuation<? super List<Task>> $completion) {
    final String _sql = "SELECT * FROM tasks WHERE deadline < ? AND status = 'PENDING'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timestamp);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfEstimatedDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDuration");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            if (_cursor.isNull(_cursorIndexOfCourseId)) {
              _tmpCourseId = null;
            } else {
              _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            }
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final Integer _tmpOrder;
            if (_cursor.isNull(_cursorIndexOfOrder)) {
              _tmpOrder = null;
            } else {
              _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            }
            final TaskType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = Converters.INSTANCE.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDifficulty)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            }
            _tmpDifficulty = Converters.INSTANCE.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            final TaskStatus _tmp_3 = Converters.INSTANCE.toTaskStatus(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.TaskStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_3;
            }
            final Integer _tmpExperienceReward;
            if (_cursor.isNull(_cursorIndexOfExperienceReward)) {
              _tmpExperienceReward = null;
            } else {
              _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            }
            final Integer _tmpCoinReward;
            if (_cursor.isNull(_cursorIndexOfCoinReward)) {
              _tmpCoinReward = null;
            } else {
              _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            }
            final Integer _tmpEstimatedMinutes;
            if (_cursor.isNull(_cursorIndexOfEstimatedMinutes)) {
              _tmpEstimatedMinutes = null;
            } else {
              _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            }
            final Integer _tmpEstimatedDuration;
            if (_cursor.isNull(_cursorIndexOfEstimatedDuration)) {
              _tmpEstimatedDuration = null;
            } else {
              _tmpEstimatedDuration = _cursor.getInt(_cursorIndexOfEstimatedDuration);
            }
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final String _tmpActionType;
            if (_cursor.isNull(_cursorIndexOfActionType)) {
              _tmpActionType = null;
            } else {
              _tmpActionType = _cursor.getString(_cursorIndexOfActionType);
            }
            final String _tmpActionPayload;
            if (_cursor.isNull(_cursorIndexOfActionPayload)) {
              _tmpActionPayload = null;
            } else {
              _tmpActionPayload = _cursor.getString(_cursorIndexOfActionPayload);
            }
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final Long _tmpCreatedAt;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmpCreatedAt = null;
            } else {
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            }
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpOrder,_tmpType,_tmpDifficulty,_tmpStatus,_tmpExperienceReward,_tmpCoinReward,_tmpEstimatedMinutes,_tmpEstimatedDuration,_tmpProgress,_tmpActionType,_tmpActionPayload,_tmpTargetGrade,_tmpSource,_tmpCreatedAt,_tmpDeadline,_tmpCompletedAt,_tmpIsCompleted);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<Integer> getCompletedTaskCount() {
    final String _sql = "SELECT COUNT(*) FROM tasks WHERE status = 'COMPLETED'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object getTaskProgress(final String taskId, final String userId,
      final Continuation<? super TaskProgress> $completion) {
    final String _sql = "SELECT * FROM task_progress WHERE taskId = ? AND userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, taskId);
    _argIndex = 2;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TaskProgress>() {
      @Override
      @Nullable
      public TaskProgress call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final TaskProgress _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final int _tmpProgress;
            _tmpProgress = _cursor.getInt(_cursorIndexOfProgress);
            _result = new TaskProgress(_tmpId,_tmpTaskId,_tmpUserId,_tmpProgress);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TeamTask>> getTeamTasksByTeam(final String teamId) {
    final String _sql = "SELECT * FROM team_tasks WHERE teamId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, teamId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"team_tasks"}, new Callable<List<TeamTask>>() {
      @Override
      @NonNull
      public List<TeamTask> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "teamId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<TeamTask> _result = new ArrayList<TeamTask>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TeamTask _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTeamId;
            _tmpTeamId = _cursor.getString(_cursorIndexOfTeamId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item = new TeamTask(_tmpId,_tmpTeamId,_tmpTitle,_tmpDescription);
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
  public Flow<Integer> getStudyMinutesForDay(final String dayKey) {
    final String _sql = "SELECT COALESCE(SUM(durationMinutes), 0) FROM study_sessions WHERE dayKey = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, dayKey);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"study_sessions"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
