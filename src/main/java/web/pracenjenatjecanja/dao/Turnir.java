package web.pracenjenatjecanja.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Turnir {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @OneToMany
    private Set<Igrac> igraci = new HashSet<>();

    @OneToMany
    private Set<Kolo> igre = new HashSet<>();

    @NonNull
    private String naziv;

    private String poveznica;

    @NonNull
    private Double pobjeda;

    @NonNull
    private Double remi;

    @NonNull
    private Double poraz;

    @NonNull
    String email;

    public Turnir() {
    }

    public Turnir(@NonNull String naziv, @NonNull Double pobjeda, @NonNull Double remi, @NonNull Double poraz, @NonNull String email) {
        this.naziv = naziv;
        this.pobjeda = pobjeda;
        this.remi = remi;
        this.poraz = poraz;
        this.email = email;
    }
}
