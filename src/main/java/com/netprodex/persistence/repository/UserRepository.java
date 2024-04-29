package com.netprodex.persistence.repository;

import com.netprodex.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByUsername(String username);

//    @Query("SELECT u FROM UserEntity u WHERE u.username = ?")
//    Optional<UserEntity> findUser(String username);
}
