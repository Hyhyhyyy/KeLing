package com.keling.app.data.repository;

import com.keling.app.data.local.dao.TaskRecordDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class TaskRecordRepository_Factory implements Factory<TaskRecordRepository> {
  private final Provider<TaskRecordDao> taskRecordDaoProvider;

  public TaskRecordRepository_Factory(Provider<TaskRecordDao> taskRecordDaoProvider) {
    this.taskRecordDaoProvider = taskRecordDaoProvider;
  }

  @Override
  public TaskRecordRepository get() {
    return newInstance(taskRecordDaoProvider.get());
  }

  public static TaskRecordRepository_Factory create(Provider<TaskRecordDao> taskRecordDaoProvider) {
    return new TaskRecordRepository_Factory(taskRecordDaoProvider);
  }

  public static TaskRecordRepository newInstance(TaskRecordDao taskRecordDao) {
    return new TaskRecordRepository(taskRecordDao);
  }
}
