package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    /**
     * @ {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {

        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * @ {@inheritDoc}
     */
    @Override
    public void save(Long id, ClientDto dto) throws Exception {

        Client client;
        List<Client> clients = this.findAll();
        Boolean nameExists = clients.stream().anyMatch(c -> c.getName().equals(dto.getName()));

        if (id == null) {
            if (!nameExists) {
                client = new Client();
            } else {
                throw new Exception("El nombre de cliente ya existe");
            }
        } else {
            client = this.clientRepository.findById(id).orElse(null);
        }

        client.setName(dto.getName());
        this.clientRepository.save(client);

    }

    /**
     * @ {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.clientRepository.findById(id).orElse(null) == null) {
            throw new Exception("Doesn´t exist");
        }
        this.clientRepository.deleteById(id);
    }
}
