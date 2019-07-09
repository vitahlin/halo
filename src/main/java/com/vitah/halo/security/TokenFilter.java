package com.vitah.halo.security;

import com.vitah.halo.constant.HeaderConstants;
import com.vitah.halo.util.BeanUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author vitah
 */
public class TokenFilter extends OncePerRequestFilter {

    private TokenAuthentication tokenAuthentication;

    public TokenFilter() {
        tokenAuthentication = BeanUtil.getBean(TokenAuthentication.class);
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        String authToken = request.getHeader(HeaderConstants.HEADER_PREFIX);
        if (authToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = tokenAuthentication.getAuthentication(authToken);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
