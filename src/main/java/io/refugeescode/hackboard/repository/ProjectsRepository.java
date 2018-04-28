package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository <Projects, Long> {
}
