package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Project;
import de.jonashackt.springbootvuejs.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value="FROM User")
    List<User> findAllUsers();

    @Query(value="FROM User u WHERE u.hasPayed = true")
    List<User> findAllUsersThatHavePayed();

    @Query(value="FROM User u WHERE u.cancellations is NULL")
    List<User> findAllUsersWithoutCancellation();

    @Query(value="FROM User u WHERE u.cancellations is not NULL")
    List<User> findAllUsersWithCancellations();

    @Query(value="SELECT u.cancellations FROM User u WHERE u.id = :id")
    List<Project> findAllCancellationsById(@Param("id") int id);

    @Query(value="UPDATE User u SET u.cancellations = :cancellations where u.id = :id")
    int updateCancellations(@Param("id") int id, @Param("cancellations") List<Project> cancellations);

    List<User> findById(@Param("id") int id);

    @Query(value="SELECT projects FROM User u WHERE u.firstName in :firstName AND u.lastName in :lastName")
    List<Project> findProjectsByFirstNameAndLastName(@Param("firstName") String firstName,@Param("lastName") String lastName);

    @Query(value="FROM User u WHERE u.firstName LIKE CONCAT('%',:firstName,'%') or u.firstName LIKE CONCAT('%',:lastName,'%') or u.lastName LIKE CONCAT('%',:firstName,'%') or u.lastName LIKE CONCAT('%',:lastName,'%')")
    List<User> findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<User> findByLastName(@Param("lastName") String lastName);

    List<User> findByFirstName(@Param("firstName") String firstName);

    int findIdByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    List<User> findByRegisterDate(@Param("registerDate") String registerDate);

}
