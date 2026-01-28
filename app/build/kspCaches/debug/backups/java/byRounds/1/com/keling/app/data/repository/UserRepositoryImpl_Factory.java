package com.keling.app.data.repository;

import com.keling.app.data.local.dao.UserDao;
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
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<UserDao> userDaoProvider;

  public UserRepositoryImpl_Factory(Provider<UserDao> userDaoProvider) {
    this.userDaoProvider = userDaoProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(userDaoProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<UserDao> userDaoProvider) {
    return new UserRepositoryImpl_Factory(userDaoProvider);
  }

  public static UserRepositoryImpl newInstance(UserDao userDao) {
    return new UserRepositoryImpl(userDao);
  }
}
