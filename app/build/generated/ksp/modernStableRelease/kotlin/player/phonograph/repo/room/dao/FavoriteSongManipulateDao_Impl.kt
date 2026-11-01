package player.phonograph.repo.room.dao

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
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.FavoriteSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavoriteSongManipulateDao_Impl(
  __db: RoomDatabase,
) : FavoriteSongManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfFavoriteSongEntity: EntityInsertAdapter<FavoriteSongEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFavoriteSongEntity = object : EntityInsertAdapter<FavoriteSongEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `favorite_songs` (`_id`,`path`,`title`,`date_added`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FavoriteSongEntity) {
        statement.bindLong(1, entity.mediastoreId)
        statement.bindText(2, entity.path)
        statement.bindText(3, entity.title)
        statement.bindLong(4, entity.date)
      }
    }
  }

  public override suspend fun add(song: FavoriteSongEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfFavoriteSongEntity.insertAndReturnId(_connection, song)
    _result
  }

  public override suspend fun add(songs: Collection<FavoriteSongEntity>): LongArray = performSuspending(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfFavoriteSongEntity.insertAndReturnIdsArray(_connection, songs)
    _result
  }

  public override suspend fun remove(id: Long, path: String): Int {
    val _sql: String = "DELETE FROM favorite_songs where _id = ? and path = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, path)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun purge(): Int {
    val _sql: String = "DELETE FROM favorite_songs"
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
