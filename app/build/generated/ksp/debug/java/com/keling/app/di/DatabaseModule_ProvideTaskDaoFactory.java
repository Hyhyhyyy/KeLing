package com.keling.app.di;

import com.keling.app.data.local.KelingDatabase;
import com.keling.app.data.local.dao.TaskDao;
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
public final class DatabaseModule_ProvideTaskDaoFactory implements Factory<TaskDao> {
  private final Provider<KelingDatabase> databaseProvider;

  public DatabaseModule_ProvideTaskDaoFactory(Provider<KelingDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TaskDao get() {
    return provideTaskDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideTaskDaoFactory create(
      Provider<KelingDatabase> databaseProvider) {
    return new DatabaseModule_ProvideTaskDaoFactory(databaseProvider);
  }

  public static TaskDao provideTaskDao(KelingDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTaskDao(database));
  }
}
