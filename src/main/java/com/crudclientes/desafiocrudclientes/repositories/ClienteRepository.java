package com.crudclientes.desafiocrudclientes.repositories;

import com.crudclientes.desafiocrudclientes.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
