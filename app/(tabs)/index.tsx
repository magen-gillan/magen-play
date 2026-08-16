import { useMemo, useState } from "react";
import { FlatList, Modal, Pressable, StyleSheet, Text, TextInput, View } from "react-native";
import { ScreenContainer } from "@/components/screen-container";
import { IconSymbol } from "@/components/ui/icon-symbol";
import { useColors } from "@/hooks/use-colors";

type LibraryMode = "songs" | "albums" | "folders" | "artists";
type ArtworkMode = "square" | "circle" | "spinning";
type Track = { id: string; title: string; artist: string; album: string; duration: string; color: string; icon: string };

const TRACKS: Track[] = [
  { id: "1", title: "Midnight Signals", artist: "Lumen Park", album: "Night Drive", duration: "3:42", color: "#705CF6", icon: "music-note" },
  { id: "2", title: "Soft Focus", artist: "Nora Vale", album: "Quiet Rooms", duration: "4:18", color: "#3D8B98", icon: "graphic-eq" },
  { id: "3", title: "Parallel Lines", artist: "North Arcade", album: "Night Drive", duration: "2:56", color: "#C76C88", icon: "album" },
  { id: "4", title: "After the Rain", artist: "Mira Sol", album: "Field Notes", duration: "5:03", color: "#B88347", icon: "wb-sunny" },
  { id: "5", title: "Small Hours", artist: "Lumen Park", album: "Quiet Rooms", duration: "3:11", color: "#556CA8", icon: "headphones" },
];

const LIBRARY_LABELS: Record<LibraryMode, string> = { songs: "الأغاني", albums: "الألبومات", folders: "المجلدات", artists: "الفنانون" };

function Artwork({ track, mode, size = 64 }: { track: Track; mode: ArtworkMode; size?: number }) {
  return (
    <View style={[styles.artwork, { width: size, height: size, borderRadius: mode === "circle" || mode === "spinning" ? size / 2 : 16, backgroundColor: track.color }, mode === "spinning" && styles.spinningArtwork]}>
      <IconSymbol name={track.icon as any} size={Math.round(size * 0.36)} color="#FFFFFF" />
    </View>
  );
}

export default function HomeScreen() {
  const colors = useColors();
  const [tracks, setTracks] = useState<Track[]>(TRACKS);
  const [libraryMode, setLibraryMode] = useState<LibraryMode>("songs");
  const [artworkMode, setArtworkMode] = useState<ArtworkMode>("spinning");
  const [current, setCurrent] = useState<Track>(TRACKS[0]);
  const [playing, setPlaying] = useState(true);
  const [query, setQuery] = useState("");
  const [sheet, setSheet] = useState<"none" | "player" | "settings" | "edit" | "crop" | "loop">("none");
  const [start, setStart] = useState("00:42");
  const [end, setEnd] = useState("01:24");
  const [editedTitle, setEditedTitle] = useState(current.title);
  const [editedAlbum, setEditedAlbum] = useState(current.album);

  const filtered = useMemo(() => tracks.filter((track) => `${track.title} ${track.artist} ${track.album}`.toLowerCase().includes(query.toLowerCase())), [query, tracks]);

  const playTrack = (track: Track) => {
    setCurrent(track);
    setEditedTitle(track.title);
    setEditedAlbum(track.album);
    setPlaying(true);
  };
  const closeSheet = () => setSheet("none");

  return (
    <ScreenContainer className="px-5" edges={["top", "left", "right"]}>
      <View style={styles.header}>
        <View>
          <Text style={[styles.eyebrow, { color: colors.muted }]}>مكتبتك المحلية</Text>
          <Text style={[styles.title, { color: colors.foreground }]}>magen-play</Text>
        </View>
        <Pressable onPress={() => setSheet("settings")} style={({ pressed }) => [styles.iconButton, { backgroundColor: colors.surface }, pressed && styles.pressed]}>
          <IconSymbol name="gearshape.fill" size={22} color={colors.foreground} />
        </Pressable>
      </View>

      <View style={[styles.search, { backgroundColor: colors.surface, borderColor: colors.border }]}>
        <IconSymbol name="magnifyingglass" size={19} color={colors.muted} />
        <TextInput value={query} onChangeText={setQuery} placeholder="ابحث في الأغاني والفنانين" placeholderTextColor={colors.muted} style={[styles.searchInput, { color: colors.foreground }]} />
      </View>

      <View style={styles.modeRow}>
        {(Object.keys(LIBRARY_LABELS) as LibraryMode[]).map((mode) => (
          <Pressable key={mode} onPress={() => setLibraryMode(mode)} style={({ pressed }) => [styles.modeChip, { backgroundColor: libraryMode === mode ? colors.primary : colors.surface }, pressed && styles.pressed]}>
            <Text style={[styles.modeText, { color: libraryMode === mode ? "#FFFFFF" : colors.muted }]}>{LIBRARY_LABELS[mode]}</Text>
          </Pressable>
        ))}
      </View>

      <View style={styles.sectionHeading}>
        <View><Text style={[styles.sectionTitle, { color: colors.foreground }]}>{LIBRARY_LABELS[libraryMode]}</Text><Text style={[styles.sectionMeta, { color: colors.muted }]}>{filtered.length} عناصر • محدثة الآن</Text></View>
        <Pressable onPress={() => setSheet("settings")}><Text style={[styles.link, { color: colors.primary }]}>تخصيص</Text></Pressable>
      </View>

      <FlatList data={filtered} keyExtractor={(item) => item.id} showsVerticalScrollIndicator={false} contentContainerStyle={styles.list} renderItem={({ item }) => (
        <Pressable onPress={() => playTrack(item)} style={({ pressed }) => [styles.trackRow, { backgroundColor: item.id === current.id ? colors.surface : "transparent" }, pressed && styles.pressed]}>
          <Artwork track={item} mode={artworkMode === "spinning" ? "square" : artworkMode} />
          <View style={styles.trackCopy}><Text numberOfLines={1} style={[styles.trackTitle, { color: colors.foreground }]}>{item.title}</Text><Text numberOfLines={1} style={[styles.trackArtist, { color: colors.muted }]}>{item.artist}  ·  {item.album}</Text></View>
          <Text style={[styles.duration, { color: colors.muted }]}>{item.duration}</Text>
          <Pressable onPress={() => { setCurrent(item); setEditedTitle(item.title); setEditedAlbum(item.album); setSheet("edit"); }} style={({ pressed }) => [styles.moreButton, pressed && styles.pressed]}><IconSymbol name="ellipsis" size={20} color={colors.muted} /></Pressable>
        </Pressable>
      )} />

      <Pressable onPress={() => setSheet("player")} style={[styles.miniPlayer, { backgroundColor: colors.surface, borderColor: colors.border }]}>
        <Artwork track={current} mode={artworkMode} size={48} />
        <View style={styles.miniCopy}><Text numberOfLines={1} style={[styles.miniTitle, { color: colors.foreground }]}>{current.title}</Text><Text numberOfLines={1} style={[styles.trackArtist, { color: colors.muted }]}>{current.artist}</Text></View>
        <Pressable onPress={() => setPlaying(!playing)} style={styles.playButton}><IconSymbol name={playing ? "pause.fill" : "play.fill"} size={21} color="#FFFFFF" /></Pressable>
        <Pressable onPress={() => setSheet("loop")} style={styles.smallAction}><IconSymbol name="repeat" size={20} color={colors.primary} /></Pressable>
      </Pressable>

      <View style={[styles.playerActions, { borderTopColor: colors.border }]}><Pressable onPress={() => setSheet("crop")}><Text style={[styles.actionText, { color: colors.primary }]}>قص الأغنية</Text></Pressable><Pressable onPress={() => setSheet("loop")}><Text style={[styles.actionText, { color: colors.primary }]}>تكرار A-B</Text></Pressable><Pressable onPress={() => setSheet("edit")}><Text style={[styles.actionText, { color: colors.primary }]}>تحرير</Text></Pressable></View>

      <Modal visible={sheet !== "none"} transparent animationType="slide" onRequestClose={closeSheet}>
        <View style={styles.modalBackdrop}><View style={[styles.sheet, { backgroundColor: colors.background }]}>
          <View style={styles.sheetHandle} />
          <View style={styles.sheetHeader}><Text style={[styles.sheetTitle, { color: colors.foreground }]}>{sheet === "player" ? "المشغل الكامل" : sheet === "settings" ? "تخصيص التجربة" : sheet === "edit" ? "تحرير بيانات الأغنية" : sheet === "crop" ? "قص الأغنية" : "تكرار مقطع A-B"}</Text><Pressable onPress={closeSheet}><IconSymbol name="xmark" size={24} color={colors.muted} /></Pressable></View>
          {sheet === "player" && <View style={styles.fullPlayer}><Artwork track={current} mode={artworkMode} size={220} /><Text style={[styles.fullPlayerTitle, { color: colors.foreground }]}>{current.title}</Text><Text style={[styles.trackArtist, { color: colors.muted }]}>{current.artist} · {current.album}</Text><View style={[styles.progressTrack, { backgroundColor: colors.border }]}><View style={[styles.progressFill, { backgroundColor: colors.primary }]} /></View><View style={styles.fullPlayerControls}><Pressable onPress={() => setPlaying(!playing)} style={[styles.playButton, styles.largePlayButton]}><IconSymbol name={playing ? "pause.fill" : "play.fill"} size={26} color="#FFFFFF" /></Pressable><Pressable onPress={() => setSheet("loop")} style={styles.smallAction}><IconSymbol name="repeat" size={22} color={colors.primary} /></Pressable><Pressable onPress={() => setSheet("crop")} style={styles.smallAction}><IconSymbol name="scissors" size={22} color={colors.primary} /></Pressable></View></View>}
          {sheet === "settings" && <View>
            <Text style={[styles.settingLabel, { color: colors.muted }]}>ما يظهر عند الدخول</Text><View style={styles.settingGrid}>{(Object.keys(LIBRARY_LABELS) as LibraryMode[]).map((mode) => <Pressable key={mode} onPress={() => setLibraryMode(mode)} style={[styles.settingChoice, { borderColor: libraryMode === mode ? colors.primary : colors.border, backgroundColor: libraryMode === mode ? `${colors.primary}22` : colors.surface }]}><Text style={{ color: colors.foreground }}>{LIBRARY_LABELS[mode]}</Text></Pressable>)}</View>
            <Text style={[styles.settingLabel, { color: colors.muted }]}>نمط الغلاف في المشغل</Text><View style={styles.settingGrid}>{(["square", "circle", "spinning"] as ArtworkMode[]).map((mode) => <Pressable key={mode} onPress={() => setArtworkMode(mode)} style={[styles.settingChoice, { borderColor: artworkMode === mode ? colors.primary : colors.border, backgroundColor: artworkMode === mode ? `${colors.primary}22` : colors.surface }]}><Text style={{ color: colors.foreground }}>{mode === "square" ? "مربع ثابت" : mode === "circle" ? "دائرة ثابتة" : "دائرة دوارة"}</Text></Pressable>)}</View>
            <Text style={[styles.settingLabel, { color: colors.muted }]}>الثيم</Text><View style={[styles.preferenceRow, { backgroundColor: colors.surface }]}><Text style={{ color: colors.foreground }}>داكن ليلي • AMOLED جاهز</Text><IconSymbol name="checkmark.circle.fill" size={22} color={colors.success} /></View>
          </View>}
          {sheet === "edit" && <View><View style={styles.editHero}><Artwork track={current} mode="square" size={92} /><View><Text style={[styles.trackTitle, { color: colors.foreground }]}>{current.title}</Text><Text style={[styles.trackArtist, { color: colors.muted }]}>غلاف الأغنية</Text></View></View><TextInput value={editedTitle} onChangeText={setEditedTitle} placeholder="اسم الأغنية" placeholderTextColor={colors.muted} style={[styles.field, { color: colors.foreground, borderColor: colors.border, backgroundColor: colors.surface }]} /><TextInput value={editedAlbum} onChangeText={setEditedAlbum} placeholder="الألبوم" placeholderTextColor={colors.muted} style={[styles.field, { color: colors.foreground, borderColor: colors.border, backgroundColor: colors.surface }]} /><Pressable onPress={() => { setTracks((items) => items.map((track) => track.id === current.id ? { ...track, title: editedTitle, album: editedAlbum } : track)); setCurrent({ ...current, title: editedTitle, album: editedAlbum }); closeSheet(); }} style={[styles.primaryButton, { backgroundColor: colors.primary }]}><Text style={styles.primaryButtonText}>حفظ التعديلات</Text></Pressable><Text style={[styles.helper, { color: colors.muted }]}>سيظهر في الإصدار الأصلي خيار كتابة الوسوم داخل الملف حسب الصيغة والصلاحيات.</Text></View>}
          {sheet === "crop" && <View><Text style={[styles.helper, { color: colors.muted }]}>حدد بداية ونهاية النسخة الجديدة. لن يتم استبدال الملف الأصلي.</Text><View style={styles.timeRow}><TextInput value="00:00" editable={false} style={[styles.timeField, { color: colors.foreground, backgroundColor: colors.surface }]} /><View style={[styles.timeline, { backgroundColor: colors.border }]}><View style={[styles.timelineFill, { backgroundColor: colors.primary }]} /></View><TextInput value={current.duration} editable={false} style={[styles.timeField, { color: colors.foreground, backgroundColor: colors.surface }]} /></View><Pressable onPress={closeSheet} style={[styles.primaryButton, { backgroundColor: colors.primary }]}><Text style={styles.primaryButtonText}>تصدير نسخة مقصوصة</Text></Pressable></View>}
          {sheet === "loop" && <View><Text style={[styles.helper, { color: colors.muted }]}>اختر نقطتي البداية والنهاية للتكرار أثناء تشغيل الأغنية.</Text><View style={styles.timeRow}><TextInput value={start} onChangeText={setStart} style={[styles.timeField, { color: colors.foreground, backgroundColor: colors.surface }]} /><Text style={[styles.loopDash, { color: colors.primary }]}>A — B</Text><TextInput value={end} onChangeText={setEnd} style={[styles.timeField, { color: colors.foreground, backgroundColor: colors.surface }]} /></View><Pressable onPress={closeSheet} style={[styles.primaryButton, { backgroundColor: colors.primary }]}><Text style={styles.primaryButtonText}>بدء التكرار وحفظ العلامة</Text></Pressable></View>}
        </View></View>
      </Modal>
    </ScreenContainer>
  );
}

const styles = StyleSheet.create({ header: { flexDirection: "row", justifyContent: "space-between", alignItems: "center", paddingTop: 8, paddingBottom: 18 }, eyebrow: { fontSize: 13, marginBottom: 5 }, title: { fontSize: 25, fontWeight: "800" }, iconButton: { width: 44, height: 44, borderRadius: 22, alignItems: "center", justifyContent: "center" }, pressed: { opacity: 0.7, transform: [{ scale: 0.98 }] }, search: { height: 48, borderRadius: 16, borderWidth: 1, flexDirection: "row", alignItems: "center", paddingHorizontal: 14, gap: 10 }, searchInput: { flex: 1, fontSize: 14, textAlign: "right" }, modeRow: { flexDirection: "row-reverse", gap: 8, paddingVertical: 18 }, modeChip: { paddingHorizontal: 14, paddingVertical: 9, borderRadius: 20 }, modeText: { fontSize: 13, fontWeight: "700" }, sectionHeading: { flexDirection: "row-reverse", justifyContent: "space-between", alignItems: "flex-end", marginBottom: 8 }, sectionTitle: { fontSize: 20, fontWeight: "800", textAlign: "right" }, sectionMeta: { fontSize: 12, marginTop: 3, textAlign: "right" }, link: { fontWeight: "700", fontSize: 13 }, list: { paddingBottom: 110 }, trackRow: { minHeight: 76, borderRadius: 18, marginVertical: 3, padding: 7, flexDirection: "row-reverse", alignItems: "center", gap: 12 }, artwork: { alignItems: "center", justifyContent: "center" }, spinningArtwork: { borderWidth: 3, borderColor: "#FFFFFF55" }, trackCopy: { flex: 1, alignItems: "flex-end" }, trackTitle: { fontSize: 15, fontWeight: "700", textAlign: "right" }, trackArtist: { fontSize: 12, marginTop: 4, textAlign: "right" }, duration: { fontSize: 12 }, moreButton: { padding: 8 }, miniPlayer: { position: "absolute", left: 20, right: 20, bottom: 50, height: 68, borderRadius: 22, borderWidth: 1, padding: 9, flexDirection: "row-reverse", alignItems: "center", gap: 10 }, miniCopy: { flex: 1, alignItems: "flex-end" }, miniTitle: { fontWeight: "800", fontSize: 14 }, playButton: { width: 38, height: 38, borderRadius: 19, backgroundColor: "#6D5DF5", alignItems: "center", justifyContent: "center" }, smallAction: { padding: 7 }, playerActions: { position: "absolute", bottom: 0, left: 20, right: 20, height: 42, borderTopWidth: 1, flexDirection: "row-reverse", justifyContent: "space-around", alignItems: "center" }, actionText: { fontSize: 12, fontWeight: "700" }, modalBackdrop: { flex: 1, backgroundColor: "#00000088", justifyContent: "flex-end" }, sheet: { borderTopLeftRadius: 28, borderTopRightRadius: 28, padding: 22, minHeight: 330 }, sheetHandle: { alignSelf: "center", width: 44, height: 4, borderRadius: 2, backgroundColor: "#77777766", marginBottom: 18 }, sheetHeader: { flexDirection: "row-reverse", justifyContent: "space-between", alignItems: "center", marginBottom: 22 }, sheetTitle: { fontSize: 21, fontWeight: "800" }, settingLabel: { textAlign: "right", fontSize: 13, marginTop: 5, marginBottom: 9 }, settingGrid: { flexDirection: "row-reverse", flexWrap: "wrap", gap: 8, marginBottom: 16 }, settingChoice: { borderWidth: 1, borderRadius: 14, paddingVertical: 12, paddingHorizontal: 13 }, preferenceRow: { borderRadius: 14, padding: 15, flexDirection: "row-reverse", justifyContent: "space-between", alignItems: "center" }, editHero: { flexDirection: "row-reverse", alignItems: "center", gap: 15, marginBottom: 18 }, field: { height: 50, borderRadius: 14, borderWidth: 1, paddingHorizontal: 15, textAlign: "right", marginBottom: 10 }, primaryButton: { height: 52, borderRadius: 16, alignItems: "center", justifyContent: "center", marginTop: 7 }, primaryButtonText: { color: "#FFFFFF", fontWeight: "800", fontSize: 15 }, helper: { textAlign: "right", fontSize: 12, lineHeight: 19, marginTop: 12 }, timeRow: { flexDirection: "row", alignItems: "center", gap: 8, marginVertical: 22 }, timeField: { width: 70, height: 44, borderRadius: 12, textAlign: "center", fontWeight: "700" }, timeline: { flex: 1, height: 6, borderRadius: 3 }, timelineFill: { width: "48%", height: 6, borderRadius: 3 }, loopDash: { fontWeight: "800" }, fullPlayer: { alignItems: "center", paddingBottom: 14 }, fullPlayerTitle: { fontSize: 22, fontWeight: "800", marginTop: 18, textAlign: "center" }, progressTrack: { alignSelf: "stretch", height: 6, borderRadius: 3, marginTop: 22 }, progressFill: { width: "42%", height: 6, borderRadius: 3 }, fullPlayerControls: { flexDirection: "row-reverse", alignItems: "center", gap: 18, marginTop: 24 }, largePlayButton: { width: 52, height: 52, borderRadius: 26 },
});
