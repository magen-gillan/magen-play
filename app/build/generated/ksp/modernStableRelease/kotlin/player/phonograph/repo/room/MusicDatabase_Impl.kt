package player.phonograph.repo.room

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass
import player.phonograph.repo.room.dao.AlbumManipulateDao
import player.phonograph.repo.room.dao.AlbumManipulateDao_Impl
import player.phonograph.repo.room.dao.AlbumQueryDao
import player.phonograph.repo.room.dao.AlbumQueryDao_Impl
import player.phonograph.repo.room.dao.ArtistManipulateDao
import player.phonograph.repo.room.dao.ArtistManipulateDao_Impl
import player.phonograph.repo.room.dao.ArtistQueryDao
import player.phonograph.repo.room.dao.ArtistQueryDao_Impl
import player.phonograph.repo.room.dao.FavoriteSongManipulateDao
import player.phonograph.repo.room.dao.FavoriteSongManipulateDao_Impl
import player.phonograph.repo.room.dao.FavoriteSongQueryDao
import player.phonograph.repo.room.dao.FavoriteSongQueryDao_Impl
import player.phonograph.repo.room.dao.GenreManipulateDao
import player.phonograph.repo.room.dao.GenreManipulateDao_Impl
import player.phonograph.repo.room.dao.GenreQueryDao
import player.phonograph.repo.room.dao.GenreQueryDao_Impl
import player.phonograph.repo.room.dao.ImageCacheDao
import player.phonograph.repo.room.dao.ImageCacheDao_Impl
import player.phonograph.repo.room.dao.MetadataDao
import player.phonograph.repo.room.dao.MetadataDao_Impl
import player.phonograph.repo.room.dao.PinedPlaylistManipulateDao
import player.phonograph.repo.room.dao.PinedPlaylistManipulateDao_Impl
import player.phonograph.repo.room.dao.PinedPlaylistQueryDao
import player.phonograph.repo.room.dao.PinedPlaylistQueryDao_Impl
import player.phonograph.repo.room.dao.PlaylistManipulateDao
import player.phonograph.repo.room.dao.PlaylistManipulateDao_Impl
import player.phonograph.repo.room.dao.PlaylistQueryDao
import player.phonograph.repo.room.dao.PlaylistQueryDao_Impl
import player.phonograph.repo.room.dao.PlaylistSongManipulateDao
import player.phonograph.repo.room.dao.PlaylistSongManipulateDao_Impl
import player.phonograph.repo.room.dao.PlaylistSongQueryDao
import player.phonograph.repo.room.dao.PlaylistSongQueryDao_Impl
import player.phonograph.repo.room.dao.RelationshipManipulateDao
import player.phonograph.repo.room.dao.RelationshipManipulateDao_Impl
import player.phonograph.repo.room.dao.RelationshipQueryDao
import player.phonograph.repo.room.dao.RelationshipQueryDao_Impl
import player.phonograph.repo.room.dao.SongManipulateDao
import player.phonograph.repo.room.dao.SongManipulateDao_Impl
import player.phonograph.repo.room.dao.SongQueryDao
import player.phonograph.repo.room.dao.SongQueryDao_Impl

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class MusicDatabase_Impl : MusicDatabase() {
  private val _metadataDao: Lazy<MetadataDao> = lazy {
    MetadataDao_Impl(this)
  }

  private val _songQueryDao: Lazy<SongQueryDao> = lazy {
    SongQueryDao_Impl(this)
  }

  private val _songManipulateDao: Lazy<SongManipulateDao> = lazy {
    SongManipulateDao_Impl(this)
  }

  private val _playlistQueryDao: Lazy<PlaylistQueryDao> = lazy {
    PlaylistQueryDao_Impl(this)
  }

  private val _albumQueryDao: Lazy<AlbumQueryDao> = lazy {
    AlbumQueryDao_Impl(this)
  }

  private val _albumManipulateDao: Lazy<AlbumManipulateDao> = lazy {
    AlbumManipulateDao_Impl(this)
  }

  private val _artistQueryDao: Lazy<ArtistQueryDao> = lazy {
    ArtistQueryDao_Impl(this)
  }

  private val _artistManipulateDao: Lazy<ArtistManipulateDao> = lazy {
    ArtistManipulateDao_Impl(this)
  }

  private val _genreQueryDao: Lazy<GenreQueryDao> = lazy {
    GenreQueryDao_Impl(this)
  }

  private val _genreManipulateDao: Lazy<GenreManipulateDao> = lazy {
    GenreManipulateDao_Impl(this)
  }

  private val _relationshipQueryDao: Lazy<RelationshipQueryDao> = lazy {
    RelationshipQueryDao_Impl(this)
  }

  private val _relationshipManipulateDao: Lazy<RelationshipManipulateDao> = lazy {
    RelationshipManipulateDao_Impl(this)
  }

  private val _playlistManipulateDao: Lazy<PlaylistManipulateDao> = lazy {
    PlaylistManipulateDao_Impl(this)
  }

  private val _playlistSongQueryDao: Lazy<PlaylistSongQueryDao> = lazy {
    PlaylistSongQueryDao_Impl(this)
  }

  private val _playlistSongManipulateDao: Lazy<PlaylistSongManipulateDao> = lazy {
    PlaylistSongManipulateDao_Impl(this)
  }

  private val _favoriteSongQueryDao: Lazy<FavoriteSongQueryDao> = lazy {
    FavoriteSongQueryDao_Impl(this)
  }

  private val _favoriteSongManipulateDao: Lazy<FavoriteSongManipulateDao> = lazy {
    FavoriteSongManipulateDao_Impl(this)
  }

  private val _pinedPlaylistQueryDao: Lazy<PinedPlaylistQueryDao> = lazy {
    PinedPlaylistQueryDao_Impl(this)
  }

  private val _pinedPlaylistManipulateDao: Lazy<PinedPlaylistManipulateDao> = lazy {
    PinedPlaylistManipulateDao_Impl(this)
  }

  private val _imageCacheDao: Lazy<ImageCacheDao> = lazy {
    ImageCacheDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(3, "261741ad6ed8b3004afdd49beb39449a", "0b1527c80c6fe1955ce421386000c0fa") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `mediastore` (`_id` INTEGER NOT NULL, `_data` TEXT NOT NULL, `duration` INTEGER NOT NULL, `date_added` INTEGER NOT NULL, `date_modified` INTEGER NOT NULL, `title` TEXT NOT NULL, `album_id` INTEGER NOT NULL, `album` TEXT NOT NULL, `artist_id` INTEGER NOT NULL, `artist` TEXT NOT NULL, `year` INTEGER NOT NULL, `track` INTEGER NOT NULL, `album_artist` TEXT NOT NULL, `composer` TEXT NOT NULL, PRIMARY KEY(`_id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_mediastore__id__data_title` ON `mediastore` (`_id`, `_data`, `title`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playlists` (`playlist_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playlist_name` TEXT NOT NULL, `date_added` INTEGER NOT NULL, `date_modified` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playlist_songs` (`playlist_song_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `_id` INTEGER NOT NULL DEFAULT 0, `playlist_id` INTEGER NOT NULL DEFAULT 0, `position` INTEGER NOT NULL, `path` TEXT NOT NULL, FOREIGN KEY(`playlist_id`) REFERENCES `playlists`(`playlist_id`) ON UPDATE NO ACTION ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playlist_songs_playlist_id__id` ON `playlist_songs` (`playlist_id`, `_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playlist_songs__id_playlist_id` ON `playlist_songs` (`_id`, `playlist_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `favorite_songs` (`_id` INTEGER NOT NULL, `path` TEXT NOT NULL, `title` TEXT NOT NULL, `date_added` INTEGER NOT NULL, PRIMARY KEY(`_id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_favorite_songs__id` ON `favorite_songs` (`_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_favorite_songs_path` ON `favorite_songs` (`path`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `favorite_playlists` (`primary_id` INTEGER NOT NULL, `type` INTEGER NOT NULL, `sub_id` INTEGER NOT NULL, `location` TEXT NOT NULL, `title` TEXT NOT NULL, `date_added` INTEGER NOT NULL, PRIMARY KEY(`primary_id`, `type`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_favorite_playlists_primary_id_type` ON `favorite_playlists` (`primary_id`, `type`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_favorite_playlists_sub_id_location` ON `favorite_playlists` (`sub_id`, `location`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `cache_images` (`cache_domain` INTEGER NOT NULL, `cache_id` INTEGER NOT NULL, `cache_source` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `empty` INTEGER NOT NULL, `filename` TEXT, PRIMARY KEY(`cache_domain`, `cache_id`, `cache_source`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `albums` (`album_id` INTEGER NOT NULL, `album` TEXT NOT NULL DEFAULT 'Unnamed Album', `artist_id` INTEGER NOT NULL, `album_artist` TEXT NOT NULL DEFAULT 'Unknown Artist', `year` INTEGER NOT NULL, `date_modified` INTEGER NOT NULL, `song_count` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`album_id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_albums_album_id_album` ON `albums` (`album_id`, `album`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `artists` (`artist_id` INTEGER NOT NULL, `artist` TEXT NOT NULL, `album_count` INTEGER NOT NULL DEFAULT 0, `song_count` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`artist_id`, `artist`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `linkage_artist_album` (`album_id` INTEGER NOT NULL, `artist_id` INTEGER NOT NULL, PRIMARY KEY(`artist_id`, `album_id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_linkage_artist_album_album_id_artist_id` ON `linkage_artist_album` (`album_id`, `artist_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `linkage_artist_song` (`_id` INTEGER NOT NULL, `artist_id` INTEGER NOT NULL, `role` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`artist_id`, `_id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_linkage_artist_song__id_artist_id` ON `linkage_artist_song` (`_id`, `artist_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `genres` (`genre_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `genre` TEXT NOT NULL, `category` TEXT, `genre_id_mediastore` INTEGER NOT NULL, `song_count` INTEGER NOT NULL DEFAULT 0)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_genres_genre_id_genre` ON `genres` (`genre_id`, `genre`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `linkage_genre_song` (`genre_id` INTEGER NOT NULL, `_id` INTEGER NOT NULL, `source` TEXT, PRIMARY KEY(`genre_id`, `_id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_linkage_genre_song_genre_id__id` ON `linkage_genre_song` (`genre_id`, `_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `metadata` (`key` TEXT NOT NULL, `value` TEXT NOT NULL, PRIMARY KEY(`key`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '261741ad6ed8b3004afdd49beb39449a')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `mediastore`")
        connection.execSQL("DROP TABLE IF EXISTS `playlists`")
        connection.execSQL("DROP TABLE IF EXISTS `playlist_songs`")
        connection.execSQL("DROP TABLE IF EXISTS `favorite_songs`")
        connection.execSQL("DROP TABLE IF EXISTS `favorite_playlists`")
        connection.execSQL("DROP TABLE IF EXISTS `cache_images`")
        connection.execSQL("DROP TABLE IF EXISTS `albums`")
        connection.execSQL("DROP TABLE IF EXISTS `artists`")
        connection.execSQL("DROP TABLE IF EXISTS `linkage_artist_album`")
        connection.execSQL("DROP TABLE IF EXISTS `linkage_artist_song`")
        connection.execSQL("DROP TABLE IF EXISTS `genres`")
        connection.execSQL("DROP TABLE IF EXISTS `linkage_genre_song`")
        connection.execSQL("DROP TABLE IF EXISTS `metadata`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsMediastore: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMediastore.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("_data", TableInfo.Column("_data", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("duration", TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("date_added", TableInfo.Column("date_added", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("date_modified", TableInfo.Column("date_modified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("album_id", TableInfo.Column("album_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("album", TableInfo.Column("album", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("artist_id", TableInfo.Column("artist_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("artist", TableInfo.Column("artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("year", TableInfo.Column("year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("track", TableInfo.Column("track", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("album_artist", TableInfo.Column("album_artist", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMediastore.put("composer", TableInfo.Column("composer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMediastore: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesMediastore: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesMediastore.add(TableInfo.Index("index_mediastore__id__data_title", false, listOf("_id", "_data", "title"), listOf("ASC", "ASC", "ASC")))
        val _infoMediastore: TableInfo = TableInfo("mediastore", _columnsMediastore, _foreignKeysMediastore, _indicesMediastore)
        val _existingMediastore: TableInfo = read(connection, "mediastore")
        if (!_infoMediastore.equals(_existingMediastore)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |mediastore(player.phonograph.repo.room.entity.MediastoreSongEntity).
              | Expected:
              |""".trimMargin() + _infoMediastore + """
              |
              | Found:
              |""".trimMargin() + _existingMediastore)
        }
        val _columnsPlaylists: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaylists.put("playlist_id", TableInfo.Column("playlist_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylists.put("playlist_name", TableInfo.Column("playlist_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylists.put("date_added", TableInfo.Column("date_added", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylists.put("date_modified", TableInfo.Column("date_modified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaylists: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlaylists: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoPlaylists: TableInfo = TableInfo("playlists", _columnsPlaylists, _foreignKeysPlaylists, _indicesPlaylists)
        val _existingPlaylists: TableInfo = read(connection, "playlists")
        if (!_infoPlaylists.equals(_existingPlaylists)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playlists(player.phonograph.repo.room.entity.PlaylistEntity).
              | Expected:
              |""".trimMargin() + _infoPlaylists + """
              |
              | Found:
              |""".trimMargin() + _existingPlaylists)
        }
        val _columnsPlaylistSongs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaylistSongs.put("playlist_song_id", TableInfo.Column("playlist_song_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistSongs.put("_id", TableInfo.Column("_id", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistSongs.put("playlist_id", TableInfo.Column("playlist_id", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistSongs.put("position", TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaylistSongs.put("path", TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaylistSongs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysPlaylistSongs.add(TableInfo.ForeignKey("playlists", "CASCADE", "NO ACTION", listOf("playlist_id"), listOf("playlist_id")))
        val _indicesPlaylistSongs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPlaylistSongs.add(TableInfo.Index("index_playlist_songs_playlist_id__id", false, listOf("playlist_id", "_id"), listOf("ASC", "ASC")))
        _indicesPlaylistSongs.add(TableInfo.Index("index_playlist_songs__id_playlist_id", false, listOf("_id", "playlist_id"), listOf("ASC", "ASC")))
        val _infoPlaylistSongs: TableInfo = TableInfo("playlist_songs", _columnsPlaylistSongs, _foreignKeysPlaylistSongs, _indicesPlaylistSongs)
        val _existingPlaylistSongs: TableInfo = read(connection, "playlist_songs")
        if (!_infoPlaylistSongs.equals(_existingPlaylistSongs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playlist_songs(player.phonograph.repo.room.entity.PlaylistSongEntity).
              | Expected:
              |""".trimMargin() + _infoPlaylistSongs + """
              |
              | Found:
              |""".trimMargin() + _existingPlaylistSongs)
        }
        val _columnsFavoriteSongs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFavoriteSongs.put("_id", TableInfo.Column("_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoriteSongs.put("path", TableInfo.Column("path", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoriteSongs.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoriteSongs.put("date_added", TableInfo.Column("date_added", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFavoriteSongs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesFavoriteSongs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesFavoriteSongs.add(TableInfo.Index("index_favorite_songs__id", false, listOf("_id"), listOf("ASC")))
        _indicesFavoriteSongs.add(TableInfo.Index("index_favorite_songs_path", false, listOf("path"), listOf("ASC")))
        val _infoFavoriteSongs: TableInfo = TableInfo("favorite_songs", _columnsFavoriteSongs, _foreignKeysFavoriteSongs, _indicesFavoriteSongs)
        val _existingFavoriteSongs: TableInfo = read(connection, "favorite_songs")
        if (!_infoFavoriteSongs.equals(_existingFavoriteSongs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |favorite_songs(player.phonograph.repo.room.entity.FavoriteSongEntity).
              | Expected:
              |""".trimMargin() + _infoFavoriteSongs + """
              |
              | Found:
              |""".trimMargin() + _existingFavoriteSongs)
        }
        val _columnsFavoritePlaylists: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFavoritePlaylists.put("primary_id", TableInfo.Column("primary_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritePlaylists.put("type", TableInfo.Column("type", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritePlaylists.put("sub_id", TableInfo.Column("sub_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritePlaylists.put("location", TableInfo.Column("location", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritePlaylists.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavoritePlaylists.put("date_added", TableInfo.Column("date_added", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFavoritePlaylists: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesFavoritePlaylists: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesFavoritePlaylists.add(TableInfo.Index("index_favorite_playlists_primary_id_type", false, listOf("primary_id", "type"), listOf("ASC", "ASC")))
        _indicesFavoritePlaylists.add(TableInfo.Index("index_favorite_playlists_sub_id_location", false, listOf("sub_id", "location"), listOf("ASC", "ASC")))
        val _infoFavoritePlaylists: TableInfo = TableInfo("favorite_playlists", _columnsFavoritePlaylists, _foreignKeysFavoritePlaylists, _indicesFavoritePlaylists)
        val _existingFavoritePlaylists: TableInfo = read(connection, "favorite_playlists")
        if (!_infoFavoritePlaylists.equals(_existingFavoritePlaylists)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |favorite_playlists(player.phonograph.repo.room.entity.PinedPlaylistsEntity).
              | Expected:
              |""".trimMargin() + _infoFavoritePlaylists + """
              |
              | Found:
              |""".trimMargin() + _existingFavoritePlaylists)
        }
        val _columnsCacheImages: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCacheImages.put("cache_domain", TableInfo.Column("cache_domain", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCacheImages.put("cache_id", TableInfo.Column("cache_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCacheImages.put("cache_source", TableInfo.Column("cache_source", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCacheImages.put("timestamp", TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCacheImages.put("empty", TableInfo.Column("empty", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCacheImages.put("filename", TableInfo.Column("filename", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCacheImages: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCacheImages: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCacheImages: TableInfo = TableInfo("cache_images", _columnsCacheImages, _foreignKeysCacheImages, _indicesCacheImages)
        val _existingCacheImages: TableInfo = read(connection, "cache_images")
        if (!_infoCacheImages.equals(_existingCacheImages)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |cache_images(player.phonograph.repo.room.entity.ImageCacheEntity).
              | Expected:
              |""".trimMargin() + _infoCacheImages + """
              |
              | Found:
              |""".trimMargin() + _existingCacheImages)
        }
        val _columnsAlbums: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsAlbums.put("album_id", TableInfo.Column("album_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbums.put("album", TableInfo.Column("album", "TEXT", true, 0, "'Unnamed Album'", TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbums.put("artist_id", TableInfo.Column("artist_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbums.put("album_artist", TableInfo.Column("album_artist", "TEXT", true, 0, "'Unknown Artist'", TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbums.put("year", TableInfo.Column("year", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbums.put("date_modified", TableInfo.Column("date_modified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsAlbums.put("song_count", TableInfo.Column("song_count", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysAlbums: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesAlbums: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesAlbums.add(TableInfo.Index("index_albums_album_id_album", false, listOf("album_id", "album"), listOf("ASC", "ASC")))
        val _infoAlbums: TableInfo = TableInfo("albums", _columnsAlbums, _foreignKeysAlbums, _indicesAlbums)
        val _existingAlbums: TableInfo = read(connection, "albums")
        if (!_infoAlbums.equals(_existingAlbums)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |albums(player.phonograph.repo.room.entity.AlbumEntity).
              | Expected:
              |""".trimMargin() + _infoAlbums + """
              |
              | Found:
              |""".trimMargin() + _existingAlbums)
        }
        val _columnsArtists: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsArtists.put("artist_id", TableInfo.Column("artist_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtists.put("artist", TableInfo.Column("artist", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtists.put("album_count", TableInfo.Column("album_count", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        _columnsArtists.put("song_count", TableInfo.Column("song_count", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysArtists: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesArtists: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoArtists: TableInfo = TableInfo("artists", _columnsArtists, _foreignKeysArtists, _indicesArtists)
        val _existingArtists: TableInfo = read(connection, "artists")
        if (!_infoArtists.equals(_existingArtists)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |artists(player.phonograph.repo.room.entity.ArtistEntity).
              | Expected:
              |""".trimMargin() + _infoArtists + """
              |
              | Found:
              |""".trimMargin() + _existingArtists)
        }
        val _columnsLinkageArtistAlbum: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLinkageArtistAlbum.put("album_id", TableInfo.Column("album_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLinkageArtistAlbum.put("artist_id", TableInfo.Column("artist_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLinkageArtistAlbum: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLinkageArtistAlbum: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesLinkageArtistAlbum.add(TableInfo.Index("index_linkage_artist_album_album_id_artist_id", false, listOf("album_id", "artist_id"), listOf("ASC", "ASC")))
        val _infoLinkageArtistAlbum: TableInfo = TableInfo("linkage_artist_album", _columnsLinkageArtistAlbum, _foreignKeysLinkageArtistAlbum, _indicesLinkageArtistAlbum)
        val _existingLinkageArtistAlbum: TableInfo = read(connection, "linkage_artist_album")
        if (!_infoLinkageArtistAlbum.equals(_existingLinkageArtistAlbum)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |linkage_artist_album(player.phonograph.repo.room.entity.LinkageAlbumAndArtist).
              | Expected:
              |""".trimMargin() + _infoLinkageArtistAlbum + """
              |
              | Found:
              |""".trimMargin() + _existingLinkageArtistAlbum)
        }
        val _columnsLinkageArtistSong: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLinkageArtistSong.put("_id", TableInfo.Column("_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLinkageArtistSong.put("artist_id", TableInfo.Column("artist_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLinkageArtistSong.put("role", TableInfo.Column("role", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLinkageArtistSong: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLinkageArtistSong: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesLinkageArtistSong.add(TableInfo.Index("index_linkage_artist_song__id_artist_id", false, listOf("_id", "artist_id"), listOf("ASC", "ASC")))
        val _infoLinkageArtistSong: TableInfo = TableInfo("linkage_artist_song", _columnsLinkageArtistSong, _foreignKeysLinkageArtistSong, _indicesLinkageArtistSong)
        val _existingLinkageArtistSong: TableInfo = read(connection, "linkage_artist_song")
        if (!_infoLinkageArtistSong.equals(_existingLinkageArtistSong)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |linkage_artist_song(player.phonograph.repo.room.entity.LinkageSongAndArtist).
              | Expected:
              |""".trimMargin() + _infoLinkageArtistSong + """
              |
              | Found:
              |""".trimMargin() + _existingLinkageArtistSong)
        }
        val _columnsGenres: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsGenres.put("genre_id", TableInfo.Column("genre_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGenres.put("genre", TableInfo.Column("genre", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGenres.put("category", TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGenres.put("genre_id_mediastore", TableInfo.Column("genre_id_mediastore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsGenres.put("song_count", TableInfo.Column("song_count", "INTEGER", true, 0, "0", TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysGenres: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesGenres: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesGenres.add(TableInfo.Index("index_genres_genre_id_genre", false, listOf("genre_id", "genre"), listOf("ASC", "ASC")))
        val _infoGenres: TableInfo = TableInfo("genres", _columnsGenres, _foreignKeysGenres, _indicesGenres)
        val _existingGenres: TableInfo = read(connection, "genres")
        if (!_infoGenres.equals(_existingGenres)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |genres(player.phonograph.repo.room.entity.GenreEntity).
              | Expected:
              |""".trimMargin() + _infoGenres + """
              |
              | Found:
              |""".trimMargin() + _existingGenres)
        }
        val _columnsLinkageGenreSong: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsLinkageGenreSong.put("genre_id", TableInfo.Column("genre_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLinkageGenreSong.put("_id", TableInfo.Column("_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsLinkageGenreSong.put("source", TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysLinkageGenreSong: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesLinkageGenreSong: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesLinkageGenreSong.add(TableInfo.Index("index_linkage_genre_song_genre_id__id", false, listOf("genre_id", "_id"), listOf("ASC", "ASC")))
        val _infoLinkageGenreSong: TableInfo = TableInfo("linkage_genre_song", _columnsLinkageGenreSong, _foreignKeysLinkageGenreSong, _indicesLinkageGenreSong)
        val _existingLinkageGenreSong: TableInfo = read(connection, "linkage_genre_song")
        if (!_infoLinkageGenreSong.equals(_existingLinkageGenreSong)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |linkage_genre_song(player.phonograph.repo.room.entity.LinkageGenreAndSong).
              | Expected:
              |""".trimMargin() + _infoLinkageGenreSong + """
              |
              | Found:
              |""".trimMargin() + _existingLinkageGenreSong)
        }
        val _columnsMetadata: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMetadata.put("key", TableInfo.Column("key", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMetadata.put("value", TableInfo.Column("value", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMetadata: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesMetadata: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoMetadata: TableInfo = TableInfo("metadata", _columnsMetadata, _foreignKeysMetadata, _indicesMetadata)
        val _existingMetadata: TableInfo = read(connection, "metadata")
        if (!_infoMetadata.equals(_existingMetadata)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |metadata(player.phonograph.repo.room.entity.Metadata).
              | Expected:
              |""".trimMargin() + _infoMetadata + """
              |
              | Found:
              |""".trimMargin() + _existingMetadata)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "mediastore", "playlists", "playlist_songs", "favorite_songs", "favorite_playlists", "cache_images", "albums", "artists", "linkage_artist_album", "linkage_artist_song", "genres", "linkage_genre_song", "metadata")
  }

  public override fun clearAllTables() {
    super.performClear(true, "mediastore", "playlists", "playlist_songs", "favorite_songs", "favorite_playlists", "cache_images", "albums", "artists", "linkage_artist_album", "linkage_artist_song", "genres", "linkage_genre_song", "metadata")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(MetadataDao::class, MetadataDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SongQueryDao::class, SongQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SongManipulateDao::class, SongManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaylistQueryDao::class, PlaylistQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AlbumQueryDao::class, AlbumQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(AlbumManipulateDao::class, AlbumManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ArtistQueryDao::class, ArtistQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ArtistManipulateDao::class, ArtistManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(GenreQueryDao::class, GenreQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(GenreManipulateDao::class, GenreManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RelationshipQueryDao::class, RelationshipQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RelationshipManipulateDao::class, RelationshipManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaylistManipulateDao::class, PlaylistManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaylistSongQueryDao::class, PlaylistSongQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaylistSongManipulateDao::class, PlaylistSongManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FavoriteSongQueryDao::class, FavoriteSongQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FavoriteSongManipulateDao::class, FavoriteSongManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PinedPlaylistQueryDao::class, PinedPlaylistQueryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PinedPlaylistManipulateDao::class, PinedPlaylistManipulateDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ImageCacheDao::class, ImageCacheDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    _autoMigrations.add(MusicDatabase_AutoMigration_1_2_Impl())
    _autoMigrations.add(MusicDatabase_AutoMigration_2_3_Impl())
    return _autoMigrations
  }

  public override fun MetadataDao(): MetadataDao = _metadataDao.value

  public override fun SongQueryDao(): SongQueryDao = _songQueryDao.value

  public override fun SongManipulateDao(): SongManipulateDao = _songManipulateDao.value

  public override fun PlaylistQueryDao(): PlaylistQueryDao = _playlistQueryDao.value

  public override fun AlbumQueryDao(): AlbumQueryDao = _albumQueryDao.value

  public override fun AlbumManipulateDao(): AlbumManipulateDao = _albumManipulateDao.value

  public override fun ArtistQueryDao(): ArtistQueryDao = _artistQueryDao.value

  public override fun ArtistManipulateDao(): ArtistManipulateDao = _artistManipulateDao.value

  public override fun GenreQueryDao(): GenreQueryDao = _genreQueryDao.value

  public override fun GenreManipulateDao(): GenreManipulateDao = _genreManipulateDao.value

  public override fun RelationshipQueryDao(): RelationshipQueryDao = _relationshipQueryDao.value

  public override fun RelationshipManipulateDao(): RelationshipManipulateDao = _relationshipManipulateDao.value

  public override fun PlaylistManipulateDao(): PlaylistManipulateDao = _playlistManipulateDao.value

  public override fun PlaylistSongQueryDao(): PlaylistSongQueryDao = _playlistSongQueryDao.value

  public override fun PlaylistSongManipulateDao(): PlaylistSongManipulateDao = _playlistSongManipulateDao.value

  public override fun FavoriteSongQueryDao(): FavoriteSongQueryDao = _favoriteSongQueryDao.value

  public override fun FavoriteSongManipulateDao(): FavoriteSongManipulateDao = _favoriteSongManipulateDao.value

  public override fun PinedPlaylistQueryDao(): PinedPlaylistQueryDao = _pinedPlaylistQueryDao.value

  public override fun PinedPlaylistManipulateDao(): PinedPlaylistManipulateDao = _pinedPlaylistManipulateDao.value

  public override fun ImageCacheDao(): ImageCacheDao = _imageCacheDao.value
}
