package com.keling.app.ui.screens.settings;

import com.keling.app.data.preferences.AccessibilityPreferencesRepository;
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
public final class AccessibilityViewModel_Factory implements Factory<AccessibilityViewModel> {
  private final Provider<AccessibilityPreferencesRepository> repositoryProvider;

  public AccessibilityViewModel_Factory(
      Provider<AccessibilityPreferencesRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AccessibilityViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AccessibilityViewModel_Factory create(
      Provider<AccessibilityPreferencesRepository> repositoryProvider) {
    return new AccessibilityViewModel_Factory(repositoryProvider);
  }

  public static AccessibilityViewModel newInstance(AccessibilityPreferencesRepository repository) {
    return new AccessibilityViewModel(repository);
  }
}
