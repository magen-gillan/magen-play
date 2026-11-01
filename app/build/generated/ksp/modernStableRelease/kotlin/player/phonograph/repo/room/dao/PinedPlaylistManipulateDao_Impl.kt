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
import player.phonograph.repo.room.entity.PinedPlaylistsEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PinedPlaylistManipulateDao_Impl(
  __db: RoomDatabase,
) : PinedPlaylistManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfPinedPlaylistsEntity: EntityInsertAdapter<PinedPlaylistsEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPinedPlaylistsEntity = object : EntityInsertAdapter<PinedPlaylistsEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `favorite_playlists` (`primary_id`,`type`,`sub_id`,`location`,`title`,`date_added`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PinedPlaylistsEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.type.toLong())
        statement.bindLong(3, entity.sub)
        statement.bindText(4, entity.data)
        statement.bindText(5, entity.title)
        statement.bindLong(6, entity.date)
      }
    }
  }

  public override suspend fun add(playlist: PinedPlaylistsEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPinedPlaylistsEntity.insertAndReturnId(_connection, playlist)
    _result
  }

  public override suspend fun add(playlists: Collection<PinedPlaylistsEntity>): LongArray = performSuspending(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfPinedPlaylistsEntity.insertAndReturnIdsArray(_connection, playlists)
    _result
  }

  public override suspend fun remove(id: Long): Int {
    val _sql: String = "DELETE FROM favorite_playlists where primary_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun remove(
    type: Int,
    subId: Long,
    path: String,
  ): Int {
    val _sql: String = "DELETE FROM favorite_playlists where type = ? and sub_id = ? and location = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, type.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, subId)
        _argIndex = 3
        _stmt.bindText(_argIndex, path)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun purge(): Int {
    val _sql: String = "DELETE FROM favorite_playlists"
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
