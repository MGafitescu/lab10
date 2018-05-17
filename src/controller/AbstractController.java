package controller;

import entity.Artists;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AbstractController<T,ID> {
    private EntityManagerFactory emf;
    private final Class<T> clazz;
    public AbstractController(Class<T> clazz,EntityManagerFactory emf) {
        this.clazz = clazz;
        this.emf = emf;
    }
    public void create(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public T findByID(ID id) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(clazz,id);
        return  entity;
    }

    public List<T> findAll()
    {
        EntityManager em = emf.createEntityManager();
        String name = clazz.getName();
        Query query = em.createQuery("select t from "+name +" t");
        List<T> entities = query.getResultList();
        em.close();
        return entities;
    }

}
