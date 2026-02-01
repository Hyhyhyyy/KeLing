package com.keling.app.ui.screens.profile;

import com.keling.app.data.remote.KelingApiService;
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
public final class LearningReportViewModel_Factory implements Factory<LearningReportViewModel> {
  private final Provider<KelingApiService> kelingApiServiceProvider;

  private final Provider<CourseRepository> courseRepositoryProvider;

  public LearningReportViewModel_Factory(Provider<KelingApiService> kelingApiServiceProvider,
      Provider<CourseRepository> courseRepositoryProvider) {
    this.kelingApiServiceProvider = kelingApiServiceProvider;
    this.courseRepositoryProvider = courseRepositoryProvider;
  }

  @Override
  public LearningReportViewModel get() {
    return newInstance(kelingApiServiceProvider.get(), courseRepositoryProvider.get());
  }

  public static LearningReportViewModel_Factory create(
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<CourseRepository> courseRepositoryProvider) {
    return new LearningReportViewModel_Factory(kelingApiServiceProvider, courseRepositoryProvider);
  }

  public static LearningReportViewModel newInstance(KelingApiService kelingApiService,
      CourseRepository courseRepository) {
    return new LearningReportViewModel(kelingApiService, courseRepository);
  }
}
