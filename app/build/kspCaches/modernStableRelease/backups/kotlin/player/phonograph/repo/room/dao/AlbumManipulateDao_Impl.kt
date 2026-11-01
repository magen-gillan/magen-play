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
import player.phonograph.repo.room.entity.AlbumEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AlbumManipulateDao_Impl(
  __db: RoomDatabase,
) : AlbumManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfAlbumEntity: EntityInsertAdapter<AlbumEntity>

  private val __deleteAdapterOfAlbumEntity: EntityDeleteOrUpdateAdapter<AlbumEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfAlbumEntity = object : EntityInsertAdapter<AlbumEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `albums` (`album_id`,`album`,`artist_id`,`album_artist`,`year`,`date_modified`,`song_count`) VALUES (?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: AlbumEntity) {
        statement.bindLong(1, entity.albumId)
        statement.bindText(2, entity.albumName)
        statement.bindLong(3, entity.artistId)
        statement.bindText(4, entity.albumArtistName)
        statement.bindLong(5, entity.year.toLong())
        statement.bindLong(6, entity.dateModified)
        statement.bindLong(7, entity.songCount.toLong())
      }
    }
    this.__deleteAdapterOfAlbumEntity = object : EntityDeleteOrUpdateAdapter<AlbumEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `albums` WHERE `album_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: AlbumEntity) {
        statement.bindLong(1, entity.albumId)
      }
    }
  }

  public override suspend fun update(album: AlbumEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfAlbumEntity.insertAndReturnId(_connection, album)
    _result
  }

  public override suspend fun update(albums: List<AlbumEntity>): LongArray = performSuspending(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfAlbumEntity.insertAndReturnIdsArray(_connection, albums)
    _result
  }

  public override suspend fun delete(album: AlbumEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfAlbumEntity.handle(_connection, album)
    _result
  }

  public override suspend fun delete(albums: List<AlbumEntity>): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfAlbumEntity.handleMultiple(_connection, albums)
    _result
  }

  public override suspend fun deleteAll(): Int {
    val _sql: String = "DELETE FROM albums"
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
