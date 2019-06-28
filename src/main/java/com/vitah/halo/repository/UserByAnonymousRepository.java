package com.vitah.halo.repository;

import com.vitah.halo.entity.UserByAnonymous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author vitah
 */
@Repository
public interface UserByAnonymousRepository extends JpaRepository<UserByAnonymous, Integer> {

    /**
     * 根据AppId和设备号查找
     *
     * @param appId
     * @param deviceId
     * @return
     */
    UserByAnonymous findByAppIdAndDeviceId(
        @Param("app_id") Integer appId,
        @Param("device_id") String deviceId);
}
