package com.vitah.halo.repository;

import com.vitah.halo.entity.UserByAnonymous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vitah
 */
@Repository
public interface UserByAnonymousRepository extends JpaRepository<UserByAnonymous, Integer> {
}
