package com.example.noteapp.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.noteapp.`data`.local.entity.NoteEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class NoteDao_Impl(
  __db: RoomDatabase,
) : NoteDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfNoteEntity: EntityInsertAdapter<NoteEntity>

  private val __deleteAdapterOfNoteEntity: EntityDeleteOrUpdateAdapter<NoteEntity>

  private val __updateAdapterOfNoteEntity: EntityDeleteOrUpdateAdapter<NoteEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfNoteEntity = object : EntityInsertAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `tb_notes` (`id`,`title`,`content`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.content)
        statement.bindLong(4, entity.createdAt)
        statement.bindLong(5, entity.updatedAt)
      }
    }
    this.__deleteAdapterOfNoteEntity = object : EntityDeleteOrUpdateAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `tb_notes` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfNoteEntity = object : EntityDeleteOrUpdateAdapter<NoteEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `tb_notes` SET `id` = ?,`title` = ?,`content` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: NoteEntity) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.title)
        statement.bindText(3, entity.content)
        statement.bindLong(4, entity.createdAt)
        statement.bindLong(5, entity.updatedAt)
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertNote(entity: NoteEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfNoteEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun deleteNote(entity: NoteEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfNoteEntity.handle(_connection, entity)
  }

  public override suspend fun updateNote(entity: NoteEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __updateAdapterOfNoteEntity.handle(_connection, entity)
    _result
  }

  public override fun getAllNotes(): Flow<List<NoteEntity>> {
    val _sql: String = "SELECT * FROM tb_notes ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("tb_notes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfContent: Int = getColumnIndexOrThrow(_stmt, "content")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "createdAt")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updatedAt")
        val _result: MutableList<NoteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: NoteEntity
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpContent: String
          _tmpContent = _stmt.getText(_columnIndexOfContent)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = NoteEntity(_tmpId,_tmpTitle,_tmpContent,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
