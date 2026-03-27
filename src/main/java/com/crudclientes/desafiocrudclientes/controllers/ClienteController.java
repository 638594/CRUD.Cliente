package com.crudclientes.desafiocrudclientes.controllers;

import com.crudclientes.desafiocrudclientes.dto.ClienteDTO;
import com.crudclientes.desafiocrudclientes.entities.Cliente;
import com.crudclientes.desafiocrudclientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        ClienteDTO dto = clienteService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> findAll(Pageable pageable){
        Page<ClienteDTO> dto = clienteService.findAll(pageable);
        return ResponseEntity.ok(dto);
    }
}
