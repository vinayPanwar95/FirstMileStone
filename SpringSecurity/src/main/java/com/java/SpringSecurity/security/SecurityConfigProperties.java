package com.java.SpringSecurity.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("com.learning.security")
@Data
public class SecurityConfigProperties {
    private List<LocalUser> localUsers;
    public boolean localSecurityEnabled;

    @Data
    public static class LocalUser{
        String userName;
        String tNumber;
        List<String> roles;
    }
}
