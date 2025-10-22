package com.example.noteapp.di;

import com.example.noteapp.data.local.dao.NoteDao;
import com.example.noteapp.data.local.database.NotesDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class AppModule_ProvideEntityDaoFactory implements Factory<NoteDao> {
  private final Provider<NotesDatabase> dbProvider;

  private AppModule_ProvideEntityDaoFactory(Provider<NotesDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public NoteDao get() {
    return provideEntityDao(dbProvider.get());
  }

  public static AppModule_ProvideEntityDaoFactory create(Provider<NotesDatabase> dbProvider) {
    return new AppModule_ProvideEntityDaoFactory(dbProvider);
  }

  public static NoteDao provideEntityDao(NotesDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideEntityDao(db));
  }
}
