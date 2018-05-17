package app;

import controller.AbstractController;
import controller.AlbumController;
import controller.ArtistController;
import controller.ChartsController;
import entity.Albums;
import entity.Artists;
import entity.Charts;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class AlbumManager {
    static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("MusicAlbumsPU");

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Input command:");
            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0]) {
                case "create-artist":
                    createArtist(params[1],params[2]); //the artist name
                    break;
                case "create-album":
                    createAlbum(params[1], params[2],params[3]); //the album name and the artist name
                    break;
                case "find-albums":
                    findAlbums(params[1]); //the artist name
                    break;
                case "ranking":
                    ranking();
                    break;
                case "insert-chart":
                    insertChart(params[1],params[2],params[3]); //albumId, number of concerts, year
                    break;
                case "list-artists":
                    listArtists();
                    break;
                default:
                    System.out.println("Comanda gresita");
                    break;
            }
        }
    }
    private void createArtist(String artistName, String artistCountry) {
        Artists artist = new Artists();
        artist.setCountry(artistCountry);
        artist.setName(artistName);
        AbstractController<Artists,Long> controller = new AbstractController<Artists,Long>(Artists.class,emf);
        controller.create(artist);

    }
    private void createAlbum(String albumName, String artistName, String releaseYear) {
        Albums album = new Albums();
        ArtistController artistController = new ArtistController(emf);
        Artists artist = artistController.findByName(artistName);
        long artistId = artist.getId();
        album.setArtist_id(artistId);
        album.setName(albumName);
        album.setReleaseYear(Long.parseLong(releaseYear));
        AbstractController<Albums,Long> controller = new AbstractController<Albums,Long>(Albums.class,emf);
        controller.create(album);
    }
    private void findAlbums(String artistName) {
		AlbumController albumController = new AlbumController(emf);
        List<Albums> albums = albumController.findByName(artistName);
        for(Albums album : albums)
            System.out.println(album.getId()+" "+album.getName()+" "+album.getReleaseYear());
    }

    private void ranking()
    {
        ChartsController chartsController = new ChartsController(emf);
        List<Artists> ranking =chartsController.ranking();
        Integer position = 1;
        for(Artists artist : ranking)
            System.out.println(Integer.valueOf(position++)+" "+artist.getName());
    }
    private void insertChart(String albumId, String numberOfConcerts, String year) {
        Charts chart = new Charts();
        chart.setAlbumId(Long.parseLong(albumId));
        chart.setConcerts(Long.parseLong(numberOfConcerts));
        chart.setYear(Long.parseLong(year));
        AbstractController<Charts,Long> controller = new AbstractController<Charts,Long>(Charts.class,emf);
        controller.create(chart);

    }

    private  void listArtists()
    {
        AbstractController<Artists,Long> controller= new AbstractController<Artists,Long>(Artists.class,emf);
        List<Artists> artists=controller.findAll();
        for(Artists artist : artists)
            System.out.println(artist.getId()+"  "+artist.getName()+"  "+artist.getCountry());
    }

    public static void main(String args[]) {

        new AlbumManager().run();
    }
}