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
import player.phonograph.repo.room.entity.ArtistEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ArtistManipulateDao_Impl(
  __db: RoomDatabase,
) : ArtistManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfArtistEntity: EntityInsertAdapter<ArtistEntity>

  private val __deleteAdapterOfArtistEntity: EntityDeleteOrUpdateAdapter<ArtistEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfArtistEntity = object : EntityInsertAdapter<ArtistEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `artists` (`artist_id`,`artist`,`album_count`,`song_count`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ArtistEntity) {
        statement.bindLong(1, entity.artistId)
        statement.bindText(2, entity.artistName)
        statement.bindLong(3, entity.albumCount.toLong())
        statement.bindLong(4, entity.songCount.toLong())
      }
    }
    this.__deleteAdapterOfArtistEntity = object : EntityDeleteOrUpdateAdapter<ArtistEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `artists` WHERE `artist_id` = ? AND `artist` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ArtistEntity) {
        statement.bindLong(1, entity.artistId)
        statement.bindText(2, entity.artistName)
      }
    }
  }

  public override suspend fun update(artist: ArtistEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfArtistEntity.insertAndReturnId(_connection, artist)
    _result
  }

  public override suspend fun update(artists: List<ArtistEntity>): LongArray = performSuspending(__db, false, true) { _connection ->
    val _result: LongArray = __insertAdapterOfArtistEntity.insertAndReturnIdsArray(_connection, artists)
    _result
  }

  public override suspend fun delete(artist: ArtistEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfArtistEntity.handle(_connection, artist)
    _result
  }

  public override suspend fun delete(artists: List<ArtistEntity>): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfArtistEntity.handleMultiple(_connection, artists)
    _result
  }

  public override suspend fun deleteAll(): Int {
    val _sql: String = "DELETE FROM artists"
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
