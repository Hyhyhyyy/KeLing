package com.keling.app.di;

import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideGsonFactory implements Factory<Gson> {
  @Override
  public Gson get() {
    return provideGson();
  }

  public static AppModule_ProvideGsonFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Gson provideGson() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGson());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideGsonFactory INSTANCE = new AppModule_ProvideGsonFactory();
  }
}
