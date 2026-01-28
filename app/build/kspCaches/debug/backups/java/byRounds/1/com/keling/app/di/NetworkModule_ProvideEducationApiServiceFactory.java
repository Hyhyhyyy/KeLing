package com.keling.app.di;

import com.keling.app.data.remote.EducationApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class NetworkModule_ProvideEducationApiServiceFactory implements Factory<EducationApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideEducationApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public EducationApiService get() {
    return provideEducationApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideEducationApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideEducationApiServiceFactory(retrofitProvider);
  }

  public static EducationApiService provideEducationApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideEducationApiService(retrofit));
  }
}
