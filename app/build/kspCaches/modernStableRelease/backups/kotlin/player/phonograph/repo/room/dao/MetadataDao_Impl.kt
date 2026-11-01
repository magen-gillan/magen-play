package player.phonograph.repo.room.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.Metadata

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class MetadataDao_Impl(
  __db: RoomDatabase,
) : MetadataDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfMetadata: EntityInsertAdapter<Metadata>
  init {
    this.__db = __db
    this.__insertAdapterOfMetadata = object : EntityInsertAdapter<Metadata>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `metadata` (`key`,`value`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Metadata) {
        statement.bindText(1, entity.key)
        statement.bindText(2, entity.value)
      }
    }
  }

  public override fun insertOrReplace(metadata: Metadata): Unit = performBlocking(__db, false, true) { _connection ->
    __insertAdapterOfMetadata.insert(_connection, metadata)
  }

  public override fun all(): List<Metadata> {
    val _sql: String = "SELECT * FROM metadata"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfKey: Int = getColumnIndexOrThrow(_stmt, "key")
        val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
        val _result: MutableList<Metadata> = mutableListOf()
        while (_stmt.step()) {
          val _item: Metadata
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfKey)
          val _tmpValue: String
          _tmpValue = _stmt.getText(_columnIndexOfValue)
          _item = Metadata(_tmpKey,_tmpValue)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByKey(key: String): Metadata? {
    val _sql: String = "SELECT * FROM metadata WHERE key = ? LIMIT 1"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, key)
        val _columnIndexOfKey: Int = getColumnIndexOrThrow(_stmt, "key")
        val _columnIndexOfValue: Int = getColumnIndexOrThrow(_stmt, "value")
        val _result: Metadata?
        if (_stmt.step()) {
          val _tmpKey: String
          _tmpKey = _stmt.getText(_columnIndexOfKey)
          val _tmpValue: String
          _tmpValue = _stmt.getText(_columnIndexOfValue)
          _result = Metadata(_tmpKey,_tmpValue)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteByKey(key: String) {
    val _sql: String = "DELETE FROM metadata WHERE key = ?"
    return performBlocking(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, key)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun deleteAll() {
    val _sql: String = "DELETE FROM metadata"
    return performBlocking(__db, false, true) { _connection ->
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
