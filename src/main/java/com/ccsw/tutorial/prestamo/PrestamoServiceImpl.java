package com.ccsw.tutorial.prestamo;

import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoRepository prestamoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Prestamo> findPage(PrestamoSearchDto dto, String gameName, String clientName, LocalDate fecha) {
        Specification<Prestamo> specification = Specification.where(null);

        if (gameName != null) {
            specification = specification.and(new PrestamoSpecification(new SearchCriteria("gamename", ":", gameName)));
        }
        if (clientName != null) {
            specification = specification.and(new PrestamoSpecification(new SearchCriteria("clientname", ":", clientName)));
        }
        if (fecha != null) {
            specification = specification.and(new PrestamoSpecification(new SearchCriteria("inidate", ":<", fecha)));
            specification = specification.and(new PrestamoSpecification(new SearchCriteria("enddate", ":>", fecha)));

        }
        return this.prestamoRepository.findAll(specification, dto.getPageable().getPageable());
    }

    public Page<Prestamo> findAll() {

        return (Page<Prestamo>) this.prestamoRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(PrestamoDto data) throws Exception {

        Prestamo prestamo;
        LocalDate iniDate = data.getIniDate();
        LocalDate endDate = data.getEndDate();
        long days = ChronoUnit.DAYS.between(iniDate, endDate);
        days = days / (1000 * 60 * 60 * 24);

        if (days > 14) {
            throw new Exception("El periodo de préstamo no puede ser mayor a 14 días.");
        }
        if (endDate.isBefore(iniDate)) {
            throw new Exception(("La fecha de devolución no puede ser menor a la de préstamos"));
        }
        List<Prestamo> prestamos = prestamoRepository.findAPrestam(data.getClientName(), data.getGameName(), iniDate, endDate);

        if (prestamos.stream().anyMatch(existente -> !existente.getClientName().equals(data.getClientName()))) {
            throw new Exception("Este juego ya está prestado a otro cliente");
        }
        int numClientPrestamos = prestamoRepository.countClientPrestam(data.getClientName(), data.getIniDate(), data.getEndDate());

        if (numClientPrestamos > 2) {
            throw new Exception("El cliente ya tiene 2 copias del juego en estas fechas");
        }

        prestamo = new Prestamo();

        BeanUtils.copyProperties(data, prestamo, "id", "client", "game");
        prestamo.setGameName(data.getGameName());
        prestamo.setClientName(data.getClientName());

        this.prestamoRepository.save(prestamo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.prestamoRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }
        this.prestamoRepository.deleteById(id);
    }

}