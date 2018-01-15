package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Project;
import de.jonashackt.springbootvuejs.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    List<Project> findByName(@Param("name") String name);

    @Query(value="FROM Project")
    List<Project> findAllProjects();

    @Query(value="SELECT users FROM Project p WHERE p.name in :name")
    List<User> findUsersByProjectName();

}
