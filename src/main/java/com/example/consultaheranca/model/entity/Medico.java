package com.example.consultaheranca.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

@Entity
public class Medico extends Pessoa implements Serializable {

    @NotBlank
    @Length(min = 9, max = 20)
    private String crm;

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @OneToMany(mappedBy = "medico")
    List<Consulta> consultas;


    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}

