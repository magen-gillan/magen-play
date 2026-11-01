package player.phonograph.repo.room.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.MediastoreSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SongManipulateDao_Impl(
  __db: RoomDatabase,
) : SongManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfMediastoreSongEntity: EntityInsertAdapter<MediastoreSongEntity>

  private val __deleteAdapterOfMediastoreSongEntity:
      EntityDeleteOrUpdateAdapter<MediastoreSongEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfMediastoreSongEntity = object : EntityInsertAdapter<MediastoreSongEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `mediastore` (`_id`,`_data`,`duration`,`date_added`,`date_modified`,`title`,`album_id`,`album`,`artist_id`,`artist`,`year`,`track`,`album_artist`,`composer`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MediastoreSongEntity) {
        statement.bindLong(1, entity.mediastorId)
        statement.bindText(2, entity.path)
        statement.bindLong(3, entity.duration)
        statement.bindLong(4, entity.dateAdded)
        statement.bindLong(5, entity.dateModified)
        statement.bindText(6, entity.title)
        statement.bindLong(7, entity.albumId)
        statement.bindText(8, entity.album)
        statement.bindLong(9, entity.artistId)
        statement.bindText(10, entity.artist)
        statement.bindLong(11, entity.year.toLong())
        statement.bindLong(12, entity.track.toLong())
        statement.bindText(13, entity.albumArtist)
        statement.bindText(14, entity.composer)
      }
    }
    this.__deleteAdapterOfMediastoreSongEntity = object : EntityDeleteOrUpdateAdapter<MediastoreSongEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `mediastore` WHERE `_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: MediastoreSongEntity) {
        statement.bindLong(1, entity.mediastorId)
      }
    }
  }

  public override suspend fun update(song: MediastoreSongEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfMediastoreSongEntity.insertAndReturnId(_connection, song)
    _result
  }

  public override suspend fun update(songs: Collection<MediastoreSongEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfMediastoreSongEntity.insert(_connection, songs)
  }

  public override suspend fun delete(song: MediastoreSongEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfMediastoreSongEntity.handle(_connection, song)
  }

  public override suspend fun delete(songs: Collection<MediastoreSongEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfMediastoreSongEntity.handleMultiple(_connection, songs)
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM mediastore"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
