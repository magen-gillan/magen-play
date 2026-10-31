/*
 * Copyright (c) 2022 chr_56
 */

package player.phonograph.mechanism.coil.audiofile

import coil.map.Mapper
import coil.request.Options
import player.phonograph.mechanism.coil.model.SongImage
import player.phonograph.model.Song

class AudioFileMapper : Mapper<Song, SongImage> {
    override fun map(data: Song, options: Options): SongImage? {
        // Always create SongImage — the fetcher's retrievers (MediaStore,
        // MediaMetadataRetriever, JAudioTagger) try multiple methods to
        // load album art, including via albumId from MediaStore which
        // doesn't require direct file access.
        //
        // Previously this checked File(data.data).exists() and returned
        // null when the file wasn't directly accessible (common on Android
        // 11+ scoped storage). This caused ALL songs to fall back to the
        // default album art, making them all appear identical.
        return SongImage.from(data)
    }
}
