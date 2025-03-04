package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Prestamo", description = "API of Prestamo")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar un listado paginado y filtrado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link PrestamoDto}
     */
    @Operation(summary = "Find Page", description = "Method that return a page of Prestamos")
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto, @RequestParam(value = "gameName", required = false) String gameName, @RequestParam(value = "clientName", required = false) String clientName,
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        System.out.println("gameName: " + gameName + ", clientName: " + clientName + ", fecha: " + fecha);

        Page<Prestamo> page = this.prestamoService.findPage(dto, gameName, clientName, fecha);

        return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
    }

    /**
     * Método para crear un {@link Prestamo}
     *
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save", description = "Method that saves a Prestamo")
    @RequestMapping(path = { "" }, method = RequestMethod.PUT)
    public void save(@RequestBody PrestamoDto dto, @RequestParam(name = "gameName", required = false) String gameName, @RequestParam(name = "clientName", required = false) String clientName) throws Exception {

        dto.setGameName(gameName);
        dto.setClientName(clientName);

        this.prestamoService.save(dto);
    }

    /**
     * Método para eliminar un {@link Prestamo}
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Prestamo")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {
        this.prestamoService.delete(id);
    }

    /**
     * Recupera un listado de autores {@link Prestamo}
     *
     * @return {@link List} de {@link PrestamoDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Prestamos")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<PrestamoDto> findAll() {

        List<Prestamo> prestamos = (List<Prestamo>) this.prestamoService.findAll();

        return prestamos.stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList());
    }

}