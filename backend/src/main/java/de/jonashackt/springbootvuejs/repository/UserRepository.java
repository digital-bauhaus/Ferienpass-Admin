package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Date;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value="FROM User")
    List<User> findAllUsers();

    List<User> findByLastName(@Param("lastName") String lastName);

    List<User> findByFirstName(@Param("firstName") String firstName);

    List<User> findByRegisterDate(@Param("registerDate") Date registerDate);

}
