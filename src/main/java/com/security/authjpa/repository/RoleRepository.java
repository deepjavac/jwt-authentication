package com.security.authjpa.repository;

import com.security.authjpa.model.User;
import com.security.authjpa.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    Set<UserRole> findByRoleIdIn(List<Long> roles);
}
