package vn.com.kietnbm1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.com.kietnbm1.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
