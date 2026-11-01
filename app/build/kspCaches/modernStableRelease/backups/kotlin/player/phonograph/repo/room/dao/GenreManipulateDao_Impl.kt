package player.phonograph.repo.room.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.LongArray
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.GenreEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GenreManipulateDao_Impl(
  __db: RoomDatabase,
) : GenreManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfGenreEntity: EntityInsertAdapter<GenreEntity>

  private val __deleteAdapterOfGenreEntity: EntityDeleteOrUpdateAdapter<GenreEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfGenreEntity = object : EntityInsertAdapter<GenreEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `genres` (`genre_id`,`genre`,`category`,`genre_id_mediastore`,`song_count`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: GenreEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmpCategory: String? = entity.category
        if (_tmpCategory == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpCategory)
        }
        statement.bindLong(4, entity.mediastoreId)
        statement.bindLong(5, entity.songCount.toLong())
      }
    }
    this.__deleteAdapterOfGenreEntity = object : EntityDeleteOrUpdateAdapter<GenreEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `genres` WHERE `genre_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: GenreEntity) {
        statement.bindLong(1, entity.id)
      }
    }
  }

  public override suspend fun update(genre: GenreEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfGenreEntity.insertAndReturnId(_connection, genre)
    _result
  }

  public override suspend fun update(genres: List<GenreEntity>): LongArray = performSuspending(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfGenreEntity.insertAndReturnIdsArray(_connection, genres)
    _result
  }

  public override suspend fun delete(genre: GenreEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfGenreEntity.handle(_connection, genre)
    _result
  }

  public override suspend fun delete(genres: List<GenreEntity>): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfGenreEntity.handleMultiple(_connection, genres)
    _result
  }

  public override suspend fun deleteAll(): Int {
    val _sql: String = "DELETE FROM genres"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
