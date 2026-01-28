package com.keling.app.di;

import com.keling.app.data.remote.KelingApiService;
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
public final class NetworkModule_ProvideKelingApiServiceFactory implements Factory<KelingApiService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideKelingApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public KelingApiService get() {
    return provideKelingApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideKelingApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideKelingApiServiceFactory(retrofitProvider);
  }

  public static KelingApiService provideKelingApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideKelingApiService(retrofit));
  }
}
