package player.phonograph.repo.room.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performBlocking
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.ImageCacheEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ImageCacheDao_Impl(
  __db: RoomDatabase,
) : ImageCacheDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfImageCacheEntity: EntityInsertAdapter<ImageCacheEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfImageCacheEntity = object : EntityInsertAdapter<ImageCacheEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `cache_images` (`cache_domain`,`cache_id`,`cache_source`,`timestamp`,`empty`,`filename`) VALUES (?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ImageCacheEntity) {
        statement.bindLong(1, entity.domain.toLong())
        statement.bindLong(2, entity.id)
        statement.bindLong(3, entity.type.toLong())
        statement.bindLong(4, entity.timestamp)
        val _tmp: Int = if (entity.empty) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        val _tmpFilename: String? = entity.filename
        if (_tmpFilename == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpFilename)
        }
      }
    }
  }

  public override fun refresh(cache: ImageCacheEntity): Long = performBlocking(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfImageCacheEntity.insertAndReturnId(_connection, cache)
    _result
  }

  public override fun obtain(
    domain: Int,
    id: Long,
    source: Int,
  ): ImageCacheEntity? {
    val _sql: String = "SELECT * FROM cache_images WHERE cache_domain = ? AND cache_id = ? AND cache_source = ? LIMIT 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, domain.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _argIndex = 3
        _stmt.bindLong(_argIndex, source.toLong())
        val _columnIndexOfDomain: Int = getColumnIndexOrThrow(_stmt, "cache_domain")
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "cache_id")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "cache_source")
        val _columnIndexOfTimestamp: Int = getColumnIndexOrThrow(_stmt, "timestamp")
        val _columnIndexOfEmpty: Int = getColumnIndexOrThrow(_stmt, "empty")
        val _columnIndexOfFilename: Int = getColumnIndexOrThrow(_stmt, "filename")
        val _result: ImageCacheEntity?
        if (_stmt.step()) {
          val _tmpDomain: Int
          _tmpDomain = _stmt.getLong(_columnIndexOfDomain).toInt()
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpType: Int
          _tmpType = _stmt.getLong(_columnIndexOfType).toInt()
          val _tmpTimestamp: Long
          _tmpTimestamp = _stmt.getLong(_columnIndexOfTimestamp)
          val _tmpEmpty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEmpty).toInt()
          _tmpEmpty = _tmp != 0
          val _tmpFilename: String?
          if (_stmt.isNull(_columnIndexOfFilename)) {
            _tmpFilename = null
          } else {
            _tmpFilename = _stmt.getText(_columnIndexOfFilename)
          }
          _result = ImageCacheEntity(_tmpDomain,_tmpId,_tmpType,_tmpTimestamp,_tmpEmpty,_tmpFilename)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun delete(
    domain: Int,
    id: Long,
    source: Int,
  ): Int {
    val _sql: String = "DELETE FROM cache_images WHERE cache_domain = ? AND cache_id = ? AND cache_source = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, domain.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _argIndex = 3
        _stmt.bindLong(_argIndex, source.toLong())
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAll(): Int {
    val _sql: String = "DELETE FROM cache_images"
    return performBlocking(__db, false, true) { _connection ->
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
