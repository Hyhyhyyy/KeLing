package com.keling.app.di;

import com.keling.app.data.local.KelingDatabase;
import com.keling.app.data.local.dao.AchievementDao;
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
public final class DatabaseModule_ProvideAchievementDaoFactory implements Factory<AchievementDao> {
  private final Provider<KelingDatabase> databaseProvider;

  public DatabaseModule_ProvideAchievementDaoFactory(Provider<KelingDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AchievementDao get() {
    return provideAchievementDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAchievementDaoFactory create(
      Provider<KelingDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAchievementDaoFactory(databaseProvider);
  }

  public static AchievementDao provideAchievementDao(KelingDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAchievementDao(database));
  }
}
