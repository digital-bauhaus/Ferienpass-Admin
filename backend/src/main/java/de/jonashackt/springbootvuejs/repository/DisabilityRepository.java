package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Disability;
import de.jonashackt.springbootvuejs.domain.Doctor;
import de.jonashackt.springbootvuejs.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisabilityRepository extends CrudRepository<Disability, Long> {

    List<Disability> findByName(@Param("name") String name);

}
