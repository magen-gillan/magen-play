export type ABLoop = { startMs: number; endMs: number; repeatCount: number | "infinite" };

export function normalizeLoop(loop: ABLoop, durationMs: number): ABLoop {
  const startMs = Math.max(0, Math.min(loop.startMs, durationMs));
  const endMs = Math.max(startMs, Math.min(loop.endMs, durationMs));
  return { ...loop, startMs, endMs };
}

export function formatDuration(totalSeconds: number): string {
  const safeSeconds = Math.max(0, Math.floor(totalSeconds));
  const minutes = Math.floor(safeSeconds / 60);
  const seconds = safeSeconds % 60;
  return `${minutes}:${seconds.toString().padStart(2, "0")}`;
}
