package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository <Projects,Long>{
    Optional<Projects> findOneByTitleIgnoreCase(String title);


<<<<<<< HEAD

=======
>>>>>>> 5c149daa549654040395e8a33be70c9bdd6fdbc5
}
