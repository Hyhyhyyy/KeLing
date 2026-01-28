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
import com.keling.app.data.model.Achievement;
import com.keling.app.data.model.AchievementCategory;
import com.keling.app.data.model.AchievementRarity;
import com.keling.app.data.model.Badge;
import com.keling.app.data.model.UserAchievement;
import com.keling.app.data.model.UserBadge;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class AchievementDao_Impl implements AchievementDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Achievement> __insertionAdapterOfAchievement;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<UserAchievement> __insertionAdapterOfUserAchievement;

  private final EntityInsertionAdapter<Badge> __insertionAdapterOfBadge;

  private final EntityInsertionAdapter<UserBadge> __insertionAdapterOfUserBadge;

  private final EntityDeletionOrUpdateAdapter<UserAchievement> __updateAdapterOfUserAchievement;

  private final SharedSQLiteStatement __preparedStmtOfUpdateBadgeEquipped;

  public AchievementDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAchievement = new EntityInsertionAdapter<Achievement>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `achievements` (`id`,`name`,`description`,`category`,`rarity`,`iconName`,`experienceReward`,`coinReward`,`conditionType`,`conditionValue`,`order`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Achievement entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        final String _tmp = __converters.fromAchievementCategory(entity.getCategory());
        statement.bindString(4, _tmp);
        final String _tmp_1 = __converters.fromAchievementRarity(entity.getRarity());
        statement.bindString(5, _tmp_1);
        statement.bindString(6, entity.getIconName());
        statement.bindLong(7, entity.getExperienceReward());
        statement.bindLong(8, entity.getCoinReward());
        statement.bindString(9, entity.getConditionType());
        statement.bindLong(10, entity.getConditionValue());
        statement.bindLong(11, entity.getOrder());
      }
    };
    this.__insertionAdapterOfUserAchievement = new EntityInsertionAdapter<UserAchievement>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_achievements` (`id`,`achievementId`,`userId`,`unlockedAt`,`progress`,`isUnlocked`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserAchievement entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAchievementId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getUnlockedAt());
        statement.bindDouble(5, entity.getProgress());
        final int _tmp = entity.isUnlocked() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__insertionAdapterOfBadge = new EntityInsertionAdapter<Badge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `badges` (`id`,`name`,`description`,`iconName`,`color`,`tier`,`category`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Badge entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getDescription());
        statement.bindString(4, entity.getIconName());
        statement.bindString(5, entity.getColor());
        statement.bindLong(6, entity.getTier());
        statement.bindString(7, entity.getCategory());
      }
    };
    this.__insertionAdapterOfUserBadge = new EntityInsertionAdapter<UserBadge>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_badges` (`id`,`badgeId`,`userId`,`obtainedAt`,`isEquipped`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserBadge entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getBadgeId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getObtainedAt());
        final int _tmp = entity.isEquipped() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
    this.__updateAdapterOfUserAchievement = new EntityDeletionOrUpdateAdapter<UserAchievement>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_achievements` SET `id` = ?,`achievementId` = ?,`userId` = ?,`unlockedAt` = ?,`progress` = ?,`isUnlocked` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserAchievement entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAchievementId());
        statement.bindString(3, entity.getUserId());
        statement.bindLong(4, entity.getUnlockedAt());
        statement.bindDouble(5, entity.getProgress());
        final int _tmp = entity.isUnlocked() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindString(7, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateBadgeEquipped = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE user_badges SET isEquipped = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAchievement(final Achievement achievement,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAchievement.insert(achievement);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAchievements(final List<Achievement> achievements,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAchievement.insert(achievements);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertUserAchievement(final UserAchievement userAchievement,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserAchievement.insert(userAchievement);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertBadges(final List<Badge> badges,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBadge.insert(badges);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertUserBadge(final UserBadge userBadge,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserBadge.insert(userBadge);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUserAchievement(final UserAchievement userAchievement,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserAchievement.handle(userAchievement);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBadgeEquipped(final String id, final boolean isEquipped,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateBadgeEquipped.acquire();
        int _argIndex = 1;
        final int _tmp = isEquipped ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
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
          __preparedStmtOfUpdateBadgeEquipped.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Achievement>> getAllAchievements() {
    final String _sql = "SELECT * FROM achievements ORDER BY category, `order`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"achievements"}, new Callable<List<Achievement>>() {
      @Override
      @NonNull
      public List<Achievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfRarity = CursorUtil.getColumnIndexOrThrow(_cursor, "rarity");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfConditionType = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionType");
          final int _cursorIndexOfConditionValue = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionValue");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final List<Achievement> _result = new ArrayList<Achievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Achievement _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final AchievementCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toAchievementCategory(_tmp);
            final AchievementRarity _tmpRarity;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRarity);
            _tmpRarity = __converters.toAchievementRarity(_tmp_1);
            final String _tmpIconName;
            _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final String _tmpConditionType;
            _tmpConditionType = _cursor.getString(_cursorIndexOfConditionType);
            final int _tmpConditionValue;
            _tmpConditionValue = _cursor.getInt(_cursorIndexOfConditionValue);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            _item = new Achievement(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpRarity,_tmpIconName,_tmpExperienceReward,_tmpCoinReward,_tmpConditionType,_tmpConditionValue,_tmpOrder);
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
  public Flow<List<Achievement>> getAchievementsByCategory(final AchievementCategory category) {
    final String _sql = "SELECT * FROM achievements WHERE category = ? ORDER BY `order`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromAchievementCategory(category);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"achievements"}, new Callable<List<Achievement>>() {
      @Override
      @NonNull
      public List<Achievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfRarity = CursorUtil.getColumnIndexOrThrow(_cursor, "rarity");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfConditionType = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionType");
          final int _cursorIndexOfConditionValue = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionValue");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final List<Achievement> _result = new ArrayList<Achievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Achievement _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final AchievementCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toAchievementCategory(_tmp_1);
            final AchievementRarity _tmpRarity;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfRarity);
            _tmpRarity = __converters.toAchievementRarity(_tmp_2);
            final String _tmpIconName;
            _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final String _tmpConditionType;
            _tmpConditionType = _cursor.getString(_cursorIndexOfConditionType);
            final int _tmpConditionValue;
            _tmpConditionValue = _cursor.getInt(_cursorIndexOfConditionValue);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            _item = new Achievement(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpRarity,_tmpIconName,_tmpExperienceReward,_tmpCoinReward,_tmpConditionType,_tmpConditionValue,_tmpOrder);
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
  public Object getAchievementById(final String achievementId,
      final Continuation<? super Achievement> $completion) {
    final String _sql = "SELECT * FROM achievements WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, achievementId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Achievement>() {
      @Override
      @Nullable
      public Achievement call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfRarity = CursorUtil.getColumnIndexOrThrow(_cursor, "rarity");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfExperienceReward = CursorUtil.getColumnIndexOrThrow(_cursor, "experienceReward");
          final int _cursorIndexOfCoinReward = CursorUtil.getColumnIndexOrThrow(_cursor, "coinReward");
          final int _cursorIndexOfConditionType = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionType");
          final int _cursorIndexOfConditionValue = CursorUtil.getColumnIndexOrThrow(_cursor, "conditionValue");
          final int _cursorIndexOfOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "order");
          final Achievement _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final AchievementCategory _tmpCategory;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toAchievementCategory(_tmp);
            final AchievementRarity _tmpRarity;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfRarity);
            _tmpRarity = __converters.toAchievementRarity(_tmp_1);
            final String _tmpIconName;
            _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            final int _tmpExperienceReward;
            _tmpExperienceReward = _cursor.getInt(_cursorIndexOfExperienceReward);
            final int _tmpCoinReward;
            _tmpCoinReward = _cursor.getInt(_cursorIndexOfCoinReward);
            final String _tmpConditionType;
            _tmpConditionType = _cursor.getString(_cursorIndexOfConditionType);
            final int _tmpConditionValue;
            _tmpConditionValue = _cursor.getInt(_cursorIndexOfConditionValue);
            final int _tmpOrder;
            _tmpOrder = _cursor.getInt(_cursorIndexOfOrder);
            _result = new Achievement(_tmpId,_tmpName,_tmpDescription,_tmpCategory,_tmpRarity,_tmpIconName,_tmpExperienceReward,_tmpCoinReward,_tmpConditionType,_tmpConditionValue,_tmpOrder);
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
  public Flow<List<UserAchievement>> getUserAchievements(final String userId) {
    final String _sql = "SELECT * FROM user_achievements WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_achievements"}, new Callable<List<UserAchievement>>() {
      @Override
      @NonNull
      public List<UserAchievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAchievementId = CursorUtil.getColumnIndexOrThrow(_cursor, "achievementId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUnlockedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedAt");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfIsUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isUnlocked");
          final List<UserAchievement> _result = new ArrayList<UserAchievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserAchievement _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAchievementId;
            _tmpAchievementId = _cursor.getString(_cursorIndexOfAchievementId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final long _tmpUnlockedAt;
            _tmpUnlockedAt = _cursor.getLong(_cursorIndexOfUnlockedAt);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final boolean _tmpIsUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUnlocked);
            _tmpIsUnlocked = _tmp != 0;
            _item = new UserAchievement(_tmpId,_tmpAchievementId,_tmpUserId,_tmpUnlockedAt,_tmpProgress,_tmpIsUnlocked);
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
  public Flow<List<UserAchievement>> getUnlockedAchievements(final String userId) {
    final String _sql = "SELECT * FROM user_achievements WHERE userId = ? AND isUnlocked = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_achievements"}, new Callable<List<UserAchievement>>() {
      @Override
      @NonNull
      public List<UserAchievement> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAchievementId = CursorUtil.getColumnIndexOrThrow(_cursor, "achievementId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUnlockedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedAt");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfIsUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isUnlocked");
          final List<UserAchievement> _result = new ArrayList<UserAchievement>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserAchievement _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAchievementId;
            _tmpAchievementId = _cursor.getString(_cursorIndexOfAchievementId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final long _tmpUnlockedAt;
            _tmpUnlockedAt = _cursor.getLong(_cursorIndexOfUnlockedAt);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final boolean _tmpIsUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUnlocked);
            _tmpIsUnlocked = _tmp != 0;
            _item = new UserAchievement(_tmpId,_tmpAchievementId,_tmpUserId,_tmpUnlockedAt,_tmpProgress,_tmpIsUnlocked);
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
  public Object getUserAchievement(final String userId, final String achievementId,
      final Continuation<? super UserAchievement> $completion) {
    final String _sql = "SELECT * FROM user_achievements WHERE userId = ? AND achievementId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, achievementId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserAchievement>() {
      @Override
      @Nullable
      public UserAchievement call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAchievementId = CursorUtil.getColumnIndexOrThrow(_cursor, "achievementId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUnlockedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "unlockedAt");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfIsUnlocked = CursorUtil.getColumnIndexOrThrow(_cursor, "isUnlocked");
          final UserAchievement _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAchievementId;
            _tmpAchievementId = _cursor.getString(_cursorIndexOfAchievementId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final long _tmpUnlockedAt;
            _tmpUnlockedAt = _cursor.getLong(_cursorIndexOfUnlockedAt);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final boolean _tmpIsUnlocked;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUnlocked);
            _tmpIsUnlocked = _tmp != 0;
            _result = new UserAchievement(_tmpId,_tmpAchievementId,_tmpUserId,_tmpUnlockedAt,_tmpProgress,_tmpIsUnlocked);
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
  public Flow<Integer> getUnlockedAchievementCount(final String userId) {
    final String _sql = "SELECT COUNT(*) FROM user_achievements WHERE userId = ? AND isUnlocked = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_achievements"}, new Callable<Integer>() {
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
  public Flow<List<Badge>> getAllBadges() {
    final String _sql = "SELECT * FROM badges ORDER BY tier, category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"badges"}, new Callable<List<Badge>>() {
      @Override
      @NonNull
      public List<Badge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfIconName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconName");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfTier = CursorUtil.getColumnIndexOrThrow(_cursor, "tier");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final List<Badge> _result = new ArrayList<Badge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Badge _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpIconName;
            _tmpIconName = _cursor.getString(_cursorIndexOfIconName);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final int _tmpTier;
            _tmpTier = _cursor.getInt(_cursorIndexOfTier);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            _item = new Badge(_tmpId,_tmpName,_tmpDescription,_tmpIconName,_tmpColor,_tmpTier,_tmpCategory);
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
  public Flow<List<UserBadge>> getUserBadges(final String userId) {
    final String _sql = "SELECT * FROM user_badges WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_badges"}, new Callable<List<UserBadge>>() {
      @Override
      @NonNull
      public List<UserBadge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBadgeId = CursorUtil.getColumnIndexOrThrow(_cursor, "badgeId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfObtainedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "obtainedAt");
          final int _cursorIndexOfIsEquipped = CursorUtil.getColumnIndexOrThrow(_cursor, "isEquipped");
          final List<UserBadge> _result = new ArrayList<UserBadge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserBadge _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpBadgeId;
            _tmpBadgeId = _cursor.getString(_cursorIndexOfBadgeId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final long _tmpObtainedAt;
            _tmpObtainedAt = _cursor.getLong(_cursorIndexOfObtainedAt);
            final boolean _tmpIsEquipped;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEquipped);
            _tmpIsEquipped = _tmp != 0;
            _item = new UserBadge(_tmpId,_tmpBadgeId,_tmpUserId,_tmpObtainedAt,_tmpIsEquipped);
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
  public Flow<List<UserBadge>> getEquippedBadges(final String userId) {
    final String _sql = "SELECT * FROM user_badges WHERE userId = ? AND isEquipped = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_badges"}, new Callable<List<UserBadge>>() {
      @Override
      @NonNull
      public List<UserBadge> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBadgeId = CursorUtil.getColumnIndexOrThrow(_cursor, "badgeId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfObtainedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "obtainedAt");
          final int _cursorIndexOfIsEquipped = CursorUtil.getColumnIndexOrThrow(_cursor, "isEquipped");
          final List<UserBadge> _result = new ArrayList<UserBadge>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserBadge _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpBadgeId;
            _tmpBadgeId = _cursor.getString(_cursorIndexOfBadgeId);
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final long _tmpObtainedAt;
            _tmpObtainedAt = _cursor.getLong(_cursorIndexOfObtainedAt);
            final boolean _tmpIsEquipped;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEquipped);
            _tmpIsEquipped = _tmp != 0;
            _item = new UserBadge(_tmpId,_tmpBadgeId,_tmpUserId,_tmpObtainedAt,_tmpIsEquipped);
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
