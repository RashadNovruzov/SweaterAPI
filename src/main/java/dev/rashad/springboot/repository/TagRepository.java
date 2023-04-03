package dev.rashad.springboot.repository;

import dev.rashad.springboot.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {

    List<Tag> findByTagTitle(String query);

}
