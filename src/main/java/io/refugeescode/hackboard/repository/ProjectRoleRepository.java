package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.ProjectRole;
import io.refugeescode.hackboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface ProjectRoleRepository extends JpaRepository<ProjectRole,Long>{

    Optional<ProjectRole> findOneByRoleName(String roleName);


}
