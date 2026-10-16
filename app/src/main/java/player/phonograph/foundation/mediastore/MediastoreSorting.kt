/*
 *  Copyright (c) 2022~2025 chr_56
 */

package player.phonograph.foundation.mediastore

import player.phonograph.model.Album
import player.phonograph.model.Artist
import player.phonograph.model.Genre
import player.phonograph.model.playlist.Playlist
import player.phonograph.model.sort.SortRef
import android.provider.MediaStore.Audio
import android.provider.MediaStore.Audio.AudioColumns

/**
 * Natural (numeric-aware) string comparison.
 * Fixes "1, 10, 2" → "1, 2, 10".
 */
fun naturalCompare(a: String, b: String): Int {
    var i = 0
    var j = 0
    while (i < a.length && j < b.length) {
        val ca = a[i]
        val cb = b[j]
        if (ca.isDigit() && cb.isDigit()) {
            val aStart = i
            while (i < a.length && a[i].isDigit()) i++
            val bStart = j
            while (j < b.length && b[j].isDigit()) j++
            val aNum = a.substring(aStart, i).toLong()
            val bNum = b.substring(bStart, j).toLong()
            if (aNum != bNum) return aNum.compareTo(bNum)
        } else {
            val aLow = ca.lowercaseChar()
            val bLow = cb.lowercaseChar()
            if (aLow != bLow) return aLow.compareTo(bLow)
            i++
            j++
        }
    }
    return (a.length - i) - (b.length - j)
}

fun <T> naturalStringComparator(selector: (T) -> String?): Comparator<T> =
    Comparator { a, b ->
        val sa = selector(a) ?: return@Comparator 1
        val sb = selector(b) ?: return@Comparator -1
        naturalCompare(sa, sb)
    }

//region Songs
fun mediastoreSongQuerySortRef(sortRef: SortRef): String = when (sortRef) {
    SortRef.ID                -> AudioColumns._ID
    SortRef.SONG_NAME         -> Audio.Media.DEFAULT_SORT_ORDER
    SortRef.ARTIST_NAME       -> Audio.Artists.DEFAULT_SORT_ORDER
    SortRef.ALBUM_NAME        -> Audio.Albums.DEFAULT_SORT_ORDER
    SortRef.ALBUM_ARTIST_NAME -> Audio.Media.ALBUM_ARTIST
    SortRef.COMPOSER          -> Audio.Media.COMPOSER
    SortRef.ADDED_DATE        -> Audio.Media.DATE_ADDED
    SortRef.MODIFIED_DATE     -> Audio.Media.DATE_MODIFIED
    SortRef.DURATION          -> Audio.Media.DURATION
    SortRef.YEAR              -> Audio.Media.YEAR
    SortRef.DISPLAY_NAME      -> Audio.Media.DEFAULT_SORT_ORDER
    SortRef.PATH              -> AudioColumns.DATA
    SortRef.SIZE              -> AudioColumns._ID // invalid
    SortRef.SONG_COUNT        -> AudioColumns._ID // invalid
    SortRef.ALBUM_COUNT       -> AudioColumns._ID // invalid
}
//endregion

//region Albums
fun mediastoreAlbumSortRefKey(sortRef: SortRef): (Album) -> Comparable<*>? =
    when (sortRef) {
        SortRef.ALBUM_NAME  -> { album: Album -> album.title }
        SortRef.ARTIST_NAME -> { album: Album -> album.artistName }
        SortRef.YEAR        -> { album: Album -> album.year }
        SortRef.SONG_COUNT  -> { album: Album -> album.songCount }
        else                -> { album: Album -> null }
    }
//endregion

//region Artists
fun mediastoreArtistSortRefKey(sortRef: SortRef): (Artist) -> Comparable<*>? =
    when (sortRef) {
        SortRef.ARTIST_NAME -> { artist: Artist -> artist.name }
        SortRef.ALBUM_COUNT -> { artist: Artist -> artist.albumCount }
        SortRef.SONG_COUNT  -> { artist: Artist -> artist.songCount }
        else                -> { artist: Artist -> null }
    }
//endregion

//region Genres
fun mediastoreGenreSortRefKey(sortRef: SortRef): (Genre) -> Comparable<*>? =
    when (sortRef) {
        SortRef.DISPLAY_NAME -> { genre: Genre -> genre.name }
        SortRef.SONG_COUNT   -> { genre: Genre -> genre.songCount }
        else                 -> { genre: Genre -> null }
    }
//endregion

//region Playlists
fun mediastorePlaylistSortRefKey(sortRef: SortRef): (Playlist) -> Comparable<*>? =
    when (sortRef) {
        SortRef.DISPLAY_NAME  -> { playlist: Playlist -> playlist.name }
        SortRef.PATH          -> { playlist: Playlist -> playlist.location }
        SortRef.ADDED_DATE    -> { playlist: Playlist -> playlist.dateAdded }
        SortRef.MODIFIED_DATE -> { playlist: Playlist -> playlist.dateModified }
        else                  -> { playlist: Playlist -> null }
    }
//endregion