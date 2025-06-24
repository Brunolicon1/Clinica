package com.example.consultaheranca.model.repository;
import com.example.consultaheranca.model.entity.Consulta;
import com.example.consultaheranca.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsultaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Consulta> consultas() {
        Query query = em.createQuery("from Consulta "); //HQL
        return query.getResultList();
    }
    public List<Consulta> searchAction(String termo) {
        String hql;
        TypedQuery<Consulta> query;

        // Tenta buscar por data completa (dd-MM-yyyy)
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate data = LocalDate.parse(termo, formatter);

            hql = "SELECT c FROM Consulta c WHERE c.data = :data";
            return em.createQuery(hql, Consulta.class)
                    .setParameter("data", data)
                    .getResultList();

        } catch (DateTimeParseException e) {
            // Não é uma data completa
        }

        // Tenta buscar por ano (ex: 2025)
        try {
            int ano = Integer.parseInt(termo);
            hql = "SELECT c FROM Consulta c WHERE FUNCTION('YEAR', c.data) = :ano";
            return em.createQuery(hql, Consulta.class)
                    .setParameter("ano", ano)
                    .getResultList();

        } catch (NumberFormatException e) {
            // Não é um número, continua para busca textual
        }

        // Busca por pedaços da data (como '05-2025') e por outros campos de texto
        hql = "SELECT c FROM Consulta c WHERE " +
                "CAST(c.data AS string) LIKE :termo " +
                "OR LOWER(c.observacao) LIKE LOWER(:termo) " +
                "OR LOWER(c.paciente.nome) LIKE LOWER(:termo) " +
                "OR LOWER(c.medico.nome) LIKE LOWER(:termo)";

        query = em.createQuery(hql, Consulta.class);
        query.setParameter("termo", "%" + termo + "%");

        return query.getResultList();
    }




    public void save(Consulta consulta) {
        em.persist(consulta);
    }

    public Consulta consulta(Long id) {
        return em.find(Consulta.class, id);
    }

    public void remove(Long id) {
        Consulta c = em.find(Consulta.class, id);
        em.remove(c);
    }

    public void update(Consulta consulta) {
        em.merge(consulta);
    }
}