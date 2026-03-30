package com.crudclientes.desafiocrudclientes.dto;

import com.crudclientes.desafiocrudclientes.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

@JsonPropertyOrder({"id","name","cpf","income","birthDate","children"})
public class ClienteDTO {

    private Long id;
    @NotBlank(message = "Campo Necessario")
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "Data invalida")
    private LocalDate birthDate;
    private Integer children;

    public ClienteDTO(){

    }

    public ClienteDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        name = cliente.getName();
        cpf = cliente.getCpf();
        income = cliente.getIncome();
        birthDate = cliente.getBirthDate();
        children = cliente.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
