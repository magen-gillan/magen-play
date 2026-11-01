package player.phonograph.repo.room

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import javax.`annotation`.processing.Generated
import kotlin.Suppress

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
internal class MusicDatabase_AutoMigration_2_3_Impl : Migration {
  public constructor() : super(2, 3)

  public override fun migrate(connection: SQLiteConnection) {
    connection.execSQL("CREATE TABLE IF NOT EXISTS `cache_images` (`cache_domain` INTEGER NOT NULL, `cache_id` INTEGER NOT NULL, `cache_source` INTEGER NOT NULL, `timestamp` INTEGER NOT NULL, `empty` INTEGER NOT NULL, `filename` TEXT, PRIMARY KEY(`cache_domain`, `cache_id`, `cache_source`))")
  }
}
