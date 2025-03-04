package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

/**
 * @author ccsw
 *
 */
public interface PrestamoService {

    /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @param gameName nombre del juego filtrado
     * @param clientName nombre del cliente filtrado
     * @param fecha fecha de filtrado
     * @return {@link Page} de {@link Prestamo}
     */
    Page<Prestamo> findPage(PrestamoSearchDto dto, String gameName, String clientName, LocalDate fecha);

    Page<Prestamo> findAll();

    /**
     * Método para crear un {@link Prestamo}
     *
     * @param dto datos de la entidad
     */
    void save(PrestamoDto dto) throws Exception;

    /**
     * Método para eliminar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}