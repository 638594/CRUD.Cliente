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

    @Transactional
    public ClienteDTO insert(ClienteDTO dto){
        Cliente cliente = new Cliente();
        copyDtoToEntity(dto, cliente);
        clienteRepository.save(cliente);
        return new ClienteDTO((cliente));
    }

    @Transactional
    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    @Transactional
    public ClienteDTO update(Long id, ClienteDTO dto){
        Cliente cliente = clienteRepository.getReferenceById(id);
        copyDtoToEntity(dto,cliente);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente cliente){
        cliente.setName(dto.getName());
        cliente.setCpf(dto.getCpf());
        cliente.setIncome(dto.getIncome());
        cliente.setBirthDate(dto.getBirthDate());
        cliente.setChildren(dto.getChildren());
    }
}
