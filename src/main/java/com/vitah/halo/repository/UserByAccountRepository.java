package com.vitah.halo.repository;

import com.vitah.halo.entity.UserByAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vitah
 */
@Repository
public interface UserByAccountRepository extends JpaRepository<UserByAccount, Integer> {
}
