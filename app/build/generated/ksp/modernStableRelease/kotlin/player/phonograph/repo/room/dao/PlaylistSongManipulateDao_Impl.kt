package player.phonograph.repo.room.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.PlaylistSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaylistSongManipulateDao_Impl(
  __db: RoomDatabase,
) : PlaylistSongManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlaylistSongEntity: EntityInsertAdapter<PlaylistSongEntity>

  private val __deleteAdapterOfPlaylistSongEntity: EntityDeleteOrUpdateAdapter<PlaylistSongEntity>

  private val __updateAdapterOfPlaylistSongEntity: EntityDeleteOrUpdateAdapter<PlaylistSongEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlaylistSongEntity = object : EntityInsertAdapter<PlaylistSongEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `playlist_songs` (`playlist_song_id`,`_id`,`playlist_id`,`position`,`path`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistSongEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.mediastoreId)
        statement.bindLong(3, entity.playlistId)
        statement.bindLong(4, entity.position.toLong())
        statement.bindText(5, entity.path)
      }
    }
    this.__deleteAdapterOfPlaylistSongEntity = object : EntityDeleteOrUpdateAdapter<PlaylistSongEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `playlist_songs` WHERE `playlist_song_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistSongEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfPlaylistSongEntity = object : EntityDeleteOrUpdateAdapter<PlaylistSongEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `playlist_songs` SET `playlist_song_id` = ?,`_id` = ?,`playlist_id` = ?,`position` = ?,`path` = ? WHERE `playlist_song_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistSongEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.mediastoreId)
        statement.bindLong(3, entity.playlistId)
        statement.bindLong(4, entity.position.toLong())
        statement.bindText(5, entity.path)
        statement.bindLong(6, entity.id)
      }
    }
  }

  public override suspend fun insert(playlist: PlaylistSongEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPlaylistSongEntity.insertAndReturnId(_connection, playlist)
    _result
  }

  public override suspend fun insert(playlist: Collection<PlaylistSongEntity>): List<Long> = performSuspending(__db, false, true) { _connection ->
    val _result: List<Long> = __insertAdapterOfPlaylistSongEntity.insertAndReturnIdsList(_connection, playlist)
    _result
  }

  public override suspend fun delete(playlist: PlaylistSongEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfPlaylistSongEntity.handle(_connection, playlist)
    _result
  }

  public override suspend fun update(playlist: PlaylistSongEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPlaylistSongEntity.handle(_connection, playlist)
  }

  public override suspend fun removeItem(
    queryDao: PlaylistSongQueryDao,
    playlistId: Long,
    songId: Long,
    position: Int,
  ): Boolean = performInTransactionSuspending(__db) {
    super@PlaylistSongManipulateDao_Impl.removeItem(queryDao, playlistId, songId, position)
  }

  public override suspend fun move(
    queryDao: PlaylistSongQueryDao,
    playlistId: Long,
    from: Int,
    to: Int,
  ): Boolean = performInTransactionSuspending(__db) {
    super@PlaylistSongManipulateDao_Impl.move(queryDao, playlistId, from, to)
  }

  public override suspend fun swap(
    queryDao: PlaylistSongQueryDao,
    playlistId: Long,
    positionA: Int,
    positionB: Int,
  ): Boolean = performInTransactionSuspending(__db) {
    super@PlaylistSongManipulateDao_Impl.swap(queryDao, playlistId, positionA, positionB)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
