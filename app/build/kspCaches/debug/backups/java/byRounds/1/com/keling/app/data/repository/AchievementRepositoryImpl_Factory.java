package com.keling.app.data.repository;

import com.keling.app.data.local.dao.AchievementDao;
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
public final class AchievementRepositoryImpl_Factory implements Factory<AchievementRepositoryImpl> {
  private final Provider<AchievementDao> achievementDaoProvider;

  public AchievementRepositoryImpl_Factory(Provider<AchievementDao> achievementDaoProvider) {
    this.achievementDaoProvider = achievementDaoProvider;
  }

  @Override
  public AchievementRepositoryImpl get() {
    return newInstance(achievementDaoProvider.get());
  }

  public static AchievementRepositoryImpl_Factory create(
      Provider<AchievementDao> achievementDaoProvider) {
    return new AchievementRepositoryImpl_Factory(achievementDaoProvider);
  }

  public static AchievementRepositoryImpl newInstance(AchievementDao achievementDao) {
    return new AchievementRepositoryImpl(achievementDao);
  }
}
