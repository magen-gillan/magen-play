# Magen Play

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)

A modern Android music player based on [Phonograph Plus](https://github.com/chr56/Phonograph_Plus) by chr_56, customized with:

- Custom app icon and branding
- Natural (numeric-aware) song sorting — "1, 2, 10" instead of "1, 10, 2"
- Queue behavior that stays within the same folder when pressing Next
- Auto-scan media library after permission grant
- MediaStore direct backend (instant song loading, no Room sync delay)
- Name-only view mode for folders
- Custom folder cover picker
- Live settings update (no restart needed)

## Features

### Playback
- A/B repeat — loop a segment between two points
- Gapless playback
- Crossfade support
- Resume playback across sessions
- Sleep timer
- Lock screen / notification controls
- Android Auto support
- ReplayGain support

### Library
- Browse by Songs, Folders, Albums, Artists, Genres, Playlists
- Configurable tab order — drag to reorder, hide unused tabs
- Three list view modes: Grid, List, Detailed
- Natural numeric sort for song titles and folder names
- Custom folder covers — long-press a folder to set a custom cover image
- Name-only view mode — show just folder names without extra info
- Smart auto-playlists: Recently played, Most played, History
- Search across songs, artists, albums

### Now Playing
- Multiple display styles (vinyl disc, card, full-bleed, etc.)
- Synced lyrics (LRC file support)
- In-app equalizer with presets
- Tag editor (read-only via MediaMetadataRetriever)

### Settings
- Light / Dark / Black (AMOLED) / System themes
- Material You dynamic color (Android 12+)
- Customizable accent color
- Live settings update — changes apply immediately without restart
- Auto-scan after permission grant
- Configurable default home view

## Tech Stack

- **Kotlin** + **Jetpack Compose** + **Media3 ExoPlayer**
- **Room** database for playlists and play history
- **Koin** for dependency injection
- **Coil** for image loading
- **minSdk 26** (Android 8.0), **targetSdk 36** (Android 16)

## Installation

Download the latest APK from the [Releases](../../releases) page and tap to install.

## Acknowledgements

- Original project: **[Phonograph Plus](https://github.com/chr56/Phonograph_Plus)** by **chr_56**
- License: GPLv3
