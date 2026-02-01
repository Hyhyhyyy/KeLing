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
import com.keling.app.data.model.Chapter;
import com.keling.app.data.model.Course;
import com.keling.app.data.model.CourseType;
import com.keling.app.data.model.Material;
import com.keling.app.data.model.MaterialType;
import com.keling.app.data.model.ScheduleItem;
import com.keling.app.data.model.WeekType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
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
public final class CourseDao_Impl implements CourseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Course> __insertionAdapterOfCourse;

  private final EntityInsertionAdapter<ScheduleItem> __insertionAdapterOfScheduleItem;

  private final EntityInsertionAdapter<Chapter> __insertionAdapterOfChapter;

  private final EntityInsertionAdapter<Material> __insertionAdapterOfMaterial;

  private final EntityDeletionOrUpdateAdapter<Course> __deletionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Course> __updateAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter<Chapter> __updateAdapterOfChapter;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllScheduleItems;

  private final SharedSQLiteStatement __preparedStmtOfDeleteScheduleItemById;

  public CourseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `courses` (`id`,`name`,`code`,`type`,`credits`,`teacherName`,`teacherId`,`location`,`description`,`coverImageUrl`,`semester`,`progress`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Course entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getCode());
        final String _tmp = Converters.INSTANCE.fromCourseType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getCredits());
        statement.bindString(6, entity.getTeacherName());
        if (entity.getTeacherId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getTeacherId());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLocation());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getDescription());
        }
        if (entity.getCoverImageUrl() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCoverImageUrl());
        }
        statement.bindString(11, entity.getSemester());
        statement.bindDouble(12, entity.getProgress());
      }
    };
    this.__insertionAdapterOfScheduleItem = new EntityInsertionAdapter<ScheduleItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `schedule_items` (`id`,`courseId`,`courseName`,`teacherName`,`dayOfWeek`,`startTime`,`endTime`,`location`,`weekStart`,`weekEnd`,`weekType`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScheduleItem entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getCourseId());
        statement.bindString(3, entity.getCourseName());
        statement.bindString(4, entity.getTeacherName());
        statement.bindLong(5, entity.getDayOfWeek());
        statement.bindString(6, entity.getStartTime());
        statement.bindString(7, entity.getEndTime());
        statement.bindString(8, entity.getLocation());
        statement.bindLong(9, entity.getWeekStart());
        statement.bindLong(10, entity.getWeekEnd());
        final String _tmp = Converters.INSTANCE.fromWeekType(entity.getWeekType());
        if (_tmp == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp);
        }
      }
    };
    this.__insertionAdapterOfChapter = new EntityInsertionAdapter<Chapter>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `chapters` (`id`,`courseId`,`title`,`orderIndex`,`description`,`contentUrl`,`duration`,`isCompleted`,`progress`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Chapter entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getCourseId());
        statement.bindString(3, entity.getTitle());
        statement.bindLong(4, entity.getOrderIndex());
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getContentUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getContentUrl());
        }
        statement.bindLong(7, entity.getDuration());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(8, _tmp);
        statement.bindDouble(9, entity.getProgress());
      }
    };
    this.__insertionAdapterOfMaterial = new EntityInsertionAdapter<Material>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `materials` (`id`,`courseId`,`chapterId`,`title`,`type`,`url`,`size`,`downloadedPath`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Material entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getCourseId());
        if (entity.getChapterId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getChapterId());
        }
        statement.bindString(4, entity.getTitle());
        final String _tmp = Converters.INSTANCE.fromMaterialType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp);
        }
        statement.bindString(6, entity.getUrl());
        statement.bindLong(7, entity.getSize());
        if (entity.getDownloadedPath() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDownloadedPath());
        }
        statement.bindLong(9, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `courses` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Course entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `courses` SET `id` = ?,`name` = ?,`code` = ?,`type` = ?,`credits` = ?,`teacherName` = ?,`teacherId` = ?,`location` = ?,`description` = ?,`coverImageUrl` = ?,`semester` = ?,`progress` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Course entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getCode());
        final String _tmp = Converters.INSTANCE.fromCourseType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getCredits());
        statement.bindString(6, entity.getTeacherName());
        if (entity.getTeacherId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getTeacherId());
        }
        if (entity.getLocation() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getLocation());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getDescription());
        }
        if (entity.getCoverImageUrl() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCoverImageUrl());
        }
        statement.bindString(11, entity.getSemester());
        statement.bindDouble(12, entity.getProgress());
        statement.bindString(13, entity.getId());
      }
    };
    this.__updateAdapterOfChapter = new EntityDeletionOrUpdateAdapter<Chapter>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `chapters` SET `id` = ?,`courseId` = ?,`title` = ?,`orderIndex` = ?,`description` = ?,`contentUrl` = ?,`duration` = ?,`isCompleted` = ?,`progress` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Chapter entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getCourseId());
        statement.bindString(3, entity.getTitle());
        statement.bindLong(4, entity.getOrderIndex());
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getContentUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getContentUrl());
        }
        statement.bindLong(7, entity.getDuration());
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(8, _tmp);
        statement.bindDouble(9, entity.getProgress());
        statement.bindString(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllScheduleItems = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM schedule_items";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteScheduleItemById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM schedule_items WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCourse(final Course course, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCourse.insert(course);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCourses(final List<Course> courses,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCourse.insert(courses);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertScheduleItem(final ScheduleItem item,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfScheduleItem.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertScheduleItems(final List<ScheduleItem> items,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfScheduleItem.insert(items);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertChapter(final Chapter chapter, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChapter.insert(chapter);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertChapters(final List<Chapter> chapters,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChapter.insert(chapters);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMaterial(final Material material,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMaterial.insert(material);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMaterials(final List<Material> materials,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMaterial.insert(materials);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCourse(final Course course, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCourse.handle(course);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCourse(final Course course, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCourse.handle(course);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateChapter(final Chapter chapter, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfChapter.handle(chapter);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllScheduleItems(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllScheduleItems.acquire();
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
          __preparedStmtOfDeleteAllScheduleItems.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteScheduleItemById(final String id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteScheduleItemById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
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
          __preparedStmtOfDeleteScheduleItemById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getCourseById(final String courseId,
      final Continuation<? super Course> $completion) {
    final String _sql = "SELECT * FROM courses WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, courseId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Course>() {
      @Override
      @Nullable
      public Course call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCredits = CursorUtil.getColumnIndexOrThrow(_cursor, "credits");
          final int _cursorIndexOfTeacherName = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherName");
          final int _cursorIndexOfTeacherId = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherId");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCoverImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImageUrl");
          final int _cursorIndexOfSemester = CursorUtil.getColumnIndexOrThrow(_cursor, "semester");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final Course _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final CourseType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            final CourseType _tmp_1 = Converters.INSTANCE.toCourseType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.CourseType', but it was NULL.");
            } else {
              _tmpType = _tmp_1;
            }
            final float _tmpCredits;
            _tmpCredits = _cursor.getFloat(_cursorIndexOfCredits);
            final String _tmpTeacherName;
            _tmpTeacherName = _cursor.getString(_cursorIndexOfTeacherName);
            final String _tmpTeacherId;
            if (_cursor.isNull(_cursorIndexOfTeacherId)) {
              _tmpTeacherId = null;
            } else {
              _tmpTeacherId = _cursor.getString(_cursorIndexOfTeacherId);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCoverImageUrl;
            if (_cursor.isNull(_cursorIndexOfCoverImageUrl)) {
              _tmpCoverImageUrl = null;
            } else {
              _tmpCoverImageUrl = _cursor.getString(_cursorIndexOfCoverImageUrl);
            }
            final String _tmpSemester;
            _tmpSemester = _cursor.getString(_cursorIndexOfSemester);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            _result = new Course(_tmpId,_tmpName,_tmpCode,_tmpType,_tmpCredits,_tmpTeacherName,_tmpTeacherId,_tmpLocation,_tmpDescription,_tmpCoverImageUrl,_tmpSemester,_tmpProgress);
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
  public Flow<Course> getCourseByIdFlow(final String courseId) {
    final String _sql = "SELECT * FROM courses WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, courseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"courses"}, new Callable<Course>() {
      @Override
      @Nullable
      public Course call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCredits = CursorUtil.getColumnIndexOrThrow(_cursor, "credits");
          final int _cursorIndexOfTeacherName = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherName");
          final int _cursorIndexOfTeacherId = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherId");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCoverImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImageUrl");
          final int _cursorIndexOfSemester = CursorUtil.getColumnIndexOrThrow(_cursor, "semester");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final Course _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final CourseType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            final CourseType _tmp_1 = Converters.INSTANCE.toCourseType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.CourseType', but it was NULL.");
            } else {
              _tmpType = _tmp_1;
            }
            final float _tmpCredits;
            _tmpCredits = _cursor.getFloat(_cursorIndexOfCredits);
            final String _tmpTeacherName;
            _tmpTeacherName = _cursor.getString(_cursorIndexOfTeacherName);
            final String _tmpTeacherId;
            if (_cursor.isNull(_cursorIndexOfTeacherId)) {
              _tmpTeacherId = null;
            } else {
              _tmpTeacherId = _cursor.getString(_cursorIndexOfTeacherId);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCoverImageUrl;
            if (_cursor.isNull(_cursorIndexOfCoverImageUrl)) {
              _tmpCoverImageUrl = null;
            } else {
              _tmpCoverImageUrl = _cursor.getString(_cursorIndexOfCoverImageUrl);
            }
            final String _tmpSemester;
            _tmpSemester = _cursor.getString(_cursorIndexOfSemester);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            _result = new Course(_tmpId,_tmpName,_tmpCode,_tmpType,_tmpCredits,_tmpTeacherName,_tmpTeacherId,_tmpLocation,_tmpDescription,_tmpCoverImageUrl,_tmpSemester,_tmpProgress);
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
  public Flow<List<Course>> getCoursesBySemester(final String semester) {
    final String _sql = "SELECT * FROM courses WHERE semester = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, semester);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"courses"}, new Callable<List<Course>>() {
      @Override
      @NonNull
      public List<Course> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCredits = CursorUtil.getColumnIndexOrThrow(_cursor, "credits");
          final int _cursorIndexOfTeacherName = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherName");
          final int _cursorIndexOfTeacherId = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherId");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCoverImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImageUrl");
          final int _cursorIndexOfSemester = CursorUtil.getColumnIndexOrThrow(_cursor, "semester");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Course _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final CourseType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            final CourseType _tmp_1 = Converters.INSTANCE.toCourseType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.CourseType', but it was NULL.");
            } else {
              _tmpType = _tmp_1;
            }
            final float _tmpCredits;
            _tmpCredits = _cursor.getFloat(_cursorIndexOfCredits);
            final String _tmpTeacherName;
            _tmpTeacherName = _cursor.getString(_cursorIndexOfTeacherName);
            final String _tmpTeacherId;
            if (_cursor.isNull(_cursorIndexOfTeacherId)) {
              _tmpTeacherId = null;
            } else {
              _tmpTeacherId = _cursor.getString(_cursorIndexOfTeacherId);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCoverImageUrl;
            if (_cursor.isNull(_cursorIndexOfCoverImageUrl)) {
              _tmpCoverImageUrl = null;
            } else {
              _tmpCoverImageUrl = _cursor.getString(_cursorIndexOfCoverImageUrl);
            }
            final String _tmpSemester;
            _tmpSemester = _cursor.getString(_cursorIndexOfSemester);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            _item = new Course(_tmpId,_tmpName,_tmpCode,_tmpType,_tmpCredits,_tmpTeacherName,_tmpTeacherId,_tmpLocation,_tmpDescription,_tmpCoverImageUrl,_tmpSemester,_tmpProgress);
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
  public Flow<List<Course>> getAllCourses() {
    final String _sql = "SELECT * FROM courses";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"courses"}, new Callable<List<Course>>() {
      @Override
      @NonNull
      public List<Course> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCredits = CursorUtil.getColumnIndexOrThrow(_cursor, "credits");
          final int _cursorIndexOfTeacherName = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherName");
          final int _cursorIndexOfTeacherId = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherId");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCoverImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "coverImageUrl");
          final int _cursorIndexOfSemester = CursorUtil.getColumnIndexOrThrow(_cursor, "semester");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Course _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final CourseType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            final CourseType _tmp_1 = Converters.INSTANCE.toCourseType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.CourseType', but it was NULL.");
            } else {
              _tmpType = _tmp_1;
            }
            final float _tmpCredits;
            _tmpCredits = _cursor.getFloat(_cursorIndexOfCredits);
            final String _tmpTeacherName;
            _tmpTeacherName = _cursor.getString(_cursorIndexOfTeacherName);
            final String _tmpTeacherId;
            if (_cursor.isNull(_cursorIndexOfTeacherId)) {
              _tmpTeacherId = null;
            } else {
              _tmpTeacherId = _cursor.getString(_cursorIndexOfTeacherId);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCoverImageUrl;
            if (_cursor.isNull(_cursorIndexOfCoverImageUrl)) {
              _tmpCoverImageUrl = null;
            } else {
              _tmpCoverImageUrl = _cursor.getString(_cursorIndexOfCoverImageUrl);
            }
            final String _tmpSemester;
            _tmpSemester = _cursor.getString(_cursorIndexOfSemester);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            _item = new Course(_tmpId,_tmpName,_tmpCode,_tmpType,_tmpCredits,_tmpTeacherName,_tmpTeacherId,_tmpLocation,_tmpDescription,_tmpCoverImageUrl,_tmpSemester,_tmpProgress);
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
  public Flow<List<ScheduleItem>> getScheduleByDay(final int dayOfWeek) {
    final String _sql = "SELECT * FROM schedule_items WHERE dayOfWeek = ? ORDER BY startTime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, dayOfWeek);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"schedule_items"}, new Callable<List<ScheduleItem>>() {
      @Override
      @NonNull
      public List<ScheduleItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "courseName");
          final int _cursorIndexOfTeacherName = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherName");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "dayOfWeek");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "weekStart");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "weekEnd");
          final int _cursorIndexOfWeekType = CursorUtil.getColumnIndexOrThrow(_cursor, "weekType");
          final List<ScheduleItem> _result = new ArrayList<ScheduleItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScheduleItem _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpCourseName;
            _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
            final String _tmpTeacherName;
            _tmpTeacherName = _cursor.getString(_cursorIndexOfTeacherName);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final String _tmpStartTime;
            _tmpStartTime = _cursor.getString(_cursorIndexOfStartTime);
            final String _tmpEndTime;
            _tmpEndTime = _cursor.getString(_cursorIndexOfEndTime);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final int _tmpWeekStart;
            _tmpWeekStart = _cursor.getInt(_cursorIndexOfWeekStart);
            final int _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getInt(_cursorIndexOfWeekEnd);
            final WeekType _tmpWeekType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfWeekType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfWeekType);
            }
            final WeekType _tmp_1 = Converters.INSTANCE.toWeekType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.WeekType', but it was NULL.");
            } else {
              _tmpWeekType = _tmp_1;
            }
            _item = new ScheduleItem(_tmpId,_tmpCourseId,_tmpCourseName,_tmpTeacherName,_tmpDayOfWeek,_tmpStartTime,_tmpEndTime,_tmpLocation,_tmpWeekStart,_tmpWeekEnd,_tmpWeekType);
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
  public Flow<List<ScheduleItem>> getAllScheduleItems() {
    final String _sql = "SELECT * FROM schedule_items ORDER BY dayOfWeek, startTime";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"schedule_items"}, new Callable<List<ScheduleItem>>() {
      @Override
      @NonNull
      public List<ScheduleItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "courseName");
          final int _cursorIndexOfTeacherName = CursorUtil.getColumnIndexOrThrow(_cursor, "teacherName");
          final int _cursorIndexOfDayOfWeek = CursorUtil.getColumnIndexOrThrow(_cursor, "dayOfWeek");
          final int _cursorIndexOfStartTime = CursorUtil.getColumnIndexOrThrow(_cursor, "startTime");
          final int _cursorIndexOfEndTime = CursorUtil.getColumnIndexOrThrow(_cursor, "endTime");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfWeekStart = CursorUtil.getColumnIndexOrThrow(_cursor, "weekStart");
          final int _cursorIndexOfWeekEnd = CursorUtil.getColumnIndexOrThrow(_cursor, "weekEnd");
          final int _cursorIndexOfWeekType = CursorUtil.getColumnIndexOrThrow(_cursor, "weekType");
          final List<ScheduleItem> _result = new ArrayList<ScheduleItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScheduleItem _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpCourseName;
            _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
            final String _tmpTeacherName;
            _tmpTeacherName = _cursor.getString(_cursorIndexOfTeacherName);
            final int _tmpDayOfWeek;
            _tmpDayOfWeek = _cursor.getInt(_cursorIndexOfDayOfWeek);
            final String _tmpStartTime;
            _tmpStartTime = _cursor.getString(_cursorIndexOfStartTime);
            final String _tmpEndTime;
            _tmpEndTime = _cursor.getString(_cursorIndexOfEndTime);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final int _tmpWeekStart;
            _tmpWeekStart = _cursor.getInt(_cursorIndexOfWeekStart);
            final int _tmpWeekEnd;
            _tmpWeekEnd = _cursor.getInt(_cursorIndexOfWeekEnd);
            final WeekType _tmpWeekType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfWeekType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfWeekType);
            }
            final WeekType _tmp_1 = Converters.INSTANCE.toWeekType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.WeekType', but it was NULL.");
            } else {
              _tmpWeekType = _tmp_1;
            }
            _item = new ScheduleItem(_tmpId,_tmpCourseId,_tmpCourseName,_tmpTeacherName,_tmpDayOfWeek,_tmpStartTime,_tmpEndTime,_tmpLocation,_tmpWeekStart,_tmpWeekEnd,_tmpWeekType);
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
  public Flow<List<Chapter>> getChaptersByCourse(final String courseId) {
    final String _sql = "SELECT * FROM chapters WHERE courseId = ? ORDER BY orderIndex";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, courseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"chapters"}, new Callable<List<Chapter>>() {
      @Override
      @NonNull
      public List<Chapter> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContentUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contentUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final List<Chapter> _result = new ArrayList<Chapter>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Chapter _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpContentUrl;
            if (_cursor.isNull(_cursorIndexOfContentUrl)) {
              _tmpContentUrl = null;
            } else {
              _tmpContentUrl = _cursor.getString(_cursorIndexOfContentUrl);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            _item = new Chapter(_tmpId,_tmpCourseId,_tmpTitle,_tmpOrderIndex,_tmpDescription,_tmpContentUrl,_tmpDuration,_tmpIsCompleted,_tmpProgress);
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
  public Object getChapterById(final String chapterId,
      final Continuation<? super Chapter> $completion) {
    final String _sql = "SELECT * FROM chapters WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, chapterId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Chapter>() {
      @Override
      @Nullable
      public Chapter call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfContentUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contentUrl");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final Chapter _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpContentUrl;
            if (_cursor.isNull(_cursorIndexOfContentUrl)) {
              _tmpContentUrl = null;
            } else {
              _tmpContentUrl = _cursor.getString(_cursorIndexOfContentUrl);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            _result = new Chapter(_tmpId,_tmpCourseId,_tmpTitle,_tmpOrderIndex,_tmpDescription,_tmpContentUrl,_tmpDuration,_tmpIsCompleted,_tmpProgress);
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
  public Flow<List<Material>> getMaterialsByCourse(final String courseId) {
    final String _sql = "SELECT * FROM materials WHERE courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, courseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"materials"}, new Callable<List<Material>>() {
      @Override
      @NonNull
      public List<Material> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfDownloadedPath = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedPath");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Material> _result = new ArrayList<Material>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Material _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final MaterialType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            final MaterialType _tmp_1 = Converters.INSTANCE.toMaterialType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.MaterialType', but it was NULL.");
            } else {
              _tmpType = _tmp_1;
            }
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final long _tmpSize;
            _tmpSize = _cursor.getLong(_cursorIndexOfSize);
            final String _tmpDownloadedPath;
            if (_cursor.isNull(_cursorIndexOfDownloadedPath)) {
              _tmpDownloadedPath = null;
            } else {
              _tmpDownloadedPath = _cursor.getString(_cursorIndexOfDownloadedPath);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Material(_tmpId,_tmpCourseId,_tmpChapterId,_tmpTitle,_tmpType,_tmpUrl,_tmpSize,_tmpDownloadedPath,_tmpCreatedAt);
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
  public Flow<List<Material>> getMaterialsByChapter(final String chapterId) {
    final String _sql = "SELECT * FROM materials WHERE chapterId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, chapterId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"materials"}, new Callable<List<Material>>() {
      @Override
      @NonNull
      public List<Material> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfDownloadedPath = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedPath");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Material> _result = new ArrayList<Material>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Material _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final MaterialType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            final MaterialType _tmp_1 = Converters.INSTANCE.toMaterialType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.MaterialType', but it was NULL.");
            } else {
              _tmpType = _tmp_1;
            }
            final String _tmpUrl;
            _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            final long _tmpSize;
            _tmpSize = _cursor.getLong(_cursorIndexOfSize);
            final String _tmpDownloadedPath;
            if (_cursor.isNull(_cursorIndexOfDownloadedPath)) {
              _tmpDownloadedPath = null;
            } else {
              _tmpDownloadedPath = _cursor.getString(_cursorIndexOfDownloadedPath);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Material(_tmpId,_tmpCourseId,_tmpChapterId,_tmpTitle,_tmpType,_tmpUrl,_tmpSize,_tmpDownloadedPath,_tmpCreatedAt);
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
