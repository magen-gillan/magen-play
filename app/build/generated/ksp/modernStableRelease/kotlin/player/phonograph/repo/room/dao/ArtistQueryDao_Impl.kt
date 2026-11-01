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
import player.phonograph.repo.room.entity.AlbumEntity
import player.phonograph.repo.room.entity.ArtistEntity
import player.phonograph.repo.room.entity.MediastoreSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ArtistQueryDao_Impl(
  __db: RoomDatabase,
) : ArtistQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun id(id: Long): ArtistEntity? {
    val _sql: String = "SELECT * from artists where artist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtistName: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfAlbumCount: Int = getColumnIndexOrThrow(_stmt, "album_count")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: ArtistEntity?
        if (_stmt.step()) {
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtistName: String
          _tmpArtistName = _stmt.getText(_columnIndexOfArtistName)
          val _tmpAlbumCount: Int
          _tmpAlbumCount = _stmt.getLong(_columnIndexOfAlbumCount).toInt()
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _result = ArtistEntity(_tmpArtistId,_tmpArtistName,_tmpAlbumCount,_tmpSongCount)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun named(name: String): ArtistEntity? {
    val _sql: String = "SELECT * from artists where artist = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtistName: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfAlbumCount: Int = getColumnIndexOrThrow(_stmt, "album_count")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: ArtistEntity?
        if (_stmt.step()) {
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtistName: String
          _tmpArtistName = _stmt.getText(_columnIndexOfArtistName)
          val _tmpAlbumCount: Int
          _tmpAlbumCount = _stmt.getLong(_columnIndexOfAlbumCount).toInt()
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _result = ArtistEntity(_tmpArtistId,_tmpArtistName,_tmpAlbumCount,_tmpSongCount)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun named(names: Collection<String>): List<ArtistEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * from artists where artist in (")
    val _inputSize: Int = names.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in names) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtistName: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfAlbumCount: Int = getColumnIndexOrThrow(_stmt, "album_count")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: MutableList<ArtistEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ArtistEntity
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtistName: String
          _tmpArtistName = _stmt.getText(_columnIndexOfArtistName)
          val _tmpAlbumCount: Int
          _tmpAlbumCount = _stmt.getLong(_columnIndexOfAlbumCount).toInt()
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item_1 = ArtistEntity(_tmpArtistId,_tmpArtistName,_tmpAlbumCount,_tmpSongCount)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchByName(name: String): List<ArtistEntity> {
    val _sql: String = "SELECT * from artists where artist like ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfArtistName: Int = getColumnIndexOrThrow(_stmt, "artist")
        val _columnIndexOfAlbumCount: Int = getColumnIndexOrThrow(_stmt, "album_count")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: MutableList<ArtistEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ArtistEntity
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpArtistName: String
          _tmpArtistName = _stmt.getText(_columnIndexOfArtistName)
          val _tmpAlbumCount: Int
          _tmpAlbumCount = _stmt.getLong(_columnIndexOfAlbumCount).toInt()
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = ArtistEntity(_tmpArtistId,_tmpArtistName,_tmpAlbumCount,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun count(): Int {
    val _sql: String = "SELECT COUNT(*) from artists"
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

  public override suspend fun artistSongs(artistId: Long): List<MediastoreSongEntity> {
    val _sql: String = "SELECT mediastore.* from linkage_artist_song inner join mediastore on linkage_artist_song._id = mediastore._id where linkage_artist_song.artist_id = ? order by album, track"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
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

  public override suspend fun artistSongCount(artistId: Long): Int {
    val _sql: String = "SELECT COUNT(_id) from linkage_artist_song where artist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
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

  public override suspend fun artistAlbums(artistId: Long): List<AlbumEntity> {
    val _sql: String = "SELECT albums.* from linkage_artist_album inner join albums on linkage_artist_album.album_id = albums.album_id where linkage_artist_album.artist_id = ? order by year desc, album"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfAlbumName: Int = getColumnIndexOrThrow(_stmt, "album")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfAlbumArtistName: Int = getColumnIndexOrThrow(_stmt, "album_artist")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: MutableList<AlbumEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: AlbumEntity
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpAlbumName: String
          _tmpAlbumName = _stmt.getText(_columnIndexOfAlbumName)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpAlbumArtistName: String
          _tmpAlbumArtistName = _stmt.getText(_columnIndexOfAlbumArtistName)
          val _tmpYear: Int
          _tmpYear = _stmt.getLong(_columnIndexOfYear).toInt()
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = AlbumEntity(_tmpAlbumId,_tmpAlbumName,_tmpArtistId,_tmpAlbumArtistName,_tmpYear,_tmpDateModified,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun artistAlbumCount(artistId: Long): Int {
    val _sql: String = "SELECT COUNT(album_id) from linkage_artist_album where artist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
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

  protected override suspend fun query(query: SupportSQLiteQuery): List<ArtistEntity> {
    val _rawQuery: RoomRawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery()
    val _sql: String = _rawQuery.sql
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _rawQuery.getBindingFunction().invoke(_stmt)
        val _result: MutableList<ArtistEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ArtistEntity
          _item = __entityStatementConverter_playerPhonographRepoRoomEntityArtistEntity(_stmt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __entityStatementConverter_playerPhonographRepoRoomEntityArtistEntity(statement: SQLiteStatement): ArtistEntity {
    val _entity: ArtistEntity
    val _columnIndexOfArtistId: Int = getColumnIndex(statement, "artist_id")
    val _columnIndexOfArtistName: Int = getColumnIndex(statement, "artist")
    val _columnIndexOfAlbumCount: Int = getColumnIndex(statement, "album_count")
    val _columnIndexOfSongCount: Int = getColumnIndex(statement, "song_count")
    val _tmpArtistId: Long
    if (_columnIndexOfArtistId == -1) {
      _tmpArtistId = 0
    } else {
      _tmpArtistId = statement.getLong(_columnIndexOfArtistId)
    }
    val _tmpArtistName: String
    if (_columnIndexOfArtistName == -1) {
      error("Missing column 'artist' for a NON-NULL value, column not found in result.")
    } else {
      _tmpArtistName = statement.getText(_columnIndexOfArtistName)
    }
    val _tmpAlbumCount: Int
    if (_columnIndexOfAlbumCount == -1) {
      _tmpAlbumCount = 0
    } else {
      _tmpAlbumCount = statement.getLong(_columnIndexOfAlbumCount).toInt()
    }
    val _tmpSongCount: Int
    if (_columnIndexOfSongCount == -1) {
      _tmpSongCount = 0
    } else {
      _tmpSongCount = statement.getLong(_columnIndexOfSongCount).toInt()
    }
    _entity = ArtistEntity(_tmpArtistId,_tmpArtistName,_tmpAlbumCount,_tmpSongCount)
    return _entity
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
