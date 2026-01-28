package com.keling.app.data.repository;

import com.google.gson.Gson;
import com.keling.app.data.local.dao.TaskDao;
import com.keling.app.data.task.GradeTaskGenerator;
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
public final class TaskRepositoryImpl_Factory implements Factory<TaskRepositoryImpl> {
  private final Provider<TaskDao> taskDaoProvider;

  private final Provider<GradeTaskGenerator> gradeTaskGeneratorProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<AchievementRepository> achievementRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public TaskRepositoryImpl_Factory(Provider<TaskDao> taskDaoProvider,
      Provider<GradeTaskGenerator> gradeTaskGeneratorProvider, Provider<Gson> gsonProvider,
      Provider<AchievementRepository> achievementRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.taskDaoProvider = taskDaoProvider;
    this.gradeTaskGeneratorProvider = gradeTaskGeneratorProvider;
    this.gsonProvider = gsonProvider;
    this.achievementRepositoryProvider = achievementRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public TaskRepositoryImpl get() {
    return newInstance(taskDaoProvider.get(), gradeTaskGeneratorProvider.get(), gsonProvider.get(), achievementRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static TaskRepositoryImpl_Factory create(Provider<TaskDao> taskDaoProvider,
      Provider<GradeTaskGenerator> gradeTaskGeneratorProvider, Provider<Gson> gsonProvider,
      Provider<AchievementRepository> achievementRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new TaskRepositoryImpl_Factory(taskDaoProvider, gradeTaskGeneratorProvider, gsonProvider, achievementRepositoryProvider, userRepositoryProvider);
  }

  public static TaskRepositoryImpl newInstance(TaskDao taskDao,
      GradeTaskGenerator gradeTaskGenerator, Gson gson, AchievementRepository achievementRepository,
      UserRepository userRepository) {
    return new TaskRepositoryImpl(taskDao, gradeTaskGenerator, gson, achievementRepository, userRepository);
  }
}
