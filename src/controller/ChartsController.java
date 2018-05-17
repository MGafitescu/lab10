package controller;

import entity.Artists;
import entity.Charts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ChartsController {
    private EntityManagerFactory emf;
    public ChartsController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Artists> ranking() {
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select a from Charts  c join Albums b on b.id = c.albumId join Artists a on a.id=b.artist_id group by a.id,a.country,a.name order by sum(c.concerts)");
        List<Artists> ranks = query.getResultList();
        em.close();
        return ranks;
    }
}