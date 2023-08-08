package com.java.SpringSecurity.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum CollateralRole {
    ROLE_VIEWER("READ_ONLY"),
    ROLE_MAKER("CREATE_EDIT_DELETE"),
    ROLE_CHECKER("RELEASE"),
    ROLE_ADMIN("ADMIN");
String name;
}
