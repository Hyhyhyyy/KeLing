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
public final class FriendsViewModel_Factory implements Factory<FriendsViewModel> {
  @Override
  public FriendsViewModel get() {
    return newInstance();
  }

  public static FriendsViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FriendsViewModel newInstance() {
    return new FriendsViewModel();
  }

  private static final class InstanceHolder {
    private static final FriendsViewModel_Factory INSTANCE = new FriendsViewModel_Factory();
  }
}
