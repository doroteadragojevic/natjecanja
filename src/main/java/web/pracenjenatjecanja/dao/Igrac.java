package web.pracenjenatjecanja.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class Igrac {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String naziv;

    private Double bodovi = 0.;

    public Igrac() {
    }

    public Igrac(@NonNull String igrac) {
        this.naziv = igrac;
    }
}
