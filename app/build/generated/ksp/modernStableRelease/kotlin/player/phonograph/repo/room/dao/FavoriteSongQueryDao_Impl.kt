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
import player.phonograph.repo.room.entity.FavoriteSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavoriteSongQueryDao_Impl(
  __db: RoomDatabase,
) : FavoriteSongQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun all(): List<FavoriteSongEntity> {
    val _sql: String = "SELECT * from favorite_songs order by DATE_ADDED DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "path")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _result: MutableList<FavoriteSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoriteSongEntity
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDate: Long
          _tmpDate = _stmt.getLong(_columnIndexOfDate)
          _item = FavoriteSongEntity(_tmpMediastoreId,_tmpPath,_tmpTitle,_tmpDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun contains(id: Long, path: String): Boolean {
    val _sql: String = "SELECT EXISTS(SELECT 1 from favorite_songs where _id = ? or path = ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
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
