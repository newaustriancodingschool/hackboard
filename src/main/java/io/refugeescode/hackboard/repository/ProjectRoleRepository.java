package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface ProjectRoleRepository extends JpaRepository<ProjectRole,Long>{
}
