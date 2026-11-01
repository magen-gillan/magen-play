package player.phonograph.repo.room.dao

import androidx.collection.LongSparseArray
import androidx.room.RoomDatabase
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.room.util.recursiveFetchLongSparseArray
import androidx.sqlite.SQLiteConnection
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
import kotlin.text.StringBuilder
import player.phonograph.repo.room.entity.MediastoreSongEntity
import player.phonograph.repo.room.entity.PlaylistEntity
import player.phonograph.repo.room.entity.PlaylistSongEntity
import player.phonograph.repo.room.entity.derived.PlaylistMediastoreSongEntity
import player.phonograph.repo.room.entity.derived.PlaylistWithSongsEntity

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaylistSongQueryDao_Impl(
  __db: RoomDatabase,
) : PlaylistSongQueryDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override suspend fun playlist(id: Long): PlaylistWithSongsEntity? {
    val _sql: String = "SELECT * FROM playlists WHERE playlist_id =?"
    return performSuspending(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "playlist_name")
        val _columnIndexOfDateAdded: Int = getColumnIndexOrThrow(_stmt, "date_added")
        val _columnIndexOfDateModified: Int = getColumnIndexOrThrow(_stmt, "date_modified")
        val _collectionSongs: LongSparseArray<MutableList<MediastoreSongEntity?>> = LongSparseArray<MutableList<MediastoreSongEntity?>>()
        while (_stmt.step()) {
          val _tmpKey: Long
          _tmpKey = _stmt.getLong(_columnIndexOfId)
          if (!_collectionSongs.containsKey(_tmpKey)) {
            _collectionSongs.put(_tmpKey, mutableListOf())
          }
        }
        _stmt.reset()
        __fetchRelationshipmediastoreAsplayerPhonographRepoRoomEntityMediastoreSongEntity(_connection, _collectionSongs)
        val _result: PlaylistWithSongsEntity?
        if (_stmt.step()) {
          val _tmpPlaylist: PlaylistEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpDateAdded: Long
          _tmpDateAdded = _stmt.getLong(_columnIndexOfDateAdded)
          val _tmpDateModified: Long
          _tmpDateModified = _stmt.getLong(_columnIndexOfDateModified)
          _tmpPlaylist = PlaylistEntity(_tmpId,_tmpName,_tmpDateAdded,_tmpDateModified)
          val _tmpSongsCollection: MutableList<MediastoreSongEntity?>
          val _tmpKey_1: Long
          _tmpKey_1 = _stmt.getLong(_columnIndexOfId)
          _tmpSongsCollection = checkNotNull(_collectionSongs.get(_tmpKey_1))
          _result = PlaylistWithSongsEntity(_tmpPlaylist,_tmpSongsCollection)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun songs(playlistId: Long): List<PlaylistMediastoreSongEntity> {
    val _sql: String = "SELECT * FROM mediastore INNER JOIN playlist_songs ON mediastore._id = playlist_songs._id WHERE playlist_id =? ORDER BY position ASC"
    return performSuspending(__db, true, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
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
        val _columnIndexOfPlaylistId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _result: MutableList<PlaylistMediastoreSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistMediastoreSongEntity
          val _tmpPlaylistId: Long
          _tmpPlaylistId = _stmt.getLong(_columnIndexOfPlaylistId)
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpSongEntity: MediastoreSongEntity
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
          _tmpSongEntity = MediastoreSongEntity(_tmpMediastorId,_tmpPath,_tmpDuration,_tmpDateAdded,_tmpDateModified,_tmpTitle,_tmpAlbumId,_tmpAlbum,_tmpArtistId,_tmpArtist,_tmpYear,_tmpTrack,_tmpAlbumArtist,_tmpComposer)
          _item = PlaylistMediastoreSongEntity(_tmpSongEntity,_tmpPosition,_tmpPlaylistId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun rawQuery(id: Long): List<PlaylistSongEntity> {
    val _sql: String = "SELECT * FROM playlist_songs WHERE playlist_id =?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "playlist_song_id")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPlaylistId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "path")
        val _result: MutableList<PlaylistSongEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaylistSongEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpPlaylistId: Long
          _tmpPlaylistId = _stmt.getLong(_columnIndexOfPlaylistId)
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          _item = PlaylistSongEntity(_tmpId,_tmpMediastoreId,_tmpPlaylistId,_tmpPosition,_tmpPath)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun size(playlistId: Long): Int {
    val _sql: String = "SELECT COALESCE(COUNT(playlist_song_id), 0) FROM playlist_songs WHERE playlist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
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

  public override suspend fun maximumIndexOf(playlistId: Long): Int {
    val _sql: String = "SELECT COALESCE(MAX(position), -1) FROM playlist_songs WHERE playlist_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
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

  public override suspend fun count(playlistId: Long, songId: Long): Int {
    val _sql: String = "SELECT COALESCE(COUNT(playlist_song_id), 0) FROM playlist_songs WHERE playlist_id = ? AND _id = ? "
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, songId)
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

  public override suspend fun at(playlistId: Long, position: Int): PlaylistSongEntity? {
    val _sql: String = "SELECT * FROM playlist_songs WHERE playlist_id = ? AND position = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, playlistId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, position.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "playlist_song_id")
        val _columnIndexOfMediastoreId: Int = getColumnIndexOrThrow(_stmt, "_id")
        val _columnIndexOfPlaylistId: Int = getColumnIndexOrThrow(_stmt, "playlist_id")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfPath: Int = getColumnIndexOrThrow(_stmt, "path")
        val _result: PlaylistSongEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpMediastoreId: Long
          _tmpMediastoreId = _stmt.getLong(_columnIndexOfMediastoreId)
          val _tmpPlaylistId: Long
          _tmpPlaylistId = _stmt.getLong(_columnIndexOfPlaylistId)
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpPath: String
          _tmpPath = _stmt.getText(_columnIndexOfPath)
          _result = PlaylistSongEntity(_tmpId,_tmpMediastoreId,_tmpPlaylistId,_tmpPosition,_tmpPath)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __fetchRelationshipmediastoreAsplayerPhonographRepoRoomEntityMediastoreSongEntity(_connection: SQLiteConnection, _map: LongSparseArray<MutableList<MediastoreSongEntity?>>) {
    if (_map.isEmpty()) {
      return
    }
    if (_map.size() > 999) {
      recursiveFetchLongSparseArray(_map, true) { _tmpMap ->
        __fetchRelationshipmediastoreAsplayerPhonographRepoRoomEntityMediastoreSongEntity(_connection, _tmpMap)
      }
      return
    }
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `mediastore`.`_id` AS `_id`,`mediastore`.`_data` AS `_data`,`mediastore`.`duration` AS `duration`,`mediastore`.`date_added` AS `date_added`,`mediastore`.`date_modified` AS `date_modified`,`mediastore`.`title` AS `title`,`mediastore`.`album_id` AS `album_id`,`mediastore`.`album` AS `album`,`mediastore`.`artist_id` AS `artist_id`,`mediastore`.`artist` AS `artist`,`mediastore`.`year` AS `year`,`mediastore`.`track` AS `track`,`mediastore`.`album_artist` AS `album_artist`,`mediastore`.`composer` AS `composer`,_junction.`playlist_id` FROM `playlist_songs` AS _junction INNER JOIN `mediastore` ON (_junction.`_id` = `mediastore`.`_id`) WHERE _junction.`playlist_id` IN (")
    val _inputSize: Int = _map.size()
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    val _stmt: SQLiteStatement = _connection.prepare(_sql)
    var _argIndex: Int = 1
    for (i in 0 until _map.size()) {
      val _item: Long = _map.keyAt(i)
      _stmt.bindLong(_argIndex, _item)
      _argIndex++
    }
    try {
      // _junction.playlist_id
      val _itemKeyIndex: Int = 14
      if (_itemKeyIndex == -1) {
        return
      }
      val _columnIndexOfMediastorId: Int = 0
      val _columnIndexOfPath: Int = 1
      val _columnIndexOfDuration: Int = 2
      val _columnIndexOfDateAdded: Int = 3
      val _columnIndexOfDateModified: Int = 4
      val _columnIndexOfTitle: Int = 5
      val _columnIndexOfAlbumId: Int = 6
      val _columnIndexOfAlbum: Int = 7
      val _columnIndexOfArtistId: Int = 8
      val _columnIndexOfArtist: Int = 9
      val _columnIndexOfYear: Int = 10
      val _columnIndexOfTrack: Int = 11
      val _columnIndexOfAlbumArtist: Int = 12
      val _columnIndexOfComposer: Int = 13
      while (_stmt.step()) {
        val _tmpKey: Long
        _tmpKey = _stmt.getLong(_itemKeyIndex)
        val _tmpRelation: MutableList<MediastoreSongEntity?>? = _map.get(_tmpKey)
        if (_tmpRelation != null) {
          val _item_1: MediastoreSongEntity?
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
          _tmpRelation.add(_item_1)
        }
      }
    } finally {
      _stmt.close()
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
