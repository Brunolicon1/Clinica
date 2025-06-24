package com.example.consultaheranca.model.repository;
import com.example.consultaheranca.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepository {


    @PersistenceContext
    private EntityManager em;

    public List<Paciente> pacientes() {
        Query query = em.createQuery("from Paciente "); //HQL
        return query.getResultList();
    }

    public List<Paciente> searchAction(String termo) {
        String hql = "SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE :termo";
        return em.createQuery(hql, Paciente.class)
                .setParameter("termo", "%" + termo.toLowerCase() + "%")
                .getResultList();
    }


    public void save(Paciente paciente) {
        em.persist(paciente);
    }

    public Paciente paciente(Long id) {
        return em.find(Paciente.class, id);
    }

    public void remove(Long id) {
        Paciente p = em.find(Paciente.class, id);
        em.remove(p);
    }

    public void update(Paciente paciente) {
        em.merge(paciente);
    }
}
