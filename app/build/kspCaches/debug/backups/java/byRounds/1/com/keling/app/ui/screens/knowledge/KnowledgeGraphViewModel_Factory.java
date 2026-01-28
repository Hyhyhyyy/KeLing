package com.keling.app.ui.screens.knowledge;

import androidx.lifecycle.SavedStateHandle;
import com.keling.app.data.local.dao.KnowledgeDao;
import com.keling.app.data.remote.KelingApiService;
import com.keling.app.data.repository.CourseRepository;
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
public final class KnowledgeGraphViewModel_Factory implements Factory<KnowledgeGraphViewModel> {
  private final Provider<KnowledgeDao> knowledgeDaoProvider;

  private final Provider<KelingApiService> kelingApiServiceProvider;

  private final Provider<CourseRepository> courseRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public KnowledgeGraphViewModel_Factory(Provider<KnowledgeDao> knowledgeDaoProvider,
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<CourseRepository> courseRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.knowledgeDaoProvider = knowledgeDaoProvider;
    this.kelingApiServiceProvider = kelingApiServiceProvider;
    this.courseRepositoryProvider = courseRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public KnowledgeGraphViewModel get() {
    return newInstance(knowledgeDaoProvider.get(), kelingApiServiceProvider.get(), courseRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static KnowledgeGraphViewModel_Factory create(Provider<KnowledgeDao> knowledgeDaoProvider,
      Provider<KelingApiService> kelingApiServiceProvider,
      Provider<CourseRepository> courseRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new KnowledgeGraphViewModel_Factory(knowledgeDaoProvider, kelingApiServiceProvider, courseRepositoryProvider, savedStateHandleProvider);
  }

  public static KnowledgeGraphViewModel newInstance(KnowledgeDao knowledgeDao,
      KelingApiService kelingApiService, CourseRepository courseRepository,
      SavedStateHandle savedStateHandle) {
    return new KnowledgeGraphViewModel(knowledgeDao, kelingApiService, courseRepository, savedStateHandle);
  }
}
