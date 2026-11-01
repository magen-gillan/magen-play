package player.phonograph.repo.room

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class MusicDatabase_AutoMigration_1_2_Impl : Migration {
  public constructor() : super(1, 2)

  public override fun migrate(connection: SQLiteConnection) {
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
  }
}
