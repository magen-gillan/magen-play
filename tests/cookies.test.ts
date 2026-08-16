import { describe, expect, it } from "vitest";
import { getSessionCookieOptions } from "../server/_core/cookies";

function requestWith(protocol: string, hostname: string) {
  return {
    protocol,
    hostname,
    headers: {},
  } as never;
}

describe("session cookie options", () => {
  it("uses secure cross-subdomain cookies for HTTPS", () => {
    expect(getSessionCookieOptions(requestWith("https", "3000-preview.example.com"))).toMatchObject({
      domain: ".example.com",
      sameSite: "none",
      secure: true,
    });
  });

  it("uses local HTTP-compatible cookies during development", () => {
    expect(getSessionCookieOptions(requestWith("http", "localhost"))).toMatchObject({
      domain: undefined,
      sameSite: "lax",
      secure: false,
    });
  });
});
