package com.keling.app.ui.screens.courses;

import androidx.lifecycle.SavedStateHandle;
import com.keling.app.data.remote.KelingApiService;
import com.keling.app.data.repository.CourseRepository;
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
public final class CourseDetailViewModel_Factory implements Factory<CourseDetailViewModel> {
  private final Provider<CourseRepository> courseRepositoryProvider;

  private final Provider<KelingApiService> kelingApiServiceProvider;

  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public CourseDetailViewModel_Factory(Provider<CourseRepository> courseRepositoryProvider,
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.courseRepositoryProvider = courseRepositoryProvider;
    this.kelingApiServiceProvider = kelingApiServiceProvider;
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public CourseDetailViewModel get() {
    return newInstance(courseRepositoryProvider.get(), kelingApiServiceProvider.get(), taskRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static CourseDetailViewModel_Factory create(
      Provider<CourseRepository> courseRepositoryProvider,
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new CourseDetailViewModel_Factory(courseRepositoryProvider, kelingApiServiceProvider, taskRepositoryProvider, savedStateHandleProvider);
  }

  public static CourseDetailViewModel newInstance(CourseRepository courseRepository,
      KelingApiService kelingApiService, TaskRepository taskRepository,
      SavedStateHandle savedStateHandle) {
    return new CourseDetailViewModel(courseRepository, kelingApiService, taskRepository, savedStateHandle);
  }
}
