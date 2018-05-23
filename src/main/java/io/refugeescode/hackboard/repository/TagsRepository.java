package io.refugeescode.hackboard.repository;

import io.refugeescode.hackboard.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface TagsRepository extends JpaRepository<Tag,Long> {

}
