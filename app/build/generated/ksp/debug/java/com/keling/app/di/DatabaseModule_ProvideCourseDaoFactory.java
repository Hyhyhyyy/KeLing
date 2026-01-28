package com.keling.app.di;

import com.keling.app.data.local.KelingDatabase;
import com.keling.app.data.local.dao.CourseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideCourseDaoFactory implements Factory<CourseDao> {
  private final Provider<KelingDatabase> databaseProvider;

  public DatabaseModule_ProvideCourseDaoFactory(Provider<KelingDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public CourseDao get() {
    return provideCourseDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideCourseDaoFactory create(
      Provider<KelingDatabase> databaseProvider) {
    return new DatabaseModule_ProvideCourseDaoFactory(databaseProvider);
  }

  public static CourseDao provideCourseDao(KelingDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCourseDao(database));
  }
}
