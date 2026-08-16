export type ABLoop = {
  startMs: number;
  endMs: number;
  repeatCount: number | "infinite";
};

export function normalizeLoop(loop: ABLoop, durationMs: number): ABLoop {
  const safeDuration = Number.isFinite(durationMs)
    ? Math.max(0, durationMs)
    : 0;
  const rawStart = Number.isFinite(loop.startMs) ? loop.startMs : 0;
  const rawEnd = Number.isFinite(loop.endMs) ? loop.endMs : safeDuration;
  const startMs = Math.max(0, Math.min(rawStart, safeDuration));
  const endMs = Math.max(startMs, Math.min(rawEnd, safeDuration));
  return { ...loop, startMs, endMs };
}

export function parseTimecode(value: string): number {
  const parts = value.trim().split(":").map(Number);
  if (parts.length !== 2 || parts.some((part) => !Number.isFinite(part)))
    return 0;
  const [minutes, seconds] = parts;
  return Math.max(0, (minutes * 60 + seconds) * 1000);
}

export function formatTimecode(milliseconds: number): string {
  const safeMilliseconds = Number.isFinite(milliseconds)
    ? Math.max(0, Math.floor(milliseconds))
    : 0;
  const totalSeconds = Math.floor(safeMilliseconds / 1000);
  const minutes = Math.floor(totalSeconds / 60);
  const seconds = totalSeconds % 60;
  return `${minutes.toString().padStart(2, "0")}:${seconds.toString().padStart(2, "0")}`;
}

export function formatDuration(totalSeconds: number): string {
  const safeSeconds = Number.isFinite(totalSeconds)
    ? Math.max(0, Math.floor(totalSeconds))
    : 0;
  const minutes = Math.floor(safeSeconds / 60);
  const seconds = safeSeconds % 60;
  return `${minutes}:${seconds.toString().padStart(2, "0")}`;
}
