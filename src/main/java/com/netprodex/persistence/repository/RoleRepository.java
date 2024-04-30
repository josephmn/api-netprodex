package com.netprodex.persistence.repository;

import com.netprodex.persistence.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
}
