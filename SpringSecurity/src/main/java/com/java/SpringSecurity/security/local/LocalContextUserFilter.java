package com.java.SpringSecurity.security.local;

import com.java.SpringSecurity.security.ContextUser;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocalContextUserFilter extends OncePerRequestFilter {

    public static final String LOCAL_USER_T_NUMBER_HEADER = "X-Auth-Local-T-Number";

    LocalUserFactory localUserFactory;

    public LocalContextUserFilter(LocalUserFactory localUserFactory){
        this.localUserFactory = localUserFactory;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String localUserHeaderTNumber =  request.getHeader(LOCAL_USER_T_NUMBER_HEADER);
        if(localUserHeaderTNumber != null){
            Optional<ContextUser> contextUserOpt = localUserFactory.getUserByTNumber(localUserHeaderTNumber);
            if(contextUserOpt.isPresent()){
                ContextUser contextUser = contextUserOpt.get();
                List<GrantedAuthority> authorities = getAuthorities(contextUser);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(contextUser, null, authorities));
            }else{
                logger.info("Local User tNumber not provided in header. Proceeding with filter chain");
            }
        }

        filterChain.doFilter(request,response);
    }

    private List<GrantedAuthority> getAuthorities(ContextUser contextUser) {
        return contextUser.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }
}
