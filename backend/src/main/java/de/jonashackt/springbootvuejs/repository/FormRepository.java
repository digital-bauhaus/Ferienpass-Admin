package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Form;
import de.jonashackt.springbootvuejs.domain.Project;
import de.jonashackt.springbootvuejs.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormRepository extends CrudRepository<Form, Long> {
}
