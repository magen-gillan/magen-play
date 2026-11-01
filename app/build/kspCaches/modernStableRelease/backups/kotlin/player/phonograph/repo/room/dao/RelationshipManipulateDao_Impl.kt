package player.phonograph.repo.room.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.appendPlaceholders
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.Collection
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import player.phonograph.repo.room.entity.LinkageAlbumAndArtist
import player.phonograph.repo.room.entity.LinkageGenreAndSong
import player.phonograph.repo.room.entity.LinkageSongAndArtist

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RelationshipManipulateDao_Impl(
  __db: RoomDatabase,
) : RelationshipManipulateDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfLinkageAlbumAndArtist: EntityInsertAdapter<LinkageAlbumAndArtist>

  private val __insertAdapterOfLinkageSongAndArtist: EntityInsertAdapter<LinkageSongAndArtist>

  private val __insertAdapterOfLinkageGenreAndSong: EntityInsertAdapter<LinkageGenreAndSong>

  private val __deleteAdapterOfLinkageAlbumAndArtist:
      EntityDeleteOrUpdateAdapter<LinkageAlbumAndArtist>

  private val __deleteAdapterOfLinkageSongAndArtist:
      EntityDeleteOrUpdateAdapter<LinkageSongAndArtist>

  private val __deleteAdapterOfLinkageGenreAndSong: EntityDeleteOrUpdateAdapter<LinkageGenreAndSong>
  init {
    this.__db = __db
    this.__insertAdapterOfLinkageAlbumAndArtist = object : EntityInsertAdapter<LinkageAlbumAndArtist>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `linkage_artist_album` (`album_id`,`artist_id`) VALUES (?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LinkageAlbumAndArtist) {
        statement.bindLong(1, entity.albumId)
        statement.bindLong(2, entity.artistId)
      }
    }
    this.__insertAdapterOfLinkageSongAndArtist = object : EntityInsertAdapter<LinkageSongAndArtist>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `linkage_artist_song` (`_id`,`artist_id`,`role`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LinkageSongAndArtist) {
        statement.bindLong(1, entity.songId)
        statement.bindLong(2, entity.artistId)
        statement.bindLong(3, entity.role.toLong())
      }
    }
    this.__insertAdapterOfLinkageGenreAndSong = object : EntityInsertAdapter<LinkageGenreAndSong>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `linkage_genre_song` (`genre_id`,`_id`,`source`) VALUES (?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: LinkageGenreAndSong) {
        statement.bindLong(1, entity.genreId)
        statement.bindLong(2, entity.songId)
        val _tmpSource: String? = entity.source
        if (_tmpSource == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpSource)
        }
      }
    }
    this.__deleteAdapterOfLinkageAlbumAndArtist = object : EntityDeleteOrUpdateAdapter<LinkageAlbumAndArtist>() {
      protected override fun createQuery(): String = "DELETE FROM `linkage_artist_album` WHERE `artist_id` = ? AND `album_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: LinkageAlbumAndArtist) {
        statement.bindLong(1, entity.artistId)
        statement.bindLong(2, entity.albumId)
      }
    }
    this.__deleteAdapterOfLinkageSongAndArtist = object : EntityDeleteOrUpdateAdapter<LinkageSongAndArtist>() {
      protected override fun createQuery(): String = "DELETE FROM `linkage_artist_song` WHERE `artist_id` = ? AND `_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: LinkageSongAndArtist) {
        statement.bindLong(1, entity.artistId)
        statement.bindLong(2, entity.songId)
      }
    }
    this.__deleteAdapterOfLinkageGenreAndSong = object : EntityDeleteOrUpdateAdapter<LinkageGenreAndSong>() {
      protected override fun createQuery(): String = "DELETE FROM `linkage_genre_song` WHERE `genre_id` = ? AND `_id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: LinkageGenreAndSong) {
        statement.bindLong(1, entity.genreId)
        statement.bindLong(2, entity.songId)
      }
    }
  }

  public override suspend fun overrideAlbumArtist(linkage: LinkageAlbumAndArtist): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfLinkageAlbumAndArtist.insert(_connection, linkage)
  }

  public override suspend fun overrideAlbumArtists(linkages: List<LinkageAlbumAndArtist>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfLinkageAlbumAndArtist.insert(_connection, linkages)
  }

  public override suspend fun overrideArtistSong(linkage: LinkageSongAndArtist): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfLinkageSongAndArtist.insert(_connection, linkage)
  }

  public override suspend fun overrideArtistSongs(linkages: List<LinkageSongAndArtist>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfLinkageSongAndArtist.insert(_connection, linkages)
  }

  public override suspend fun overrideGenreSong(linkage: LinkageGenreAndSong): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfLinkageGenreAndSong.insert(_connection, linkage)
  }

  public override suspend fun overrideGenreSongs(linkages: List<LinkageGenreAndSong>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfLinkageGenreAndSong.insert(_connection, linkages)
  }

  public override suspend fun removeAlbumArtist(linkage: LinkageAlbumAndArtist): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfLinkageAlbumAndArtist.handle(_connection, linkage)
    _result
  }

  public override suspend fun removeArtistSong(linkage: LinkageSongAndArtist): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfLinkageSongAndArtist.handle(_connection, linkage)
    _result
  }

  public override suspend fun removeArtistSongs(linkages: List<LinkageSongAndArtist>): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfLinkageSongAndArtist.handleMultiple(_connection, linkages)
    _result
  }

  public override suspend fun removeGenreSong(linkage: LinkageGenreAndSong): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfLinkageGenreAndSong.handle(_connection, linkage)
    _result
  }

  public override suspend fun removeGenreSongs(linkages: List<LinkageGenreAndSong>): Int = performSuspending(__db, false, true) { _connection ->
    var _result: Int = 0
    _result += __deleteAdapterOfLinkageGenreAndSong.handleMultiple(_connection, linkages)
    _result
  }

  public override suspend fun deleteAllAlbumArtists(): Int {
    val _sql: String = "DELETE FROM linkage_artist_album"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeAlbum(albumId: Long): Int {
    val _sql: String = "DELETE FROM linkage_artist_album where album_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, albumId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeArtist(artistId: Long): Int {
    val _sql: String = "DELETE FROM linkage_artist_album where artist_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, artistId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeAlbums(albumIds: Collection<Long>): Int {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM linkage_artist_album where album_id in (")
    val _inputSize: Int = albumIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in albumIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeArtists(artistIds: Collection<Long>): Int {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM linkage_artist_album where artist_id in (")
    val _inputSize: Int = artistIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in artistIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllArtistSongs(): Int {
    val _sql: String = "DELETE FROM linkage_artist_song"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeGenre(genreId: Long): Int {
    val _sql: String = "DELETE FROM linkage_genre_song where genre_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, genreId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeSong(songId: Long): Int {
    val _sql: String = "DELETE FROM linkage_genre_song where _id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, songId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeGenres(genreIds: Collection<Long>): Int {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM linkage_genre_song where genre_id in (")
    val _inputSize: Int = genreIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in genreIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun removeSongs(songIds: Collection<Long>): Int {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM linkage_genre_song where _id in (")
    val _inputSize: Int = songIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in songIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAllGenreSongs(): Int {
    val _sql: String = "DELETE FROM linkage_genre_song"
    return performSuspending(__db, false, true) { _connection ->
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
