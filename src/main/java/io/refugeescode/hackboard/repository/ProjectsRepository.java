package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Project, Long> {
    Optional<Project> findOneByTitleIgnoreCase(String title);

}
