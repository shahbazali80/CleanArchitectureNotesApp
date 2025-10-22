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
public final class UpdateNoteUseCase_Factory implements Factory<UpdateNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  private UpdateNoteUseCase_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateNoteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateNoteUseCase_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new UpdateNoteUseCase_Factory(repositoryProvider);
  }

  public static UpdateNoteUseCase newInstance(NoteRepository repository) {
    return new UpdateNoteUseCase(repository);
  }
}
