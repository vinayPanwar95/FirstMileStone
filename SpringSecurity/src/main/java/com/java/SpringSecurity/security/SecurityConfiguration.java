package com.java.SpringSecurity.security;

import com.java.SpringSecurity.security.local.LocalContextUserFilter;
import com.java.SpringSecurity.security.local.LocalUserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;


@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(SecurityConfigProperties.class)
public class SecurityConfiguration {

    private static final String ORDER_SECURED_ANT_PATTERN = "/order/**";
    private final SecurityConfigProperties securityConfigProperties;

    @Bean("serviceToServiceSecurityFilterChain")
    @ConditionalOnProperty(prefix = "com.learning.security", name = "localSecurityEnabled", havingValue = "false", matchIfMissing = true)
    public SecurityFilterChain serviceToServiceFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Creating serviceToService security filter chain");
        return httpSecurity
                .headers().frameOptions().disable()
                .and()
                .antMatcher(ORDER_SECURED_ANT_PATTERN)
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .addFilterAfter(new SecurityContextHeaderFilter(), BearerTokenAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authz->authz.anyRequest().authenticated())
                .build();
    }

    @Bean("localSecurityFilterChain")
    @ConditionalOnProperty(prefix = "com.learning.security", name = "localSecurityEnabled", havingValue = "true")
    public SecurityFilterChain localFilterChain(HttpSecurity httpSecurity) throws Exception{
        log.info("Creating local security filter chain");
        LocalUserFactory localUserFactory = new LocalUserFactory(securityConfigProperties);

        return httpSecurity
                .headers().frameOptions().disable()
                .and()
                .antMatcher(ORDER_SECURED_ANT_PATTERN)
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .addFilterAfter(new LocalContextUserFilter(localUserFactory), BearerTokenAuthenticationFilter.class)
//                .addFilterAfter(new SecurityContextHeaderFilter(), BearerTokenAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests(authz->authz.anyRequest().authenticated())
                .build();
    }



}
