package com.vitah.halo.security;

import org.springframework.security.core.Authentication;

/**
 * @author vitah
 */
public interface IAuthenticationFacade {

    /**
     * 获取Authentication对象
     *
     * @return
     */
    Authentication getAuthentication();
}
