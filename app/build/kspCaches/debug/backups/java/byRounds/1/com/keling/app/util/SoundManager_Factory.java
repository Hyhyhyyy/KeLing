package com.keling.app.util;

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
public final class SoundManager_Factory implements Factory<SoundManager> {
  private final Provider<Context> contextProvider;

  public SoundManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SoundManager get() {
    return newInstance(contextProvider.get());
  }

  public static SoundManager_Factory create(Provider<Context> contextProvider) {
    return new SoundManager_Factory(contextProvider);
  }

  public static SoundManager newInstance(Context context) {
    return new SoundManager(context);
  }
}
