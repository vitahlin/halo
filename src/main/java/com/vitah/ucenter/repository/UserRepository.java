package com.vitah.ucenter.repository;

import com.vitah.ucenter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vitah
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
