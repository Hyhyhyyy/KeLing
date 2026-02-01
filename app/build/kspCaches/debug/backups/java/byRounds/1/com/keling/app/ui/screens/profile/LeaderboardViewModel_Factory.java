package com.keling.app.ui.screens.profile;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LeaderboardViewModel_Factory implements Factory<LeaderboardViewModel> {
  @Override
  public LeaderboardViewModel get() {
    return newInstance();
  }

  public static LeaderboardViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LeaderboardViewModel newInstance() {
    return new LeaderboardViewModel();
  }

  private static final class InstanceHolder {
    private static final LeaderboardViewModel_Factory INSTANCE = new LeaderboardViewModel_Factory();
  }
}
