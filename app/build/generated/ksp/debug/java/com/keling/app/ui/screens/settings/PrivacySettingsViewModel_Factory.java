package com.keling.app.ui.screens.settings;

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
public final class PrivacySettingsViewModel_Factory implements Factory<PrivacySettingsViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public PrivacySettingsViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public PrivacySettingsViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static PrivacySettingsViewModel_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new PrivacySettingsViewModel_Factory(userRepositoryProvider);
  }

  public static PrivacySettingsViewModel newInstance(UserRepository userRepository) {
    return new PrivacySettingsViewModel(userRepository);
  }
}
