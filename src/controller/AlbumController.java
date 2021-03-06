package controller;

import entity.Albums;
import entity.Artists;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class AlbumController {
    private EntityManagerFactory emf;
    public AlbumController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Albums> findByName(String artistName) {
        EntityManager em = emf.createEntityManager();
        ArtistController artistController = new ArtistController(emf);
        Artists artist = artistController.findByName(artistName);
        long artistId = artist.getId();
        Query query = em.createQuery("select t from Albums  t where t.artist_id=:artistId");
        List<Albums> albums = query.setParameter("artistId", artistId).getResultList();
        em.close();
        return albums;
    }
}