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
import com.keling.app.data.model.KnowledgePoint;
import com.keling.app.data.model.KnowledgeRelation;
import com.keling.app.data.model.LearningRecord;
import com.keling.app.data.model.RelationType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
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
public final class KnowledgeDao_Impl implements KnowledgeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<KnowledgePoint> __insertionAdapterOfKnowledgePoint;

  private final EntityInsertionAdapter<KnowledgeRelation> __insertionAdapterOfKnowledgeRelation;

  private final EntityInsertionAdapter<LearningRecord> __insertionAdapterOfLearningRecord;

  private final EntityDeletionOrUpdateAdapter<KnowledgePoint> __updateAdapterOfKnowledgePoint;

  private final SharedSQLiteStatement __preparedStmtOfUpdateMasteryLevel;

  public KnowledgeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfKnowledgePoint = new EntityInsertionAdapter<KnowledgePoint>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `knowledge_points` (`id`,`name`,`description`,`courseId`,`chapterId`,`difficulty`,`importance`,`masteryLevel`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final KnowledgePoint entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindString(4, entity.getCourseId());
        if (entity.getChapterId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getChapterId());
        }
        statement.bindLong(6, entity.getDifficulty());
        statement.bindLong(7, entity.getImportance());
        statement.bindDouble(8, entity.getMasteryLevel());
      }
    };
    this.__insertionAdapterOfKnowledgeRelation = new EntityInsertionAdapter<KnowledgeRelation>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `knowledge_relations` (`id`,`fromPointId`,`toPointId`,`relationType`,`weight`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final KnowledgeRelation entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getFromPointId());
        statement.bindString(3, entity.getToPointId());
        final String _tmp = Converters.INSTANCE.fromRelationType(entity.getRelationType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getWeight());
      }
    };
    this.__insertionAdapterOfLearningRecord = new EntityInsertionAdapter<LearningRecord>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `learning_records` (`id`,`userId`,`knowledgePointId`,`questionId`,`isCorrect`,`timeSpentSeconds`,`attemptCount`,`recordedAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LearningRecord entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUserId());
        statement.bindString(3, entity.getKnowledgePointId());
        if (entity.getQuestionId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getQuestionId());
        }
        final int _tmp = entity.isCorrect() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getTimeSpentSeconds());
        statement.bindLong(7, entity.getAttemptCount());
        statement.bindLong(8, entity.getRecordedAt());
      }
    };
    this.__updateAdapterOfKnowledgePoint = new EntityDeletionOrUpdateAdapter<KnowledgePoint>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `knowledge_points` SET `id` = ?,`name` = ?,`description` = ?,`courseId` = ?,`chapterId` = ?,`difficulty` = ?,`importance` = ?,`masteryLevel` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final KnowledgePoint entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        statement.bindString(4, entity.getCourseId());
        if (entity.getChapterId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getChapterId());
        }
        statement.bindLong(6, entity.getDifficulty());
        statement.bindLong(7, entity.getImportance());
        statement.bindDouble(8, entity.getMasteryLevel());
        statement.bindString(9, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateMasteryLevel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE knowledge_points SET masteryLevel = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertKnowledgePoint(final KnowledgePoint point,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfKnowledgePoint.insert(point);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertKnowledgePoints(final List<KnowledgePoint> points,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfKnowledgePoint.insert(points);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRelation(final KnowledgeRelation relation,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfKnowledgeRelation.insert(relation);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRelations(final List<KnowledgeRelation> relations,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfKnowledgeRelation.insert(relations);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLearningRecord(final LearningRecord record,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLearningRecord.insert(record);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLearningRecords(final List<LearningRecord> records,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLearningRecord.insert(records);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateKnowledgePoint(final KnowledgePoint point,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfKnowledgePoint.handle(point);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMasteryLevel(final String pointId, final float level,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateMasteryLevel.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, level);
        _argIndex = 2;
        _stmt.bindString(_argIndex, pointId);
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
          __preparedStmtOfUpdateMasteryLevel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<KnowledgePoint>> getKnowledgePointsByCourse(final String courseId) {
    final String _sql = "SELECT * FROM knowledge_points WHERE courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, courseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"knowledge_points"}, new Callable<List<KnowledgePoint>>() {
      @Override
      @NonNull
      public List<KnowledgePoint> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImportance = CursorUtil.getColumnIndexOrThrow(_cursor, "importance");
          final int _cursorIndexOfMasteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "masteryLevel");
          final List<KnowledgePoint> _result = new ArrayList<KnowledgePoint>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KnowledgePoint _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpImportance;
            _tmpImportance = _cursor.getInt(_cursorIndexOfImportance);
            final float _tmpMasteryLevel;
            _tmpMasteryLevel = _cursor.getFloat(_cursorIndexOfMasteryLevel);
            _item = new KnowledgePoint(_tmpId,_tmpName,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpDifficulty,_tmpImportance,_tmpMasteryLevel);
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
  public Object getKnowledgePointById(final String pointId,
      final Continuation<? super KnowledgePoint> $completion) {
    final String _sql = "SELECT * FROM knowledge_points WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, pointId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<KnowledgePoint>() {
      @Override
      @Nullable
      public KnowledgePoint call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImportance = CursorUtil.getColumnIndexOrThrow(_cursor, "importance");
          final int _cursorIndexOfMasteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "masteryLevel");
          final KnowledgePoint _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpImportance;
            _tmpImportance = _cursor.getInt(_cursorIndexOfImportance);
            final float _tmpMasteryLevel;
            _tmpMasteryLevel = _cursor.getFloat(_cursorIndexOfMasteryLevel);
            _result = new KnowledgePoint(_tmpId,_tmpName,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpDifficulty,_tmpImportance,_tmpMasteryLevel);
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
  public Flow<List<KnowledgePoint>> getWeakKnowledgePoints(final float threshold) {
    final String _sql = "SELECT * FROM knowledge_points WHERE masteryLevel < ? ORDER BY masteryLevel ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, threshold);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"knowledge_points"}, new Callable<List<KnowledgePoint>>() {
      @Override
      @NonNull
      public List<KnowledgePoint> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImportance = CursorUtil.getColumnIndexOrThrow(_cursor, "importance");
          final int _cursorIndexOfMasteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "masteryLevel");
          final List<KnowledgePoint> _result = new ArrayList<KnowledgePoint>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KnowledgePoint _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpImportance;
            _tmpImportance = _cursor.getInt(_cursorIndexOfImportance);
            final float _tmpMasteryLevel;
            _tmpMasteryLevel = _cursor.getFloat(_cursorIndexOfMasteryLevel);
            _item = new KnowledgePoint(_tmpId,_tmpName,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpDifficulty,_tmpImportance,_tmpMasteryLevel);
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
  public Flow<List<KnowledgePoint>> getStrongKnowledgePoints(final float threshold) {
    final String _sql = "SELECT * FROM knowledge_points WHERE masteryLevel >= ? ORDER BY masteryLevel DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, threshold);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"knowledge_points"}, new Callable<List<KnowledgePoint>>() {
      @Override
      @NonNull
      public List<KnowledgePoint> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfChapterId = CursorUtil.getColumnIndexOrThrow(_cursor, "chapterId");
          final int _cursorIndexOfDifficulty = CursorUtil.getColumnIndexOrThrow(_cursor, "difficulty");
          final int _cursorIndexOfImportance = CursorUtil.getColumnIndexOrThrow(_cursor, "importance");
          final int _cursorIndexOfMasteryLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "masteryLevel");
          final List<KnowledgePoint> _result = new ArrayList<KnowledgePoint>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KnowledgePoint _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpChapterId;
            if (_cursor.isNull(_cursorIndexOfChapterId)) {
              _tmpChapterId = null;
            } else {
              _tmpChapterId = _cursor.getString(_cursorIndexOfChapterId);
            }
            final int _tmpDifficulty;
            _tmpDifficulty = _cursor.getInt(_cursorIndexOfDifficulty);
            final int _tmpImportance;
            _tmpImportance = _cursor.getInt(_cursorIndexOfImportance);
            final float _tmpMasteryLevel;
            _tmpMasteryLevel = _cursor.getFloat(_cursorIndexOfMasteryLevel);
            _item = new KnowledgePoint(_tmpId,_tmpName,_tmpDescription,_tmpCourseId,_tmpChapterId,_tmpDifficulty,_tmpImportance,_tmpMasteryLevel);
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
  public Flow<List<KnowledgeRelation>> getRelationsFrom(final String pointId) {
    final String _sql = "SELECT * FROM knowledge_relations WHERE fromPointId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, pointId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"knowledge_relations"}, new Callable<List<KnowledgeRelation>>() {
      @Override
      @NonNull
      public List<KnowledgeRelation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromPointId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromPointId");
          final int _cursorIndexOfToPointId = CursorUtil.getColumnIndexOrThrow(_cursor, "toPointId");
          final int _cursorIndexOfRelationType = CursorUtil.getColumnIndexOrThrow(_cursor, "relationType");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final List<KnowledgeRelation> _result = new ArrayList<KnowledgeRelation>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KnowledgeRelation _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFromPointId;
            _tmpFromPointId = _cursor.getString(_cursorIndexOfFromPointId);
            final String _tmpToPointId;
            _tmpToPointId = _cursor.getString(_cursorIndexOfToPointId);
            final RelationType _tmpRelationType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRelationType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRelationType);
            }
            final RelationType _tmp_1 = Converters.INSTANCE.toRelationType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.RelationType', but it was NULL.");
            } else {
              _tmpRelationType = _tmp_1;
            }
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            _item = new KnowledgeRelation(_tmpId,_tmpFromPointId,_tmpToPointId,_tmpRelationType,_tmpWeight);
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
  public Flow<List<KnowledgeRelation>> getRelationsTo(final String pointId) {
    final String _sql = "SELECT * FROM knowledge_relations WHERE toPointId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, pointId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"knowledge_relations"}, new Callable<List<KnowledgeRelation>>() {
      @Override
      @NonNull
      public List<KnowledgeRelation> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromPointId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromPointId");
          final int _cursorIndexOfToPointId = CursorUtil.getColumnIndexOrThrow(_cursor, "toPointId");
          final int _cursorIndexOfRelationType = CursorUtil.getColumnIndexOrThrow(_cursor, "relationType");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final List<KnowledgeRelation> _result = new ArrayList<KnowledgeRelation>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final KnowledgeRelation _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpFromPointId;
            _tmpFromPointId = _cursor.getString(_cursorIndexOfFromPointId);
            final String _tmpToPointId;
            _tmpToPointId = _cursor.getString(_cursorIndexOfToPointId);
            final RelationType _tmpRelationType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRelationType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRelationType);
            }
            final RelationType _tmp_1 = Converters.INSTANCE.toRelationType(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.RelationType', but it was NULL.");
            } else {
              _tmpRelationType = _tmp_1;
            }
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            _item = new KnowledgeRelation(_tmpId,_tmpFromPointId,_tmpToPointId,_tmpRelationType,_tmpWeight);
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
  public Flow<List<LearningRecord>> getLearningRecords(final String userId) {
    final String _sql = "SELECT * FROM learning_records WHERE userId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"learning_records"}, new Callable<List<LearningRecord>>() {
      @Override
      @NonNull
      public List<LearningRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfKnowledgePointId = CursorUtil.getColumnIndexOrThrow(_cursor, "knowledgePointId");
          final int _cursorIndexOfQuestionId = CursorUtil.getColumnIndexOrThrow(_cursor, "questionId");
          final int _cursorIndexOfIsCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "isCorrect");
          final int _cursorIndexOfTimeSpentSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "timeSpentSeconds");
          final int _cursorIndexOfAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "attemptCount");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final List<LearningRecord> _result = new ArrayList<LearningRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LearningRecord _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpKnowledgePointId;
            _tmpKnowledgePointId = _cursor.getString(_cursorIndexOfKnowledgePointId);
            final String _tmpQuestionId;
            if (_cursor.isNull(_cursorIndexOfQuestionId)) {
              _tmpQuestionId = null;
            } else {
              _tmpQuestionId = _cursor.getString(_cursorIndexOfQuestionId);
            }
            final boolean _tmpIsCorrect;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCorrect);
            _tmpIsCorrect = _tmp != 0;
            final int _tmpTimeSpentSeconds;
            _tmpTimeSpentSeconds = _cursor.getInt(_cursorIndexOfTimeSpentSeconds);
            final int _tmpAttemptCount;
            _tmpAttemptCount = _cursor.getInt(_cursorIndexOfAttemptCount);
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            _item = new LearningRecord(_tmpId,_tmpUserId,_tmpKnowledgePointId,_tmpQuestionId,_tmpIsCorrect,_tmpTimeSpentSeconds,_tmpAttemptCount,_tmpRecordedAt);
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
  public Flow<List<LearningRecord>> getLearningRecordsForPoint(final String userId,
      final String pointId) {
    final String _sql = "SELECT * FROM learning_records WHERE userId = ? AND knowledgePointId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, pointId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"learning_records"}, new Callable<List<LearningRecord>>() {
      @Override
      @NonNull
      public List<LearningRecord> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfKnowledgePointId = CursorUtil.getColumnIndexOrThrow(_cursor, "knowledgePointId");
          final int _cursorIndexOfQuestionId = CursorUtil.getColumnIndexOrThrow(_cursor, "questionId");
          final int _cursorIndexOfIsCorrect = CursorUtil.getColumnIndexOrThrow(_cursor, "isCorrect");
          final int _cursorIndexOfTimeSpentSeconds = CursorUtil.getColumnIndexOrThrow(_cursor, "timeSpentSeconds");
          final int _cursorIndexOfAttemptCount = CursorUtil.getColumnIndexOrThrow(_cursor, "attemptCount");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final List<LearningRecord> _result = new ArrayList<LearningRecord>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LearningRecord _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpKnowledgePointId;
            _tmpKnowledgePointId = _cursor.getString(_cursorIndexOfKnowledgePointId);
            final String _tmpQuestionId;
            if (_cursor.isNull(_cursorIndexOfQuestionId)) {
              _tmpQuestionId = null;
            } else {
              _tmpQuestionId = _cursor.getString(_cursorIndexOfQuestionId);
            }
            final boolean _tmpIsCorrect;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCorrect);
            _tmpIsCorrect = _tmp != 0;
            final int _tmpTimeSpentSeconds;
            _tmpTimeSpentSeconds = _cursor.getInt(_cursorIndexOfTimeSpentSeconds);
            final int _tmpAttemptCount;
            _tmpAttemptCount = _cursor.getInt(_cursorIndexOfAttemptCount);
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            _item = new LearningRecord(_tmpId,_tmpUserId,_tmpKnowledgePointId,_tmpQuestionId,_tmpIsCorrect,_tmpTimeSpentSeconds,_tmpAttemptCount,_tmpRecordedAt);
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
  public Object getAccuracyForPoint(final String userId, final String pointId,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT AVG(CASE WHEN isCorrect THEN 1.0 ELSE 0.0 END) FROM learning_records WHERE userId = ? AND knowledgePointId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, pointId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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
  public Object getOverallAccuracy(final String userId,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT AVG(CASE WHEN isCorrect THEN 1.0 ELSE 0.0 END) FROM learning_records WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
