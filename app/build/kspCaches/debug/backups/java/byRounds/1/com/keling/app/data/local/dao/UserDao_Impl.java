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
import com.keling.app.data.model.PrivacyLevel;
import com.keling.app.data.model.User;
import com.keling.app.data.model.UserRole;
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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllUsers;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastLogin;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`username`,`realName`,`role`,`avatarUrl`,`schoolId`,`classId`,`grade`,`email`,`phone`,`privacyLevel`,`createdAt`,`lastLoginAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getRealName());
        final String _tmp = Converters.INSTANCE.fromUserRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        if (entity.getAvatarUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAvatarUrl());
        }
        if (entity.getSchoolId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSchoolId());
        }
        if (entity.getClassId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getClassId());
        }
        if (entity.getGrade() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getGrade());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getEmail());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPhone());
        }
        final String _tmp_1 = Converters.INSTANCE.fromPrivacyLevel(entity.getPrivacyLevel());
        if (_tmp_1 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp_1);
        }
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getLastLoginAt());
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `users` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`username` = ?,`realName` = ?,`role` = ?,`avatarUrl` = ?,`schoolId` = ?,`classId` = ?,`grade` = ?,`email` = ?,`phone` = ?,`privacyLevel` = ?,`createdAt` = ?,`lastLoginAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getUsername());
        statement.bindString(3, entity.getRealName());
        final String _tmp = Converters.INSTANCE.fromUserRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        if (entity.getAvatarUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getAvatarUrl());
        }
        if (entity.getSchoolId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSchoolId());
        }
        if (entity.getClassId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getClassId());
        }
        if (entity.getGrade() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getGrade());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getEmail());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getPhone());
        }
        final String _tmp_1 = Converters.INSTANCE.fromPrivacyLevel(entity.getPrivacyLevel());
        if (_tmp_1 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp_1);
        }
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getLastLoginAt());
        statement.bindString(14, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllUsers = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateLastLogin = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET lastLoginAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertUser(final User user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUser.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertUsers(final List<User> users, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUser.insert(users);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUser(final User user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUser.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateUser(final User user, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUser.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllUsers(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllUsers.acquire();
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
          __preparedStmtOfDeleteAllUsers.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLastLogin(final String userId, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastLogin.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindString(_argIndex, userId);
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
          __preparedStmtOfUpdateLastLogin.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getUserById(final String userId, final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfRealName = CursorUtil.getColumnIndexOrThrow(_cursor, "realName");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarUrl");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfClassId = CursorUtil.getColumnIndexOrThrow(_cursor, "classId");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPrivacyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "privacyLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastLoginAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastLoginAt");
          final User _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpRealName;
            _tmpRealName = _cursor.getString(_cursorIndexOfRealName);
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            final UserRole _tmp_1 = Converters.INSTANCE.toUserRole(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.UserRole', but it was NULL.");
            } else {
              _tmpRole = _tmp_1;
            }
            final String _tmpAvatarUrl;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatarUrl = null;
            } else {
              _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final String _tmpSchoolId;
            if (_cursor.isNull(_cursorIndexOfSchoolId)) {
              _tmpSchoolId = null;
            } else {
              _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            }
            final String _tmpClassId;
            if (_cursor.isNull(_cursorIndexOfClassId)) {
              _tmpClassId = null;
            } else {
              _tmpClassId = _cursor.getString(_cursorIndexOfClassId);
            }
            final String _tmpGrade;
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _tmpGrade = null;
            } else {
              _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final PrivacyLevel _tmpPrivacyLevel;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPrivacyLevel)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfPrivacyLevel);
            }
            final PrivacyLevel _tmp_3 = Converters.INSTANCE.toPrivacyLevel(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.PrivacyLevel', but it was NULL.");
            } else {
              _tmpPrivacyLevel = _tmp_3;
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastLoginAt;
            _tmpLastLoginAt = _cursor.getLong(_cursorIndexOfLastLoginAt);
            _result = new User(_tmpId,_tmpUsername,_tmpRealName,_tmpRole,_tmpAvatarUrl,_tmpSchoolId,_tmpClassId,_tmpGrade,_tmpEmail,_tmpPhone,_tmpPrivacyLevel,_tmpCreatedAt,_tmpLastLoginAt);
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
  public Flow<User> getUserByIdFlow(final String userId) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfRealName = CursorUtil.getColumnIndexOrThrow(_cursor, "realName");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarUrl");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfClassId = CursorUtil.getColumnIndexOrThrow(_cursor, "classId");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPrivacyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "privacyLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastLoginAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastLoginAt");
          final User _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpRealName;
            _tmpRealName = _cursor.getString(_cursorIndexOfRealName);
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            final UserRole _tmp_1 = Converters.INSTANCE.toUserRole(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.UserRole', but it was NULL.");
            } else {
              _tmpRole = _tmp_1;
            }
            final String _tmpAvatarUrl;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatarUrl = null;
            } else {
              _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final String _tmpSchoolId;
            if (_cursor.isNull(_cursorIndexOfSchoolId)) {
              _tmpSchoolId = null;
            } else {
              _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            }
            final String _tmpClassId;
            if (_cursor.isNull(_cursorIndexOfClassId)) {
              _tmpClassId = null;
            } else {
              _tmpClassId = _cursor.getString(_cursorIndexOfClassId);
            }
            final String _tmpGrade;
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _tmpGrade = null;
            } else {
              _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final PrivacyLevel _tmpPrivacyLevel;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPrivacyLevel)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfPrivacyLevel);
            }
            final PrivacyLevel _tmp_3 = Converters.INSTANCE.toPrivacyLevel(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.PrivacyLevel', but it was NULL.");
            } else {
              _tmpPrivacyLevel = _tmp_3;
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastLoginAt;
            _tmpLastLoginAt = _cursor.getLong(_cursorIndexOfLastLoginAt);
            _result = new User(_tmpId,_tmpUsername,_tmpRealName,_tmpRole,_tmpAvatarUrl,_tmpSchoolId,_tmpClassId,_tmpGrade,_tmpEmail,_tmpPhone,_tmpPrivacyLevel,_tmpCreatedAt,_tmpLastLoginAt);
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
  public Object getUserByUsername(final String username,
      final Continuation<? super User> $completion) {
    final String _sql = "SELECT * FROM users WHERE username = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, username);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<User>() {
      @Override
      @Nullable
      public User call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfRealName = CursorUtil.getColumnIndexOrThrow(_cursor, "realName");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarUrl");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfClassId = CursorUtil.getColumnIndexOrThrow(_cursor, "classId");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPrivacyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "privacyLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastLoginAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastLoginAt");
          final User _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpRealName;
            _tmpRealName = _cursor.getString(_cursorIndexOfRealName);
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            final UserRole _tmp_1 = Converters.INSTANCE.toUserRole(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.UserRole', but it was NULL.");
            } else {
              _tmpRole = _tmp_1;
            }
            final String _tmpAvatarUrl;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatarUrl = null;
            } else {
              _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final String _tmpSchoolId;
            if (_cursor.isNull(_cursorIndexOfSchoolId)) {
              _tmpSchoolId = null;
            } else {
              _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            }
            final String _tmpClassId;
            if (_cursor.isNull(_cursorIndexOfClassId)) {
              _tmpClassId = null;
            } else {
              _tmpClassId = _cursor.getString(_cursorIndexOfClassId);
            }
            final String _tmpGrade;
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _tmpGrade = null;
            } else {
              _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final PrivacyLevel _tmpPrivacyLevel;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPrivacyLevel)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfPrivacyLevel);
            }
            final PrivacyLevel _tmp_3 = Converters.INSTANCE.toPrivacyLevel(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.PrivacyLevel', but it was NULL.");
            } else {
              _tmpPrivacyLevel = _tmp_3;
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastLoginAt;
            _tmpLastLoginAt = _cursor.getLong(_cursorIndexOfLastLoginAt);
            _result = new User(_tmpId,_tmpUsername,_tmpRealName,_tmpRole,_tmpAvatarUrl,_tmpSchoolId,_tmpClassId,_tmpGrade,_tmpEmail,_tmpPhone,_tmpPrivacyLevel,_tmpCreatedAt,_tmpLastLoginAt);
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
  public Flow<List<User>> getAllUsers() {
    final String _sql = "SELECT * FROM users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<List<User>>() {
      @Override
      @NonNull
      public List<User> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfRealName = CursorUtil.getColumnIndexOrThrow(_cursor, "realName");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfAvatarUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "avatarUrl");
          final int _cursorIndexOfSchoolId = CursorUtil.getColumnIndexOrThrow(_cursor, "schoolId");
          final int _cursorIndexOfClassId = CursorUtil.getColumnIndexOrThrow(_cursor, "classId");
          final int _cursorIndexOfGrade = CursorUtil.getColumnIndexOrThrow(_cursor, "grade");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfPrivacyLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "privacyLevel");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastLoginAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastLoginAt");
          final List<User> _result = new ArrayList<User>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final User _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpRealName;
            _tmpRealName = _cursor.getString(_cursorIndexOfRealName);
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            final UserRole _tmp_1 = Converters.INSTANCE.toUserRole(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.UserRole', but it was NULL.");
            } else {
              _tmpRole = _tmp_1;
            }
            final String _tmpAvatarUrl;
            if (_cursor.isNull(_cursorIndexOfAvatarUrl)) {
              _tmpAvatarUrl = null;
            } else {
              _tmpAvatarUrl = _cursor.getString(_cursorIndexOfAvatarUrl);
            }
            final String _tmpSchoolId;
            if (_cursor.isNull(_cursorIndexOfSchoolId)) {
              _tmpSchoolId = null;
            } else {
              _tmpSchoolId = _cursor.getString(_cursorIndexOfSchoolId);
            }
            final String _tmpClassId;
            if (_cursor.isNull(_cursorIndexOfClassId)) {
              _tmpClassId = null;
            } else {
              _tmpClassId = _cursor.getString(_cursorIndexOfClassId);
            }
            final String _tmpGrade;
            if (_cursor.isNull(_cursorIndexOfGrade)) {
              _tmpGrade = null;
            } else {
              _tmpGrade = _cursor.getString(_cursorIndexOfGrade);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final PrivacyLevel _tmpPrivacyLevel;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPrivacyLevel)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfPrivacyLevel);
            }
            final PrivacyLevel _tmp_3 = Converters.INSTANCE.toPrivacyLevel(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.keling.app.data.model.PrivacyLevel', but it was NULL.");
            } else {
              _tmpPrivacyLevel = _tmp_3;
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastLoginAt;
            _tmpLastLoginAt = _cursor.getLong(_cursorIndexOfLastLoginAt);
            _item = new User(_tmpId,_tmpUsername,_tmpRealName,_tmpRole,_tmpAvatarUrl,_tmpSchoolId,_tmpClassId,_tmpGrade,_tmpEmail,_tmpPhone,_tmpPrivacyLevel,_tmpCreatedAt,_tmpLastLoginAt);
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
