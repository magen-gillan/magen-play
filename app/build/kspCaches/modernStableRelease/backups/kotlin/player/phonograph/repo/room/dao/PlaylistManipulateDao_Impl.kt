package player.phonograph.repo.room.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.PlaylistEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaylistManipulateDao_Impl(
  __db: RoomDatabase,
) : PlaylistManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlaylistEntity: EntityInsertAdapter<PlaylistEntity>

  private val __deleteAdapterOfPlaylistEntity: EntityDeleteOrUpdateAdapter<PlaylistEntity>

  private val __updateAdapterOfPlaylistEntity: EntityDeleteOrUpdateAdapter<PlaylistEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlaylistEntity = object : EntityInsertAdapter<PlaylistEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `playlists` (`playlist_id`,`playlist_name`,`date_added`,`date_modified`) VALUES (nullif(?, 0),?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.dateAdded)
        statement.bindLong(4, entity.dateModified)
      }
    }
    this.__deleteAdapterOfPlaylistEntity = object : EntityDeleteOrUpdateAdapter<PlaylistEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `playlists` WHERE `playlist_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistEntity) {
        statement.bindLong(1, entity.id)
      }
    }
    this.__updateAdapterOfPlaylistEntity = object : EntityDeleteOrUpdateAdapter<PlaylistEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `playlists` SET `playlist_id` = ?,`playlist_name` = ?,`date_added` = ?,`date_modified` = ? WHERE `playlist_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: PlaylistEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindLong(3, entity.dateAdded)
        statement.bindLong(4, entity.dateModified)
        statement.bindLong(5, entity.id)
      }
    }
  }

  public override suspend fun insert(playlist: PlaylistEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPlaylistEntity.insertAndReturnId(_connection, playlist)
    _result
  }

  public override suspend fun delete(playlist: PlaylistEntity): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfPlaylistEntity.handle(_connection, playlist)
    _result
  }

  public override suspend fun update(playlist: PlaylistEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfPlaylistEntity.handle(_connection, playlist)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
