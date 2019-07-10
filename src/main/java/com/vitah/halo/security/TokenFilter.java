package com.vitah.halo.security;

import com.vitah.halo.constant.HeaderConstants;
import com.vitah.halo.entity.App;
import com.vitah.halo.repository.AppRepository;
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

    private AppRepository appRepository;

    public TokenFilter() {
        tokenAuthentication = BeanUtil.getBean(TokenAuthentication.class);
        appRepository = BeanUtil.getBean(AppRepository.class);
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        String authToken = request.getHeader(HeaderConstants.HEADER_PREFIX);
        String appId = request.getHeader(HeaderConstants.APP_ID);
        if (authToken == null || appId == null) {
            filterChain.doFilter(request, response);
            return;
        }

        App app = appRepository.findById(Integer.valueOf(appId)).orElse(null);
        if (app == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        try {
            Authentication authentication = tokenAuthentication.getAuthentication(authToken, app.getAppKey());
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
