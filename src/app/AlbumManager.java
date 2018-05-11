package app;

import controller.AlbumController;
import controller.ArtistController;
import entity.Albums;
import entity.Artists;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
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
                case "list-albums":
                    listAlbums(params[1]); //the artist name
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
        ArtistController controller = new ArtistController(emf);
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
        AlbumController controller = new AlbumController(emf);
        controller.create(album);
    }
    private void listAlbums(String artistName) {
		AlbumController albumController = new AlbumController(emf);
        List<Albums> albums = albumController.findByName(artistName);
        for(Albums album : albums)
            System.out.println(album.getId()+" "+album.getName()+" "+album.getReleaseYear());
    }
    public static void main(String args[]) {
        new AlbumManager().run();
    }
}