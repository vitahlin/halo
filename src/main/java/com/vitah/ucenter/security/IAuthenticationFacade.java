package com.vitah.ucenter.security;

import com.vitah.ucenter.entity.User;
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

    /**
     * 获取当前用户信息
     *
     * @return
     */
    User currentUser();
}
