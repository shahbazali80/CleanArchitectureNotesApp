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
public final class GetAllNotesUseCase_Factory implements Factory<GetAllNotesUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  private GetAllNotesUseCase_Factory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllNotesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllNotesUseCase_Factory create(Provider<NoteRepository> repositoryProvider) {
    return new GetAllNotesUseCase_Factory(repositoryProvider);
  }

  public static GetAllNotesUseCase newInstance(NoteRepository repository) {
    return new GetAllNotesUseCase(repository);
  }
}
