package player.phonograph.repo.room.dao

import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.PlaylistEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaylistQueryDao_Impl(
  __db: RoomDatabase,
) : PlaylistQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun all(): List<PlaylistEntity> {
    val _sql: String = "SELECT * from playlists"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "playlist_name")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _result: MutableList<PlaylistEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          _item = PlaylistEntity(_tmpId,_tmpName,_tmpDateAdded,_tmpDateModified)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun id(id: Long): PlaylistEntity? {
    val _sql: String = "SELECT * from playlists where playlist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "playlist_name")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _result: PlaylistEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          _result = PlaylistEntity(_tmpId,_tmpName,_tmpDateAdded,_tmpDateModified)
        } else {
          _result = null
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
