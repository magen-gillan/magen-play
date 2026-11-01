package player.phonograph.repo.room.dao

import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.PinedPlaylistsEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PinedPlaylistQueryDao_Impl(
  __db: RoomDatabase,
) : PinedPlaylistQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun all(): List<PinedPlaylistsEntity> {
    val _sql: String = "SELECT * from favorite_playlists order by DATE_ADDED DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "primary_id")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfSub: Int = getColumnIndexOrThrow(_stmt, "sub_id")
        val _columnIndexOfData: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _result: MutableList<PinedPlaylistsEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PinedPlaylistsEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpType: Int
          _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
          val _tmpSub: Long
          _tmpSub = _stmt.getLong(_columnIndexOfSub)
          val _tmpData: String
          _tmpData = _stmt.getText(_columnIndexOfData)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          _item = PinedPlaylistsEntity(_tmpId,_tmpType,_tmpSub,_tmpData,_tmpTitle,_tmpDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun contains(
    type: Int,
    subId: Long,
    path: String,
  ): Boolean {
    val _sql: String = "SELECT EXISTS(SELECT 1 FROM favorite_playlists WHERE type = ? AND (sub_id = ? OR location = ?))"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, type.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, subId)
        _argIndex = 3
        _stmt.bindText(_argIndex, path)
        val _result: Boolean
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp != 0
        } else {
          _result = false
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
