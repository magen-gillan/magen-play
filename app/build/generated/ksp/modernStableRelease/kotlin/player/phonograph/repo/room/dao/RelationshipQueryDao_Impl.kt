package player.phonograph.repo.room.dao

import androidx.room.RoomDatabase
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
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
import player.phonograph.repo.room.entity.LinkageAlbumAndArtist
import player.phonograph.repo.room.entity.LinkageGenreAndSong
import player.phonograph.repo.room.entity.LinkageSongAndArtist

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RelationshipQueryDao_Impl(
  __db: RoomDatabase,
) : RelationshipQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun albumsOfArtist(artistId: Long): List<LinkageAlbumAndArtist> {
    val _sql: String = "SELECT * from linkage_artist_album where artist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _result: MutableList<LinkageAlbumAndArtist> = mutableListOf()
        while (_stmt.step()) {
          val _item: LinkageAlbumAndArtist
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          _item = LinkageAlbumAndArtist(_tmpAlbumId,_tmpArtistId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun artistsOfAlbum(albumId: Long): List<LinkageAlbumAndArtist> {
    val _sql: String = "SELECT * from linkage_artist_album where album_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, albumId)
        val _columnIndexOfAlbumId: Int = getColumnIndexOrThrow(_stmt, "album_id")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _result: MutableList<LinkageAlbumAndArtist> = mutableListOf()
        while (_stmt.step()) {
          val _item: LinkageAlbumAndArtist
          val _tmpAlbumId: Long
          _tmpAlbumId = _stmt.getLong(_columnIndexOfAlbumId)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          _item = LinkageAlbumAndArtist(_tmpAlbumId,_tmpArtistId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun songsOfArtist(artistId: Long): List<LinkageSongAndArtist> {
    val _sql: String = "SELECT * from linkage_artist_song where artist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _result: MutableList<LinkageSongAndArtist> = mutableListOf()
        while (_stmt.step()) {
          val _item: LinkageSongAndArtist
          val _tmpSongId: Long
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpRole: Int
          _tmpRole = _stmt.getLong(_columnIndexOfRole).toInt()
          _item = LinkageSongAndArtist(_tmpSongId,_tmpArtistId,_tmpRole)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun artistsOfSong(songId: Long): List<LinkageSongAndArtist> {
    val _sql: String = "SELECT * from linkage_artist_song where _id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, songId)
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _result: MutableList<LinkageSongAndArtist> = mutableListOf()
        while (_stmt.step()) {
          val _item: LinkageSongAndArtist
          val _tmpSongId: Long
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpRole: Int
          _tmpRole = _stmt.getLong(_columnIndexOfRole).toInt()
          _item = LinkageSongAndArtist(_tmpSongId,_tmpArtistId,_tmpRole)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun artistsOfSongs(songIds: Collection<Long>): List<LinkageSongAndArtist> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * from linkage_artist_song where _id in (")
    val _inputSize: Int = songIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in songIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfArtistId: Int = getColumnIndexOrThrow(_stmt, "artist_id")
        val _columnIndexOfRole: Int = getColumnIndexOrThrow(_stmt, "role")
        val _result: MutableList<LinkageSongAndArtist> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: LinkageSongAndArtist
          val _tmpSongId: Long
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId)
          val _tmpArtistId: Long
          _tmpArtistId = _stmt.getLong(_columnIndexOfArtistId)
          val _tmpRole: Int
          _tmpRole = _stmt.getLong(_columnIndexOfRole).toInt()
          _item_1 = LinkageSongAndArtist(_tmpSongId,_tmpArtistId,_tmpRole)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun songsOfGenre(genreId: Long): List<LinkageGenreAndSong> {
    val _sql: String = "SELECT * from linkage_genre_song where genre_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, genreId)
        val _columnIndexOfGenreId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: MutableList<LinkageGenreAndSong> = mutableListOf()
        while (_stmt.step()) {
          val _item: LinkageGenreAndSong
          val _tmpGenreId: Long
          _tmpGenreId = _stmt.getLong(_columnIndexOfGenreId)
          val _tmpSongId: Long
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId)
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          _item = LinkageGenreAndSong(_tmpGenreId,_tmpSongId,_tmpSource)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun genresOfSong(songId: Long): List<LinkageGenreAndSong> {
    val _sql: String = "SELECT * from linkage_genre_song where _id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, songId)
        val _columnIndexOfGenreId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: MutableList<LinkageGenreAndSong> = mutableListOf()
        while (_stmt.step()) {
          val _item: LinkageGenreAndSong
          val _tmpGenreId: Long
          _tmpGenreId = _stmt.getLong(_columnIndexOfGenreId)
          val _tmpSongId: Long
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId)
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          _item = LinkageGenreAndSong(_tmpGenreId,_tmpSongId,_tmpSource)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun genresOfSongs(songIds: Collection<Long>): List<LinkageGenreAndSong> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * from linkage_genre_song where _id in (")
    val _inputSize: Int = songIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in songIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfGenreId: Int = getColumnIndexOrThrow(_stmt, "genre_id")
        val _columnIndexOfSongId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _result: MutableList<LinkageGenreAndSong> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: LinkageGenreAndSong
          val _tmpGenreId: Long
          _tmpGenreId = _stmt.getLong(_columnIndexOfGenreId)
          val _tmpSongId: Long
          _tmpSongId = _stmt.getLong(_columnIndexOfSongId)
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          _item_1 = LinkageGenreAndSong(_tmpGenreId,_tmpSongId,_tmpSource)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun songIdsOfGenre(genreId: Long): List<Long> {
    val _sql: String = "SELECT _id from linkage_genre_song where genre_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, genreId)
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
