package com.keling.app.ui.screens.courses;

import com.keling.app.data.repository.CourseRepository;
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
public final class CoursesViewModel_Factory implements Factory<CoursesViewModel> {
  private final Provider<CourseRepository> courseRepositoryProvider;

  public CoursesViewModel_Factory(Provider<CourseRepository> courseRepositoryProvider) {
    this.courseRepositoryProvider = courseRepositoryProvider;
  }

  @Override
  public CoursesViewModel get() {
    return newInstance(courseRepositoryProvider.get());
  }

  public static CoursesViewModel_Factory create(
      Provider<CourseRepository> courseRepositoryProvider) {
    return new CoursesViewModel_Factory(courseRepositoryProvider);
  }

  public static CoursesViewModel newInstance(CourseRepository courseRepository) {
    return new CoursesViewModel(courseRepository);
  }
}
