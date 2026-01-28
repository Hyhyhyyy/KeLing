package com.keling.app.ui.screens.ai;

import com.keling.app.data.remote.KelingApiService;
import com.keling.app.data.repository.TaskRepository;
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
public final class AIAssistantViewModel_Factory implements Factory<AIAssistantViewModel> {
  private final Provider<TaskRepository> taskRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<KelingApiService> kelingApiServiceProvider;

  public AIAssistantViewModel_Factory(Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<KelingApiService> kelingApiServiceProvider) {
    this.taskRepositoryProvider = taskRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.kelingApiServiceProvider = kelingApiServiceProvider;
  }

  @Override
  public AIAssistantViewModel get() {
    return newInstance(taskRepositoryProvider.get(), userRepositoryProvider.get(), kelingApiServiceProvider.get());
  }

  public static AIAssistantViewModel_Factory create(Provider<TaskRepository> taskRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<KelingApiService> kelingApiServiceProvider) {
    return new AIAssistantViewModel_Factory(taskRepositoryProvider, userRepositoryProvider, kelingApiServiceProvider);
  }

  public static AIAssistantViewModel newInstance(TaskRepository taskRepository,
      UserRepository userRepository, KelingApiService kelingApiService) {
    return new AIAssistantViewModel(taskRepository, userRepository, kelingApiService);
  }
}
