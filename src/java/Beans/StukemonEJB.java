/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entities.Pokemon;
import Entities.Trainer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Y3895917F
 */
@Stateless
public class StukemonEJB {

    @PersistenceUnit
    EntityManagerFactory emf;

    public boolean insertTrainer(Trainer t) {
        if (!existsTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
            em.close();
            return true;
        }
        return false;
    }

    public boolean existsTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer trainerEncontrado = em.find(Trainer.class, t.getName());
        em.close();
        return trainerEncontrado != null;
    }
    
    public boolean insertPoke(Pokemon p) {
        if (!existsPoke(p)) {
            EntityManager em = emf.createEntityManager();
            em.persist(p);
            em.close();
            return true;
        }
        return false;
    }
    
    public boolean existsPoke(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        Pokemon pokeEncontrado = em.find(Pokemon.class, p.getName());
        em.close();
        return pokeEncontrado != null;
    }
    
    public List<Trainer> selectAllTrainers() {
        return emf.createEntityManager().createNamedQuery("Trainer.findAll").getResultList();
    }
    
    public Trainer findTrainer(String name) {
        return (Trainer) emf.createEntityManager().createNamedQuery("Trainer.findByName").setParameter("name", name).getSingleResult();
    }
    
}
