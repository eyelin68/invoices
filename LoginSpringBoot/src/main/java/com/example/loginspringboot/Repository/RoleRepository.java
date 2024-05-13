package com.example.loginspringboot.Repository;

import com.example.loginspringboot.models.Erole;
import com.example.loginspringboot.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(Erole name);
}
