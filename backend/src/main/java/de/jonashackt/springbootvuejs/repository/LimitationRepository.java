package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Limitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Date;

import java.util.List;

public interface LimitationRepository extends CrudRepository<Limitation, Long> {
    List<Limitation> findByName(@Param("name") String name);

}
