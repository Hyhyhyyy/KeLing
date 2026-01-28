package com.keling.app.data.preferences;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AccessibilityPreferencesRepository_Factory implements Factory<AccessibilityPreferencesRepository> {
  private final Provider<Context> contextProvider;

  public AccessibilityPreferencesRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AccessibilityPreferencesRepository get() {
    return newInstance(contextProvider.get());
  }

  public static AccessibilityPreferencesRepository_Factory create(
      Provider<Context> contextProvider) {
    return new AccessibilityPreferencesRepository_Factory(contextProvider);
  }

  public static AccessibilityPreferencesRepository newInstance(Context context) {
    return new AccessibilityPreferencesRepository(context);
  }
}
