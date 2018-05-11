package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Artists {
    private long id;
    private String name;
    private String country;

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="artist_seq", schema="student", sequenceName="artist_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_seq")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;
        return id == artists.id &&
                Objects.equals(name, artists.name) &&
                Objects.equals(country, artists.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, country);
    }
}
