package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

import java.util.List;

public interface ClientService {

    /*
     * Metodo para recuperar los clientes
     *
     * @return {@link List} de {@ Client}
     * */
    List<Client> findAll();

    /*
     * Metodo para crear o actualizar un cliente
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     * */
    void save(Long id, ClientDto dto) throws Exception;

    /*
     * Metodo para borrar un cliente
     *
     * @param id PK de la entidad
     * */
    void delete(Long id) throws Exception;

}
