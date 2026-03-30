package com.crudclientes.desafiocrudclientes.services;

import com.crudclientes.desafiocrudclientes.dto.ClienteDTO;
import com.crudclientes.desafiocrudclientes.entities.Cliente;
import com.crudclientes.desafiocrudclientes.repositories.ClienteRepository;

import com.crudclientes.desafiocrudclientes.services.exceptions.DataBaseException;
import com.crudclientes.desafiocrudclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente nao encontrado. Id: " + id));
        return new ClienteDTO(cliente);


    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);
        return page.map(ClienteDTO::new);
    }

    @Transactional
    public ClienteDTO insert(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        copyDtoToEntity(dto, cliente);
        clienteRepository.save(cliente);
        return new ClienteDTO((cliente));
    }

    @Transactional
    public void delete(Long id) {
        if(!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso nao encontrado. Id: " + id);
        }
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Falha de integridade referencial.");
        }
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto) {
        try{
            Cliente cliente = clienteRepository.getReferenceById(id);
            copyDtoToEntity(dto, cliente);
            cliente = clienteRepository.save(cliente);
            return new ClienteDTO(cliente);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Cliente nao encontrado. Id: " + id);
        }
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente cliente) {
        cliente.setName(dto.getName());
        cliente.setCpf(dto.getCpf());
        cliente.setIncome(dto.getIncome());
        cliente.setBirthDate(dto.getBirthDate());
        cliente.setChildren(dto.getChildren());
    }
}
