package com.java.SpringSecurity.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.java.SpringSecurity.security.SecurityContextHeader.*;

@Slf4j
public class SecurityContextHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(!SecurityContextUtil.isUserAuthenticated()){
            String authTNumber= request.getHeader(X_AUTH_TNUMBER.getHeaderKey());
            String authUser= request.getHeader(X_AUTH_USER.getHeaderKey());
            String authMandator= request.getHeader(X_AUTH_MANDATOR.getHeaderKey());

            boolean isAnyContextHeaderMissing = authTNumber == null ||  authUser == null || authMandator == null;

            if(isAnyContextHeaderMissing){
                log.error("Required security context header(s) are missing");
                return;
            }
            else{
                ContextUser contextUser = ContextUser.builder()
                        .username(authUser)
                        .tNumber(authTNumber)
                        .build();

                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(contextUser ,
                        null,null));
            }
        }
        filterChain.doFilter(request,response);

    }
}
