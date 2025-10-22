package com.example.noteapp.presentation.notes_list;

import com.example.noteapp.domain.usecase.DeleteNoteUseCase;
import com.example.noteapp.domain.usecase.GetAllNotesUseCase;
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
public final class NotesListViewModel_Factory implements Factory<NotesListViewModel> {
  private final Provider<GetAllNotesUseCase> getAllNotesUseCaseProvider;

  private final Provider<DeleteNoteUseCase> deleteNoteUseCaseProvider;

  private NotesListViewModel_Factory(Provider<GetAllNotesUseCase> getAllNotesUseCaseProvider,
      Provider<DeleteNoteUseCase> deleteNoteUseCaseProvider) {
    this.getAllNotesUseCaseProvider = getAllNotesUseCaseProvider;
    this.deleteNoteUseCaseProvider = deleteNoteUseCaseProvider;
  }

  @Override
  public NotesListViewModel get() {
    return newInstance(getAllNotesUseCaseProvider.get(), deleteNoteUseCaseProvider.get());
  }

  public static NotesListViewModel_Factory create(
      Provider<GetAllNotesUseCase> getAllNotesUseCaseProvider,
      Provider<DeleteNoteUseCase> deleteNoteUseCaseProvider) {
    return new NotesListViewModel_Factory(getAllNotesUseCaseProvider, deleteNoteUseCaseProvider);
  }

  public static NotesListViewModel newInstance(GetAllNotesUseCase getAllNotesUseCase,
      DeleteNoteUseCase deleteNoteUseCase) {
    return new NotesListViewModel(getAllNotesUseCase, deleteNoteUseCase);
  }
}
