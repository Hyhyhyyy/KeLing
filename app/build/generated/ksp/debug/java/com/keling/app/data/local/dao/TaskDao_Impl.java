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

  private final Converters __converters = new Converters();

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
        return "INSERT OR REPLACE INTO `tasks` (`id`,`title`,`description`,`type`,`difficulty`,`status`,`courseId`,`chapterId`,`experienceReward`,`coinReward`,`deadline`,`estimatedMinutes`,`progress`,`createdAt`,`completedAt`,`parentTaskId`,`order`,`targetGrade`,`actionType`,`actionPayload`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        final String _tmp = __converters.fromTaskType(entity.getType());
        statement.bindString(4, _tmp);
        final String _tmp_1 = __converters.fromTaskDifficulty(entity.getDifficulty());
        statement.bindString(5, _tmp_1);
        final String _tmp_2 = __converters.fromTaskStatus(entity.getStatus());
        statement.bindString(6, _tmp_2);
        if (entity.getCourseId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCourseId());
        }
        if (entity.getChapterId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getChapterId());
        }
        statement.bindLong(9, entity.getExperienceReward());
        statement.bindLong(10, entity.getCoinReward());
        if (entity.getDeadline() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getDeadline());
        }
        statement.bindLong(12, entity.getEstimatedMinutes());
        statement.bindDouble(13, entity.getProgress());
        statement.bindLong(14, entity.getCreatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getCompletedAt());
        }
        if (entity.getParentTaskId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getParentTaskId());
        }
        statement.bindLong(17, entity.getOrder());
        if (entity.getTargetGrade() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getTargetGrade());
        }
        if (entity.getActionType() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getActionType());
        }
        if (entity.getActionPayload() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getActionPayload());
        }
      }
    };
    this.__insertionAdapterOfTaskProgress = new EntityInsertionAdapter<TaskProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `task_progress` (`id`,`taskId`,`userId`,`progress`,`timeSpentMinutes`,`lastUpdatedAt`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskProgress entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getUserId());
        statement.bindDouble(4, entity.getProgress());
        statement.bindLong(5, entity.getTimeSpentMinutes());
        statement.bindLong(6, entity.getLastUpdatedAt());
      }
    };
    this.__insertionAdapterOfTeamTask = new EntityInsertionAdapter<TeamTask>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `team_tasks` (`id`,`taskId`,`teamId`,`memberIds`,`leaderUserId`,`status`,`createdAt`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TeamTask entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getTeamId());
        final String _tmp = __converters.fromStringList(entity.getMemberIds());
        statement.bindString(4, _tmp);
        statement.bindString(5, entity.getLeaderUserId());
        final String _tmp_1 = __converters.fromTaskStatus(entity.getStatus());
        statement.bindString(6, _tmp_1);
        statement.bindLong(7, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfStudySession = new EntityInsertionAdapter<StudySession>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `study_sessions` (`id`,`dayKey`,`source`,`taskId`,`durationMinutes`,`createdAt`) VALUES (?,?,?,?,?,?)";
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
        statement.bindLong(6, entity.getCreatedAt());
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
        return "UPDATE OR ABORT `tasks` SET `id` = ?,`title` = ?,`description` = ?,`type` = ?,`difficulty` = ?,`status` = ?,`courseId` = ?,`chapterId` = ?,`experienceReward` = ?,`coinReward` = ?,`deadline` = ?,`estimatedMinutes` = ?,`progress` = ?,`createdAt` = ?,`completedAt` = ?,`parentTaskId` = ?,`order` = ?,`targetGrade` = ?,`actionType` = ?,`actionPayload` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Task entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getDescription());
        final String _tmp = __converters.fromTaskType(entity.getType());
        statement.bindString(4, _tmp);
        final String _tmp_1 = __converters.fromTaskDifficulty(entity.getDifficulty());
        statement.bindString(5, _tmp_1);
        final String _tmp_2 = __converters.fromTaskStatus(entity.getStatus());
        statement.bindString(6, _tmp_2);
        if (entity.getCourseId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCourseId());
        }
        if (entity.getChapterId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getChapterId());
        }
        statement.bindLong(9, entity.getExperienceReward());
        statement.bindLong(10, entity.getCoinReward());
        if (entity.getDeadline() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getDeadline());
        }
        statement.bindLong(12, entity.getEstimatedMinutes());
        statement.bindDouble(13, entity.getProgress());
        statement.bindLong(14, entity.getCreatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getCompletedAt());
        }
        if (entity.getParentTaskId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getParentTaskId());
        }
        statement.bindLong(17, entity.getOrder());
        if (entity.getTargetGrade() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getTargetGrade());
        }
        if (entity.getActionType() == null) {
          statement.bindNull(19);
        } else {
          statement.bindString(19, entity.getActionType());
        }
        if (entity.getActionPayload() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getActionPayload());
        }
        statement.bindString(21, entity.getId());
      }
    };
    this.__updateAdapterOfTaskProgress = new EntityDeletionOrUpdateAdapter<TaskProgress>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `task_progress` SET `id` = ?,`taskId` = ?,`userId` = ?,`progress` = ?,`timeSpentMinutes` = ?,`lastUpdatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TaskProgress entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTaskId());
        statement.bindString(3, entity.getUserId());
        statement.bindDouble(4, entity.getProgress());
        statement.bindLong(5, entity.getTimeSpentMinutes());
        statement.bindLong(6, entity.getLastUpdatedAt());
        statement.bindString(7, entity.getId());
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
        final String _tmp = __converters.fromTaskStatus(status);
        _stmt.bindString(_argIndex, _tmp);
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
        final String _tmp = __converters.fromTaskStatus(status);
        _stmt.bindString(_argIndex, _tmp);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final Task _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _result = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final Task _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _result = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
    final String _tmp = __converters.fromTaskStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp_1);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_2);
            final TaskStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_3);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
    final String _tmp = __converters.fromTaskType(type);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tasks"}, new Callable<List<Task>>() {
      @Override
      @NonNull
      public List<Task> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp_1);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_2);
            final TaskStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_3);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfDeadline = CursorUtil.getColumnIndexOrThrow(_cursor, "deadline");
          final int _cursorIndexOfEstimatedMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedMinutes");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfParentTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentTaskId");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final int _cursorIndexOfTargetGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "targetGrade");
          final int _cursorIndexOfActionType = CursorUtil.getColumnIndexOrThrow(_cursor, "actionType");
          final int _cursorIndexOfActionPayload = CursorUtil.getColumnIndexOrThrow(_cursor, "actionPayload");
          final List<Task> _result = new ArrayList<Task>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Task _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final TaskType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTaskType(_tmp);
            final TaskDifficulty _tmpDifficulty;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfDifficulty);
            _tmpDifficulty = __converters.toTaskDifficulty(_tmp_1);
            final TaskStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_2);
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
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final Long _tmpDeadline;
            if (_cursor.isNull(_cursorIndexOfDeadline)) {
              _tmpDeadline = null;
            } else {
              _tmpDeadline = _cursor.getLong(_cursorIndexOfDeadline);
            }
            final int _tmpEstimatedMinutes;
            _tmpEstimatedMinutes = _cursor.getInt(_cursorIndexOfEstimatedMinutes);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpParentTaskId;
            if (_cursor.isNull(_cursorIndexOfParentTaskId)) {
              _tmpParentTaskId = null;
            } else {
              _tmpParentTaskId = _cursor.getString(_cursorIndexOfParentTaskId);
            }
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            final String _tmpTargetGrade;
            if (_cursor.isNull(_cursorIndexOfTargetGrade)) {
              _tmpTargetGrade = null;
            } else {
              _tmpTargetGrade = _cursor.getString(_cursorIndexOfTargetGrade);
            }
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
            _item = new Task(_tmpId,_tmpTitle,_tmpDescription,_tmpType,_tmpDifficulty,_tmpStatus,_tmpCourseId,_tmpChapterId,_tmpExperienceReward,_tmpCoinReward,_tmpDeadline,_tmpEstimatedMinutes,_tmpProgress,_tmpCreatedAt,_tmpCompletedAt,_tmpParentTaskId,_tmpOrder,_tmpTargetGrade,_tmpActionType,_tmpActionPayload);
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
          final int _cursorIndexOfTimeSpentMinutes = CursorUtil.getColumnIndexOrThrow(_cursor, "timeSpentMinutes");
          final int _cursorIndexOfLastUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdatedAt");
          final TaskProgress _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final int _tmpTimeSpentMinutes;
            _tmpTimeSpentMinutes = _cursor.getInt(_cursorIndexOfTimeSpentMinutes);
            final long _tmpLastUpdatedAt;
            _tmpLastUpdatedAt = _cursor.getLong(_cursorIndexOfLastUpdatedAt);
            _result = new TaskProgress(_tmpId,_tmpTaskId,_tmpUserId,_tmpProgress,_tmpTimeSpentMinutes,_tmpLastUpdatedAt);
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
          final int _cursorIndexOfTaskId = CursorUtil.getColumnIndexOrThrow(_cursor, "taskId");
          final int _cursorIndexOfTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "teamId");
          final int _cursorIndexOfMemberIds = CursorUtil.getColumnIndexOrThrow(_cursor, "memberIds");
          final int _cursorIndexOfLeaderUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "leaderUserId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<TeamTask> _result = new ArrayList<TeamTask>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TeamTask _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTaskId;
            _tmpTaskId = _cursor.getString(_cursorIndexOfTaskId);
            final String _tmpTeamId;
            _tmpTeamId = _cursor.getString(_cursorIndexOfTeamId);
            final List<String> _tmpMemberIds;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfMemberIds);
            _tmpMemberIds = __converters.toStringList(_tmp);
            final String _tmpLeaderUserId;
            _tmpLeaderUserId = _cursor.getString(_cursorIndexOfLeaderUserId);
            final TaskStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTaskStatus(_tmp_1);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new TeamTask(_tmpId,_tmpTaskId,_tmpTeamId,_tmpMemberIds,_tmpLeaderUserId,_tmpStatus,_tmpCreatedAt);
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
