package com.java.SpringSecurity.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SecurityContextHeader {
    COOKIE("cookie"),
    REMOTE_USER("remote-user"),
    X_AUTH_USER("X-Auth-User"),
    X_AUTH_MANDATOR("x-Auth-Mandator"),
    X_AUTH_TNUMBER("x-Auth-Tnumber");

    private final String headerKey;
}
