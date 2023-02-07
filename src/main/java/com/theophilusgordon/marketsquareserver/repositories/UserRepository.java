package com.theophilusgordon.marketsquareserver.repositories;

import com.theophilusgordon.marketsquareserver.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
