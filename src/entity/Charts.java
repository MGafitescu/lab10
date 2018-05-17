package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Charts {
    private long id;
    private Long concerts;
    private Long year;
    private Long albumId;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="charts_seq", schema="student", sequenceName="artist_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "charts" +
            "_seq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CONCERTS")
    public Long getConcerts() {
        return concerts;
    }

    public void setConcerts(Long concerts) {
        this.concerts = concerts;
    }

    @Basic
    @Column(name = "ALBUM_ID")
    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long concerts) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "YEAR")
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Charts charts = (Charts) o;
        return id == charts.id &&
                Objects.equals(concerts, charts.concerts) &&
                Objects.equals(year, charts.year);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, concerts, year);
    }
}
