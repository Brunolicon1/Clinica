package com.example.consultaheranca.model.repository;
import com.example.consultaheranca.model.entity.Medico;
import com.example.consultaheranca.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Medico> medicos() {
        Query query = em.createQuery("from Medico "); //HQL
        return query.getResultList();
    }
    public List<Medico> searchAction(String termo) {
        String hql = "SELECT p FROM Medico p WHERE LOWER(p.nome) LIKE :termo";
        return em.createQuery(hql, Medico.class)
                .setParameter("termo", "%" + termo.toLowerCase() + "%")
                .getResultList();
    }

    public void save(Medico medico) {
        em.persist(medico);
    }

    public Medico medico(Long id) {
        return em.find(Medico.class, id);
    }

    public void remove(Long id) {
        Medico m = em.find(Medico.class, id);
        em.remove(m);
    }

    public void update(Medico medico) {
        em.merge(medico);
    }
}

