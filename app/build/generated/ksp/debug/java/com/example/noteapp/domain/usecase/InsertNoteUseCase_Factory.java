package com.example.noteapp.domain.usecase;

import com.example.noteapp.domain.respository.NoteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class InsertNoteUseCase_Factory implements Factory<InsertNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  private InsertNoteUseCase_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public InsertNoteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static InsertNoteUseCase_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new InsertNoteUseCase_Factory(repositoryProvider);
  }

  public static InsertNoteUseCase newInstance(NoteRepository repository) {
    return new InsertNoteUseCase(repository);
  }
}
