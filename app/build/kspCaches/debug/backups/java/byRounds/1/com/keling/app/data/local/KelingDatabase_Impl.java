package com.keling.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.keling.app.data.local.dao.AchievementDao;
import com.keling.app.data.local.dao.AchievementDao_Impl;
import com.keling.app.data.local.dao.CourseDao;
import com.keling.app.data.local.dao.CourseDao_Impl;
import com.keling.app.data.local.dao.KnowledgeDao;
import com.keling.app.data.local.dao.KnowledgeDao_Impl;
import com.keling.app.data.local.dao.TaskDao;
import com.keling.app.data.local.dao.TaskDao_Impl;
import com.keling.app.data.local.dao.UserDao;
import com.keling.app.data.local.dao.UserDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class KelingDatabase_Impl extends KelingDatabase {
  private volatile UserDao _userDao;

  private volatile CourseDao _courseDao;

  private volatile TaskDao _taskDao;

  private volatile AchievementDao _achievementDao;

  private volatile KnowledgeDao _knowledgeDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(3) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` TEXT NOT NULL, `username` TEXT NOT NULL, `realName` TEXT NOT NULL, `role` TEXT NOT NULL, `avatarUrl` TEXT, `schoolId` TEXT, `classId` TEXT, `grade` TEXT, `email` TEXT, `phone` TEXT, `privacyLevel` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `lastLoginAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `courses` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `code` TEXT NOT NULL, `type` TEXT NOT NULL, `credits` REAL NOT NULL, `teacherName` TEXT NOT NULL, `teacherId` TEXT, `location` TEXT, `description` TEXT, `coverImageUrl` TEXT, `semester` TEXT NOT NULL, `progress` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `schedule_items` (`id` TEXT NOT NULL, `courseId` TEXT NOT NULL, `courseName` TEXT NOT NULL, `teacherName` TEXT NOT NULL, `dayOfWeek` INTEGER NOT NULL, `startTime` TEXT NOT NULL, `endTime` TEXT NOT NULL, `location` TEXT NOT NULL, `weekStart` INTEGER NOT NULL, `weekEnd` INTEGER NOT NULL, `weekType` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `chapters` (`id` TEXT NOT NULL, `courseId` TEXT NOT NULL, `title` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, `description` TEXT, `contentUrl` TEXT, `duration` INTEGER NOT NULL, `isCompleted` INTEGER NOT NULL, `progress` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `materials` (`id` TEXT NOT NULL, `courseId` TEXT NOT NULL, `chapterId` TEXT, `title` TEXT NOT NULL, `type` TEXT NOT NULL, `url` TEXT NOT NULL, `size` INTEGER NOT NULL, `downloadedPath` TEXT, `createdAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tasks` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, `courseId` TEXT, `chapterId` TEXT, `order` INTEGER, `type` TEXT, `difficulty` TEXT, `status` TEXT NOT NULL, `experienceReward` INTEGER, `coinReward` INTEGER, `estimatedMinutes` INTEGER, `estimatedDuration` INTEGER, `progress` REAL NOT NULL, `actionType` TEXT, `actionPayload` TEXT, `targetGrade` TEXT, `source` TEXT, `createdAt` INTEGER, `deadline` INTEGER, `completedAt` INTEGER, `isCompleted` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `team_tasks` (`id` TEXT NOT NULL, `teamId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `task_progress` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `taskId` TEXT NOT NULL, `userId` TEXT NOT NULL, `progress` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `study_sessions` (`id` TEXT NOT NULL, `dayKey` TEXT NOT NULL, `source` TEXT NOT NULL, `taskId` TEXT, `durationMinutes` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `achievements` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `category` TEXT NOT NULL, `rarity` TEXT NOT NULL, `iconName` TEXT NOT NULL, `experienceReward` INTEGER NOT NULL, `coinReward` INTEGER NOT NULL, `conditionType` TEXT NOT NULL, `conditionValue` INTEGER NOT NULL, `order` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_achievements` (`id` TEXT NOT NULL, `achievementId` TEXT NOT NULL, `userId` TEXT NOT NULL, `unlockedAt` INTEGER NOT NULL, `progress` REAL NOT NULL, `isUnlocked` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `badges` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `iconName` TEXT NOT NULL, `color` TEXT NOT NULL, `tier` INTEGER NOT NULL, `category` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_badges` (`id` TEXT NOT NULL, `badgeId` TEXT NOT NULL, `userId` TEXT NOT NULL, `obtainedAt` INTEGER NOT NULL, `isEquipped` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `knowledge_points` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `description` TEXT, `courseId` TEXT NOT NULL, `chapterId` TEXT, `difficulty` INTEGER NOT NULL, `importance` INTEGER NOT NULL, `masteryLevel` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `knowledge_relations` (`id` TEXT NOT NULL, `fromPointId` TEXT NOT NULL, `toPointId` TEXT NOT NULL, `relationType` TEXT NOT NULL, `weight` REAL NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `learning_records` (`id` TEXT NOT NULL, `userId` TEXT NOT NULL, `knowledgePointId` TEXT NOT NULL, `questionId` TEXT, `isCorrect` INTEGER NOT NULL, `timeSpentSeconds` INTEGER NOT NULL, `attemptCount` INTEGER NOT NULL, `recordedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bea98894f299d19005373968081e8855')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `courses`");
        db.execSQL("DROP TABLE IF EXISTS `schedule_items`");
        db.execSQL("DROP TABLE IF EXISTS `chapters`");
        db.execSQL("DROP TABLE IF EXISTS `materials`");
        db.execSQL("DROP TABLE IF EXISTS `tasks`");
        db.execSQL("DROP TABLE IF EXISTS `team_tasks`");
        db.execSQL("DROP TABLE IF EXISTS `task_progress`");
        db.execSQL("DROP TABLE IF EXISTS `study_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `achievements`");
        db.execSQL("DROP TABLE IF EXISTS `user_achievements`");
        db.execSQL("DROP TABLE IF EXISTS `badges`");
        db.execSQL("DROP TABLE IF EXISTS `user_badges`");
        db.execSQL("DROP TABLE IF EXISTS `knowledge_points`");
        db.execSQL("DROP TABLE IF EXISTS `knowledge_relations`");
        db.execSQL("DROP TABLE IF EXISTS `learning_records`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(13);
        _columnsUsers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("realName", new TableInfo.Column("realName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("avatarUrl", new TableInfo.Column("avatarUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("schoolId", new TableInfo.Column("schoolId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("classId", new TableInfo.Column("classId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("grade", new TableInfo.Column("grade", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("privacyLevel", new TableInfo.Column("privacyLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("lastLoginAt", new TableInfo.Column("lastLoginAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.keling.app.data.model.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsCourses = new HashMap<String, TableInfo.Column>(12);
        _columnsCourses.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("code", new TableInfo.Column("code", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("credits", new TableInfo.Column("credits", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("teacherName", new TableInfo.Column("teacherName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("teacherId", new TableInfo.Column("teacherId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("location", new TableInfo.Column("location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("coverImageUrl", new TableInfo.Column("coverImageUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("semester", new TableInfo.Column("semester", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCourses.put("progress", new TableInfo.Column("progress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourses = new TableInfo("courses", _columnsCourses, _foreignKeysCourses, _indicesCourses);
        final TableInfo _existingCourses = TableInfo.read(db, "courses");
        if (!_infoCourses.equals(_existingCourses)) {
          return new RoomOpenHelper.ValidationResult(false, "courses(com.keling.app.data.model.Course).\n"
                  + " Expected:\n" + _infoCourses + "\n"
                  + " Found:\n" + _existingCourses);
        }
        final HashMap<String, TableInfo.Column> _columnsScheduleItems = new HashMap<String, TableInfo.Column>(11);
        _columnsScheduleItems.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("courseName", new TableInfo.Column("courseName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("teacherName", new TableInfo.Column("teacherName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("dayOfWeek", new TableInfo.Column("dayOfWeek", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("startTime", new TableInfo.Column("startTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("endTime", new TableInfo.Column("endTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("location", new TableInfo.Column("location", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("weekStart", new TableInfo.Column("weekStart", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("weekEnd", new TableInfo.Column("weekEnd", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScheduleItems.put("weekType", new TableInfo.Column("weekType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScheduleItems = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScheduleItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScheduleItems = new TableInfo("schedule_items", _columnsScheduleItems, _foreignKeysScheduleItems, _indicesScheduleItems);
        final TableInfo _existingScheduleItems = TableInfo.read(db, "schedule_items");
        if (!_infoScheduleItems.equals(_existingScheduleItems)) {
          return new RoomOpenHelper.ValidationResult(false, "schedule_items(com.keling.app.data.model.ScheduleItem).\n"
                  + " Expected:\n" + _infoScheduleItems + "\n"
                  + " Found:\n" + _existingScheduleItems);
        }
        final HashMap<String, TableInfo.Column> _columnsChapters = new HashMap<String, TableInfo.Column>(9);
        _columnsChapters.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("contentUrl", new TableInfo.Column("contentUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChapters.put("progress", new TableInfo.Column("progress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChapters = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChapters = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoChapters = new TableInfo("chapters", _columnsChapters, _foreignKeysChapters, _indicesChapters);
        final TableInfo _existingChapters = TableInfo.read(db, "chapters");
        if (!_infoChapters.equals(_existingChapters)) {
          return new RoomOpenHelper.ValidationResult(false, "chapters(com.keling.app.data.model.Chapter).\n"
                  + " Expected:\n" + _infoChapters + "\n"
                  + " Found:\n" + _existingChapters);
        }
        final HashMap<String, TableInfo.Column> _columnsMaterials = new HashMap<String, TableInfo.Column>(9);
        _columnsMaterials.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("chapterId", new TableInfo.Column("chapterId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("size", new TableInfo.Column("size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("downloadedPath", new TableInfo.Column("downloadedPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMaterials.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMaterials = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMaterials = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMaterials = new TableInfo("materials", _columnsMaterials, _foreignKeysMaterials, _indicesMaterials);
        final TableInfo _existingMaterials = TableInfo.read(db, "materials");
        if (!_infoMaterials.equals(_existingMaterials)) {
          return new RoomOpenHelper.ValidationResult(false, "materials(com.keling.app.data.model.Material).\n"
                  + " Expected:\n" + _infoMaterials + "\n"
                  + " Found:\n" + _existingMaterials);
        }
        final HashMap<String, TableInfo.Column> _columnsTasks = new HashMap<String, TableInfo.Column>(22);
        _columnsTasks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("courseId", new TableInfo.Column("courseId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("chapterId", new TableInfo.Column("chapterId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("order", new TableInfo.Column("order", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("difficulty", new TableInfo.Column("difficulty", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("experienceReward", new TableInfo.Column("experienceReward", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("coinReward", new TableInfo.Column("coinReward", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("estimatedMinutes", new TableInfo.Column("estimatedMinutes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("estimatedDuration", new TableInfo.Column("estimatedDuration", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("progress", new TableInfo.Column("progress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("actionType", new TableInfo.Column("actionType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("actionPayload", new TableInfo.Column("actionPayload", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("targetGrade", new TableInfo.Column("targetGrade", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("deadline", new TableInfo.Column("deadline", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTasks.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTasks = new TableInfo("tasks", _columnsTasks, _foreignKeysTasks, _indicesTasks);
        final TableInfo _existingTasks = TableInfo.read(db, "tasks");
        if (!_infoTasks.equals(_existingTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "tasks(com.keling.app.data.model.Task).\n"
                  + " Expected:\n" + _infoTasks + "\n"
                  + " Found:\n" + _existingTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsTeamTasks = new HashMap<String, TableInfo.Column>(4);
        _columnsTeamTasks.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeamTasks.put("teamId", new TableInfo.Column("teamId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeamTasks.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeamTasks.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTeamTasks = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTeamTasks = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTeamTasks = new TableInfo("team_tasks", _columnsTeamTasks, _foreignKeysTeamTasks, _indicesTeamTasks);
        final TableInfo _existingTeamTasks = TableInfo.read(db, "team_tasks");
        if (!_infoTeamTasks.equals(_existingTeamTasks)) {
          return new RoomOpenHelper.ValidationResult(false, "team_tasks(com.keling.app.data.model.TeamTask).\n"
                  + " Expected:\n" + _infoTeamTasks + "\n"
                  + " Found:\n" + _existingTeamTasks);
        }
        final HashMap<String, TableInfo.Column> _columnsTaskProgress = new HashMap<String, TableInfo.Column>(4);
        _columnsTaskProgress.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskProgress.put("taskId", new TableInfo.Column("taskId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskProgress.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTaskProgress.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTaskProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTaskProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTaskProgress = new TableInfo("task_progress", _columnsTaskProgress, _foreignKeysTaskProgress, _indicesTaskProgress);
        final TableInfo _existingTaskProgress = TableInfo.read(db, "task_progress");
        if (!_infoTaskProgress.equals(_existingTaskProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "task_progress(com.keling.app.data.model.TaskProgress).\n"
                  + " Expected:\n" + _infoTaskProgress + "\n"
                  + " Found:\n" + _existingTaskProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsStudySessions = new HashMap<String, TableInfo.Column>(5);
        _columnsStudySessions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("dayKey", new TableInfo.Column("dayKey", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("taskId", new TableInfo.Column("taskId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudySessions.put("durationMinutes", new TableInfo.Column("durationMinutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudySessions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudySessions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudySessions = new TableInfo("study_sessions", _columnsStudySessions, _foreignKeysStudySessions, _indicesStudySessions);
        final TableInfo _existingStudySessions = TableInfo.read(db, "study_sessions");
        if (!_infoStudySessions.equals(_existingStudySessions)) {
          return new RoomOpenHelper.ValidationResult(false, "study_sessions(com.keling.app.data.model.StudySession).\n"
                  + " Expected:\n" + _infoStudySessions + "\n"
                  + " Found:\n" + _existingStudySessions);
        }
        final HashMap<String, TableInfo.Column> _columnsAchievements = new HashMap<String, TableInfo.Column>(11);
        _columnsAchievements.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("rarity", new TableInfo.Column("rarity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("iconName", new TableInfo.Column("iconName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("experienceReward", new TableInfo.Column("experienceReward", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("coinReward", new TableInfo.Column("coinReward", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("conditionType", new TableInfo.Column("conditionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("conditionValue", new TableInfo.Column("conditionValue", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAchievements.put("order", new TableInfo.Column("order", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAchievements = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAchievements = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAchievements = new TableInfo("achievements", _columnsAchievements, _foreignKeysAchievements, _indicesAchievements);
        final TableInfo _existingAchievements = TableInfo.read(db, "achievements");
        if (!_infoAchievements.equals(_existingAchievements)) {
          return new RoomOpenHelper.ValidationResult(false, "achievements(com.keling.app.data.model.Achievement).\n"
                  + " Expected:\n" + _infoAchievements + "\n"
                  + " Found:\n" + _existingAchievements);
        }
        final HashMap<String, TableInfo.Column> _columnsUserAchievements = new HashMap<String, TableInfo.Column>(6);
        _columnsUserAchievements.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserAchievements.put("achievementId", new TableInfo.Column("achievementId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserAchievements.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserAchievements.put("unlockedAt", new TableInfo.Column("unlockedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserAchievements.put("progress", new TableInfo.Column("progress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserAchievements.put("isUnlocked", new TableInfo.Column("isUnlocked", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserAchievements = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserAchievements = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserAchievements = new TableInfo("user_achievements", _columnsUserAchievements, _foreignKeysUserAchievements, _indicesUserAchievements);
        final TableInfo _existingUserAchievements = TableInfo.read(db, "user_achievements");
        if (!_infoUserAchievements.equals(_existingUserAchievements)) {
          return new RoomOpenHelper.ValidationResult(false, "user_achievements(com.keling.app.data.model.UserAchievement).\n"
                  + " Expected:\n" + _infoUserAchievements + "\n"
                  + " Found:\n" + _existingUserAchievements);
        }
        final HashMap<String, TableInfo.Column> _columnsBadges = new HashMap<String, TableInfo.Column>(7);
        _columnsBadges.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("iconName", new TableInfo.Column("iconName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("tier", new TableInfo.Column("tier", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBadges.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBadges = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBadges = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBadges = new TableInfo("badges", _columnsBadges, _foreignKeysBadges, _indicesBadges);
        final TableInfo _existingBadges = TableInfo.read(db, "badges");
        if (!_infoBadges.equals(_existingBadges)) {
          return new RoomOpenHelper.ValidationResult(false, "badges(com.keling.app.data.model.Badge).\n"
                  + " Expected:\n" + _infoBadges + "\n"
                  + " Found:\n" + _existingBadges);
        }
        final HashMap<String, TableInfo.Column> _columnsUserBadges = new HashMap<String, TableInfo.Column>(5);
        _columnsUserBadges.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserBadges.put("badgeId", new TableInfo.Column("badgeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserBadges.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserBadges.put("obtainedAt", new TableInfo.Column("obtainedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserBadges.put("isEquipped", new TableInfo.Column("isEquipped", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserBadges = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserBadges = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserBadges = new TableInfo("user_badges", _columnsUserBadges, _foreignKeysUserBadges, _indicesUserBadges);
        final TableInfo _existingUserBadges = TableInfo.read(db, "user_badges");
        if (!_infoUserBadges.equals(_existingUserBadges)) {
          return new RoomOpenHelper.ValidationResult(false, "user_badges(com.keling.app.data.model.UserBadge).\n"
                  + " Expected:\n" + _infoUserBadges + "\n"
                  + " Found:\n" + _existingUserBadges);
        }
        final HashMap<String, TableInfo.Column> _columnsKnowledgePoints = new HashMap<String, TableInfo.Column>(8);
        _columnsKnowledgePoints.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("chapterId", new TableInfo.Column("chapterId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("difficulty", new TableInfo.Column("difficulty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("importance", new TableInfo.Column("importance", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgePoints.put("masteryLevel", new TableInfo.Column("masteryLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysKnowledgePoints = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesKnowledgePoints = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoKnowledgePoints = new TableInfo("knowledge_points", _columnsKnowledgePoints, _foreignKeysKnowledgePoints, _indicesKnowledgePoints);
        final TableInfo _existingKnowledgePoints = TableInfo.read(db, "knowledge_points");
        if (!_infoKnowledgePoints.equals(_existingKnowledgePoints)) {
          return new RoomOpenHelper.ValidationResult(false, "knowledge_points(com.keling.app.data.model.KnowledgePoint).\n"
                  + " Expected:\n" + _infoKnowledgePoints + "\n"
                  + " Found:\n" + _existingKnowledgePoints);
        }
        final HashMap<String, TableInfo.Column> _columnsKnowledgeRelations = new HashMap<String, TableInfo.Column>(5);
        _columnsKnowledgeRelations.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgeRelations.put("fromPointId", new TableInfo.Column("fromPointId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgeRelations.put("toPointId", new TableInfo.Column("toPointId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgeRelations.put("relationType", new TableInfo.Column("relationType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsKnowledgeRelations.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysKnowledgeRelations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesKnowledgeRelations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoKnowledgeRelations = new TableInfo("knowledge_relations", _columnsKnowledgeRelations, _foreignKeysKnowledgeRelations, _indicesKnowledgeRelations);
        final TableInfo _existingKnowledgeRelations = TableInfo.read(db, "knowledge_relations");
        if (!_infoKnowledgeRelations.equals(_existingKnowledgeRelations)) {
          return new RoomOpenHelper.ValidationResult(false, "knowledge_relations(com.keling.app.data.model.KnowledgeRelation).\n"
                  + " Expected:\n" + _infoKnowledgeRelations + "\n"
                  + " Found:\n" + _existingKnowledgeRelations);
        }
        final HashMap<String, TableInfo.Column> _columnsLearningRecords = new HashMap<String, TableInfo.Column>(8);
        _columnsLearningRecords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("knowledgePointId", new TableInfo.Column("knowledgePointId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("questionId", new TableInfo.Column("questionId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("isCorrect", new TableInfo.Column("isCorrect", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("timeSpentSeconds", new TableInfo.Column("timeSpentSeconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("attemptCount", new TableInfo.Column("attemptCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRecords.put("recordedAt", new TableInfo.Column("recordedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLearningRecords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLearningRecords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLearningRecords = new TableInfo("learning_records", _columnsLearningRecords, _foreignKeysLearningRecords, _indicesLearningRecords);
        final TableInfo _existingLearningRecords = TableInfo.read(db, "learning_records");
        if (!_infoLearningRecords.equals(_existingLearningRecords)) {
          return new RoomOpenHelper.ValidationResult(false, "learning_records(com.keling.app.data.model.LearningRecord).\n"
                  + " Expected:\n" + _infoLearningRecords + "\n"
                  + " Found:\n" + _existingLearningRecords);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bea98894f299d19005373968081e8855", "3728e533ca37881509e965b9cce81f55");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "users","courses","schedule_items","chapters","materials","tasks","team_tasks","task_progress","study_sessions","achievements","user_achievements","badges","user_badges","knowledge_points","knowledge_relations","learning_records");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `courses`");
      _db.execSQL("DELETE FROM `schedule_items`");
      _db.execSQL("DELETE FROM `chapters`");
      _db.execSQL("DELETE FROM `materials`");
      _db.execSQL("DELETE FROM `tasks`");
      _db.execSQL("DELETE FROM `team_tasks`");
      _db.execSQL("DELETE FROM `task_progress`");
      _db.execSQL("DELETE FROM `study_sessions`");
      _db.execSQL("DELETE FROM `achievements`");
      _db.execSQL("DELETE FROM `user_achievements`");
      _db.execSQL("DELETE FROM `badges`");
      _db.execSQL("DELETE FROM `user_badges`");
      _db.execSQL("DELETE FROM `knowledge_points`");
      _db.execSQL("DELETE FROM `knowledge_relations`");
      _db.execSQL("DELETE FROM `learning_records`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CourseDao.class, CourseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TaskDao.class, TaskDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AchievementDao.class, AchievementDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(KnowledgeDao.class, KnowledgeDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public CourseDao courseDao() {
    if (_courseDao != null) {
      return _courseDao;
    } else {
      synchronized(this) {
        if(_courseDao == null) {
          _courseDao = new CourseDao_Impl(this);
        }
        return _courseDao;
      }
    }
  }

  @Override
  public TaskDao taskDao() {
    if (_taskDao != null) {
      return _taskDao;
    } else {
      synchronized(this) {
        if(_taskDao == null) {
          _taskDao = new TaskDao_Impl(this);
        }
        return _taskDao;
      }
    }
  }

  @Override
  public AchievementDao achievementDao() {
    if (_achievementDao != null) {
      return _achievementDao;
    } else {
      synchronized(this) {
        if(_achievementDao == null) {
          _achievementDao = new AchievementDao_Impl(this);
        }
        return _achievementDao;
      }
    }
  }

  @Override
  public KnowledgeDao knowledgeDao() {
    if (_knowledgeDao != null) {
      return _knowledgeDao;
    } else {
      synchronized(this) {
        if(_knowledgeDao == null) {
          _knowledgeDao = new KnowledgeDao_Impl(this);
        }
        return _knowledgeDao;
      }
    }
  }
}
