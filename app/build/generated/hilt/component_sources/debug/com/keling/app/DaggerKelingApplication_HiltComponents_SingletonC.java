package com.keling.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import com.keling.app.data.local.KelingDatabase;
import com.keling.app.data.local.dao.AchievementDao;
import com.keling.app.data.local.dao.CourseDao;
import com.keling.app.data.local.dao.KnowledgeDao;
import com.keling.app.data.local.dao.TaskDao;
import com.keling.app.data.local.dao.UserDao;
import com.keling.app.data.preferences.AccessibilityPreferencesRepository;
import com.keling.app.data.remote.KelingApiService;
import com.keling.app.data.repository.AchievementRepository;
import com.keling.app.data.repository.AchievementRepositoryImpl;
import com.keling.app.data.repository.CourseRepository;
import com.keling.app.data.repository.CourseRepositoryImpl;
import com.keling.app.data.repository.TaskRepository;
import com.keling.app.data.repository.TaskRepositoryImpl;
import com.keling.app.data.repository.UserRepository;
import com.keling.app.data.repository.UserRepositoryImpl;
import com.keling.app.data.task.GradeTaskGenerator;
import com.keling.app.di.AppModule;
import com.keling.app.di.AppModule_ProvideGradeTaskGeneratorFactory;
import com.keling.app.di.AppModule_ProvideGsonFactory;
import com.keling.app.di.DatabaseModule;
import com.keling.app.di.DatabaseModule_ProvideAchievementDaoFactory;
import com.keling.app.di.DatabaseModule_ProvideCourseDaoFactory;
import com.keling.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.keling.app.di.DatabaseModule_ProvideKnowledgeDaoFactory;
import com.keling.app.di.DatabaseModule_ProvideTaskDaoFactory;
import com.keling.app.di.DatabaseModule_ProvideUserDaoFactory;
import com.keling.app.di.NetworkModule;
import com.keling.app.di.NetworkModule_ProvideKelingApiServiceFactory;
import com.keling.app.di.NetworkModule_ProvideKelingRetrofitFactory;
import com.keling.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.keling.app.ui.screens.ai.AIAssistantViewModel;
import com.keling.app.ui.screens.ai.AIAssistantViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.campus.CampusTasksViewModel;
import com.keling.app.ui.screens.campus.CampusTasksViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.courses.CourseDetailViewModel;
import com.keling.app.ui.screens.courses.CourseDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.courses.CoursesViewModel;
import com.keling.app.ui.screens.courses.CoursesViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.focus.FocusViewModel;
import com.keling.app.ui.screens.focus.FocusViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.home.HomeViewModel;
import com.keling.app.ui.screens.home.HomeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.knowledge.KnowledgeGraphViewModel;
import com.keling.app.ui.screens.knowledge.KnowledgeGraphViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.knowledge.KnowledgePracticeViewModel;
import com.keling.app.ui.screens.knowledge.KnowledgePracticeViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.login.LoginViewModel;
import com.keling.app.ui.screens.login.LoginViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.settings.AccessibilityViewModel;
import com.keling.app.ui.screens.settings.AccessibilityViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.settings.PersonalProfileViewModel;
import com.keling.app.ui.screens.settings.PersonalProfileViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.settings.PrivacySettingsViewModel;
import com.keling.app.ui.screens.settings.PrivacySettingsViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.tasks.TaskDetailViewModel;
import com.keling.app.ui.screens.tasks.TaskDetailViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.tasks.TaskExecutionViewModel;
import com.keling.app.ui.screens.tasks.TaskExecutionViewModel_HiltModules_KeyModule_ProvideFactory;
import com.keling.app.ui.screens.tasks.TasksViewModel;
import com.keling.app.ui.screens.tasks.TasksViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerKelingApplication_HiltComponents_SingletonC {
  private DaggerKelingApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder appModule(AppModule appModule) {
      Preconditions.checkNotNull(appModule);
      return this;
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder databaseModule(DatabaseModule databaseModule) {
      Preconditions.checkNotNull(databaseModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(
        HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
      Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder networkModule(NetworkModule networkModule) {
      Preconditions.checkNotNull(networkModule);
      return this;
    }

    public KelingApplication_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements KelingApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public KelingApplication_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonCImpl);
    }
  }

  private static final class ActivityCBuilder implements KelingApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public KelingApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements KelingApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public KelingApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements KelingApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public KelingApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements KelingApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public KelingApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements KelingApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public KelingApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements KelingApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public KelingApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends KelingApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends KelingApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends KelingApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends KelingApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return ImmutableSet.<String>of(AIAssistantViewModel_HiltModules_KeyModule_ProvideFactory.provide(), AccessibilityViewModel_HiltModules_KeyModule_ProvideFactory.provide(), CampusTasksViewModel_HiltModules_KeyModule_ProvideFactory.provide(), CourseDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide(), CoursesViewModel_HiltModules_KeyModule_ProvideFactory.provide(), FocusViewModel_HiltModules_KeyModule_ProvideFactory.provide(), HomeViewModel_HiltModules_KeyModule_ProvideFactory.provide(), KnowledgeGraphViewModel_HiltModules_KeyModule_ProvideFactory.provide(), KnowledgePracticeViewModel_HiltModules_KeyModule_ProvideFactory.provide(), LoginViewModel_HiltModules_KeyModule_ProvideFactory.provide(), PersonalProfileViewModel_HiltModules_KeyModule_ProvideFactory.provide(), PrivacySettingsViewModel_HiltModules_KeyModule_ProvideFactory.provide(), TaskDetailViewModel_HiltModules_KeyModule_ProvideFactory.provide(), TaskExecutionViewModel_HiltModules_KeyModule_ProvideFactory.provide(), TasksViewModel_HiltModules_KeyModule_ProvideFactory.provide());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends KelingApplication_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AIAssistantViewModel> aIAssistantViewModelProvider;

    private Provider<AccessibilityViewModel> accessibilityViewModelProvider;

    private Provider<CampusTasksViewModel> campusTasksViewModelProvider;

    private Provider<CourseDetailViewModel> courseDetailViewModelProvider;

    private Provider<CoursesViewModel> coursesViewModelProvider;

    private Provider<FocusViewModel> focusViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<KnowledgeGraphViewModel> knowledgeGraphViewModelProvider;

    private Provider<KnowledgePracticeViewModel> knowledgePracticeViewModelProvider;

    private Provider<LoginViewModel> loginViewModelProvider;

    private Provider<PersonalProfileViewModel> personalProfileViewModelProvider;

    private Provider<PrivacySettingsViewModel> privacySettingsViewModelProvider;

    private Provider<TaskDetailViewModel> taskDetailViewModelProvider;

    private Provider<TaskExecutionViewModel> taskExecutionViewModelProvider;

    private Provider<TasksViewModel> tasksViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.aIAssistantViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.accessibilityViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.campusTasksViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.courseDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.coursesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.focusViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.knowledgeGraphViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.knowledgePracticeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.loginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.personalProfileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.privacySettingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.taskDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.taskExecutionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.tasksViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return ImmutableMap.<String, Provider<ViewModel>>builderWithExpectedSize(15).put("com.keling.app.ui.screens.ai.AIAssistantViewModel", ((Provider) aIAssistantViewModelProvider)).put("com.keling.app.ui.screens.settings.AccessibilityViewModel", ((Provider) accessibilityViewModelProvider)).put("com.keling.app.ui.screens.campus.CampusTasksViewModel", ((Provider) campusTasksViewModelProvider)).put("com.keling.app.ui.screens.courses.CourseDetailViewModel", ((Provider) courseDetailViewModelProvider)).put("com.keling.app.ui.screens.courses.CoursesViewModel", ((Provider) coursesViewModelProvider)).put("com.keling.app.ui.screens.focus.FocusViewModel", ((Provider) focusViewModelProvider)).put("com.keling.app.ui.screens.home.HomeViewModel", ((Provider) homeViewModelProvider)).put("com.keling.app.ui.screens.knowledge.KnowledgeGraphViewModel", ((Provider) knowledgeGraphViewModelProvider)).put("com.keling.app.ui.screens.knowledge.KnowledgePracticeViewModel", ((Provider) knowledgePracticeViewModelProvider)).put("com.keling.app.ui.screens.login.LoginViewModel", ((Provider) loginViewModelProvider)).put("com.keling.app.ui.screens.settings.PersonalProfileViewModel", ((Provider) personalProfileViewModelProvider)).put("com.keling.app.ui.screens.settings.PrivacySettingsViewModel", ((Provider) privacySettingsViewModelProvider)).put("com.keling.app.ui.screens.tasks.TaskDetailViewModel", ((Provider) taskDetailViewModelProvider)).put("com.keling.app.ui.screens.tasks.TaskExecutionViewModel", ((Provider) taskExecutionViewModelProvider)).put("com.keling.app.ui.screens.tasks.TasksViewModel", ((Provider) tasksViewModelProvider)).build();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.keling.app.ui.screens.ai.AIAssistantViewModel 
          return (T) new AIAssistantViewModel(singletonCImpl.bindTaskRepositoryProvider.get(), singletonCImpl.bindUserRepositoryProvider.get());

          case 1: // com.keling.app.ui.screens.settings.AccessibilityViewModel 
          return (T) new AccessibilityViewModel(singletonCImpl.accessibilityPreferencesRepositoryProvider.get());

          case 2: // com.keling.app.ui.screens.campus.CampusTasksViewModel 
          return (T) new CampusTasksViewModel(singletonCImpl.bindTaskRepositoryProvider.get(), singletonCImpl.bindUserRepositoryProvider.get());

          case 3: // com.keling.app.ui.screens.courses.CourseDetailViewModel 
          return (T) new CourseDetailViewModel(singletonCImpl.bindCourseRepositoryProvider.get(), singletonCImpl.provideKelingApiServiceProvider.get(), singletonCImpl.bindTaskRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 4: // com.keling.app.ui.screens.courses.CoursesViewModel 
          return (T) new CoursesViewModel(singletonCImpl.bindCourseRepositoryProvider.get());

          case 5: // com.keling.app.ui.screens.focus.FocusViewModel 
          return (T) new FocusViewModel(singletonCImpl.bindTaskRepositoryProvider.get());

          case 6: // com.keling.app.ui.screens.home.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.bindUserRepositoryProvider.get(), singletonCImpl.bindTaskRepositoryProvider.get(), singletonCImpl.bindCourseRepositoryProvider.get(), singletonCImpl.provideKelingApiServiceProvider.get());

          case 7: // com.keling.app.ui.screens.knowledge.KnowledgeGraphViewModel 
          return (T) new KnowledgeGraphViewModel(singletonCImpl.knowledgeDao(), singletonCImpl.provideKelingApiServiceProvider.get(), singletonCImpl.bindCourseRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 8: // com.keling.app.ui.screens.knowledge.KnowledgePracticeViewModel 
          return (T) new KnowledgePracticeViewModel(singletonCImpl.knowledgeDao(), singletonCImpl.provideKelingApiServiceProvider.get(), singletonCImpl.bindUserRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 9: // com.keling.app.ui.screens.login.LoginViewModel 
          return (T) new LoginViewModel(singletonCImpl.bindUserRepositoryProvider.get());

          case 10: // com.keling.app.ui.screens.settings.PersonalProfileViewModel 
          return (T) new PersonalProfileViewModel(singletonCImpl.bindUserRepositoryProvider.get());

          case 11: // com.keling.app.ui.screens.settings.PrivacySettingsViewModel 
          return (T) new PrivacySettingsViewModel(singletonCImpl.bindUserRepositoryProvider.get());

          case 12: // com.keling.app.ui.screens.tasks.TaskDetailViewModel 
          return (T) new TaskDetailViewModel(singletonCImpl.bindTaskRepositoryProvider.get());

          case 13: // com.keling.app.ui.screens.tasks.TaskExecutionViewModel 
          return (T) new TaskExecutionViewModel(singletonCImpl.bindTaskRepositoryProvider.get());

          case 14: // com.keling.app.ui.screens.tasks.TasksViewModel 
          return (T) new TasksViewModel(singletonCImpl.bindTaskRepositoryProvider.get(), singletonCImpl.bindUserRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends KelingApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends KelingApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends KelingApplication_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<KelingDatabase> provideDatabaseProvider;

    private Provider<Gson> provideGsonProvider;

    private Provider<GradeTaskGenerator> provideGradeTaskGeneratorProvider;

    private Provider<AchievementRepositoryImpl> achievementRepositoryImplProvider;

    private Provider<AchievementRepository> bindAchievementRepositoryProvider;

    private Provider<UserRepositoryImpl> userRepositoryImplProvider;

    private Provider<UserRepository> bindUserRepositoryProvider;

    private Provider<TaskRepositoryImpl> taskRepositoryImplProvider;

    private Provider<TaskRepository> bindTaskRepositoryProvider;

    private Provider<AccessibilityPreferencesRepository> accessibilityPreferencesRepositoryProvider;

    private Provider<CourseRepositoryImpl> courseRepositoryImplProvider;

    private Provider<CourseRepository> bindCourseRepositoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideKelingRetrofitProvider;

    private Provider<KelingApiService> provideKelingApiServiceProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private TaskDao taskDao() {
      return DatabaseModule_ProvideTaskDaoFactory.provideTaskDao(provideDatabaseProvider.get());
    }

    private AchievementDao achievementDao() {
      return DatabaseModule_ProvideAchievementDaoFactory.provideAchievementDao(provideDatabaseProvider.get());
    }

    private UserDao userDao() {
      return DatabaseModule_ProvideUserDaoFactory.provideUserDao(provideDatabaseProvider.get());
    }

    private CourseDao courseDao() {
      return DatabaseModule_ProvideCourseDaoFactory.provideCourseDao(provideDatabaseProvider.get());
    }

    private KnowledgeDao knowledgeDao() {
      return DatabaseModule_ProvideKnowledgeDaoFactory.provideKnowledgeDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<KelingDatabase>(singletonCImpl, 1));
      this.provideGsonProvider = DoubleCheck.provider(new SwitchingProvider<Gson>(singletonCImpl, 3));
      this.provideGradeTaskGeneratorProvider = DoubleCheck.provider(new SwitchingProvider<GradeTaskGenerator>(singletonCImpl, 2));
      this.achievementRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 4);
      this.bindAchievementRepositoryProvider = DoubleCheck.provider((Provider) achievementRepositoryImplProvider);
      this.userRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 5);
      this.bindUserRepositoryProvider = DoubleCheck.provider((Provider) userRepositoryImplProvider);
      this.taskRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 0);
      this.bindTaskRepositoryProvider = DoubleCheck.provider((Provider) taskRepositoryImplProvider);
      this.accessibilityPreferencesRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AccessibilityPreferencesRepository>(singletonCImpl, 6));
      this.courseRepositoryImplProvider = new SwitchingProvider<>(singletonCImpl, 7);
      this.bindCourseRepositoryProvider = DoubleCheck.provider((Provider) courseRepositoryImplProvider);
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 10));
      this.provideKelingRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 9));
      this.provideKelingApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<KelingApiService>(singletonCImpl, 8));
    }

    @Override
    public void injectKelingApplication(KelingApplication kelingApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.keling.app.data.repository.TaskRepositoryImpl 
          return (T) new TaskRepositoryImpl(singletonCImpl.taskDao(), singletonCImpl.provideGradeTaskGeneratorProvider.get(), singletonCImpl.provideGsonProvider.get(), singletonCImpl.bindAchievementRepositoryProvider.get(), singletonCImpl.bindUserRepositoryProvider.get());

          case 1: // com.keling.app.data.local.KelingDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.keling.app.data.task.GradeTaskGenerator 
          return (T) AppModule_ProvideGradeTaskGeneratorFactory.provideGradeTaskGenerator(singletonCImpl.provideGsonProvider.get());

          case 3: // com.google.gson.Gson 
          return (T) AppModule_ProvideGsonFactory.provideGson();

          case 4: // com.keling.app.data.repository.AchievementRepositoryImpl 
          return (T) new AchievementRepositoryImpl(singletonCImpl.achievementDao());

          case 5: // com.keling.app.data.repository.UserRepositoryImpl 
          return (T) new UserRepositoryImpl(singletonCImpl.userDao());

          case 6: // com.keling.app.data.preferences.AccessibilityPreferencesRepository 
          return (T) new AccessibilityPreferencesRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.keling.app.data.repository.CourseRepositoryImpl 
          return (T) new CourseRepositoryImpl(singletonCImpl.courseDao());

          case 8: // com.keling.app.data.remote.KelingApiService 
          return (T) NetworkModule_ProvideKelingApiServiceFactory.provideKelingApiService(singletonCImpl.provideKelingRetrofitProvider.get());

          case 9: // @javax.inject.Named("keling") retrofit2.Retrofit 
          return (T) NetworkModule_ProvideKelingRetrofitFactory.provideKelingRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 10: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
