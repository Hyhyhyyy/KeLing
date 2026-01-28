package com.keling.app.di;

import com.google.gson.Gson;
import com.keling.app.data.task.GradeTaskGenerator;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class AppModule_ProvideGradeTaskGeneratorFactory implements Factory<GradeTaskGenerator> {
  private final Provider<Gson> gsonProvider;

  public AppModule_ProvideGradeTaskGeneratorFactory(Provider<Gson> gsonProvider) {
    this.gsonProvider = gsonProvider;
  }

  @Override
  public GradeTaskGenerator get() {
    return provideGradeTaskGenerator(gsonProvider.get());
  }

  public static AppModule_ProvideGradeTaskGeneratorFactory create(Provider<Gson> gsonProvider) {
    return new AppModule_ProvideGradeTaskGeneratorFactory(gsonProvider);
  }

  public static GradeTaskGenerator provideGradeTaskGenerator(Gson gson) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideGradeTaskGenerator(gson));
  }
}
