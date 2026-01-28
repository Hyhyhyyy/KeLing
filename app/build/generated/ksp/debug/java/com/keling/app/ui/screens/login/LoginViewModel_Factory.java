package com.keling.app.ui.screens.login;

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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public LoginViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<UserRepository> userRepositoryProvider) {
    return new LoginViewModel_Factory(userRepositoryProvider);
  }

  public static LoginViewModel newInstance(UserRepository userRepository) {
    return new LoginViewModel(userRepository);
  }
}
