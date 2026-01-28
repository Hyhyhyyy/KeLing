package com.keling.app.ui.screens.campus;

import com.keling.app.data.repository.TaskRepository;
import com.keling.app.data.repository.UserRepository;
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
public final class CampusTasksViewModel_Factory implements Factory<CampusTasksViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public CampusTasksViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public CampusTasksViewModel get() {
    return newInstance(taskRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static CampusTasksViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new CampusTasksViewModel_Factory(taskRepositoryProvider, userRepositoryProvider);
  }

  public static CampusTasksViewModel newInstance(TaskRepository taskRepository,
      UserRepository userRepository) {
    return new CampusTasksViewModel(taskRepository, userRepository);
  }
}
