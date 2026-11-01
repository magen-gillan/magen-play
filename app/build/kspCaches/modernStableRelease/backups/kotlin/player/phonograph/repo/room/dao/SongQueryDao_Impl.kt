package player.phonograph.repo.room.dao

import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.RoomSQLiteQuery
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndex
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import androidx.sqlite.db.SupportSQLiteQuery
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import player.phonograph.repo.room.entity.MediastoreSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SongQueryDao_Impl(
  __db: RoomDatabase,
) : SongQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun id(id: Long): MediastoreSongEntity? {
    val _sql: String = "SELECT * from mediastore where _id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MediastoreSongEntity?
        if (_stmt.step()) {
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _result = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun ids(ids: Collection<Long>): List<MediastoreSongEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * from mediastore where _id in (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MutableList<MediastoreSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: MediastoreSongEntity
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _item_1 = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun allIds(): List<Long> {
    val _sql: String = "SELECT _id from mediastore"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: MutableList<Long> = mutableListOf()
        while (_stmt.step()) {
          val _item: Long
          _item = _stmt.getLong(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun title(title: String): MediastoreSongEntity? {
    val _sql: String = "SELECT * from mediastore where title = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, title)
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MediastoreSongEntity?
        if (_stmt.step()) {
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _result = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun path(path: String): MediastoreSongEntity? {
    val _sql: String = "SELECT * from mediastore where _data like ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, path)
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MediastoreSongEntity?
        if (_stmt.step()) {
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _result = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchByTitle(title: String): List<MediastoreSongEntity> {
    val _sql: String = "SELECT * from mediastore where title like ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, title)
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MutableList<MediastoreSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MediastoreSongEntity
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _item = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchByPath(path: String): List<MediastoreSongEntity> {
    val _sql: String = "SELECT * from mediastore where _data like ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, path)
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MutableList<MediastoreSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MediastoreSongEntity
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _item = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun total(): Int {
    val _sql: String = "SELECT COUNT(*) from mediastore"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun latest(): MediastoreSongEntity? {
    val _sql: String = "SELECT * from mediastore order by date_modified DESC limit 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfMediastorId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "_data")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbum: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtist: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTrack: Int = getColumnIndexOrThrow(_stmt, "track")
        val _columnIndexOfAlbumArtist: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfComposer: Int = getColumnIndexOrThrow(_stmt, "composer")
        val _result: MediastoreSongEntity?
        if (_stmt.step()) {
          val _tmpMediastorId: Long
          _tmpMediastorId = _stmt.getLong(_columnIndexOfMediastorId)
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          val _tmpDuration: Long
          _tmpDuration = _stmt.getLong(_columnIndexOfDuration)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbum: String
          _tmpAlbum = _stmt.getText(_columnIndexOfAlbum)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtist: String
          _tmpArtist = _stmt.getText(_columnIndexOfArtist)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpTrack: Int
          _tmpTrack = _stmt.getLong(_columnIndexOfTrack).toInt()
          val _tmpAlbumArtist: String
          _tmpAlbumArtist = _stmt.getText(_columnIndexOfAlbumArtist)
          val _tmpComposer: String
          _tmpComposer = _stmt.getText(_columnIndexOfComposer)
          _result = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  protected override suspend fun query(query: SupportSQLiteQuery): List<MediastoreSongEntity> {
    val _rawQuery: RoomRawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery()
    val _sql: String = _rawQuery.sql
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _rawQuery.getBindingFunction().invoke(_stmt)
        val _result: MutableList<MediastoreSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MediastoreSongEntity
          _item = __entityStatementConverter_playerPhonographRepoRoomEntityMediastoreSongEntity(_stmt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __entityStatementConverter_playerPhonographRepoRoomEntityMediastoreSongEntity(statement: SQLiteStatement): MediastoreSongEntity {
    val _entity: MediastoreSongEntity
    val _columnIndexOfMediastorId: Int = getColumnIndex(statement, "_id")
    val _columnIndexOfPath: Int = getColumnIndex(statement, "_data")
    val _columnIndexOfDuration: Int = getColumnIndex(statement, "duration")
    val _columnIndexOfDateAdded: Int = getColumnIndex(statement, "date_added")
    val _columnIndexOfDateModified: Int = getColumnIndex(statement, "date_modified")
    val _columnIndexOfTitle: Int = getColumnIndex(statement, "title")
    val _columnIndexOfAlbumId: Int = getColumnIndex(statement, "album_id")
    val _columnIndexOfAlbum: Int = getColumnIndex(statement, "album")
    val _columnIndexOfArtistId: Int = getColumnIndex(statement, "artist_id")
    val _columnIndexOfArtist: Int = getColumnIndex(statement, "artist")
    val _columnIndexOfYear: Int = getColumnIndex(statement, "year")
    val _columnIndexOfTrack: Int = getColumnIndex(statement, "track")
    val _columnIndexOfAlbumArtist: Int = getColumnIndex(statement, "album_artist")
    val _columnIndexOfComposer: Int = getColumnIndex(statement, "composer")
    val _tmpMediastorId: Long
    if (_columnIndexOfMediastorId == -1) {
      _tmpMediastorId = 0
    } else {
      _tmpMediastorId = statement.getLong(_columnIndexOfMediastorId)
    }
    val _tmpPath: String
    if (_columnIndexOfPath == -1) {
      error("Missing column '_data' for a NON-NULL value, column not found in result.")
    } else {
      _tmpPath = statement.getText(_columnIndexOfPath)
    }
    val _tmpDuration: Long
    if (_columnIndexOfDuration == -1) {
      _tmpDuration = 0
    } else {
      _tmpDuration = statement.getLong(_columnIndexOfDuration)
    }
    val _tmpDateAdded: Long
    if (_columnIndexOfDateAdded == -1) {
      _tmpDateAdded = 0
    } else {
      _tmpDateAdded = statement.getLong(_columnIndexOfDateAdded)
    }
    val _tmpDateModified: Long
    if (_columnIndexOfDateModified == -1) {
      _tmpDateModified = 0
    } else {
      _tmpDateModified = statement.getLong(_columnIndexOfDateModified)
    }
    val _tmpTitle: String
    if (_columnIndexOfTitle == -1) {
      error("Missing column 'title' for a NON-NULL value, column not found in result.")
    } else {
      _tmpTitle = statement.getText(_columnIndexOfTitle)
    }
    val _tmpAlbumId: Long
    if (_columnIndexOfAlbumId == -1) {
      _tmpAlbumId = 0
    } else {
      _tmpAlbumId = statement.getLong(_columnIndexOfAlbumId)
    }
    val _tmpAlbum: String
    if (_columnIndexOfAlbum == -1) {
      error("Missing column 'album' for a NON-NULL value, column not found in result.")
    } else {
      _tmpAlbum = statement.getText(_columnIndexOfAlbum)
    }
    val _tmpArtistId: Long
    if (_columnIndexOfArtistId == -1) {
      _tmpArtistId = 0
    } else {
      _tmpArtistId = statement.getLong(_columnIndexOfArtistId)
    }
    val _tmpArtist: String
    if (_columnIndexOfArtist == -1) {
      error("Missing column 'artist' for a NON-NULL value, column not found in result.")
    } else {
      _tmpArtist = statement.getText(_columnIndexOfArtist)
    }
    val _tmpYear: Int
    if (_columnIndexOfYear == -1) {
      _tmpYear = 0
    } else {
      _tmpYear = statement.getLong(_columnIndexOfYear).toInt()
    }
    val _tmpTrack: Int
    if (_columnIndexOfTrack == -1) {
      _tmpTrack = 0
    } else {
      _tmpTrack = statement.getLong(_columnIndexOfTrack).toInt()
    }
    val _tmpAlbumArtist: String
    if (_columnIndexOfAlbumArtist == -1) {
      error("Missing column 'album_artist' for a NON-NULL value, column not found in result.")
    } else {
      _tmpAlbumArtist = statement.getText(_columnIndexOfAlbumArtist)
    }
    val _tmpComposer: String
    if (_columnIndexOfComposer == -1) {
      error("Missing column 'composer' for a NON-NULL value, column not found in result.")
    } else {
      _tmpComposer = statement.getText(_columnIndexOfComposer)
    }
    _entity = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
    return _entity
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
