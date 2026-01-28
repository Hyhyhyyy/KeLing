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
public final class PersonalProfileViewModel_Factory implements Factory<PersonalProfileViewModel> {
  private final Provider<UserRepository> userRepositoryProvider;

  public PersonalProfileViewModel_Factory(Provider<UserRepository> userRepositoryProvider) {
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public PersonalProfileViewModel get() {
    return newInstance(userRepositoryProvider.get());
  }

  public static PersonalProfileViewModel_Factory create(
      Provider<UserRepository> userRepositoryProvider) {
    return new PersonalProfileViewModel_Factory(userRepositoryProvider);
  }

  public static PersonalProfileViewModel newInstance(UserRepository userRepository) {
    return new PersonalProfileViewModel(userRepository);
  }
}
