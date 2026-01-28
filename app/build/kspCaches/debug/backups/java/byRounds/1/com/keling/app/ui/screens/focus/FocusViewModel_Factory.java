package com.keling.app.ui.screens.focus;

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
public final class FocusViewModel_Factory implements Factory<FocusViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  public FocusViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
  }

  @Override
  public FocusViewModel get() {
    return newInstance(taskRepositoryProvider.get());
  }

  public static FocusViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider) {
    return new FocusViewModel_Factory(taskRepositoryProvider);
  }

  public static FocusViewModel newInstance(TaskRepository taskRepository) {
    return new FocusViewModel(taskRepository);
  }
}
