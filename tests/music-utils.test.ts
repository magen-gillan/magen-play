import { describe, expect, it } from "vitest";
import {
  formatDuration,
  formatTimecode,
  normalizeLoop,
  parseTimecode,
} from "../lib/music-utils";

describe("music utilities", () => {
  it("clamps A-B loop values to the track duration", () => {
    expect(
      normalizeLoop(
        { startMs: -20, endMs: 90_000, repeatCount: "infinite" },
        60_000,
      ),
    ).toEqual({ startMs: 0, endMs: 60_000, repeatCount: "infinite" });
  });

  it("never allows the end point before the start point", () => {
    expect(
      normalizeLoop({ startMs: 30_000, endMs: 10_000, repeatCount: 2 }, 60_000),
    ).toEqual({ startMs: 30_000, endMs: 30_000, repeatCount: 2 });
  });

  it("formats seconds as mm:ss", () => {
    expect(formatDuration(0)).toBe("0:00");
    expect(formatDuration(185)).toBe("3:05");
  });

  it("parses and formats timecodes consistently", () => {
    expect(parseTimecode("01:24")).toBe(84_000);
    expect(parseTimecode("invalid")).toBe(0);
    expect(formatTimecode(84_000)).toBe("01:24");
    expect(formatTimecode(Number.NaN)).toBe("00:00");
  });

  it("falls back safely for non-finite duration values", () => {
    expect(formatDuration(Number.NaN)).toBe("0:00");
    expect(formatDuration(Number.POSITIVE_INFINITY)).toBe("0:00");
    expect(
      normalizeLoop(
        {
          startMs: Number.NaN,
          endMs: Number.POSITIVE_INFINITY,
          repeatCount: 1,
        },
        60_000,
      ),
    ).toEqual({
      startMs: 0,
      endMs: 60_000,
      repeatCount: 1,
    });
  });
});
