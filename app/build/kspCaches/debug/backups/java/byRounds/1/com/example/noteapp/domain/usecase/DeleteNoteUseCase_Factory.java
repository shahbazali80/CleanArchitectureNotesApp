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
public final class DeleteNoteUseCase_Factory implements Factory<DeleteNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  private DeleteNoteUseCase_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteNoteUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static DeleteNoteUseCase_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new DeleteNoteUseCase_Factory(repositoryProvider);
  }

  public static DeleteNoteUseCase newInstance(NoteRepository repository) {
    return new DeleteNoteUseCase(repository);
  }
}
