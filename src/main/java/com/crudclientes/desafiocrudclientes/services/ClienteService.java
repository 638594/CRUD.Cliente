package com.crudclientes.desafiocrudclientes.services;

import com.crudclientes.desafiocrudclientes.dto.ClienteDTO;
import com.crudclientes.desafiocrudclientes.entities.Cliente;
import com.crudclientes.desafiocrudclientes.repositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id){
        Cliente cliente =clienteRepository.findById(id).orElseThrow();
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> findAll(Pageable pageable){
        Page<Cliente> page = clienteRepository.findAll(pageable);
        return page.map(ClienteDTO::new);
    }
}
