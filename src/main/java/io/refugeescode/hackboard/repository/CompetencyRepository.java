package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.Competency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface CompetencyRepository extends JpaRepository<Competency,Long>{
}
