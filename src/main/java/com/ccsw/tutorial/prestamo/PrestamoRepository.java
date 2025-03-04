package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**v
 * @author ccsw
 *
 */
public interface PrestamoRepository extends CrudRepository<Prestamo, Long> {

    Page<Prestamo> findAll(Specification<Prestamo> specification, Pageable pageable);

    @Query("SELECT p FROM Prestamo p WHERE p.game_name = :game_name AND :ini_date <= p.end_date AND :end_date >= p.ini_date AND p.client_name <> :client_name")
    List<Prestamo> findAPrestam(@Param("client_name") String clientName, @Param("game_name") String gameName, @Param("ini_date") LocalDate iniDate, @Param("end_date") LocalDate endDate);

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.client_name = :client_name AND :ini_date <= p.end_date AND :end_date >= p.ini_date")
    int countClientPrestam(@Param("client_name") String client_name, @Param("ini_date") LocalDate iniDate, @Param("end_date") LocalDate endDate);
}