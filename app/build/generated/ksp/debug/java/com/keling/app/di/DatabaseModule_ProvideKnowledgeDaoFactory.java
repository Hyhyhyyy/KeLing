package com.keling.app.di;

import com.keling.app.data.local.KelingDatabase;
import com.keling.app.data.local.dao.KnowledgeDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideKnowledgeDaoFactory implements Factory<KnowledgeDao> {
  private final Provider<KelingDatabase> databaseProvider;

  public DatabaseModule_ProvideKnowledgeDaoFactory(Provider<KelingDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public KnowledgeDao get() {
    return provideKnowledgeDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideKnowledgeDaoFactory create(
      Provider<KelingDatabase> databaseProvider) {
    return new DatabaseModule_ProvideKnowledgeDaoFactory(databaseProvider);
  }

  public static KnowledgeDao provideKnowledgeDao(KelingDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideKnowledgeDao(database));
  }
}
