/*
 *  Copyright (c) 2022~2025 chr_56
 */

package player.phonograph.model.file

import player.phonograph.model.Song
import player.phonograph.model.playlist.Playlist
import player.phonograph.model.sort.SortMode
import player.phonograph.model.sort.SortRef

class FileItem(
    val name: String,
    val mediaPath: MediaPath,
    val dateAdded: Long = -1,
    val dateModified: Long = -1,
    val size: Long = -1,
    val content: Content,
) {

    sealed interface Content

    object MediaContent: Content
    class SongContent(val song: Song) : Content
    class PlaylistContent(val playlist: Playlist) : Content
    class FolderContent(var count: Int) : Content


    val path: String get() = mediaPath.path
    val isFolder: Boolean get() = content is FolderContent
    val isFile: Boolean get() = content !is FolderContent

    //region Object methods

    // only path matters
    override fun toString(): String = mediaPath.toString()
    override fun hashCode(): Int = mediaPath.hashCode()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FileItem) return false
        if (mediaPath != other.mediaPath) return false
        return true
    }

    //endregion

    class SortedComparator(private val sortMode: SortMode) : Comparator<FileItem> {
        override fun compare(a: FileItem?, b: FileItem?): Int {
            if (a == null || b == null) return 0
            return if ((a.isFolder) xor (b.isFolder)) {
                if (a.isFolder) -1 else 1
            } else {
                when (sortMode.sortRef) {
                    SortRef.MODIFIED_DATE -> a.dateModified.compareTo(b.dateModified)
                    SortRef.ADDED_DATE    -> a.dateAdded.compareTo(b.dateAdded)
                    SortRef.SIZE          -> {
                        if (a.isFile && b.isFolder) a.size.compareTo(b.size)
                        else naturalCompare(a.name, b.name)
                    }

                    else                  -> naturalCompare(a.name, b.name)
                }.let {
                    if (sortMode.revert) -it else it
                }
            }
        }

        /**
         * Natural (numeric-aware) string comparison.
         * Fixes the user-reported bug where song titles like "1, 2, 10" sorted as "1, 10, 2".
         */
        private fun naturalCompare(a: String, b: String): Int {
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
    }
}