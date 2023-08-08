package com.java.SpringSecurity.security.local;

import com.java.SpringSecurity.security.CollateralRole;
import com.java.SpringSecurity.security.ContextUser;
import com.java.SpringSecurity.security.SecurityConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class LocalUserFactory {
    private final Map<String, ContextUser> localAvailableUsers;

    public LocalUserFactory(SecurityConfigProperties securityConfigProperties){
        List<SecurityConfigProperties.LocalUser> localUsers = securityConfigProperties.getLocalUsers();
        localAvailableUsers = localUsers.stream()
                                .map(this::convertToContextUser)
                                .collect(Collectors.toMap(ContextUser::getTNumber, Function.identity(), (o1,o2)->o1, () ->new TreeMap<>(String.CASE_INSENSITIVE_ORDER)));
    }

    private ContextUser convertToContextUser(SecurityConfigProperties.LocalUser localUser){
        return ContextUser.builder()
                .tNumber(localUser.getTNumber())
                .username(localUser.getUserName())
                .roles(convertToCollateralRoles(localUser.getRoles()))
                .build();
    }
    private List<CollateralRole> convertToCollateralRoles(List<String> roles){
        return roles.stream().map(CollateralRole::valueOf).collect(Collectors.toList());
    }

    public Optional<ContextUser> getUserByTNumber(String tNumber){
        return Optional.ofNullable(localAvailableUsers.get(tNumber));
    }
}
