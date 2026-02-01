package com.keling.app.ui.screens.profile;

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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<TaskRecordDao> taskRecordDaoProvider;

  public ProfileViewModel_Factory(Provider<TaskRecordDao> taskRecordDaoProvider) {
    this.taskRecordDaoProvider = taskRecordDaoProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(taskRecordDaoProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<TaskRecordDao> taskRecordDaoProvider) {
    return new ProfileViewModel_Factory(taskRecordDaoProvider);
  }

  public static ProfileViewModel newInstance(TaskRecordDao taskRecordDao) {
    return new ProfileViewModel(taskRecordDao);
  }
}
