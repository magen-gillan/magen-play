package player.phonograph.repo.room.dao

import androidx.room.RoomDatabase
import androidx.room.RoomRawQuery
import androidx.room.RoomSQLiteQuery
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
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import player.phonograph.repo.room.entity.GenreEntity
import player.phonograph.repo.room.entity.MediastoreSongEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class GenreQueryDao_Impl(
  __db: RoomDatabase,
) : GenreQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun id(id: Long): GenreEntity? {
    val _sql: String = "SELECT * from genres where genre_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "genre_id_mediastore")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: GenreEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _result = GenreEntity(_tmpId,_tmpName,_tmpCategory,_tmpMediastoreId,_tmpSongCount)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun named(name: String): GenreEntity? {
    val _sql: String = "SELECT * from genres where genre = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "genre_id_mediastore")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: GenreEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _result = GenreEntity(_tmpId,_tmpName,_tmpCategory,_tmpMediastoreId,_tmpSongCount)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchByName(name: String): List<GenreEntity> {
    val _sql: String = "SELECT * from genres where genre like ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "genre_id_mediastore")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: MutableList<GenreEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GenreEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = GenreEntity(_tmpId,_tmpName,_tmpCategory,_tmpMediastoreId,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun count(): Int {
    val _sql: String = "SELECT COUNT(*) from genres"
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

  public override suspend fun mediaStoreId(mediastoreId: Long): GenreEntity? {
    val _sql: String = "SELECT * from genres where genre_id_mediastore = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, mediastoreId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "genre_id_mediastore")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: GenreEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _result = GenreEntity(_tmpId,_tmpName,_tmpCategory,_tmpMediastoreId,_tmpSongCount)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun allMediaStoreIds(): List<Long> {
    val _sql: String = "SELECT genre_id_mediastore from genres"
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

  public override suspend fun genreSongs(genreId: Long): List<MediastoreSongEntity> {
    val _sql: String = "SELECT mediastore.* from linkage_genre_song inner join mediastore on linkage_genre_song._id = mediastore._id where linkage_genre_song.genre_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, genreId)
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

  public override suspend fun of(songId: Long): List<GenreEntity> {
    val _sql: String = "SELECT genres.* from linkage_genre_song inner join genres on linkage_genre_song.genre_id = genres.genre_id where linkage_genre_song._id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, songId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "genre_id_mediastore")
        val _columnIndexOfSongCount: Int = getColumnIndexOrThrow(_stmt, "song_count")
        val _result: MutableList<GenreEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GenreEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpSongCount: Int
          _tmpSongCount = _stmt.getLong(_columnIndexOfSongCount).toInt()
          _item = GenreEntity(_tmpId,_tmpName,_tmpCategory,_tmpMediastoreId,_tmpSongCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun query(query: SupportSQLiteQuery): List<GenreEntity> {
    val _rawQuery: RoomRawQuery = RoomSQLiteQuery.copyFrom(query).toRoomRawQuery()
    val _sql: String = _rawQuery.sql
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _rawQuery.getBindingFunction().invoke(_stmt)
        val _result: MutableList<GenreEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: GenreEntity
          _item = __entityStatementConverter_playerPhonographRepoRoomEntityGenreEntity(_stmt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __entityStatementConverter_playerPhonographRepoRoomEntityGenreEntity(statement: SQLiteStatement): GenreEntity {
    val _entity: GenreEntity
    val _columnIndexOfId: Int = getColumnIndex(statement, "genre_id")
    val _columnIndexOfName: Int = getColumnIndex(statement, "genre")
    val _columnIndexOfCategory: Int = getColumnIndex(statement, "category")
    val _columnIndexOfMediastoreId: Int = getColumnIndex(statement, "genre_id_mediastore")
    val _columnIndexOfSongCount: Int = getColumnIndex(statement, "song_count")
    val _tmpId: Long
    if (_columnIndexOfId == -1) {
      _tmpId = 0
    } else {
      _tmpId = statement.getLong(_columnIndexOfId)
    }
    val _tmpName: String
    if (_columnIndexOfName == -1) {
      error("Missing column 'genre' for a NON-NULL value, column not found in result.")
    } else {
      _tmpName = statement.getText(_columnIndexOfName)
    }
    val _tmpCategory: String?
    if (_columnIndexOfCategory == -1) {
      _tmpCategory = null
    } else {
      if (statement.isNull(_columnIndexOfCategory)) {
        _tmpCategory = null
      } else {
        _tmpCategory = statement.getText(_columnIndexOfCategory)
      }
    }
    val _tmpMediastoreId: Long
    if (_columnIndexOfMediastoreId == -1) {
      _tmpMediastoreId = 0
    } else {
      _tmpMediastoreId = statement.getLong(_columnIndexOfMediastoreId)
    }
    val _tmpSongCount: Int
    if (_columnIndexOfSongCount == -1) {
      _tmpSongCount = 0
    } else {
      _tmpSongCount = statement.getLong(_columnIndexOfSongCount).toInt()
    }
    _entity = GenreEntity(_tmpId,_tmpName,_tmpCategory,_tmpMediastoreId,_tmpSongCount)
    return _entity
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
