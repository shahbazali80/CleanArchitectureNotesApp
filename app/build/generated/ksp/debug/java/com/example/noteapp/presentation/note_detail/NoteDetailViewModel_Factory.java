package com.example.noteapp.presentation.note_detail;

import com.example.noteapp.domain.usecase.InsertNoteUseCase;
import com.example.noteapp.domain.usecase.UpdateNoteUseCase;
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
public final class NoteDetailViewModel_Factory implements Factory<NoteDetailViewModel> {
  private final Provider<InsertNoteUseCase> insertNoteUseCaseProvider;

  private final Provider<UpdateNoteUseCase> updateNoteUseCaseProvider;

  private NoteDetailViewModel_Factory(Provider<InsertNoteUseCase> insertNoteUseCaseProvider,
      Provider<UpdateNoteUseCase> updateNoteUseCaseProvider) {
    this.insertNoteUseCaseProvider = insertNoteUseCaseProvider;
    this.updateNoteUseCaseProvider = updateNoteUseCaseProvider;
  }

  @Override
  public NoteDetailViewModel get() {
    return newInstance(insertNoteUseCaseProvider.get(), updateNoteUseCaseProvider.get());
  }

  public static NoteDetailViewModel_Factory create(
      Provider<InsertNoteUseCase> insertNoteUseCaseProvider,
      Provider<UpdateNoteUseCase> updateNoteUseCaseProvider) {
    return new NoteDetailViewModel_Factory(insertNoteUseCaseProvider, updateNoteUseCaseProvider);
  }

  public static NoteDetailViewModel newInstance(InsertNoteUseCase insertNoteUseCase,
      UpdateNoteUseCase updateNoteUseCase) {
    return new NoteDetailViewModel(insertNoteUseCase, updateNoteUseCase);
  }
}
