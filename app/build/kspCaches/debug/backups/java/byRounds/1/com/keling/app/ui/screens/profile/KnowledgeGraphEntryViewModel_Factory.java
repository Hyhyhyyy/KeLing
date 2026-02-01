package com.keling.app.ui.screens.profile;

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
public final class KnowledgeGraphEntryViewModel_Factory implements Factory<KnowledgeGraphEntryViewModel> {
  private final Provider<CourseRepository> courseRepositoryProvider;

  public KnowledgeGraphEntryViewModel_Factory(Provider<CourseRepository> courseRepositoryProvider) {
    this.courseRepositoryProvider = courseRepositoryProvider;
  }

  @Override
  public KnowledgeGraphEntryViewModel get() {
    return newInstance(courseRepositoryProvider.get());
  }

  public static KnowledgeGraphEntryViewModel_Factory create(
      Provider<CourseRepository> courseRepositoryProvider) {
    return new KnowledgeGraphEntryViewModel_Factory(courseRepositoryProvider);
  }

  public static KnowledgeGraphEntryViewModel newInstance(CourseRepository courseRepository) {
    return new KnowledgeGraphEntryViewModel(courseRepository);
  }
}
