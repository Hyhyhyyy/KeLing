package com.keling.app.ui.screens.home;

import com.keling.app.data.remote.KelingApiService;
import com.keling.app.data.repository.CourseRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<CourseRepository> courseRepositoryProvider;

  private final Provider<KelingApiService> kelingApiServiceProvider;

  public HomeViewModel_Factory(Provider<UserRepository> userRepositoryProvider,
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<CourseRepository> courseRepositoryProvider,
      Provider<KelingApiService> kelingApiServiceProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.courseRepositoryProvider = courseRepositoryProvider;
    this.kelingApiServiceProvider = kelingApiServiceProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(userRepositoryProvider.get(), taskRepositoryProvider.get(), courseRepositoryProvider.get(), kelingApiServiceProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<UserRepository> userRepositoryProvider,
      Provider<TaskRepository> taskRepositoryProvider,
      Provider<CourseRepository> courseRepositoryProvider,
      Provider<KelingApiService> kelingApiServiceProvider) {
    return new HomeViewModel_Factory(userRepositoryProvider, taskRepositoryProvider, courseRepositoryProvider, kelingApiServiceProvider);
  }

  public static HomeViewModel newInstance(UserRepository userRepository,
      TaskRepository taskRepository, CourseRepository courseRepository,
      KelingApiService kelingApiService) {
    return new HomeViewModel(userRepository, taskRepository, courseRepository, kelingApiService);
  }
}
