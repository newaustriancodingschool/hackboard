package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProjectsRepository extends JpaRepository <Projects,Long>{

    Optional<Projects> findOneByTitleIgnoreCase(String title);

}
