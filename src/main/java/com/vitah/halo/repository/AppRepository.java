package com.vitah.halo.repository;

import com.vitah.halo.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author vitah
 */
@Repository
public interface AppRepository extends JpaRepository<App, Integer> {
}
