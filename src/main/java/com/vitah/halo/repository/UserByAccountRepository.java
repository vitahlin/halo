package com.vitah.halo.repository;

import com.vitah.halo.entity.UserByAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author vitah
 */
@Repository
public interface UserByAccountRepository extends JpaRepository<UserByAccount, Integer> {

    /**
     * 根据邮箱和AppId查找用户是否注册过
     *
     * @param appId
     * @param email
     * @return
     */
    Boolean existsByAppIdAndEmail(@Param("app_id") Integer appId, @Param("email") String email);

    /**
     * 根据userId查找用户信息
     *
     * @param userId
     * @return
     */
    UserByAccount findByUserId(@Param("user_id") Integer userId);

    /**
     * 根据AppId和Email查找
     *
     * @param appId
     * @param email
     * @return
     */
    UserByAccount findByAppIdAndEmail(@Param("app_id") Integer appId, @Param("email") String email);
}
