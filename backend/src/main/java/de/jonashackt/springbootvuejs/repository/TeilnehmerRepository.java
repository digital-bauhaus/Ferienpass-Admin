package de.jonashackt.springbootvuejs.repository;


import de.jonashackt.springbootvuejs.domain.Projekt;
import de.jonashackt.springbootvuejs.domain.Teilnehmer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TeilnehmerRepository extends CrudRepository<Teilnehmer, Long> {

    @Query(value="FROM Teilnehmer")
    List<Teilnehmer> findAllUsers();

    @Query(value="FROM Teilnehmer u WHERE u.bezahlt = true")
    List<Teilnehmer> findAllUsersThatHavePayed();

    @Query(value="FROM Teilnehmer u WHERE u.stornierungen is NULL")
    List<Teilnehmer> findAllUsersWithoutCancellation();

    @Query(value="FROM Teilnehmer u WHERE u.stornierungen is not NULL")
    List<Teilnehmer> findAllUsersWithCancellations();

    @Query(value="SELECT u.stornierungen FROM Teilnehmer u WHERE u.id = :id")
    List<Projekt> findAllCancellationsById(@Param("id") int id);

    @Query(value="UPDATE Teilnehmer u SET u.stornierungen = :stornierungen where u.id = :id")
    int updateStornierungen(@Param("id") int id, @Param("stornierungen") List<Projekt> stornierungen);

    List<Teilnehmer> findById(@Param("id") int id);

    @Query(value="SELECT u.angemeldeteProjekte FROM Teilnehmer u WHERE u.vorname in :vorname AND u.nachname in :nachname")
    List<Projekt> findProjektsByVornameAndNachname(@Param("vorname") String vorname, @Param("nachname") String nachname);

    @Query(value="FROM Teilnehmer u WHERE u.vorname LIKE CONCAT('%',:vorname,'%') or u.vorname LIKE CONCAT('%',:nachname,'%') or u.nachname LIKE CONCAT('%',:vorname,'%') or u.nachname LIKE CONCAT('%',:nachname,'%')")
    List<Teilnehmer> findByName(@Param("vorname") String vorname, @Param("nachname") String nachname);

    List<Teilnehmer> findByNachname(@Param("nachname") String nachname);

    List<Teilnehmer> findByVorname(@Param("vorname") String vorname);

    int findProjekt_idByVornameAndNachname(@Param("vorname") String vorname, @Param("nachname") String nachname);

    List<Teilnehmer> findByRegistrierungsdatum(@Param("registrierungsdatum") LocalDate registrierungsdatum);
}
