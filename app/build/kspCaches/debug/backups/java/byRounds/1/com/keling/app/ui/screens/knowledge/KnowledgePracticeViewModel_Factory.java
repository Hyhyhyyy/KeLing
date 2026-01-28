package com.keling.app.ui.screens.knowledge;

import androidx.lifecycle.SavedStateHandle;
import com.keling.app.data.local.dao.KnowledgeDao;
import com.keling.app.data.remote.KelingApiService;
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
public final class KnowledgePracticeViewModel_Factory implements Factory<KnowledgePracticeViewModel> {
  private final Provider<KnowledgeDao> knowledgeDaoProvider;

  private final Provider<KelingApiService> kelingApiServiceProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public KnowledgePracticeViewModel_Factory(Provider<KnowledgeDao> knowledgeDaoProvider,
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.knowledgeDaoProvider = knowledgeDaoProvider;
    this.kelingApiServiceProvider = kelingApiServiceProvider;
    this.userRepositoryProvider = userRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public KnowledgePracticeViewModel get() {
    return newInstance(knowledgeDaoProvider.get(), kelingApiServiceProvider.get(), userRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static KnowledgePracticeViewModel_Factory create(
      Provider<KnowledgeDao> knowledgeDaoProvider,
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<UserRepository> userRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new KnowledgePracticeViewModel_Factory(knowledgeDaoProvider, kelingApiServiceProvider, userRepositoryProvider, savedStateHandleProvider);
  }

  public static KnowledgePracticeViewModel newInstance(KnowledgeDao knowledgeDao,
      KelingApiService kelingApiService, UserRepository userRepository,
      SavedStateHandle savedStateHandle) {
    return new KnowledgePracticeViewModel(knowledgeDao, kelingApiService, userRepository, savedStateHandle);
  }
}
