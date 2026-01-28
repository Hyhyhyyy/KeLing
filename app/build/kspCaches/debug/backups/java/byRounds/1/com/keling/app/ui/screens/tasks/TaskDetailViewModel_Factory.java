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
public final class TaskDetailViewModel_Factory implements Factory<TaskDetailViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public TaskDetailViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public TaskDetailViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static TaskDetailViewModel_Factory create(
      Provider<TaskRepository> taskRepositoryProvider) {
    return new TaskDetailViewModel_Factory(taskRepositoryProvider);
  }

  public static TaskDetailViewModel newInstance(TaskRepository taskRepository) {
    return new TaskDetailViewModel(taskRepository);
  }
}
