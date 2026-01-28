package com.keling.app.ui.screens.tasks;

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
public final class TasksViewModel_Factory implements Factory<TasksViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public TasksViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public TasksViewModel get() {
    return newInstance(taskRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static TasksViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new TasksViewModel_Factory(taskRepositoryProvider, userRepositoryProvider);
  }

  public static TasksViewModel newInstance(TaskRepository taskRepository,
      UserRepository userRepository) {
    return new TasksViewModel(taskRepository, userRepository);
  }
}
