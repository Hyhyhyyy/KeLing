package com.keling.app.ui.screens.tasks;

import com.keling.app.data.repository.TaskRepository;
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
public final class TaskExecutionViewModel_Factory implements Factory<TaskExecutionViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public TaskExecutionViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public TaskExecutionViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static TaskExecutionViewModel_Factory create(
      Provider<TaskRepository> taskRepositoryProvider) {
    return new TaskExecutionViewModel_Factory(taskRepositoryProvider);
  }

  public static TaskExecutionViewModel newInstance(TaskRepository taskRepository) {
    return new TaskExecutionViewModel(taskRepository);
  }
}
